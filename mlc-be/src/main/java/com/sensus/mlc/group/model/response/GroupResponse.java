package com.sensus.mlc.group.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.group.model.Group;

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

	/**
	 * Gets the groups.
	 * 
	 * @return the groups
	 */
	public List<Group> getGroups()
	{
		return this.groups;
	}

	/**
	 * Sets the groups.
	 * 
	 * @param groupObjects the new groups
	 */
	public void setGroups(List<Group> groupObjects)
	{
		this.groups = groupObjects;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "GroupResponse [getGroups()=" + this.getGroups() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}
