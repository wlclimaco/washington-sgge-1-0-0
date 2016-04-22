package com.qat.samples.sysmgmt.estado.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.estado.model.Estado;

public class EstadoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Estado> EstadoList;

	/**
	 * The Constructor.
	 */
	public EstadoResponse()
	{

	}

	/**
	 * @return the EstadoList
	 */
	public List<Estado> getEstadoList()
	{
		return EstadoList;
	}

	/**
	 * @param EstadoList the EstadoList to set
	 */
	public void setEstadoList(List<Estado> EstadoList)
	{
		this.EstadoList = EstadoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setEstadoList((List<Estado>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EstadoResponse [getEstadoList()=" + getEstadoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}