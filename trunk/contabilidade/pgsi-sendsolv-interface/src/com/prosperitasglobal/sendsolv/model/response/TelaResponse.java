package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Tela;

/**
 * The Class LocationResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class TelaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Tela> telaList;

	/**
	 * The Constructor.
	 */
	public TelaResponse()
	{

	}

	/**
	 * @return the telaList
	 */
	public List<Tela> getTelaList()
	{
		return telaList;
	}

	/**
	 * @param telaList the telaList to set
	 */
	public void setTelaList(List<Tela> telaList)
	{
		this.telaList = telaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setTelaList((List<Tela>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getTelaList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}