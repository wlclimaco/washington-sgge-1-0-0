package com.qat.samples.sysmgmt.condominio.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.condominio.model.Sindico;

public class SindicoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Sindico> SindicoList;

	/**
	 * The Constructor.
	 */
	public SindicoResponse()
	{

	}

	/**
	 * @return the SindicoList
	 */
	public List<Sindico> getSindicoList()
	{
		return SindicoList;
	}

	/**
	 * @param SindicoList the SindicoList to set
	 */
	public void setSindicoList(List<Sindico> SindicoList)
	{
		this.SindicoList = SindicoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setSindicoList((List<Sindico>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getSindicoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}