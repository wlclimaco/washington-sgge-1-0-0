package com.sensus.dm.common.group.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.common.group.model.Group;

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

	/** The file name. */
	private String fileName;

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
	 * @param groupList the new groups
	 */
	public void setGroups(List<Group> groupList)
	{
		groups = groupList;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
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
		return "InquiryGroupResponse [groups=" + groups + ", fileName=" + fileName + ", getGroups()=" + getGroups()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
