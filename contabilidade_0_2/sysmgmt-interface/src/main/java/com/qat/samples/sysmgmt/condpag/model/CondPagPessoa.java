package com.qat.samples.sysmgmt.condpag.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class CondPagPessoa extends ModelCosmeDamiao
{

	private Integer id;

	/** The description. */
	private CondPag condPagId;

	/**
	 * Default constructor.
	 */
	public CondPagPessoa()
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
	 * @return the condPagId
	 */
	public CondPag getCondPagId()
	{
		return condPagId;
	}

	/**
	 * @param condPagId the condPagId to set
	 */
	public void setCondPagId(CondPag condPagId)
	{
		this.condPagId = condPagId;
	}

	@Override
	public String toString()
	{
		return "CondPagPessoa [getId()=" + getId() + ", getCondPagId()=" + getCondPagId() + ", toString()="
				+ super.toString() + "]";
	}

}
