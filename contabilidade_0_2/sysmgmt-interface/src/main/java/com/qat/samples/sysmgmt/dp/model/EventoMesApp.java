package com.qat.samples.sysmgmt.dp.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class EventoMesApp extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	/** The parent key type. */
	private Long data;

	/**
	 * The Constructor.
	 */
	public EventoMesApp()
	{

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

	@Override
	public String toString()
	{
		return "EventoMesApp [getId()=" + getId() + ", getData()=" + getData() + ", toString()=" + super.toString()
				+ "]";
	}

}