package com.prosperitasglobal.sendsolv.settings.model.response;

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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryUserResponse [getUsers()=" + getUsers() + ", toString()=" + super.toString() + "]";
	}

}
