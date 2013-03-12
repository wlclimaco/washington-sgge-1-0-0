package com.sensus.mlc.wui;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.Request;
import com.sensus.common.validation.ValidationUtil;

public abstract class BaseController implements MessageSourceAware
{

	/** The message source. */
	private MessageSource messageSource;

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.defaultexception";

	/**
	 * Sets the user context.
	 * 
	 * @param request
	 *            the request
	 * @param httRequest
	 *            the htt request
	 */
	protected void setUserContext(Request request,
			HttpServletRequest httpRequest)
	{

		if (ValidationUtil.isNull(request)
				|| ValidationUtil.isNull(httpRequest))
		{
			return;
		}

		request.setUserContext((UserContext)httpRequest.getSession()
				.getAttribute("userContext"));
	}

	protected String getText(String key, Locale locale)
	{

		return getMessageSource().getMessage(key, null, locale);

	}

	/**
	 * @return the messageSource
	 */
	public MessageSource getMessageSource()
	{
		return messageSource;
	}

	/**
	 * @param messageSource the messageSource to set
	 */
	@Resource
	public void setMessageSource(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

}
