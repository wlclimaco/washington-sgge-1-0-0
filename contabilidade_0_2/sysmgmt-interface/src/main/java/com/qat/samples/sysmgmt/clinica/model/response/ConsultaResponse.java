package com.qat.samples.sysmgmt.clinica.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.clinica.model.Consulta;

public class ConsultaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Consulta> consultaList;

	/**
	 * The Constructor.
	 */
	public ConsultaResponse()
	{

	}

	/**
	 * @return the consultaList
	 */
	public List<Consulta> getConsultaList()
	{
		return consultaList;
	}

	/**
	 * @param consultaList the consultaList to set
	 */
	public void setConsultaList(List<Consulta> consultaList)
	{
		this.consultaList = consultaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConsultaList((List<Consulta>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getConsultaList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}