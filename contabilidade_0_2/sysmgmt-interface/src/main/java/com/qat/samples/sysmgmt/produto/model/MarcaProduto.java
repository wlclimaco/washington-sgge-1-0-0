package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class MarcaProduto extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Marca marcaId;

	/**
	 * Default constructor.
	 */
	public MarcaProduto()
	{
		super();
	}

	public MarcaProduto(Integer id)
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

	public Marca getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Marca marcaId) {
		this.marcaId = marcaId;
	}

	@Override
	public String toString() {
		return "MarcaProd [getId()=" + getId() + ", getUnimedId()=" + getUnimedId() + ", getMarcaId()=" + getMarcaId()
				+ ", toString()=" + super.toString() + "]";
	}

}
