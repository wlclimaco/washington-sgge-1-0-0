package com.qat.samples.sysmgmt.clinica.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;

public class PacienteResponse extends InquiryResponse
{

	/** Attributes */
	private List<Paciente> pacienteList;

	/**
	 * The Constructor.
	 */
	public PacienteResponse()
	{

	}

	/**
	 * @return the pacienteList
	 */
	public List<Paciente> getPacienteList()
	{
		return pacienteList;
	}

	/**
	 * @param pacienteList the pacienteList to set
	 */
	public void setPacienteList(List<Paciente> pacienteList)
	{
		this.pacienteList = pacienteList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPacienteList((List<Paciente>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getPacienteList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}