/*
 * 
 */
package com.sensus.mlc.wui.security.unittest.mock;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The Class CustomUsernamePasswordAuthenticationFilter.
 * 
 * author: Gustavo_Aragao@qat.com
 * 
 */
public class CustomUsernamePasswordAuthenticationFilterMockImpl extends
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
		List<GrantedAuthorityImpl> grant = new ArrayList<GrantedAuthorityImpl>();
		grant.add(new GrantedAuthorityImpl("ROLE_Role.Admin"));

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken("rod", "koala", grant);

		CustomAuthenticationManagerMockImpl authenticationManager =
				(CustomAuthenticationManagerMockImpl)getAuthenticationManager();
		// Necessary pass the request to the Authentication Manager in order to get the hostName and store the
		// UserContext at the session;
		authenticationManager.setServletRequest(request);
		return authenticationManager.authenticate(authenticationToken);

	}

}
