package com.sensus.common.security;

import javax.servlet.http.HttpServletRequest;

/**
 * This extension to @link WebAuthenticationDetails} is needed to hold the
 * SSO Session ID. The SSO session ID needs to be passed to objects in the
 * chain of processing who do not have access to the HttpServletRequest being
 * passed down the filter chain.
 *
 */
public class WebAuthenticationDetailsWithSso extends
		org.springframework.security.web.authentication.WebAuthenticationDetails
{
	private static final long serialVersionUID = 1;

	private String ssoSessionId;

	public WebAuthenticationDetailsWithSso(HttpServletRequest request, String ssoSessionIdName)
	{
		super(request);

		ssoSessionId = (String)request.getAttribute(ssoSessionIdName);
	}

	public void setSsoSessionId(String argSessionId)
	{
		ssoSessionId = argSessionId;
	}

	public String getSsoSessionId()
	{
		return ssoSessionId;
	}

	@Override
	public String toString()
	{
		StringBuilder strBuilder = new StringBuilder(super.toString());

		strBuilder.append(", SSO Session ID=[" + ssoSessionId + "]");

		return strBuilder.toString();
	}

}
