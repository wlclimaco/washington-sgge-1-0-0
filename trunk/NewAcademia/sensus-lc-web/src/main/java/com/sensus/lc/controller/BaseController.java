package com.sensus.lc.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.Request;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.model.UserSettings;

/**
 * The Class BaseController.
 */
public abstract class BaseController implements MessageSourceAware
{

	/** The Constant FIVE. */
	private static final int FIVE = 5;

	/** The Constant THREE. */
	private static final int THREE = 3;

	/** The message source. */
	private MessageSource messageSource;

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.defaultexception";

	/** The Constant USER_LOCAL_SETTINGS. */
	private static final String USER_SETTINGS = "userSettings";

	/**
	 * Sets the user context.
	 * 
	 * @param request the request
	 * @param httpRequest the http request
	 */
	protected void setUserContext(Request request,
			HttpServletRequest httpRequest)
	{

		if (ValidationUtil.isNull(request)
				|| ValidationUtil.isNull(httpRequest))
		{
			return;
		}

		request.setUserContext(getUserContext(httpRequest));
	}

	/**
	 * Gets the user context.
	 * 
	 * @param request the request
	 * @return the user context
	 */
	protected UserContext getUserContext(HttpServletRequest request)
	{
		return (UserContext)request.getSession().getAttribute("userContext");
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
	 * Gets the text.
	 * 
	 * @param key the key
	 * @param sLocale the s locale
	 * @return the text
	 */
	protected String getText(String key, String sLocale)
	{

		Locale locale = new Locale(sLocale.substring(0, 2), sLocale.substring(THREE, FIVE));

		return getMessageSource().getMessage(key, null, locale);

	}

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

}
