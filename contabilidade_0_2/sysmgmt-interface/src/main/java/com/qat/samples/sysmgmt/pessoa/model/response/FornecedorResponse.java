package com.qat.samples.sysmgmt.pessoa.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;

public class FornecedorResponse extends InquiryResponse
{

	/** Attributes */
	private List<Fornecedor> fornecedorList;

	/**
	 * The Constructor.
	 */
	public FornecedorResponse()
	{

	}

	/**
	 * @return the fornecedorList
	 */
	public List<Fornecedor> getFornecedorList()
	{
		return fornecedorList;
	}

	/**
	 * @param fornecedorList the fornecedorList to set
	 */
	public void setFornecedorList(List<Fornecedor> fornecedorList)
	{
		this.fornecedorList = fornecedorList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setFornecedorList((List<Fornecedor>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FornecedorResponse [getFornecedorList()=" + getFornecedorList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}