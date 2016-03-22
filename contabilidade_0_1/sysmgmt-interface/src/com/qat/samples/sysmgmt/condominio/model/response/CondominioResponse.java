package com.qat.samples.sysmgmt.condominio.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.condominio.model.Condominio;

public class CondominioResponse extends InquiryResponse
{

	/** Attributes */
	private List<Condominio> CondominioList;

	/**
	 * The Constructor.
	 */
	public CondominioResponse()
	{

	}

	/**
	 * @return the CondominioList
	 */
	public List<Condominio> getCondominioList()
	{
		return CondominioList;
	}

	/**
	 * @param CondominioList the CondominioList to set
	 */
	public void setCondominioList(List<Condominio> CondominioList)
	{
		this.CondominioList = CondominioList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCondominioList((List<Condominio>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getCondominioList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}