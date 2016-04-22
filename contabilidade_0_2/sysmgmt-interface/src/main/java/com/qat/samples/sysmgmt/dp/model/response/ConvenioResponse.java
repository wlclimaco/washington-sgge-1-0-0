package com.qat.samples.sysmgmt.dp.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.convenio.model.Convenio;

public class ConvenioResponse extends InquiryResponse
{

	/** Attributes */
	private List<Convenio> convenioList;

	/**
	 * The Constructor.
	 */
	public ConvenioResponse()
	{

	}

	/**
	 * @return the convenioList
	 */
	public List<Convenio> getConvenioList()
	{
		return convenioList;
	}

	/**
	 * @param convenioList the convenioList to set
	 */
	public void setConvenioList(List<Convenio> convenioList)
	{
		this.convenioList = convenioList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConvenioList((List<Convenio>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ConvenioResponse [getConvenioList()=" + getConvenioList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}