package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class GrupoProd extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Grupo grupoId;

	/**
	 * Default constructor.
	 */
	public GrupoProd()
	{
		super();
	}

	public GrupoProd(Integer id)
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
	 * @return the grupoId
	 */
	public Grupo getUnimedId()
	{
		return grupoId;
	}

	/**
	 * @param grupoId the grupoId to set
	 */
	public void setUnimedId(Grupo grupoId)
	{
		this.grupoId = grupoId;
	}

	@Override
	public String toString()
	{
		return "GrupoProd [getId()=" + getId() + ", getUnimedId()=" + getUnimedId() + ", toString()="
				+ super.toString() + "]";
	}

}
