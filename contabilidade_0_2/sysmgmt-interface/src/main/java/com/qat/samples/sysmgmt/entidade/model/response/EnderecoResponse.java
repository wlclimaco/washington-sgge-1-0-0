package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.util.model.Endereco;

/**
 * The Class EnderecoResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class EnderecoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Endereco> enderecoList;

	/**
	 * The Constructor.
	 */
	public EnderecoResponse()
	{

	}

	/**
	 * @return the enderecoList
	 */
	public List<Endereco> getEnderecoList()
	{
		return enderecoList;
	}

	/**
	 * @param enderecoList the enderecoList to set
	 */
	public void setEnderecoList(List<Endereco> enderecoList)
	{
		this.enderecoList = enderecoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setEnderecoList((List<Endereco>)coll);
	}

	@Override
	public String toString()
	{
		return "EnderecoResponse [getEnderecoList()=" + getEnderecoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}
}