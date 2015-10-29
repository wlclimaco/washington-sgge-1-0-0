package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.UniMed;

public class UniMedResponse extends InquiryResponse
{

	/** Attributes */
	private List<UniMed> unimedList;

	/**
	 * The Constructor.
	 */
	public UniMedResponse()
	{

	}

	/**
	 * @return the unimedList
	 */
	public List<UniMed> getUnimedList()
	{
		return unimedList;
	}

	/**
	 * @param unimedList the unimedList to set
	 */
	public void setUnimedList(List<UniMed> unimedList)
	{
		this.unimedList = unimedList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setUnimedList((List<UniMed>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UniMedResponse [getUnimedList()=" + getUnimedList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}