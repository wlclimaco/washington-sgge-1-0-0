package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.light.model.LastOperationalData;

/**
 * Used for updating last operational data information about a given Light.
 */
public class LastOperationalDataMaintenanceRequest extends MaintenanceRequest
{

	/** The light id. */
	private Integer lightId;

	/** The last operational data. */
	private LastOperationalData lastOperationalData;

	/**
	 * Gets the light id.
	 * 
	 * @return the light id
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * Sets the light id.
	 * 
	 * @param lightId the new light id
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * Gets the last operational data.
	 * 
	 * @return the lastOperationalData
	 */
	public LastOperationalData getLastOperationalData()
	{
		return lastOperationalData;
	}

	/**
	 * Sets the last operational data.
	 * 
	 * @param lastOperationalData the lastOperationalData to set
	 */
	public void setLastOperationalData(LastOperationalData lastOperationalData)
	{
		this.lastOperationalData = lastOperationalData;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LastOperationalDataMaintenanceRequest [getLightId()=" + getLightId() + ", getLastOperationalData()="
				+ getLastOperationalData() + ", toString()=" + super.toString() + "]";
	}

}
