package com.sensus.mlc.wui.base.interceptor;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.I18nInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * The Class I18nCustomInterceptor.
 */
@SuppressWarnings("serial")
public class I18nCustomInterceptor implements Interceptor
{

	/** The Constant log. */
	protected static final Log LOG = LogFactory.getLog(I18nInterceptor.class);

	/** The Constant DEFAULT_SESSION_ATTRIBUTE. */
	public static final String DEFAULT_SESSION_ATTRIBUTE = "WW_TRANS_I18N_LOCALE";

	/** The Constant DEFAULT_PARAMETER. */
	public static final String DEFAULT_PARAMETER = "request_locale";

	/** The parameter name. */
	private String parameterName = DEFAULT_PARAMETER;

	/** The attribute name. */
	private String attributeName = DEFAULT_SESSION_ATTRIBUTE;

	/**
	 * Instantiates a new i18n custom interceptor.
	 */
	public I18nCustomInterceptor()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("new I18nInterceptor()");
		}
	}

	/**
	 * Sets the parameter name.
	 * 
	 * @param parameterName the new parameter name
	 */
	public void setParameterName(String parameterName)
	{
		this.parameterName = parameterName;
	}

	/**
	 * Sets the attribute name.
	 * 
	 * @param attributeName the new attribute name
	 */
	public void setAttributeName(String attributeName)
	{
		this.attributeName = attributeName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	public void init()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("init()");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	public void destroy()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("destroy()");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	public String intercept(ActionInvocation invocation) throws Exception
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("intercept '"
					+ invocation.getProxy().getNamespace() + "/"
					+ invocation.getProxy().getActionName() + "' { ");
		}

		// get requested locale
		Object requestedLocale = invocation.getInvocationContext().getParameters().get(parameterName);
		if ((requestedLocale != null) && requestedLocale.getClass().isArray()
				&& (((Object[])requestedLocale).length == 1))
		{
			requestedLocale = ((Object[])requestedLocale)[0];
		}
		if (LOG.isDebugEnabled())
		{
			LOG.debug("requested_locale=" + requestedLocale);
		}
		// save it in session
		if (requestedLocale != null)
		{
			Locale locale = null;
			if (requestedLocale instanceof Locale)
			{
				locale = (Locale)requestedLocale;
			}
			else
			{
				locale = localeFromString(requestedLocale.toString());
			}
			if (LOG.isDebugEnabled())
			{
				LOG.debug("store locale=" + locale);
			}
			if (locale != null)
			{
				invocation.getInvocationContext().getSession().put(attributeName, locale);
			}
		}
		// set locale for action
		Object locale = null;
		if (invocation.getInvocationContext().getSession() != null)
		{
			locale = invocation.getInvocationContext().getSession().get(attributeName);
		}
		if ((locale != null) && (locale instanceof Locale))
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("apply locale=" + locale);
			}
			invocation.getInvocationContext().setLocale((Locale)locale);
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("before Locale=" + ((ActionSupport)invocation.getAction()).getLocale());
		}
		final String result = invocation.invoke();
		if (LOG.isDebugEnabled())
		{
			LOG.debug("after Locale=" + ((ActionSupport)invocation.getAction()).getLocale());
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("intercept } ");
		}
		return result;
	}

	/**
	 * Locale from string.
	 * 
	 * @param localeStr the locale str
	 * @return the locale
	 */
	Locale localeFromString(String localeStr)
	{
		if ((localeStr == null) || (localeStr.trim().length() == 0) || (localeStr.equals("_")))
		{
			return Locale.getDefault();
		}
		int index = localeStr.indexOf('_');
		if (index < 0)
		{
			return new Locale(localeStr);
		}
		String language = localeStr.substring(0, index);
		if (index == localeStr.length())
		{
			return new Locale(language);
		}
		localeStr = localeStr.substring(index + 1);
		index = localeStr.indexOf('_');
		if (index < 0)
		{
			return new Locale(language, localeStr);
		}
		String country = localeStr.substring(0, index);
		if (index == localeStr.length())
		{
			return new Locale(language, country);
		}
		localeStr = localeStr.substring(index + 1);
		return new Locale(language, country, localeStr);
	}

}