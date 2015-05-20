package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

public class CsosnResponse extends InquiryResponse
{

	/** Attributes */
	private List<Csosn> csosnList;

	/**
	 * The Constructor.
	 */
	public CsosnResponse()
	{

	}

	/**
	 * @return the csosnList
	 */
	public List<Csosn> getCsosnList()
	{
		return csosnList;
	}

	/**
	 * @param csosnList the csosnList to set
	 */
	public void setCsosnList(List<Csosn> csosnList)
	{
		this.csosnList = csosnList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCsosnList((List<Csosn>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CsosnResponse [getCsosnList()=" + getCsosnList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}