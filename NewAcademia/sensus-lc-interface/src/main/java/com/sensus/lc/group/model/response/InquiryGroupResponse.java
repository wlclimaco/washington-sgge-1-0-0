package com.sensus.lc.group.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.group.model.Group;

/**
 * The Class InquiryGroupResponse.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class InquiryGroupResponse extends InquiryResponse
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
	 * @param groupList the new groups
	 */
	public void setGroups(List<Group> groupList)
	{
		this.groups = groupList;
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
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
		return "InquiryGroupResponse [getGroups()=" + getGroups() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}
