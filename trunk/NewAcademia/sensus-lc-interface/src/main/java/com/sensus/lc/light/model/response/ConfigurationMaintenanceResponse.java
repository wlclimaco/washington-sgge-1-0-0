package com.sensus.lc.light.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.Configuration;

/**
 * The Class ConfigurationMaintenanceResponse.
 */
public class ConfigurationMaintenanceResponse extends InquiryResponse
{

	/** The configuration list. */
	private List<Configuration> configurationList;

	/**
	 * Gets the configuration list.
	 *
	 * @return the configuration list
	 */
	public List<Configuration> getConfigurationList()
	{
		return configurationList;
	}

	/**
	 * Sets the configuration list.
	 *
	 * @param configurationList the new configuration list
	 */
	public void setConfigurationList(List<Configuration> configurationList)
	{
		this.configurationList = configurationList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ConfigurationMaintenanceResponse [getConfigurationList()=" + getConfigurationList() + ", toString()="
				+ super.toString() + "]";
	}
}
