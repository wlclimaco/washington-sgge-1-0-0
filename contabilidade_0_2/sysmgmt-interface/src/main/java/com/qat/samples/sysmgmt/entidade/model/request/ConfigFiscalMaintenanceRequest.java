package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.ConfigFiscal;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfigFiscalMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ConfigFiscal configFiscal;

	/**
	 * The Constructor.
	 */
	public ConfigFiscalMaintenanceRequest()
	{

	}

	/**
	 * @return the configFiscal
	 */
	public ConfigFiscal getConfigFiscal()
	{
		return configFiscal;
	}

	/**
	 * @param configFiscal the configFiscal to set
	 */
	public void setConfigFiscal(ConfigFiscal configFiscal)
	{
		this.configFiscal = configFiscal;
	}

	@Override
	public String toString() {
		return "ConfigFiscalMaintenanceRequest [getConfigFiscal()=" + getConfigFiscal() + ", toString()=" + super.toString() + "]";
	}

}