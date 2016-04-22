package com.qat.samples.sysmgmt.nf.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.nf.model.Orcamento;

public class OrcamentoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Orcamento> orcamentoList;

	/**
	 * The Constructor.
	 */
	public OrcamentoResponse()
	{

	}

	/**
	 * @return the orcamentoList
	 */
	public List<Orcamento> getOrcamentoList()
	{
		return orcamentoList;
	}

	/**
	 * @param orcamentoList the orcamentoList to set
	 */
	public void setOrcamentoList(List<Orcamento> orcamentoList)
	{
		this.orcamentoList = orcamentoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setOrcamentoList((List<Orcamento>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getOrcamentoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}