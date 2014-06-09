package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Produto;

/**
 * The Model Object PagedInquiryRequest.
 */
public class ProdutoInquiryRequest extends InquiryRequest
{

	/** The cadastro. */
	private Produto produto;

	/**
	 * Instantiates a new cadastro inquiry request.
	 */
	public ProdutoInquiryRequest()
	{

	}

	/**
	 * Instantiates a new produto inquiry request.
	 * 
	 * @param produto the produto
	 */
	public ProdutoInquiryRequest(Produto produto)
	{
		super();
		this.produto = produto;
	}

	/**
	 * Gets the cadastro.
	 * 
	 * @return the cadastro
	 */
	public Produto getProduto()
	{
		return produto;
	}

	/**
	 * Sets the cadastro.
	 * 
	 * @param produto the new cadastro
	 */
	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProdutoInquiryRequest [getProduto()=" + getProduto() + ", toString()=" + super.toString() + "]";
	}

}
