package com.sensus.mlc.user.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.user.model.User;

/**
 * The Class UserRequest.
 */
public class UserRequest extends LightSelectionRequest
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
	 * Instantiates a new user request.
	 * 
	 * @param userContext the user context
	 */
	public UserRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new user request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public UserRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
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

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	@Override
	public String toString()
	{
		return "UserRequest [getUser()=" + getUser() + ", getActualPassword()=" + getActualPassword()
				+ ", getNewPassword()=" + getNewPassword() + "]";
	}

}
