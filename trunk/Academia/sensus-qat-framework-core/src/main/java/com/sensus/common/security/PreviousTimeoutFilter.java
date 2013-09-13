package com.sensus.common.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

/**
 * This filter is used to detect the presence of a timeout attribute in the session and
 * interrupt the filter chain if that is the case. A redirect URL will be placed in the
 * Response object as well.
 * <p>
 * This filter should be placed in the chain just before the LOGOUT_FILTER position and used in conjunction with the
 * {@link TimeoutLogoutFilter}. Following are the custom filter settings which should be set within the http element:
 *
 * <pre>
 * <code>
 * &lt;sec:custom-filter before="LOGOUT_FILTER" ref="timeoutCheckFilter" /&gt;
 * &lt;sec:custom-filter position="LOGOUT_FILTER" ref="logoutFilter" /&gt;
 * </code>
 * </pre>
 *
 * Following are needed to satisfy the references:
 *
 * <pre>
 * <code>
 * &lt;bean id="logoutFilter" class="com.qat.josso.TimeoutLogoutFilter"&gt;
 *  &lt;constructor-arg value="/josso_logout/" /&gt;
 *  &lt;constructor-arg&gt;
 *   &lt;list&gt;
 *    &lt;bean class="org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler"&gt;
 *     &lt;property name="invalidateHttpSession" value="false" /&gt;
 *    &lt;/bean&gt;
 *   &lt;/list&gt;
 *  &lt;/constructor-arg&gt;
 *  &lt;property name="timeOutLogoutUri" value="/timeout.jsp" /&gt;
 * &lt;/bean&gt;
 *
 * &lt;bean id="timeoutCheckFilter" class="com.qat.josso.PreviousTimeoutFilter"
 * 	&lt;property name="timeOutRedirectUrl" value="/index.jsp?Session timed out." /&gt;
 * &lt;/bean&gt;
 * </code>
 * </pre>
 *
 *
 */
public class PreviousTimeoutFilter extends GenericFilterBean
{
	private static final Logger LOG = LoggerFactory.getLogger(PreviousTimeoutFilter.class);

	private String timeOutRedirectUrl;

	public String getTimeOutRedirectUrl()
	{
		return timeOutRedirectUrl;
	}

	public void setTimeOutRedirectUrl(String timeOutRedirectUrl)
	{
		this.timeOutRedirectUrl = timeOutRedirectUrl;
	}

	/**
	 * If a timeout did occur, the session should have a timeout attribute added to it.
	 * The state of the session should be that the timeout has occurred and the user has been
	 * logged out. This method will check for the timeout marker attribute to be in the session.
	 * If it is, a URL will be constructed and passed to the sendRedirect method of the HttpResponse.
	 * The timeout attribute will be removed from the session. This method will then return, causing
	 * the filter chain to be interrupted.
	 *
	 * @param req - {@link ServletRequest} containing an {@link HttpServletRequest} with the HttpSession.
	 * @param res - {@link ServletResponse} containing an {@link HttpServletResponse} where the redirect URL will be
	 *            placed.
	 * @param chain - {@link FilterChain} which will be continued if no timeout attribute is found in the session.
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException
	{
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;

		Object timeoutMarker = request.getSession().getAttribute(TimeoutLogoutFilter.TIMEOUT);

		if (timeoutMarker != null)
		{
			String redirectUrl = createRedirectUrl(request);

			if (LOG.isDebugEnabled())
			{
				LOG.debug(timeoutMarker + " found in session.  Will discontinue the filter chain and redirect to ["
						+ redirectUrl + "]");
			}

			response.sendRedirect(redirectUrl);

			request.getSession().removeAttribute(TimeoutLogoutFilter.TIMEOUT);

			return;
		}

		chain.doFilter(req, res);
	}

	@Override
	public void afterPropertiesSet() throws ServletException
	{
		super.afterPropertiesSet();

		Assert.isTrue(!StringUtils.hasLength(getTimeOutRedirectUrl()) ||
				UrlUtils.isValidRedirectUrl(getTimeOutRedirectUrl()), getTimeOutRedirectUrl()
				+ " isn't a valid redirect URL");

	}

	private String createRedirectUrl(HttpServletRequest request)
	{
		if (getTimeOutRedirectUrl() == null)
		{
			return null;
		}

		StringBuffer requestURLBuffer = request.getRequestURL();
		String contextPath = request.getContextPath();

		if (contextPath == null)
		{
			contextPath = "/";
		}

		int ndxOfContextInURL = -1;

		if (contextPath.length() < 2)
		{
			ndxOfContextInURL = requestURLBuffer.lastIndexOf(contextPath);
		}
		else
		{
			ndxOfContextInURL = requestURLBuffer.indexOf(contextPath);
		}

		StringBuilder strBuilder =
				new StringBuilder(requestURLBuffer.substring(0, ndxOfContextInURL + contextPath.length()));

		strBuilder.append(getTimeOutRedirectUrl());

		return strBuilder.toString();
	}
}
