package com.qat.samples.sysmgmt.condominio.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;

public class InquilinoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Inquilino> InquilinoList;

	/**
	 * The Constructor.
	 */
	public InquilinoResponse()
	{

	}

	/**
	 * @return the InquilinoList
	 */
	public List<Inquilino> getInquilinoList()
	{
		return InquilinoList;
	}

	/**
	 * @param InquilinoList the InquilinoList to set
	 */
	public void setInquilinoList(List<Inquilino> InquilinoList)
	{
		this.InquilinoList = InquilinoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setInquilinoList((List<Inquilino>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getInquilinoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}