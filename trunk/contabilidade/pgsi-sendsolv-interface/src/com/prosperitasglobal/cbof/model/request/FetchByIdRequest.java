package com.prosperitasglobal.cbof.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;

/**
 * The Class FetchByIdRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:22:38 AM
 */
public class FetchByIdRequest extends Request
{

	/** The id. */
	@XmlElement(nillable = true)
	private Integer id;

	/** The string id. */
	@XmlElement(nillable = true)
	private String stringId;

	/**
	 * The Constructor.
	 */
	public FetchByIdRequest()
	{

	}

	/**
	 * The Constructor.
	 *
	 * @param fetchId the fetch id
	 */
	public FetchByIdRequest(Integer fetchId)
	{
		id = fetchId;
	}

	/**
	 * The Constructor.
	 *
	 * @param fetchId the fetch id
	 */
	public FetchByIdRequest(String fetchId)
	{
		stringId = fetchId;
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

	/**
	 * Gets the string id.
	 *
	 * @return the string id
	 */
	public String getStringId()
	{
		return stringId;
	}

	/**
	 * Sets the string id.
	 *
	 * @param stringId the string id
	 */
	public void setStringId(String stringId)
	{
		this.stringId = stringId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FetchByIdRequest [getId()=" + getId() + ", getStringId()=" + getStringId() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}