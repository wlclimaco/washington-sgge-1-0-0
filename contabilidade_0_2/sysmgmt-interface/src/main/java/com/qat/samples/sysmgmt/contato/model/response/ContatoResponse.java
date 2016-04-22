package com.qat.samples.sysmgmt.contato.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.contato.model.Contato;

public class ContatoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Contato> contatoList;

	/**
	 * The Constructor.
	 */
	public ContatoResponse()
	{

	}

	/**
	 * @return the contatoList
	 */
	public List<Contato> getContatoList()
	{
		return contatoList;
	}

	/**
	 * @param contatoList the contatoList to set
	 */
	public void setContatoList(List<Contato> contatoList)
	{
		this.contatoList = contatoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setContatoList((List<Contato>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContatoResponse [getContatoList()=" + getContatoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}