package com.qat.samples.sysmgmt.clinica.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.pessoa.model.Medico;

public class MedicoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Medico> medicoList;

	/**
	 * The Constructor.
	 */
	public MedicoResponse()
	{

	}

	/**
	 * @return the medicoList
	 */
	public List<Medico> getMedicoList()
	{
		return medicoList;
	}

	/**
	 * @param medicoList the medicoList to set
	 */
	public void setMedicoList(List<Medico> medicoList)
	{
		this.medicoList = medicoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setMedicoList((List<Medico>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getMedicoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}