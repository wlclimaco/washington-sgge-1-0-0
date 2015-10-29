package com.qat.samples.sysmgmt.condpag;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class TipoPag extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String descricao;

	/**
	 * Default constructor.
	 */
	public TipoPag()
	{
		super();
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	@Override
	public String toString()
	{
		return "TipoPag [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", toString()="
				+ super.toString() + "]";
	}

}
