package com.qat.samples.sysmgmt.security.model;

import java.util.Map;

/**
 * The Class TokenModel.
 */
public class TokenModel
{

	/** The auth token. */
	private final String token;

	/** The user name. */
	private final String name;

	/** The roles. */
	private final Map<String, Boolean> roles;

	/**
	 * Instantiates a new token model.
	 *
	 * @param token the token
	 */
	public TokenModel(String token, String userName, Map<String, Boolean> roles)
	{
		this.token = token;
		name = userName;
		this.roles = roles;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public Map<String, Boolean> getRoles()
	{
		return roles;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TokenModel [getToken()=" + getToken() + ", getName()=" + getName() + ", getRoles()=" + getRoles() + "]";
	}

}