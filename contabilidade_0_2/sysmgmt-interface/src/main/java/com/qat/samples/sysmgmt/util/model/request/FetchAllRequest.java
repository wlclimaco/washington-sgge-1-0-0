package com.qat.samples.sysmgmt.util.model.request;

import com.qat.framework.model.request.Request;

/**
 * The Class FetchAllRequest.
 */
public class FetchAllRequest extends Request
{

	/**
	 * Instantiates a new fetch all request.
	 */
	public FetchAllRequest()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FetchAllRequest [getUserContext()=" + getRequestContext() + "]";
	}

}
