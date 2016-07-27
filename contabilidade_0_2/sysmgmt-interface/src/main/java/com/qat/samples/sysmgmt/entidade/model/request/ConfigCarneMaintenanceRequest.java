package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfigCarneMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ConfigCarne configCarne;

	/**
	 * The Constructor.
	 */
	public ConfigCarneMaintenanceRequest()
	{

	}

	/**
	 * @return the configCarne
	 */
	public ConfigCarne getConfigCarne()
	{
		return configCarne;
	}

	/**
	 * @param configCarne the configCarne to set
	 */
	public void setConfigCarne(ConfigCarne configCarne)
	{
		this.configCarne = configCarne;
	}

	@Override
	public String toString() {
		return "ConfigCarneMaintenanceRequest [getConfigCarne()=" + getConfigCarne() + ", toString()=" + super.toString() + "]";
	}

}