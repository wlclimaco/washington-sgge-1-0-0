package com.prosperitasglobal.sendsolv.user.model.request;

import com.prosperitasglobal.sendsolv.user.model.User;
import com.qat.framework.model.request.InquiryRequest;

public class InquiryUserRequest extends InquiryRequest
{

	/** The user. */
	private User user;

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
	 * Instantiates a new inquiry user request.
	 */
	public InquiryUserRequest()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryUserRequest [getUser()=" + getUser() + ", toString()=" + super.toString() + "]";
	}

}
