package com.sensus.dm.common.group.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.group.model.Group;

/**
 * The Class GroupResponse.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class GroupResponse extends Response
{

	/** The groups. */
	@XmlElement(nillable = true)
	private List<Group> groups;

	/** The is group name unique. */
	private Boolean isGroupNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the groups.
	 * 
	 * @return the groups
	 */
	public List<Group> getGroups()
	{
		return groups;
	}

	/**
	 * Sets the groups.
	 * 
	 * @param groupObjects the new groups
	 */
	public void setGroups(List<Group> groupObjects)
	{
		groups = groupObjects;
	}

	/**
	 * Gets the checks if is group name unique.
	 * 
	 * @return the checks if is group name unique
	 */
	public Boolean getIsGroupNameUnique()
	{
		return isGroupNameUnique;
	}

	/**
	 * Sets the checks if is group name unique.
	 * 
	 * @param isGroupNameUnique the new checks if is group name unique
	 */
	public void setIsGroupNameUnique(Boolean isGroupNameUnique)
	{
		this.isGroupNameUnique = isGroupNameUnique;
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
		return "GroupResponse [getGroups()=" + getGroups() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsGroupNameUnique()="
				+ getIsGroupNameUnique() + "]";
	}

}
