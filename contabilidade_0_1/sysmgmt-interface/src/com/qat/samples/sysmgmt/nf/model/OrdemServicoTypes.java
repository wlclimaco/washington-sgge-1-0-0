package com.qat.samples.sysmgmt.nf.model;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class OrdemServicoTypes extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer typeServico;

	/**
	 * Default constructor.
	 */
	public OrdemServicoTypes()
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the typeServico
	 */
	public Integer getTypeServico()
	{
		return typeServico;
	}

	/**
	 * @param typeServico the typeServico to set
	 */
	public void setTypeServico(Integer typeServico)
	{
		this.typeServico = typeServico;
	}

	@Override
	public String toString()
	{
		return "OrdemServicoTypes [getId()=" + getId() + ", getTypeServico()=" + getTypeServico() + ", toString()="
				+ super.toString() + "]";
	}

}
