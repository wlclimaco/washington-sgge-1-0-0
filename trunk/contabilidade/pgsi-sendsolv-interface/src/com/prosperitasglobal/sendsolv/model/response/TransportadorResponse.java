package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Transportador;

public class TransportadorResponse extends InquiryResponse
{

	/** Attributes */
	private List<Transportador> transportadorList;

	/**
	 * The Constructor.
	 */
	public TransportadorResponse()
	{

	}

	/**
	 * @return the transportadorList
	 */
	public List<Transportador> getTransportadorList()
	{
		return transportadorList;
	}

	/**
	 * @param transportadorList the transportadorList to set
	 */
	public void setTransportadorList(List<Transportador> transportadorList)
	{
		this.transportadorList = transportadorList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setTransportadorList((List<Transportador>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getTransportadorList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}