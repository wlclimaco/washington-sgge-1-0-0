/*
 *
 */
package com.sensus.lc.api.service.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.sensus.lc.security.CustomAuthenticationManager;

/**
 * Custom authentication filter for Rest API.
 */
public class CustomUsernamePasswordAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter
{

	/**
	 * Instantiates a new custom username password authentication filter.
	 */
	public CustomUsernamePasswordAuthenticationFilter()
	{
		super("/");
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#doFilter(javax.servlet
	 * .ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException
	{

		// Put the servlet request into authentication manager.
		// The servlet request is mandatory because the tenant validation is host based also the we need to put the
		// userContext into http session.
		CustomAuthenticationManager am = (CustomAuthenticationManager)this.getAuthenticationManager();
		am.setServletRequest((HttpServletRequest)req);

		chain.doFilter(req, res);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#attemptAuthentication(
	 * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	{
		return null;
	}
}
