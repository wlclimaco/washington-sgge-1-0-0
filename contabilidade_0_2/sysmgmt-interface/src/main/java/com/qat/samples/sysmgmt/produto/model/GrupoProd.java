package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

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

	public Grupo getGrupoId()
	{
		return grupoId;
	}

	public void setGrupoId(Grupo grupoId)
	{
		this.grupoId = grupoId;
	}

	@Override
	public String toString()
	{
		return "GrupoProd [getId()=" + getId() + ", getGrupoId()=" + getGrupoId() + ", toString()=" + super.toString()
				+ "]";
	}

}
