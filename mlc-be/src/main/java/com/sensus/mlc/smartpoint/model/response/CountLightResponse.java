package com.sensus.mlc.smartpoint.model.response;

import com.sensus.common.model.response.Response;

/**
 * The Class LightResponse.
 *
 * @author - Gustavo Aragao - QAT Brazil
 */
/**
 * @author QATEmployee
 * 
 */
public class CountLightResponse extends Response
{

	/** The device light count. */
	private Integer deviceLightCount;

	/**
	 * Sets the device light count.
	 * 
	 * @param deviceLightCountInteger the new device light count
	 */
	public void setDeviceLightCount(Integer deviceLightCountInteger)
	{
		this.deviceLightCount = deviceLightCountInteger;
	}

	/**
	 * Gets the device light count.
	 * 
	 * @return the device light count
	 */
	public Integer getDeviceLightCount()
	{
		return this.deviceLightCount;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CountLightResponse [getDeviceLightCount()=" + this.getDeviceLightCount() + ", getMessageIterator()="
				+ this.getMessageIterator() + ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()="
				+ this.getMessageInfoList() + ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}