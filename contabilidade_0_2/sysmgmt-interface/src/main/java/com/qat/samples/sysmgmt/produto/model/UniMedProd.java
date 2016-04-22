package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class UniMedProd extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private UniMed unimedId;

	/**
	 * Default constructor.
	 */
	public UniMedProd()
	{
		super();
	}

	public UniMedProd(Integer id)
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
	 * @return the unimedId
	 */
	public UniMed getUnimedId()
	{
		return unimedId;
	}

	/**
	 * @param unimedId the unimedId to set
	 */
	public void setUnimedId(UniMed unimedId)
	{
		this.unimedId = unimedId;
	}

	@Override
	public String toString()
	{
		return "UniMedProd [getId()=" + getId() + ", getUnimedId()=" + getUnimedId() + ", toString()="
				+ super.toString() + "]";
	}

}
