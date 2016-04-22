package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class MarcaProd extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Marca marcaId;

	/**
	 * Default constructor.
	 */
	public MarcaProd()
	{
		super();
	}

	public MarcaProd(Integer id)
	{
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the marcaId
	 */
	public Marca getUnimedId()
	{
		return marcaId;
	}

	/**
	 * @param marcaId the marcaId to set
	 */
	public void setUnimedId(Marca marcaId)
	{
		this.marcaId = marcaId;
	}

	@Override
	public String toString()
	{
		return "MarcaProd [getId()=" + getId() + ", getUnimedId()=" + getUnimedId() + ", toString()="
				+ super.toString() + "]";
	}

}
