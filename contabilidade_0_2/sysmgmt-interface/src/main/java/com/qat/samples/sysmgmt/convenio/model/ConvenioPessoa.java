package com.qat.samples.sysmgmt.convenio.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConvenioPessoa extends ModelCosmeDamiao
{
	private Integer id;

	private Long data;

	/** The description. */
	private Convenio convId;

	/**
	 * Default constructor.
	 */
	public ConvenioPessoa()
	{
		super();
	}

	public ConvenioPessoa(Integer id, PersistenceActionEnum mode)
	{
		super();
		this.id = id;
		setModelAction(mode);
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
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * @return the convId
	 */
	public Convenio getConvId()
	{
		return convId;
	}

	/**
	 * @param convId the convId to set
	 */
	public void setConvId(Convenio convId)
	{
		this.convId = convId;
	}

	@Override
	public String toString()
	{
		return "ConvenioPessoa [getId()=" + getId() + ", getData()=" + getData() + ", getConvId()=" + getConvId()
				+ ", toString()=" + super.toString() + "]";
	}

}
