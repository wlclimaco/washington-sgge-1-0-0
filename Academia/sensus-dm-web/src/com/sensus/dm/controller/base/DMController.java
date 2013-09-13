package com.sensus.dm.controller.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.property.model.response.PropertyResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.controller.model.UserSettings;
import com.sensus.dm.controller.util.PreferencesSessionUtil;
import com.sensus.dm.controller.util.ResultUtil;
import com.sensus.dm.elec.settings.bcf.ISettingsBCF;

/**
 * The Class DMController.
 */
@Controller
public class DMController extends BaseController
{

	/** The Constant SERVICE_TYPE. */
	private static final String SERVICE_TYPE = "serviceType";

	/** The Constant SET_SERVICE. */
	private static final String SET_SERVICE = "/setService";

	/** The Constant BAR. */
	private static final String BAR = "/";

	/** The Constant SERVICE. */
	private static final String SERVICE = "/service";

	/** The logger for this class. */
	private static final Log LOG = LogFactory.getLog(DMController.class);

	/** The Constant ACCESS_DENIED_JSP. */
	private static final String ACCESS_DENIED_JSP = "redirect:/access-denied.jsp?";

	/** The Constant CREATE_USER_PROFILE. */
	private static final String CREATE_USER_PROFILE = "######### CREATE USER PROFILE ####";

	/** The Constant CUSTOMER. */
	private static final String CUSTOMER = "CUSTOMER_";

	/** The Constant DM_MAIN. */
	private static final String DM_MAIN = "dm_main";

	/** The Constant EPM. */
	private static final String EPM = "EPM_";

	/** The Constant HEADER. */
	private static final String WRAPPER = "wrapper";

	/** The Constant INVALID_TENTANT. */
	private static final String INVALID_TENTANT = "invalidTenant";

	/** The Constant MESSAGE_LOGIN_ERROR. */
	private static final String MESSAGE_LOGIN_ERROR = "login.redirect.login.error";

	/** The Constant MESSAGE_TENANT_ERROR. */
	private static final String MESSAGE_TENANT_ERROR = "login.redirect.invalid.tenant";

	/** The Constant ACCESS_DENIED_SERVICE. */
	private static final String ACCESS_DENIED_SERVICE = "access.denied.service";

	/** The Constant ROLE_EPM_SERVICE. */
	private static final String ROLE_EPM_SERVICE = "ROLE_EPM_SERVICE_";

	/** Settings BCF. */
	private ISettingsBCF settingsBCF;

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settings bcf
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the settings bcf.
	 * 
	 * @param settingsBCFParam the new settings bcf
	 */
	@Resource
	public void setSettingsBCF(ISettingsBCF settingsBCFParam)
	{
		settingsBCF = settingsBCFParam;
	}

	/**
	 * Checks for permission to service.
	 * 
	 * @param service the service
	 * @param roles the roles
	 * @return the boolean
	 */
	private Boolean hasPermissionToService(String service, List<String> roles)
	{
		// If contains the service at roles list returns true, otherwise return false
		return roles.contains(ROLE_EPM_SERVICE + service);
	}

	/**
	 * Sets the service.
	 * 
	 * @param serviceType the service type
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = SET_SERVICE, method = RequestMethod.GET)
	public String setService(@RequestParam(value = "service", required = true) ServiceTypeEnum serviceType,
			HttpServletRequest request)
	{
		// Get roles and tenant
		List<String> roles = new ArrayList<String>();
		String tenant = fillRolesAndTenant(roles);

		// Check if user has permission to service type selected
		if (hasPermissionToService(serviceType.toString(), roles))
		{
			PreferencesSessionUtil.setSession(null, request, serviceType.toString(), tenant, roles, true);

			// Returns the wrapper for application content
			return WRAPPER;
		}

		return ACCESS_DENIED_JSP + getText(ACCESS_DENIED_SERVICE, request.getLocale());
	}

	/**
	 * Main.
	 * 
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = {BAR, SERVICE}, method = RequestMethod.GET)
	public String main(HttpServletRequest request)
	{
		UserSettings userSettings = getUserSettings();

		// If already has the user session
		if (ValidationUtil.isNull(userSettings))
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(CREATE_USER_PROFILE);
			}

			List<String> roles = new ArrayList<String>();

			// Get Tenant
			String tenant = fillRolesAndTenant(roles);

			// Verify if exist the tenant and if the a valid tenant.
			if (ValidationUtil.isNullOrEmpty(tenant) || INVALID_TENTANT.equals(tenant))
			{
				return ACCESS_DENIED_JSP + getText(MESSAGE_TENANT_ERROR, request.getLocale());
			}

			PropertyRequest propertyRequest = new PropertyRequest();
			propertyRequest.setUserContext(new UserContext(fillUserDetails().getUsername()));
			propertyRequest.setTenant(new DMTenant(tenant));

			// Insert User
			PropertyResponse propertyResponse = getSettingsBCF().insertUser(propertyRequest);

			// Verify if the user was inserted successfully. If not returns to the access_denied JSP.
			if (!propertyResponse.isOperationSuccess())
			{
				return ACCESS_DENIED_JSP + getText(MESSAGE_LOGIN_ERROR, request.getLocale());
			}

			// Set user settings on session
			propertyResponse = getSettingsBCF().fetchUserSettings(propertyRequest);

			if (ResultUtil.setMessages(propertyResponse, null))
			{
				PreferencesSessionUtil.setSession(propertyResponse.getProperties(), request, "", tenant, roles, true);
			}
		}

		request.setAttribute(SERVICE_TYPE, getServiceType());

		return DM_MAIN;
	}

	/**
	 * Fill roles returning tenant.
	 * 
	 * @param listAuthorities the list authorities
	 * @return the string
	 */
	public String fillRolesAndTenant(List<String> listAuthorities)
	{
		String tenant = null;

		Iterator iterator = getIteratorAuthorities();

		while (iterator.hasNext())
		{
			String value = iterator.next().toString();

			/*
			 * Verify if the value contains CUSTOMER text and no contains EPM_ text.
			 * If contains EPM_ text the VALUE it's a user and no a TENANT.
			 * If contains just the CUSTOMER_ text get the Tenant value.
			 */
			if ((value.indexOf(CUSTOMER) != -1) && (value.indexOf(EPM) == -1))
			{
				// Verify if the user contains more than one tenant, if has, returns text informing Invalid tenant
				if (!ValidationUtil.isNullOrEmpty(tenant))
				{
					return INVALID_TENTANT;
				}

				// Split the tenant value. Ie : "CUSTOMER_ACME" gets only the "ACME" value
				tenant = value.substring(value.indexOf(CUSTOMER) + CUSTOMER.length(), value.length());
				continue;
			}

			// Add roles at list authorities
			listAuthorities.add(value);
		}

		return tenant;
	}
}