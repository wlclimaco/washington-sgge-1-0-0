package com.sensus.common.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * Used to adapt an SSO provider implementation to a client requiring Spring security
 * authentication using either information from an authentication token or by
 * retrieving information associated with a session ID.
 *
 */
public interface IAuthenticationServiceAdapter
{
	UserDetails retrieveUserDetails(String argUserName,
			UsernamePasswordAuthenticationToken argAuthToken);

	UsernamePasswordAuthenticationToken createAuthentication(String ssoSessionId);

}
