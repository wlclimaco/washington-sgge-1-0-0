package com.qat.samples.sysmgmt.clinica.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.clinica.model.Clinica;

public class ClinicaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Clinica> clinicaList;

	/**
	 * The Constructor.
	 */
	public ClinicaResponse()
	{

	}

	/**
	 * @return the clinicaList
	 */
	public List<Clinica> getClinicaList()
	{
		return clinicaList;
	}

	/**
	 * @param clinicaList the clinicaList to set
	 */
	public void setClinicaList(List<Clinica> clinicaList)
	{
		this.clinicaList = clinicaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setClinicaList((List<Clinica>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getClinicaList()=" + getClinicaList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}