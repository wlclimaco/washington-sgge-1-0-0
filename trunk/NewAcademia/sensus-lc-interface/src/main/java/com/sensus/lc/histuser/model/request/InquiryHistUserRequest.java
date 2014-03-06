package com.sensus.lc.histuser.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.histuser.model.HistUser;

/**
 * The Class InquiryHistUserRequest.
 * 
 * @author Washington
 */
public class InquiryHistUserRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The histUsers. */
	private List<HistUser> histUsers;

	/**
	 * Instantiates a new inquiry histUser request.
	 */
	public InquiryHistUserRequest()
	{

	}

	/**
	 * Instantiates a new inquiry histUser request.
	 * 
	 * @param histUser the histUser
	 */
	public InquiryHistUserRequest(HistUser histUser)
	{
		addHistUser(histUser);
	}

	/**
	 * Gets the histUsers.
	 * 
	 * @return the histUsers
	 */
	public List<HistUser> getHistUsers()
	{
		return histUsers;
	}

	/**
	 * Sets the histUsers.
	 * 
	 * @param histUsers the new histUsers
	 */
	public void setHistUsers(List<HistUser> histUsers)
	{
		this.histUsers = histUsers;
	}

	/**
	 * Gets the first histUser.
	 * 
	 * @return the first histUser
	 */
	public HistUser getFirstHistUser()
	{
		if ((getHistUsers() != null) && !getHistUsers().isEmpty())
		{
			return getHistUsers().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the histUser.
	 * 
	 * @param histUser the histUser
	 */
	public void addHistUser(HistUser histUser)
	{
		if (getHistUsers() == null)
		{
			setHistUsers(new ArrayList<HistUser>());
		}

		getHistUsers().add(histUser);
	}

	@Override
	public String toString()
	{
		return "InquiryHistUserRequest [getHistUsers()=" + getHistUsers() + ", getFirstHistUser()="
				+ getFirstHistUser() + "]";
	}

}
