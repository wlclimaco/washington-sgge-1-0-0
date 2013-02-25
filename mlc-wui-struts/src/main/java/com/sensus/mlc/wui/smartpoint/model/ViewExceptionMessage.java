package com.sensus.mlc.wui.smartpoint.model;

import java.io.Serializable;

/**
 * The Class ViewExceptionMessages is view helper class.
 * 
 * @author Jose Carlos Pereira
 * 
 */

@SuppressWarnings("serial")
public class ViewExceptionMessage implements Serializable
{

	/** The id. */
	private Integer id;

	/** The message. */
	private String message;

	/** The date. */
	private String date;

	/** The status. */
	private String status;

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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message the new message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date the new date
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ViewExceptionMessage [id=" + id + ", message=" + message + ", date=" + date + ", status=" + status
				+ "]";
	}
}
