package com.sensus.common.web.controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.Request;

public abstract class BaseController implements MessageSourceAware
{
	/** The message source. */
	private MessageSource messageSource;

	/**
	 * Enable context.
	 *
	 * @param request the request
	 */
	public static void addUserContextToRequest(Request request)
	{
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (user instanceof User)
		{
			UserContext uc = new UserContext(((User)user).getUsername());

			request.setUserContext(uc);
		}
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
	@Override
	@Resource
	public void setMessageSource(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

}
