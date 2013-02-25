package com.sensus.mlc.smartpoint.model.response;

import com.sensus.common.model.response.Response;

/**
 * The Class LightStatusResponse.
 */
public class LightStatusResponse extends Response
{

	/** The result. */
	private Float result;

	/**
	 * Gets the result.
	 * 
	 * @return the result
	 */
	public Float getResult()
	{
		return this.result;
	}

	/**
	 * Sets the result.
	 * 
	 * @param result the new result
	 */
	public void setResult(Float result)
	{
		this.result = result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightStatusResponse [getResult()=" + this.getResult() + ", getMessageIterator()="
				+ this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}
