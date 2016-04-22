package com.qat.samples.sysmgmt.security.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


/**
 * The Class UnauthorizedEntryPoint.
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint
{

	/** The Constant UNAUTHORIZED_MSG. */
	private static final String UNAUTHORIZED_MSG = "Unauthorized: Authentication token was either missing or invalid.";

	/**
	 * Commence.
	 *
	 * @param request the request
	 * @param response the response
	 * @param authException the auth exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException
	{
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED_MSG);
	}

}