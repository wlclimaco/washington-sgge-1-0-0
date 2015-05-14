package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Arquivo;

/**
 * The Class LocationResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class ArquivoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Arquivo> arquivoList;

	/**
	 * The Constructor.
	 */
	public ArquivoResponse()
	{

	}

	/**
	 * @return the arquivoList
	 */
	public List<Arquivo> getArquivoList()
	{
		return arquivoList;
	}

	/**
	 * @param arquivoList the arquivoList to set
	 */
	public void setArquivoList(List<Arquivo> arquivoList)
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
		setArquivoList((List<Arquivo>)coll);
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