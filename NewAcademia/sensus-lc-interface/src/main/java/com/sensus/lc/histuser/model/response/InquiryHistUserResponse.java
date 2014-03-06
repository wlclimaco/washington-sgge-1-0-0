package com.sensus.lc.histuser.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.histuser.model.HistUser;

/**
 * The Class InquiryHistUserResponse.
 * 
 * @author - Washington
 */
public class InquiryHistUserResponse extends InquiryResponse
{

	/** The histUsers. */
	@XmlElement(nillable = true)
	private List<HistUser> histUsers;

	/** The file name. */
	private String fileName;

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
	 * @param histUserList the new histUsers
	 */
	public void setHistUsers(List<HistUser> histUserList)
	{
		histUsers = histUserList;
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
		setHistUsers(new ArrayList<HistUser>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryHistUserResponse [histUsers=" + histUsers + ", fileName=" + fileName + ", getHistUsers()="
				+ getHistUsers()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
