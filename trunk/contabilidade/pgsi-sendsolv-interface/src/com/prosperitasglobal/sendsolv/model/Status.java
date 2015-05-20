package com.prosperitasglobal.sendsolv.model;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Status extends QATModelOL
{

	/** Attributes. */
	private Integer id;

	/** The parent key. */
	private Integer dataStatus;

	/** The parent key type. */
	private StatusEnum status;

	private String note;

	/**
	 * The Constructor.
	 */
	public Status()
	{

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
	 * @param id the id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getDataStatus()
	{
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus)
	{
		this.dataStatus = dataStatus;
	}

	public StatusEnum getStatus()
	{
		return status;
	}

	public void setStatus(StatusEnum status)
	{
		this.status = status;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

}