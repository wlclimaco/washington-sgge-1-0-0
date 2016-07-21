package com.qat.samples.sysmgmt.security.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

/**
 * The Class AuthenticationTokenProcessingFilter.
 */
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	/** The user service. */
	private final UserDetailsService userService;

	/** The Constant EXPECTING_HTTP_MSG. */
	private static final String EXPECTING_HTTP_MSG = "Expecting an HTTP request";

	/** The Constant XAUTH_HEADER. */
	private static final String XAUTH_HEADER = "X-Auth-Token";

	/** The Constant TOKEN_PARAM. */
	private static final String TOKEN_PARAM = "token";

	/**
	 * Instantiates a new authentication token processing filter.
	 *
	 * @param userService
	 *            the user service
	 */
	public AuthenticationTokenProcessingFilter(UserDetailsService userService) {
		this.userService = userService;
	}

	/**
	 * Do filter.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param chain
	 *            the chain
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = getAsHttpRequest(request);
		String authToken = extractAuthTokenFromRequest(httpRequest);
		String userName = TokenUtils.getUserNameFromToken(authToken);
		HttpServletResponse responses = (HttpServletResponse) response;
		responses.setHeader("Access-Control-Allow-Origin", "*");
		responses.setHeader("Access-Control-Allow-Credentials", "true");
		responses.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
		responses.setHeader("Access-Control-Allow-Headers",
				"Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

				if (userName != null) {

			UserDetails userDetails = userService.loadUserByUsername(userName);

			if (TokenUtils.validateToken(authToken, userDetails)) {

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * Gets the as http request.
	 *
	 * @param request
	 *            the request
	 * @return the as http request
	 */
	private HttpServletRequest getAsHttpRequest(ServletRequest request) {
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException(EXPECTING_HTTP_MSG);
		}

		return (HttpServletRequest) request;
	}

	/**
	 * Extract auth token from request.
	 *
	 * @param httpRequest
	 *            the http request
	 * @return the string
	 */
	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
		String authToken = httpRequest.getHeader(XAUTH_HEADER);

		/* If token not found get it from request parameter */
		if (authToken == null) {
			authToken = httpRequest.getParameter(TOKEN_PARAM);
		}

		return authToken;
	}
}