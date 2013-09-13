package com.sensus.common.security.josso;

import java.util.List;

import org.josso.gateway.identity.SSORole;
import org.josso.gateway.identity.SSOUser;
import org.springframework.security.core.GrantedAuthority;

import com.sensus.security.josso.mapper.SSOUserMapper;

/**
 * This extension uses a custom {@link SSOUserMapper} to create {@link GrantedAuthority}s
 * from {@link SSORole}s.
 *
 */
public class JOSSOAuthenticationWithMapperAdapter extends JOSSOAuthenticationAdapter
{
	private SSOUserMapper userMapper;

	public SSOUserMapper getUserMapper()
	{
		return userMapper;
	}

	public void setUserMapper(SSOUserMapper userMapper)
	{
		this.userMapper = userMapper;
	}

	@Override
	protected List<GrantedAuthority> createGrantedAuthoritiesList(SSOUser argUser, SSORole[] argRoles)
	{
		return getUserMapper().toGrantedAuthorities(argUser, argRoles);
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		super.afterPropertiesSet();

		if (getUserMapper() == null)
		{
			throw new NullPointerException(getClass().getName() + ": userMapper property is null!");
		}
	}

}
