package com.prosperitasglobal.cbof.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;

/**
 * The Class FetchByCodeRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:32:37 AM
 */
public class FetchByCodeRequest extends Request
{

	/** The code. */
	@XmlElement(nillable = true)
	private String code;

	/**
	 * The Constructor.
	 */
	public FetchByCodeRequest()
	{

	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FetchByCodeRequest [getCode()=" + getCode() + ", getUserContext()=" + getUserContext() + "]";
	}

}