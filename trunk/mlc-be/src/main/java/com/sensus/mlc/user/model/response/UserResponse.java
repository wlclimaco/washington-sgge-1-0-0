package com.sensus.mlc.user.model.response;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.user.model.User;

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
		return this.user;
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
		return "UserResponse [getUsers()=" + this.getUser() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}
