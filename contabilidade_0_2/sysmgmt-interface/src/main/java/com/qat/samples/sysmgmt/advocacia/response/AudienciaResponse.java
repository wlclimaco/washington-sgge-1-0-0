package com.qat.samples.sysmgmt.advocacia.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.advocacia.Audiencia;

public class AudienciaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Audiencia> AudienciaList;

	/**
	 * The Constructor.
	 */
	public AudienciaResponse()
	{

	}

	/**
	 * @return the AudienciaList
	 */
	public List<Audiencia> getAudienciaList()
	{
		return AudienciaList;
	}

	/**
	 * @param AudienciaList the AudienciaList to set
	 */
	public void setAudienciaList(List<Audiencia> AudienciaList)
	{
		this.AudienciaList = AudienciaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setAudienciaList((List<Audiencia>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getAudienciaList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}