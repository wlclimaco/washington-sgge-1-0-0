package com.qat.samples.sysmgmt.cnae.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.cnae.model.Cnae;

public class CnaeResponse extends InquiryResponse
{

	/** Attributes */
	private List<Cnae> cnaeList;

	/**
	 * The Constructor.
	 */
	public CnaeResponse()
	{

	}

	/**
	 * @return the cnaeList
	 */
	public List<Cnae> getCnaeList()
	{
		return cnaeList;
	}

	/**
	 * @param cnaeList the cnaeList to set
	 */
	public void setCnaeList(List<Cnae> cnaeList)
	{
		this.cnaeList = cnaeList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCnaeList((List<Cnae>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CnaeResponse [getCnaeList()=" + getCnaeList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}