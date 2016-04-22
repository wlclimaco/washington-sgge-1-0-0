package com.qat.samples.sysmgmt.cfop.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class CfopPessoa extends ModelCosmeDamiao
{

	private Integer id;

	/** The description. */
	private Cfop idCfop;

	/**
	 * Default constructor.
	 */
	public CfopPessoa()
	{
		super();
	}

	public CfopPessoa(Integer id, PersistenceActionEnum mode)
	{
		super();
		this.id = id;
		setModelAction(mode);
	}

	public CfopPessoa(Integer id, Cfop idCfop, Integer parentId, PersistenceActionEnum mode)
	{
		super();
		this.id = id;
		this.idCfop = idCfop;
		setModelAction(mode);
		setParentId(parentId);
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
	 * @return the idCfop
	 */
	public Cfop getIdCfop()
	{
		return idCfop;
	}

	/**
	 * @param idCfop the idCfop to set
	 */
	public void setIdCfop(Cfop idCfop)
	{
		this.idCfop = idCfop;
	}

	@Override
	public String toString()
	{
		return "CfopPessoa [getId()=" + getId() + ", getIdCfop()=" + getIdCfop() + ", toString()=" + super.toString()
				+ "]";
	}

}
