package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Produto;

/**
 * The Model Object ProdutoResponse.
 */
public class ProdutoResponse extends InquiryResponse
{

	/** The produto. */
	@XmlElement(nillable = true)
	private List<Produto> produto;

	/**
	 * Gets the produto.
	 * 
	 * @return the produto
	 */
	public List<Produto> getProdutos()
	{
		return produto;
	}

	/**
	 * Sets the produto.
	 * 
	 * @param produto the new produto
	 */
	public void setProdutos(List<Produto> produto)
	{
		this.produto = produto;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setProdutos((List<Produto>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "ProdutoResponse [getProdutos()=" + getProdutos() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
