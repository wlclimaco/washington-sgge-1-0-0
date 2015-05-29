package com.prosperitasglobal.sendsolv.user.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.prosperitasglobal.sendsolv.user.model.User;
import com.qat.framework.model.response.InquiryResponse;

public class InquiryUserResponse extends InquiryResponse
{

	/** The users. */
	@XmlElement(nillable = true)
	private List<User> users;

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers()
	{
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param userList the new users
	 */
	public void setUsers(List<User> userList)
	{
		users = userList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setUsers(new ArrayList<User>(coll));
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
