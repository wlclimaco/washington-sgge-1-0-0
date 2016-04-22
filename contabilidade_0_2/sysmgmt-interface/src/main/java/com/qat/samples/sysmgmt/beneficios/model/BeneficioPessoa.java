package com.qat.samples.sysmgmt.beneficios.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class BeneficioPessoa extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer idFunc;

	/** The description. */
	private Beneficios benefId;

	/**
	 * Default constructor.
	 */
	public BeneficioPessoa()
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
	 * @return the benefId
	 */
	public Beneficios getBenefId()
	{
		return benefId;
	}

	/**
	 * @param benefId the benefId to set
	 */
	public void setBenefId(Beneficios benefId)
	{
		this.benefId = benefId;
	}

	public Integer getIdFunc()
	{
		return idFunc;
	}

	public void setIdFunc(Integer idFunc)
	{
		this.idFunc = idFunc;
	}

	@Override
	public String toString()
	{
		return "BeneficioPessoa [getId()=" + getId() + ", getBenefId()=" + getBenefId() + ", getIdFunc()="
				+ getIdFunc() + ", toString()=" + super.toString() + "]";
	}
}
