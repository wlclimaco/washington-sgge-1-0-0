package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.ProdutoEmpresa;

/**
 * The Class ProdutoParentResponse.
 *
 * @author aportoProdutoParentParentResponse
 * @version 1.0
 * @created 11-Sep-2014 09:39:00 AM
 */
public class ProdutoEmpresaResponse extends InquiryResponse
{

	/** Attributes. */
	private List<ProdutoEmpresa> produtoParentList;

	/**
	 * Gets the produtoParent list.
	 *
	 * @return the produtoParent list
	 */
	public List<ProdutoEmpresa> getProdutoParentList()
	{
		return produtoParentList;
	}

	/**
	 * Sets the produtoParent list.
	 *
	 * @param produtoParentList the produtoParent list
	 */
	public void setProdutoParentList(List<ProdutoEmpresa> produtoParentList)
	{
		this.produtoParentList = produtoParentList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setProdutoParentList((List<ProdutoEmpresa>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProdutoParentResponse [getProdutoParentList()=" + getProdutoParentList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
