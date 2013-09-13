package com.sensus.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * This extension to {@link User} is needed to hold the SSO Session ID.
 * The SSO session ID needs to be passed to objects in the
 * chain of processing who do not have access to the HttpServletRequest being
 * passed down the filter chain.
 *
 */
public class UserWithSsoSessionId extends User
{
	private static final long serialVersionUID = 1;

	private String ssoSessionId;

	public UserWithSsoSessionId(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String argSsoSessionId)
	{
		super(username, password, authorities);
		ssoSessionId = argSsoSessionId;
	}

	public UserWithSsoSessionId(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String argSsoSessionid)
	{
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		ssoSessionId = argSsoSessionid;
	}

	public String getSsoSessionId()
	{
		return ssoSessionId;
	}

	@Override
	public String toString()
	{
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(super.toString() + ", SSOSessionID=[" + ssoSessionId + "]");

		return strBuilder.toString();
	}

}
