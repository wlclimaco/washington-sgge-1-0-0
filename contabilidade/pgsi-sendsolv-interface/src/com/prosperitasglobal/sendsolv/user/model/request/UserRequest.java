package com.prosperitasglobal.sendsolv.user.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.prosperitasglobal.sendsolv.user.model.User;
import com.qat.framework.model.request.Request;

/**
 * The Class UserRequest.
 */
public class UserRequest extends Request
{
	/** The user. */
	@XmlElement(nillable = true)
	private User user;

	/** The actual password. */
	private String actualPassword;

	/** The new password. */
	private String newPassword;

	/**
	 * Instantiates a new user request.
	 */
	public UserRequest()
	{
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * Gets the actual password.
	 *
	 * @return the actual password
	 */
	public String getActualPassword()
	{
		return actualPassword;
	}

	/**
	 * Sets the actual password.
	 *
	 * @param actualPassword the new actual password
	 */
	public void setActualPassword(String actualPassword)
	{
		this.actualPassword = actualPassword;
	}

	/**
	 * Gets the new password.
	 *
	 * @return the new password
	 */
	public String getNewPassword()
	{
		return newPassword;
	}

	/**
	 * Sets the new password.
	 *
	 * @param newPassword the new new password
	 */
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UserRequest [getUser()=" + getUser() + ", getActualPassword()=" + getActualPassword()
				+ ", getNewPassword()=" + getNewPassword() + ", toString()=" + super.toString() + "]";
	}

}
