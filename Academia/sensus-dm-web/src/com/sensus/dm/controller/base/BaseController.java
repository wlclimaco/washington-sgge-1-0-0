package com.sensus.dm.controller.base;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.controller.model.UserSettings;

/**
 * The Class BaseController.
 */
public abstract class BaseController implements MessageSourceAware
{
	/** The message source. */
	private MessageSource messageSource;

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sensus.epm.base.dmapicontroller.defaultexception";

	/** The Constant USER_LOCAL_SETTINGS. */
	private static final String USER_SETTINGS = "userSettings";

	/**
	 * Gets the message source.
	 * 
	 * @return the messageSource
	 */
	public MessageSource getMessageSource()
	{
		return messageSource;
	}

	/**
	 * Sets the message source.
	 * 
	 * @param messageSource the messageSource to set
	 */
	@Override
	@Resource
	public void setMessageSource(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

	/**
	 * Enable context.
	 * 
	 * @param request the request
	 */
	public void addUserContextToRequest(TenantRequest request)
	{
		User user = fillUserDetails();

		if (!ValidationUtil.isNull(user))
		{
			UserContext uc = new UserContext(user.getUsername());

			UserSettings userSettings = getUserSettings();

			if (!ValidationUtil.isNull(userSettings))
			{
				uc.setLocaleString(userSettings.getBaseLocale());

				if (!ValidationUtil.isNull(userSettings.getServiceType()))
				{
					request.setServiceTypeEnum(ServiceTypeEnum.valueOf(userSettings.getServiceType()));
				}

				request.setTenant(new DMTenant(userSettings.getTenant()));
			}

			request.setUserContext(uc);
		}
	}

	/**
	 * Gets the user settings.
	 * 
	 * @return the user settings
	 */
	public UserSettings getUserSettings()
	{
		return (UserSettings)RequestContextHolder.currentRequestAttributes().getAttribute(
				USER_SETTINGS, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * Gets the iterator authorities.
	 * 
	 * @return the iterator authorities
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Iterator getIteratorAuthorities()
	{
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
				SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		return authorities.iterator();
	}

	/**
	 * Gets the service type.
	 * 
	 * @return the service type
	 */
	public String getServiceType()
	{
		return getUserSettings().getServiceType();
	}

	/**
	 * Gets the text.
	 * 
	 * @param key the key
	 * @param locale the locale
	 * @return the text
	 */
	protected String getText(String key, Locale locale)
	{
		return getMessageSource().getMessage(key, null, locale);
	}

	/**
	 * Fill user details.
	 * 
	 * @return the user details
	 */
	public User fillUserDetails()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userInfo = null;

		Object obj = auth.getPrincipal();

		if (obj instanceof User)
		{
			userInfo = (User)obj;
		}

		return userInfo;
	}
}