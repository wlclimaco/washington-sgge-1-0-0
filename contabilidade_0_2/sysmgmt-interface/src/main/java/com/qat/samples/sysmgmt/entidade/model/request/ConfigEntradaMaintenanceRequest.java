package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfigEntradaMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ConfigEntrada configEntrada;

	/**
	 * The Constructor.
	 */
	public ConfigEntradaMaintenanceRequest()
	{

	}

	/**
	 * @return the configEntrada
	 */
	public ConfigEntrada getConfigEntrada()
	{
		return configEntrada;
	}

	/**
	 * @param configEntrada the configEntrada to set
	 */
	public void setConfigEntrada(ConfigEntrada configEntrada)
	{
		this.configEntrada = configEntrada;
	}

	@Override
	public String toString() {
		return "ConfigEntradaMaintenanceRequest [getConfigEntrada()=" + getConfigEntrada() + ", toString()=" + super.toString() + "]";
	}

}