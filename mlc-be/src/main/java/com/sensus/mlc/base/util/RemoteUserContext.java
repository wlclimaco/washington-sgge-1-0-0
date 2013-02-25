package com.sensus.mlc.base.util;

/**
 * Authentication credentials for a remote user. This is the user context
 * for outgoing web service requests.
 * 
 * @author Jatin.Hansoty
 */
public class RemoteUserContext
{

	/** The user name. */
	private String userName;

	/** The password. */
	private String password;

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName the new user name
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password the new password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}
