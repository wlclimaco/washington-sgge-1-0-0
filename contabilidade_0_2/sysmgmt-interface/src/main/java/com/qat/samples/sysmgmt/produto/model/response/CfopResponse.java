package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.cfop.model.Cfop;

public class CfopResponse extends InquiryResponse
{

	/** Attributes */
	private List<Cfop> cfopList;

	/**
	 * The Constructor.
	 */
	public CfopResponse()
	{

	}

	/**
	 * @return the cfopList
	 */
	public List<Cfop> getCfopList()
	{
		return cfopList;
	}

	/**
	 * @param cfopList the cfopList to set
	 */
	public void setCfopList(List<Cfop> cfopList)
	{
		this.cfopList = cfopList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCfopList((List<Cfop>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CfopResponse [getCfopList()=" + getCfopList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}