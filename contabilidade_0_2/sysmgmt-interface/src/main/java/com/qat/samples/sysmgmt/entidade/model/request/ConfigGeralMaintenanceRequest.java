package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfigGeralMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ConfigGeral configGeral;

	/**
	 * The Constructor.
	 */
	public ConfigGeralMaintenanceRequest()
	{

	}

	/**
	 * @return the configGeral
	 */
	public ConfigGeral getConfigGeral()
	{
		return configGeral;
	}

	/**
	 * @param configGeral the configGeral to set
	 */
	public void setConfigGeral(ConfigGeral configGeral)
	{
		this.configGeral = configGeral;
	}

	@Override
	public String toString() {
		return "ConfigGeralMaintenanceRequest [getConfigGeral()=" + getConfigGeral() + ", toString()=" + super.toString() + "]";
	}

}