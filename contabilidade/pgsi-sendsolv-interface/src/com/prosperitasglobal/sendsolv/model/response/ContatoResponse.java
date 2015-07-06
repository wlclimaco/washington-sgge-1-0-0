package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Contato;

public class ContatoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Contato> arquivoList;

	/**
	 * The Constructor.
	 */
	public ContatoResponse()
	{

	}

	/**
	 * @return the arquivoList
	 */
	public List<Contato> getArquivoList()
	{
		return arquivoList;
	}

	/**
	 * @param arquivoList the arquivoList to set
	 */
	public void setArquivoList(List<Contato> arquivoList)
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