package com.qat.samples.sysmgmt.advocacia.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.advocacia.model.Advogado;

public class AvisoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Aviso> AvisoList;

	/**
	 * The Constructor.
	 */
	public AvisoResponse()
	{

	}

	/**
	 * @return the AvisoList
	 */
	public List<Aviso> getAvisoList()
	{
		return AvisoList;
	}

	/**
	 * @param AvisoList the AvisoList to set
	 */
	public void setAvisoList(List<Aviso> AvisoList)
	{
		this.AvisoList = AvisoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setAvisoList((List<Aviso>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getAvisoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}