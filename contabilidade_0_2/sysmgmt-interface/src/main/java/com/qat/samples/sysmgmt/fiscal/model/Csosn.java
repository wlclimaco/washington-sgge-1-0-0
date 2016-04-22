package com.qat.samples.sysmgmt.fiscal.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

@SuppressWarnings("serial")
public class Csosn extends ModelCosmeDamiao
{

	/** The id. */
	private Integer id;

	private Integer prodId;

	public Csosn(Integer id)
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

	public Integer getProdId()
	{
		return prodId;
	}

	public void setProdId(Integer prodId)
	{
		this.prodId = prodId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "Csosn [getId()=" + getId() + ", getProdId()=" + getProdId() + ", toString()=" + super.toString() + "]";
	}

}