package com.qat.samples.sysmgmt.cotacao.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.cotacao.model.Cotacao;

public class CotacaoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Cotacao> cotacaoList;

	/**
	 * The Constructor.
	 */
	public CotacaoResponse()
	{

	}

	/**
	 * @return the CotacaoList
	 */
	public List<Cotacao> getCotacaoList()
	{
		return cotacaoList;
	}

	/**
	 * @param CotacaoList the CotacaoList to set
	 */
	public void setCotacaoList(List<Cotacao> CotacaoList)
	{
		this.cotacaoList = CotacaoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCotacaoList((List<Cotacao>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CotacaoResponse [getCotacaoList()=" + getCotacaoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}