package com.qat.samples.sysmgmt.dicionario.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.dicionario.Interface;

public class InterfaceResponse extends InquiryResponse
{

	/** Attributes */
	private List<Interface> InterfaceList;

	/**
	 * The Constructor.
	 */
	public InterfaceResponse()
	{

	}

	/**
	 * @return the InterfaceList
	 */
	public List<Interface> getInterfaceList()
	{
		return InterfaceList;
	}

	/**
	 * @param InterfaceList the InterfaceList to set
	 */
	public void setInterfaceList(List<Interface> InterfaceList)
	{
		this.InterfaceList = InterfaceList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setInterfaceList((List<Interface>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getInterfaceList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}