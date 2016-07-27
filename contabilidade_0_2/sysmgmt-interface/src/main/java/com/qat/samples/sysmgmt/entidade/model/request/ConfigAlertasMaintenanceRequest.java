package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.ConfigAlertas;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfigAlertasMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ConfigAlertas configAlertas;

	/**
	 * The Constructor.
	 */
	public ConfigAlertasMaintenanceRequest()
	{

	}

	/**
	 * @return the configAlertas
	 */
	public ConfigAlertas getConfigAlertas()
	{
		return configAlertas;
	}

	/**
	 * @param configAlertas the configAlertas to set
	 */
	public void setConfigAlertas(ConfigAlertas configAlertas)
	{
		this.configAlertas = configAlertas;
	}

	@Override
	public String toString() {
		return "ConfigAlertasMaintenanceRequest [getConfigAlertas()=" + getConfigAlertas() + ", toString()=" + super.toString() + "]";
	}

}