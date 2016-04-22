package com.qat.samples.sysmgmt.util.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;

/**
 * The Class FetchByCodeRequest.
 */
public class FetchByCodeRequest extends Request
{

	/** The fetch code. */
	@XmlElement(nillable = true)
	private String fetchCode;

	/**
	 * Instantiates a new fetch by code request.
	 *
	 * @param fetchCode the fetch code
	 */
	public FetchByCodeRequest(String fetchCode)
	{
		this.fetchCode = fetchCode;
	}

	/**
	 * Instantiates a new fetch by code request.
	 */
	public FetchByCodeRequest()
	{
	}

	/**
	 * Gets the fetch code.
	 *
	 * @return the fetch code
	 */
	public String getFetchCode()
	{
		return fetchCode;
	}

	/**
	 * Sets the fetch code.
	 *
	 * @param fetchCode the new fetch code
	 */
	public void setFetchCode(String fetchCode)
	{
		this.fetchCode = fetchCode;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FetchByCodeRequest [getFetchCode()=" + getFetchCode() + ", getUserContext()=" + getRequestContext() + "]";
	}
}
