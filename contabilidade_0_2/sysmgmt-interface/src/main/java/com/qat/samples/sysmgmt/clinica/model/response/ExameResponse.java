package com.qat.samples.sysmgmt.clinica.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.clinica.model.Exame;

public class ExameResponse extends InquiryResponse
{

	/** Attributes */
	private List<Exame> exameList;

	/**
	 * The Constructor.
	 */
	public ExameResponse()
	{

	}

	/**
	 * @return the exameList
	 */
	public List<Exame> getExameList()
	{
		return exameList;
	}

	/**
	 * @param exameList the exameList to set
	 */
	public void setExameList(List<Exame> exameList)
	{
		this.exameList = exameList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setExameList((List<Exame>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getExameList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}