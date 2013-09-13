package com.sensus.dm.controller.systemsettings;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.property.model.response.PropertyResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.model.UserSettings;
import com.sensus.dm.controller.util.PreferencesSessionUtil;
import com.sensus.dm.elec.settings.bcf.ISettingsBCF;

/**
 * The Class SettingsAPIController.
 */
@Controller
@RequestMapping("/api/settings")
public class SettingsAPIController extends BaseController
{

	/** The Constant SIX. */
	private static final int SIX = 6;

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SettingsAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "SettingsAPIController";

	/** The Constant UPSERT. */
	private static final String UPSERT = "/upsert";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant SYSTEM. */
	private static final String SYSTEM = "system";

	/** The Constant USER. */
	private static final String USER = "user";

	/** The Constant TYPE_SETTINGS. */
	private static final String TYPE_SETTINGS = "typeSettings";

	/** The settings BCF. */
	private ISettingsBCF settingsBCF;

	/**
	 * Gets the settings BCF.
	 * 
	 * @return the settingsBCF
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the settings BCF.
	 * 
	 * @param settingsBCF the settingsBCF to set
	 */
	@Resource
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Fetch User or System settings.
	 * 
	 * @param requestMap the request map
	 * @return the response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody Map<String, String> requestMap)
	{
		PropertyResponse response = new PropertyResponse();
		PropertyRequest request = new PropertyRequest();

		try
		{
			// ADD user context
			addUserContextToRequest(request);

			String typeSettings = requestMap.get(TYPE_SETTINGS);

			// System Settings
			if (SYSTEM.equals(typeSettings))
			{

				return getSettingsBCF().fetchSystemSettings(request);
			}

			// User Settings
			if (USER.equals(typeSettings))
			{

				return getSettingsBCF().fetchUserSettings(request);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Update/insert.
	 * 
	 * @param propertyRequest the property request
	 * @param servletRequest the servlet request
	 * @return the response
	 */
	@RequestMapping(value = UPSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response upsert(@RequestBody PropertyRequest propertyRequest, HttpServletRequest servletRequest)
	{
		PropertyResponse response = new PropertyResponse();

		UserSettings userSettings = getUserSettings();

		try
		{
			// ADD user context
			addUserContextToRequest(propertyRequest);

			if (!ValidationUtil.isNullOrEmpty(propertyRequest.getProperties())
					&& ((propertyRequest.getProperties().size() >= SIX) || (propertyRequest.getProperties().size() == ONE)))
			{
				// User Profile Page
				response = getSettingsBCF().upsertUserSettings(propertyRequest);

				if (response.isOperationSuccess())
				{
					// Update User Session
					PreferencesSessionUtil.setSession(propertyRequest.getProperties(), servletRequest,
							userSettings.getServiceType(), userSettings.getTenant(),
							userSettings.getRoles(), false);
				}
			}
			else
			{
				// System Settings Page
				response = getSettingsBCF().upsertSystemSettings(propertyRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}
}
