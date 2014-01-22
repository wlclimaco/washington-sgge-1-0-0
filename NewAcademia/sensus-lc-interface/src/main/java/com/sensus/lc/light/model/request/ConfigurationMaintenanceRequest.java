package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.light.model.Configuration;

/**
 * Used for updating last operational data information about a given Light.
 */
public class ConfigurationMaintenanceRequest extends MaintenanceRequest
{

	/** The light id. */
	private Integer lightId;

	/** The configuration. */
	private Configuration configuration;

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
	 * Gets the configuration.
	 *
	 * @return the configuration
	 */
	public Configuration getConfiguration()
	{
		return configuration;
	}

	/**
	 * Sets the configuration.
	 *
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration)
	{
		this.configuration = configuration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ConfigurationMaintenanceRequest [getLightId()=" + getLightId() + ", getConfiguration()="
				+ getConfiguration() + ", toString()=" + super.toString() + "]";
	}


}
