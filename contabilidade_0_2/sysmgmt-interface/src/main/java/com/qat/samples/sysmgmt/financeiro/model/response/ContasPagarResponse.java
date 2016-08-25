package com.qat.samples.sysmgmt.financeiro.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;

/**
 * The Class ContasPagarResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class ContasPagarResponse extends InquiryResponse
{

	/** Attributes */
	private List<ContasPagar> contasPagarList;

	/**
	 * The Constructor.
	 */
	public ContasPagarResponse()
	{

	}

	/**
	 * @return the contasPagarList
	 */
	public List<ContasPagar> getContasPagarList()
	{
		return contasPagarList;
	}

	/**
	 * @param contasPagarList the contasPagarList to set
	 */
	public void setContasPagarList(List<ContasPagar> contasPagarList)
	{
		this.contasPagarList = contasPagarList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setContasPagarList((List<ContasPagar>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContasPagarResponse [getContasPagarList()=" + getContasPagarList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}