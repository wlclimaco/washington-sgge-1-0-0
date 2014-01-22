package com.sensus.lc.user.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.user.model.User;

/**
 * The Class InquiryTagRequest.
 * 
 * @author - Igor Mendes - QAT Brazil
 */
public class InquiryUserRequest extends InquiryPaginationRequest
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

	/**
	 * Instantiates a new inquiry user request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryUserRequest(UserContext userContext)
	{
		super(userContext);
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
