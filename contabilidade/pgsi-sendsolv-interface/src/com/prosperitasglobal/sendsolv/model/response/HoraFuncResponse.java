package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

public class HoraFuncResponse extends InquiryResponse
{

	/** Attributes */
	private List<HoraFunc> arquivoList;

	/**
	 * The Constructor.
	 */
	public HoraFuncResponse()
	{

	}

	/**
	 * @return the arquivoList
	 */
	public List<HoraFunc> getArquivoList()
	{
		return arquivoList;
	}

	/**
	 * @param arquivoList the arquivoList to set
	 */
	public void setArquivoList(List<HoraFunc> arquivoList)
	{
		this.arquivoList = arquivoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setArquivoList((List<HoraFunc>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getArquivoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}