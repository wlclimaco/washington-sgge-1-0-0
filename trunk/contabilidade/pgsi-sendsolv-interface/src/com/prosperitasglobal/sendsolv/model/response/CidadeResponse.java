package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Cidade;
import com.qat.framework.model.response.InquiryResponse;

public class CidadeResponse extends InquiryResponse
{

	/** Attributes */
	private List<Cidade> cidadeList;

	/**
	 * The Constructor.
	 */
	public CidadeResponse()
	{

	}

	/**
	 * @return the cidadeList
	 */
	public List<Cidade> getCidadeList()
	{
		return cidadeList;
	}

	/**
	 * @param cidadeList the cidadeList to set
	 */
	public void setCidadeList(List<Cidade> cidadeList)
	{
		this.cidadeList = cidadeList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCidadeList((List<Cidade>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CidadeResponse [getCidadeList()=" + getCidadeList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}