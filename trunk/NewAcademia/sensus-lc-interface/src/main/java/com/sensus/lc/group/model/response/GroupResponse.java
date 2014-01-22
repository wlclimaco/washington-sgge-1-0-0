package com.sensus.lc.group.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.group.model.Group;

/**
 * The Class GroupResponse.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class GroupResponse extends InquiryResponse
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

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setGroups(new ArrayList<Group>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "GroupResponse [getGroups()=" + getGroups() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}
