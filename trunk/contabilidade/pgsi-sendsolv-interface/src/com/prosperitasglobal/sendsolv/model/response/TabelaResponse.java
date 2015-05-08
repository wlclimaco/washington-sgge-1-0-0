package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Tabela;

/**
 * The Class LocationResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class TabelaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Tabela> tabelaList;

	/**
	 * The Constructor.
	 */
	public TabelaResponse()
	{

	}

	/**
	 * @return the tabelaList
	 */
	public List<Tabela> getTabelaList()
	{
		return tabelaList;
	}

	/**
	 * @param tabelaList the tabelaList to set
	 */
	public void setTabelaList(List<Tabela> tabelaList)
	{
		this.tabelaList = tabelaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setTabelaList((List<Tabela>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getTabelaList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}