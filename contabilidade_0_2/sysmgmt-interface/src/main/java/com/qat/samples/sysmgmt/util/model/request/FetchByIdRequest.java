package com.qat.samples.sysmgmt.util.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;

/**
 * The Class FetchByIdRequest.
 */
public class FetchByIdRequest extends Request
{

	/** The fetch id. */
	@XmlElement(nillable = true)
	private Integer fetchId;

	/**
	 * Instantiates a new fetch by id request.
	 *
	 * @param fetchId the fetch id
	 */
	public FetchByIdRequest(Integer fetchId)
	{
		this.fetchId = fetchId;
	}

	/**
	 * Instantiates a new fetch by id request.
	 */
	public FetchByIdRequest()
	{
	}

	/**
	 * Gets the fetch id.
	 *
	 * @return the fetch id
	 */
	public Integer getFetchId()
	{
		return fetchId;
	}

	/**
	 * Sets the fetch id.
	 *
	 * @param fetchId the new fetch id
	 */
	public void setFetchId(Integer fetchId)
	{
		this.fetchId = fetchId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FetchByIdRequest [fetchId=" + fetchId + ", getFetchId()=" + getFetchId() + ", getUserContext()=" + getRequestContext() + "]";
	}

}
