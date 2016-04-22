package com.qat.samples.sysmgmt.agencia.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.agencia.model.Agencia;

public class AgenciaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Agencia> agenciaList;

	/**
	 * The Constructor.
	 */
	public AgenciaResponse()
	{

	}

	/**
	 * @return the agenciaList
	 */
	public List<Agencia> getAgenciaList()
	{
		return agenciaList;
	}

	/**
	 * @param agenciaList the agenciaList to set
	 */
	public void setAgenciaList(List<Agencia> agenciaList)
	{
		this.agenciaList = agenciaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setAgenciaList((List<Agencia>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getAgenciaList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}