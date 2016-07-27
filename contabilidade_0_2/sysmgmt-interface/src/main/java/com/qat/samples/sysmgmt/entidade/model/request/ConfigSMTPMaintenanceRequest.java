package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.ConfigSMTP;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfigSMTPMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ConfigSMTP configSMTP;

	/**
	 * The Constructor.
	 */
	public ConfigSMTPMaintenanceRequest()
	{

	}

	/**
	 * @return the configSMTP
	 */
	public ConfigSMTP getConfigSMTP()
	{
		return configSMTP;
	}

	/**
	 * @param configSMTP the configSMTP to set
	 */
	public void setConfigSMTP(ConfigSMTP configSMTP)
	{
		this.configSMTP = configSMTP;
	}

	@Override
	public String toString() {
		return "ConfigSMTPMaintenanceRequest [getConfigSMTP()=" + getConfigSMTP() + ", toString()=" + super.toString() + "]";
	}

}