package com.sensus.dm.elec.settings.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InquiryResponse;

/**
 * The Class InquiryUserResponse.
 * 
 * @author - Igor Henrique - QAT Brazil
 */
public class InquiryUserResponse extends InquiryResponse
{

	/** The users. */
	@XmlElement(nillable = true)
	private List<UserContext> users;

	/**
	 * Gets the users.
	 * 
	 * @return the users
	 */
	public List<UserContext> getUsers()
	{
		return users;
	}

	/**
	 * Sets the users.
	 * 
	 * @param userList the new users
	 */
	public void setUsers(List<UserContext> userList)
	{
		users = userList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryUserResponse [getUsers()=" + getUsers() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
