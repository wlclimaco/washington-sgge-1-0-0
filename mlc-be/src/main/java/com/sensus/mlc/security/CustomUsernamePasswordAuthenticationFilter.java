/*
 *
 */
package com.sensus.mlc.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

/**
 * The Class CustomUsernamePasswordAuthenticationFilter.
 *
 * author: Gustavo_Aragao@qat.com
 *
 */
public class CustomUsernamePasswordAuthenticationFilter extends
UsernamePasswordAuthenticationFilter
{

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response)
	{
		if (!request.getMethod().equals("POST"))
		{
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null)
		{
			username = "";
		}

		if (password == null)
		{
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// Place the last username attempted into HttpSession for views
		HttpSession session = request.getSession(false);

		if ((session != null) || getAllowSessionCreation())
		{
			request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY,
					TextEscapeUtils.escapeEntities(username));
		}

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		CustomAuthenticationManager authenticationManager = (CustomAuthenticationManager)getAuthenticationManager();
		// Necessary pass the request to the Authentication Manager in order to get the hostName and store the
		// UserContext at the session;
		authenticationManager.setServletRequest(request);
		return authenticationManager.authenticate(authRequest);
	}

}
