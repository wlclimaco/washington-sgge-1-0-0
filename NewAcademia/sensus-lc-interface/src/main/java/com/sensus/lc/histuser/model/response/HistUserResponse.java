package com.sensus.lc.histuser.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.histuser.model.HistUser;

/**
 * The Class HistUserResponse.
 * 
 * @author - Washington
 */
public class HistUserResponse extends Response
{

	/** The histUsers. */
	@XmlElement(nillable = true)
	private List<HistUser> histUsers;

	/** The is histUser name unique. */
	private Boolean isHistUserNameUnique;

	/** The process id. */
	private Integer processId;

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
	 * @param histUserObjects the new histUsers
	 */
	public void setHistUsers(List<HistUser> histUserObjects)
	{
		histUsers = histUserObjects;
	}

	/**
	 * Gets the checks if is histUser name unique.
	 * 
	 * @return the checks if is histUser name unique
	 */
	public Boolean getIsHistUserNameUnique()
	{
		return isHistUserNameUnique;
	}

	/**
	 * Sets the checks if is histUser name unique.
	 * 
	 * @param isHistUserNameUnique the new checks if is histUser name unique
	 */
	public void setIsHistUserNameUnique(Boolean isHistUserNameUnique)
	{
		this.isHistUserNameUnique = isHistUserNameUnique;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	@Override
	public String toString()
	{
		return "HistUserResponse [getHistUsers()=" + getHistUsers() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsHistUserNameUnique()="
				+ getIsHistUserNameUnique() + "]";
	}

}
