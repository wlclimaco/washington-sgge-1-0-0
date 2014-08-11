package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.util.Criteria;

/**
 * The Model Object PagedInquiryRequest.
 */
public class ProdutoInquiryRequest extends InquiryRequest
{

	/** The cadastro. */
	private Produto produto;

	/** The criteria. */
	private Criteria criteria;

	/**
	 * Instantiates a new cadastro inquiry request.
	 */
	public ProdutoInquiryRequest()
	{

	}

	public Criteria getCriteria()
	{
		return criteria;
	}

	public void setCriteria(Criteria criteria)
	{
		this.criteria = criteria;
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

	@Override
	public String toString()
	{
		return "ProdutoInquiryRequest [getCriteria()=" + getCriteria() + ", getProduto()=" + getProduto()
				+ ", toString()=" + super.toString() + "]";
	}

}
