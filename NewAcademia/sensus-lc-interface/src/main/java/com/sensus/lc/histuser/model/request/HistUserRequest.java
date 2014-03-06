package com.sensus.lc.histuser.model.request;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.LightSelectionRequest;
import com.sensus.lc.histuser.model.HistUser;

/**
 * The Class HistUserRequest.
 * 
 * @author Washington
 */
public class HistUserRequest extends LightSelectionRequest
{
	/** The group. */
	@XmlElement(nillable = true)
	private HistUser histUser;

	/** The old name. */
	@XmlElement(nillable = true)
	private String oldHistUser;
	/** The histUsers. */
	private List<HistUser> histUsers;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new histUser request.
	 */
	public HistUserRequest()
	{
	}

	/**
	 * Instantiates a new histUser request.
	 * 
	 * @param histUser the histUser
	 */
	public HistUserRequest(HistUser histUser)
	{
		addHistUser(histUser);
	}

	/**
	 * Instantiates a new histUser request.
	 * 
	 * @param userContext the user context
	 */
	public HistUserRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new histUser request.
	 * 
	 * @param histUser the histUser
	 * @param userContext the user context
	 */
	public HistUserRequest(HistUser histUser, UserContext userContext)
	{
		addHistUser(histUser);
		setUserContext(userContext);
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
	 * Gets the first histUser.
	 * 
	 * @return the first histUser
	 */
	public HistUser getFirstHistUser()
	{
		if ((getHistUsers() != null) && !getHistUsers().isEmpty())
		{
			return getHistUsers().get(0);
		}

		return null;
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

	/**
	 * Gets the retrieve history.
	 * 
	 * @return the retrieve history
	 */
	public Boolean getRetrieveHistory()
	{
		return retrieveHistory;
	}

	/**
	 * Sets the retrieve history.
	 * 
	 * @param retrieveHistory the new retrieve history
	 */
	public void setRetrieveHistory(Boolean retrieveHistory)
	{
		this.retrieveHistory = retrieveHistory;
	}

	public HistUser getHistUser()
	{
		return histUser;
	}

	public void setHistUser(HistUser histUser)
	{
		this.histUser = histUser;
	}

	public String getOldHistUser()
	{
		return oldHistUser;
	}

	public void setOldHistUser(String oldHistUser)
	{
		this.oldHistUser = oldHistUser;
	}

	@Override
	public String toString()
	{
		return "HistUserRequest [getHistUsers()=" + getHistUsers() + ", getFirstHistUser()=" + getFirstHistUser()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", getHistUser()=" + getHistUser()
				+ ", getOldHistUser()=" + getOldHistUser() + "]";
	}

}
