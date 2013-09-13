package com.sensus.common.security;

import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * This filter is used to detect a session timeout so that it can invoke logout processing.
 * The detection is done by checking the request URL for a specific pattern. If the pattern
 * matches, it returns true to indicate to {@link LogoutFilter} that logout processing should occur.
 * It also adds a timeout attribute to the session so that other filters in the chain can
 * detect the state of the session.
 *
 */
public class TimeoutLogoutFilter extends LogoutFilter
{
	public static final String TIMEOUT = "timeout";

	private static final String TIMEOUT_MARKER = "TIMEOUT_MARKER";

	private static final Logger LOG = LoggerFactory.getLogger(TimeoutLogoutFilter.class);

	private String timeOutLogoutUri;
	private String parameterName;

	public TimeoutLogoutFilter(LogoutSuccessHandler logoutSuccessHandler, LogoutHandler... handlers)
	{
		super(logoutSuccessHandler, handlers);
	}

	public TimeoutLogoutFilter(String logoutSuccessUrl, LogoutHandler... handlers)
	{
		super(logoutSuccessUrl, handlers);
	}

	/**
	 * Return a true to indicate logout required if a specifc URL pattern is matched.
	 * This method first calls up to {@link LogoutFilter} for it to check for a logout
	 * pattern. If LogoutFilter does not require a logout, this method will check
	 * the request URL to match the pattern injected via timeOutLogoutUri property.
	 * It also checks for a parameter match to be as specific as possible.
	 * <p>
	 * If timeout is detected a timeout attribute is added to the session.
	 *
	 * @param request - {@link HttpServletRequest} containing the current request that will be searched for the pattern.
	 * @param response - {@link HttpServletResponse} not used in this method.
	 * @return boolean indicating true if logout is required.
	 */
	@Override
	protected boolean requiresLogout(HttpServletRequest request, HttpServletResponse response)
	{
		boolean requiresLogout = super.requiresLogout(request, response);

		String requestUri = null;
		String parameterName = null;

		if (!requiresLogout)
		{
			requestUri = request.getRequestURI();

			if (requestUri.endsWith(getTimeOutLogoutUri()))
			{
				Enumeration<String> parameters = request.getParameterNames();

				// If no parameterName property was injected, a match on parameter
				// occurs only if there are no parameters in the request.
				if (getParameterName() == null)
				{
					if (!parameters.hasMoreElements())
					{
						requiresLogout = true;
					}
				}
				else
				{
					while (parameters.hasMoreElements())
					{
						parameterName = parameters.nextElement();
						if (parameterName.equals(getParameterName()))
						{
							requiresLogout = true;
							break;
						}
					}
				}
			}

			// if requiresLogout is set in the logic above, this means a timeout has occurred.
			// Set the session attribute so that after logout occurs, other filters
			// in the chain will be able to detect it.
			if (requiresLogout)
			{
				request.getSession().setAttribute(TIMEOUT, TIMEOUT_MARKER);
			}

			if (LOG.isDebugEnabled())
			{
				LOG.debug("RequiresLogout = " + requiresLogout + " for URI " + requestUri + " and parameter "
						+ parameterName);
				LOG.debug("Session timeout attribute is [" + request.getSession().getAttribute(TIMEOUT) + "]");
			}

		}

		return requiresLogout;
	}

	@Override
	public void afterPropertiesSet() throws ServletException
	{
		super.afterPropertiesSet();

		if (getTimeOutLogoutUri() == null)
		{
			throw new ServletException(getClass().getName()
					+ ".afterPropertiesSet: timeOutLogoutUri property required.");
		}
	}

	public String getTimeOutLogoutUri()
	{
		return timeOutLogoutUri;
	}

	public void setTimeOutLogoutUri(String timeOutLogoutUri)
	{
		this.timeOutLogoutUri = timeOutLogoutUri;
	}

	public String getParameterName()
	{
		return parameterName;
	}

	public void setParameterName(String argParameterName)
	{
		if (argParameterName != null)
		{
			if (argParameterName.trim().length() == 0)
			{
				argParameterName = null;
			}
		}

		parameterName = argParameterName;
	}
}
