package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.ConfigVendas;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfigVendasMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ConfigVendas configVendas;

	/**
	 * The Constructor.
	 */
	public ConfigVendasMaintenanceRequest()
	{

	}

	/**
	 * @return the configVendas
	 */
	public ConfigVendas getConfigVendas()
	{
		return configVendas;
	}

	/**
	 * @param configVendas the configVendas to set
	 */
	public void setConfigVendas(ConfigVendas configVendas)
	{
		this.configVendas = configVendas;
	}

	@Override
	public String toString() {
		return "ConfigVendasMaintenanceRequest [getConfigVendas()=" + getConfigVendas() + ", toString()=" + super.toString() + "]";
	}

}