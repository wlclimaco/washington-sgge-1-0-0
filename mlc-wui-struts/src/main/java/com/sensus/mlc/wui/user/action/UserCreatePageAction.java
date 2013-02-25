package com.sensus.mlc.wui.user.action;

import com.sensus.common.model.Response;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.wui.base.action.LayoutBase;

/**
 * The Class UserCreatePageAction.
 * 
 * @author Alex Tiveron
 */
@SuppressWarnings("serial")
public class UserCreatePageAction extends LayoutBase
{
	/** The user bcf. */
	private IUserBCF userBCF;

	/** The response. */
	private Response response;

	/** The user id. */
	private String userId;

	/** The user. */
	private User user;

	/**
	 * Fill update user.
	 * 
	 * @return the string
	 */
	public String fillUpdateUser()
	{

		return SUCCESS;
	}

	/**
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * @return the userBCF
	 */
	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	/**
	 * @param userBCF the userBCF to set
	 */
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

}
