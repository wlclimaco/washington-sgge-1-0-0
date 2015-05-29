package com.prosperitasglobal.sendsolv.user.model.response;

import com.prosperitasglobal.sendsolv.user.model.User;
import com.qat.framework.model.response.Response;

/**
 * The Class UserResponse.
 */
public class UserResponse extends Response
{

	/** The users. */
	private User user;

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * Sets the users.
	 *
	 * @param user the new users
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UserResponse [getUser()=" + getUser() + ", toString()=" + super.toString() + "]";
	}

}
