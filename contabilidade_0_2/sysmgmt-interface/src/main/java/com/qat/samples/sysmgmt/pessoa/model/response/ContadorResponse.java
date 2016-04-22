package com.qat.samples.sysmgmt.pessoa.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.pessoa.model.Contador;

public class ContadorResponse extends InquiryResponse
{

	/** Attributes */
	private List<Contador> contadorList;

	/**
	 * The Constructor.
	 */
	public ContadorResponse()
	{

	}

	/**
	 * @return the contadorList
	 */
	public List<Contador> getContadorList()
	{
		return contadorList;
	}

	/**
	 * @param contadorList the contadorList to set
	 */
	public void setContadorList(List<Contador> contadorList)
	{
		this.contadorList = contadorList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setContadorList((List<Contador>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getContadorList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}