package com.qat.samples.sysmgmt.dp.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class EventoPessoa extends ModelCosmeDamiao
{

	private Integer id;

	private Long data;

	/** The description. */
	private Eventos idEvent;

	/** The codigo. */
	private Integer idFunc;

	/**
	 * Default constructor.
	 */
	public EventoPessoa()
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
	 * @return the idEvent
	 */
	public Eventos getIdEvent()
	{
		return idEvent;
	}

	/**
	 * @param idEvent the idEvent to set
	 */
	public void setIdEvent(Eventos idEvent)
	{
		this.idEvent = idEvent;
	}

	/**
	 * @return the idFunc
	 */
	public Integer getIdFunc()
	{
		return idFunc;
	}

	/**
	 * @param idFunc the idFunc to set
	 */
	public void setIdFunc(Integer idFunc)
	{
		this.idFunc = idFunc;
	}

	@Override
	public String toString()
	{
		return "EventoPessoa [getId()=" + getId() + ", getData()=" + getData() + ", getIdEvent()=" + getIdEvent()
				+ ", getIdFunc()=" + getIdFunc() + ", toString()=" + super.toString() + "]";
	}

}
