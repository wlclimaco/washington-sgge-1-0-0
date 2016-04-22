package com.qat.samples.sysmgmt.dp.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.dp.model.Profissao;

public class ProfissaoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Profissao> arquivoList;

	/**
	 * The Constructor.
	 */
	public ProfissaoResponse()
	{

	}

	/**
	 * @return the arquivoList
	 */
	public List<Profissao> getProfissaoList()
	{
		return arquivoList;
	}

	/**
	 * @param arquivoList the arquivoList to set
	 */
	public void setProfissaoList(List<Profissao> arquivoList)
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
		setProfissaoList((List<Profissao>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProfissaoResponse [getProfissaoList()=" + getProfissaoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}