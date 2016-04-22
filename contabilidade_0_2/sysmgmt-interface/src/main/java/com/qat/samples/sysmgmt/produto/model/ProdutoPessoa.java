package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ProdutoPessoa extends ModelCosmeDamiao
{

	private Integer id;

	/** The description. */
	private Produto prodId;

	/**
	 * Default constructor.
	 */
	public ProdutoPessoa()
	{
		super();
	}

	public ProdutoPessoa(Produto prodId)
	{
		super();
		this.prodId = prodId;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the prodId
	 */
	public Produto getProdId()
	{
		return prodId;
	}

	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(Produto prodId)
	{
		this.prodId = prodId;
	}

	@Override
	public String toString()
	{
		return "ProdutoPessoa [getId()=" + getId() + ", getProdId()=" + getProdId() + ", toString()="
				+ super.toString() + "]";
	}

}
