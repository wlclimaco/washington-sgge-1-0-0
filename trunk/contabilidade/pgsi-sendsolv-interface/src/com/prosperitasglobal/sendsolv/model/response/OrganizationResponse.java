package com.prosperitasglobal.sendsolv.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Organization;
import com.qat.framework.model.response.InquiryResponse;

/**
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:15:57 AM
 */
public class OrganizationResponse extends InquiryResponse
{

	/** Attributes */
	private List<Organization> organizationList;

	/**
	 * The Constructor.
	 */
	public OrganizationResponse()
	{
		organizationList = new ArrayList<Organization>();
	}

	/**
	 * Gets the organization list.
	 *
	 * @return the organization list
	 */
	public List<Organization> getOrganizationList()
	{
		return organizationList;
	}

	/**
	 * Sets the organizations.
	 *
	 * @param organizationList the new organizations
	 */
	public void setOrganizationList(List<Organization> organizationList)
	{
		this.organizationList = organizationList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setOrganizationList((List<Organization>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrganizationResponse [getOrganizationList()=" + getOrganizationList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}