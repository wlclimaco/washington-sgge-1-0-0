package com.qat.samples.sysmgmt.condpag.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class FormaPgPessoa extends ModelCosmeDamiao
{

	private Integer id;

	/** The description. */
	private FormaPg formaPgId;

	/**
	 * Default constructor.
	 */
	public FormaPgPessoa()
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
	 * @return the formaPgId
	 */
	public FormaPg getFormaPgId()
	{
		return formaPgId;
	}

	/**
	 * @param formaPgId the formaPgId to set
	 */
	public void setFormaPgId(FormaPg formaPgId)
	{
		this.formaPgId = formaPgId;
	}

	@Override
	public String toString()
	{
		return "FormaPgPessoa [getId()=" + getId() + ", getFormaPgId()=" + getFormaPgId() + ", toString()="
				+ super.toString() + "]";
	}

}
