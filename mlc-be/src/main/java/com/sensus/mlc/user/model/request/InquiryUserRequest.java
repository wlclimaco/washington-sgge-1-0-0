package com.sensus.mlc.user.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.user.model.User;

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
		return this.user;
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
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryUserRequest [user=" + this.user + ", getUser()=" + getUser() + ", getPaginationAllSelected()="
				+ getPaginationAllSelected() + ", getSelectionPaginationIds()=" + getSelectionPaginationIds()
				+ ", getStartRow()=" + getStartRow() + ", getEndRow()=" + getEndRow() + ", getPageSize()="
				+ getPageSize() + ", getStartPage()=" + getStartPage() + ", getSortExpressions()="
				+ getSortExpressions() + ", getSortExpression()=" + getSortExpression() + ", getTenant()="
				+ getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}

}
