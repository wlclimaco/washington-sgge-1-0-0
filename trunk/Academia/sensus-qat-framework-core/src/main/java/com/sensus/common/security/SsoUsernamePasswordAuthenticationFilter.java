package com.sensus.common.security;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is used to replace the default FORM_LOGIN_FILTER so that single sign-on can
 * be integrated. The methods triggered by the Spring security filter chain are attemptAuthentication
 * and requiresAuthentication.
 * <p>
 * The Spring security configuration file should follow the example below to include this class in the filter chain.
 * <p>
 * To replace the default login filter, remove any form-login element within the http element and add a custom-filter:
 *
 * <pre>
 * <code>
 * &lt;custom-filter position="FORM_LOGIN_FILTER" ref="authFilter" /&gt;
 * </code>
 * </pre>
 *
 * The authFilter reference is to a bean defined as follows:
 *
 * <pre>
 * <code>
 * &lt;bean id="authFilter" class="com.sensus.security.authentication.SsoUsernamePasswordAuthenticationFilter"&gt;
 *  &lt;property name="authenticationManager" ref="authenticationManager"/&gt;
 *  &lt;property name="authenticationFailureHandler" ref="failureHandler"/&gt;
 *  &lt;property name="authenticationSuccesseHandler" ref="successHandler"/&gt;
 *  &lt;property name="authenticationServiceAdapter" ref="authenticationServiceAdapter"/&gt;
 *  &lt;property name="ssoSessionIdName" value="org.josso.agent.ssoSessionId"/&gt;
 *  &lt;property name="ssoCookieName" value="JOSSO_SESSIONID"/&gt;
 *  &lt;property name="ssoSecurityDomain" value="josso"/&gt;
 * &lt;/bean&gt;
 * </code>
 * </pre>
 *
 * The AuthenticationManager to be used is the default. So, the reference to the authenticationManager would be the
 * default in the Spring security name space. See the JavaDoc on the {@link AuthenticationProvider} for the reference of the
 * authentication-manager to the authentication-provider.
 *
 * <pre>
 * <code>
 * &lt;authentication-manager alias="authenticationManager" erase-credentials="false"&gt;
 *  &lt;authentication-provider ref="authenticationProvider" /&gt;
 * &lt;/authentication-manager&gt;
 * </code>
 * </pre>
 *
 * The successHandler and failureHandler are the default ones from Spring. The successHandler defaultTargetUrl should
 * direct to the web page after successful login. The defaultFailureUrl should direct to an unauthenticated page.
 *
 * <pre>
 * <code>
 * &lt;bean id="successHandler" class="org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler"&gt;
 *  &lt;property name="alwaysUseDefaultTargetUrl" value="true" /&gt;
 *  &lt;property name="defaultTargetUrl" value="/pages/wd_main.jsp" /&gt;
 * &lt;/bean&gt;
 *
 * &lt;bean id="failureHandler" class="org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler" &gt;
 *  &lt;defaultFailureUrl="/index.jsp?Login failed!" /&gt;
 * &lt;/bean&gt;
 * </code>
 * </pre>
 *
 * The authenticationAdapter should refer to an adapter class which implements {@link IAuthenticationServiceAdapter}:
 * <pre>
 * <code>
 * &lt;bean id="authenticationServiceAdapter"
 * 	class="com.sensus.security.authentication.JOSSOAuthenticationAdapter" &gt;
 *  &lt;property name="serviceLocator" ref="jossoGatewayServiceLocator" /&gt;>
 * &lt;/bean&gt;
 * </code>
 * </pre>
 * In turn the adapter refers to the service locator:
 * <pre>
 * <code>
 * &lt;bean id="jossoGatewayServiceLocator" class="org.josso.gateway.WebserviceGatewayServiceLocator" &gt;
 *  &lt;property name="endpoint" value="localhost:8880" /&gt;
 *  &lt;property name="transportSecurity" value="none" /&gt;
 * &lt;/bean&gt;
 * </code>
 * </pre>
 */
public class SsoUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter
{
	private static final Logger LOG = LoggerFactory.getLogger(SsoUsernamePasswordAuthenticationFilter.class);

	private IAuthenticationServiceAdapter authenticationServiceAdapter;
	private String ssoSessionIdName;
	private String ssoCookieName;
	private String securityDomain;
	private boolean ssoAuthenticationRequired = true;


	public IAuthenticationServiceAdapter getAuthenticationServiceAdapter()
	{
		return authenticationServiceAdapter;
	}

	public void setAuthenticationServiceAdapter(
			IAuthenticationServiceAdapter authenticationServiceAdapter)
	{
		this.authenticationServiceAdapter = authenticationServiceAdapter;
	}

	public String getSsoSessionIdName()
	{
		return ssoSessionIdName;
	}

	public void setSsoSessionIdName(String ssoSessionIdName)
	{
		this.ssoSessionIdName = ssoSessionIdName;
	}

	public boolean isSsoAuthenticationRequired()
	{
		return ssoAuthenticationRequired;
	}

	public void setSsoAuthenticationRequired(boolean ssoAuthenticationRequired)
	{
		this.ssoAuthenticationRequired = ssoAuthenticationRequired;
	}

	public String getSsoCookieName()
	{
		return ssoCookieName;
	}

	public void setSsoCookieName(String ssoCookieName)
	{
		this.ssoCookieName = ssoCookieName;
	}

	public String getSecurityDomain()
	{
		return securityDomain;
	}

	public void setSecurityDomain(String securityDomain)
	{
		this.securityDomain = securityDomain;
	}

	public SsoUsernamePasswordAuthenticationFilter()
	{
		super();

		if (LOG.isDebugEnabled())
		{
			LOG.debug("default constructor");
		}
	}

	public SsoUsernamePasswordAuthenticationFilter(String filterProcessesUrl)
	{
		setFilterProcessesUrl(filterProcessesUrl);

		if (LOG.isDebugEnabled())
		{
			LOG.debug("String constructor argument: [" + filterProcessesUrl + "]");
		}
	}

	/**
	 * This setDetails uses the {@link WebAuthenticationDetailsWithSso} for the details of the
	 * authRequest
	 *
	 * @param request - {@link HttpServletRequest} object containing JOSSO session ID
	 * @param authRequest - {@link UsernamePasswordAuthenticationToken} the user details will be added to.
	 */
	@Override
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Before setDetails(" + toStringRequest(request) + ", " + authRequest + ")");
		}

		WebAuthenticationDetailsWithSso authDetails =
				new WebAuthenticationDetailsWithSso(request, getSsoSessionIdName());
		authRequest.setDetails(authDetails);

		if (LOG.isDebugEnabled())
		{
			LOG.debug("After setDetails(" + toStringRequest(request) + ", " + authRequest + ")");
		}

	}

	/**
	 * This method will create a {@link UsernamePasswordAuthenticationToken} from the SSO session ID found
	 * in the request if one exists. Otherwise, it calls its ancestor to attemptAuthentication.
	 *
	 * @param request - {@link HttpServletRequest} that may contain a SSO session ID.
	 * @param response - not used in this method.
	 * @return {@link Authentication} object
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException
	{
		Authentication auth = null;

		String ssoSessionId = (String)request.getAttribute(getSsoSessionIdName());
		if ((ssoSessionId != null) && (ssoSessionId.trim().length() > 0))
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("SSO Session ID [" + ssoSessionId
						+ "] being used to retrieve authentication information.");
			}

			UsernamePasswordAuthenticationToken authToken = getAuthenticationServiceAdapter().createAuthentication(ssoSessionId);

			if (LOG.isDebugEnabled())
			{
				LOG.debug("UsernamePasswordAuthenticationToken created from SSO Session ID: [" + authToken
						+ "]");
			}

			setDetails(request, authToken);

			auth = getAuthenticationManager().authenticate(authToken);

		}
		else
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("No SSO Session ID is in the HttpServletRequest. Will let parent attemptAuthentication.");
			}

			auth = super.attemptAuthentication(request, response);

			if ((auth != null) && auth.isAuthenticated())
			{

				WebAuthenticationDetailsWithSso details = (WebAuthenticationDetailsWithSso)auth.getDetails();

				addSsoSessionCookies(request, response, details.getSsoSessionId());
			}

		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("attemptAuthentication(" + toStringRequest(request) + ", " + response + ") returned [" + auth
					+ "]");
		}
		return auth;
	}

	/**
	 *
	 *
	 * @param request - {@link HttpServletRequest} which may contain a SSO session ID.
	 * @param response - not used here.
	 * @return boolean true if there is no {@link Authentication} object in the security context and there is
	 *         a SSO session ID in the HttpServletRequest. Otherwise, returns the ancestor method result.
	 */
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Before requiresAuthentication(" + toStringRequest(request) + ", " + response + ")");
		}
		boolean requiresAuthentication = false;

		SecurityContext secContext = SecurityContextHolder.getContext();
		if (secContext == null)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("SecurityContext is null");
			}
		}
		else
		{
			Authentication auth = secContext.getAuthentication();
			String ssoSessionId = (String)request.getAttribute(getSsoSessionIdName());

			if (auth == null)
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Authentication is null");
				}

				if ((ssoSessionId != null) && (ssoSessionId.trim().length() > 0))
				{
					requiresAuthentication = true;
				}
			}
			else
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Authentication: [" + auth + "]");
				}
				if (isSsoAuthenticationRequired() && ((ssoSessionId == null) || (ssoSessionId.trim().length() == 0)))
				{
					if (LOG.isDebugEnabled())
					{
						LOG.debug("There is no " + getSsoSessionIdName()
								+ " in the request.  Setting Authentication to null in SecurityContext.");
					}
					secContext.setAuthentication(null);
				}
			}
		}

		if (!requiresAuthentication)
		{
			requiresAuthentication = super.requiresAuthentication(request, response);
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("After requiresAuthentication(" + toStringRequest(request) + ", " + response + ") returns ["
					+ requiresAuthentication
					+ "]");
		}

		return requiresAuthentication;
	}

	private void addSsoSessionCookies(HttpServletRequest request, HttpServletResponse response, String ssoSessionId)
	{
		String domainName = extractDomainName(request.getServerName());

		Cookie cookie = new Cookie(getSsoCookieName() + "_" + getSecurityDomain(), ssoSessionId);
		cookie.setMaxAge(-1);
		cookie.setPath("/" + getSecurityDomain());
		cookie.setDomain(domainName);
		response.addCookie(cookie);

		cookie = new Cookie(getSsoCookieName(), ssoSessionId);
		cookie.setMaxAge(-1);
		cookie.setPath(request.getContextPath());
		cookie.setDomain(domainName);
		response.addCookie(cookie);

		if (LOG.isDebugEnabled())
		{
			LOG.debug("SSO Session ID [" + ssoSessionId
					+ "] cookie added to Response");
		}
	}

	private String extractDomainName(String hostName)
	{
		String domainName = hostName;

		int ndx = hostName.indexOf('.');

		if (ndx < 0)
		{
			return domainName;
		}

		if (isIPAddress(hostName))
		{
			return domainName;
		}

		return domainName.substring(ndx + 1);
	}

	private boolean isIPAddress(String argString)
	{
		String[] strArray = argString.split("\\.");

		for (String aString : strArray)
		{
			try
			{
				Integer.parseInt(aString);
			}
			catch (NumberFormatException ex)
			{
				return false;
			}
		}

		return true;
	}

	private String toStringRequest(ServletRequest argRequest)
	{
		StringBuilder strBuilder = new StringBuilder(argRequest.toString());

		if (argRequest instanceof HttpServletRequest)
		{
			HttpServletRequest httpRequest = (HttpServletRequest)argRequest;

			strBuilder.append("]: Context Path: [");
			strBuilder.append(httpRequest.getContextPath());
			strBuilder.append("]: Request URI: [");
			strBuilder.append(httpRequest.getRequestURI());
			strBuilder.append("]: Request URL: [");
			strBuilder.append(httpRequest.getRequestURL());
			strBuilder.append(']');
		}
		strBuilder.append(": Attribute Names:");

		Enumeration<String> enumerationStrings = argRequest.getAttributeNames();

		while (enumerationStrings.hasMoreElements())
		{
			strBuilder.append(' ');
			strBuilder.append(enumerationStrings.nextElement());
		}

		strBuilder.append(" : Parameter Names:");

		enumerationStrings = argRequest.getParameterNames();

		while (enumerationStrings.hasMoreElements())
		{
			strBuilder.append(' ');
			strBuilder.append(enumerationStrings.nextElement());
		}

		return strBuilder.toString();
	}

	@Override
	public void afterPropertiesSet()
	{
		super.afterPropertiesSet();

		if (getAuthenticationServiceAdapter() == null)
		{
			throw new NullPointerException(getClass().getName() + ": authenticationServiceAdapter property is null!");
		}

		if (getSsoSessionIdName() == null)
		{
			throw new NullPointerException(getClass().getName() + ": ssoSessionIdName property is null!");
		}
	}

}
