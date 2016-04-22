package com.qat.samples.sysmgmt.advocacia.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.advocacia.Processo;

public class ProcessoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Processo> ProcessoList;

	/**
	 * The Constructor.
	 */
	public ProcessoResponse()
	{

	}

	/**
	 * @return the ProcessoList
	 */
	public List<Processo> getProcessoList()
	{
		return ProcessoList;
	}

	/**
	 * @param ProcessoList the ProcessoList to set
	 */
	public void setProcessoList(List<Processo> ProcessoList)
	{
		this.ProcessoList = ProcessoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setProcessoList((List<Processo>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getProcessoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}