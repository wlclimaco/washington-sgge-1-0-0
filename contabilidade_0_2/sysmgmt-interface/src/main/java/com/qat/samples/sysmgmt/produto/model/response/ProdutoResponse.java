package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Produto;

/**
 * The Class ProdutoResponse.
 * 
 * @author aporto
 * @version 1.0
 * @created 11-Sep-2014 09:39:00 AM
 */
public class ProdutoResponse extends InquiryResponse
{

	/** Attributes. */
	private List<Produto> produtoList;

	/**
	 * Gets the produto list.
	 * 
	 * @return the produto list
	 */
	public List<Produto> getProdutoList()
	{
		return produtoList;
	}

	/**
	 * Sets the produto list.
	 * 
	 * @param produtoList the produto list
	 */
	public void setProdutoList(List<Produto> produtoList)
	{
		this.produtoList = produtoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setProdutoList((List<Produto>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProdutoResponse [getProdutoList()=" + getProdutoList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
