package com.qat.samples.sysmgmt.advocacia.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.advocacia.ProcessoCliente;

public class ProcessoClientesResponse extends InquiryResponse
{

	/** Attributes */
	private List<ProcessoCliente> ProcessoClienteList;

	/**
	 * The Constructor.
	 */
	public ProcessoClientesResponse()
	{

	}

	/**
	 * @return the ProcessoClienteList
	 */
	public List<ProcessoCliente> getProcessoClienteList()
	{
		return ProcessoClienteList;
	}

	/**
	 * @param ProcessoClienteList the ProcessoClienteList to set
	 */
	public void setProcessoClienteList(List<ProcessoCliente> ProcessoClienteList)
	{
		this.ProcessoClienteList = ProcessoClienteList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setProcessoClienteList((List<ProcessoCliente>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getProcessoClienteList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}