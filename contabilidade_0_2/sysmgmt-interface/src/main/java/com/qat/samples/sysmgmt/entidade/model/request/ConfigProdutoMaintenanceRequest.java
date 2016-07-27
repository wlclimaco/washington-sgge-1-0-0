package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.ConfigProduto;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfigProdutoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ConfigProduto configProduto;

	/**
	 * The Constructor.
	 */
	public ConfigProdutoMaintenanceRequest()
	{

	}

	/**
	 * @return the configProduto
	 */
	public ConfigProduto getConfigProduto()
	{
		return configProduto;
	}

	/**
	 * @param configProduto the configProduto to set
	 */
	public void setConfigProduto(ConfigProduto configProduto)
	{
		this.configProduto = configProduto;
	}

	@Override
	public String toString() {
		return "ConfigProdutoMaintenanceRequest [getConfigProduto()=" + getConfigProduto() + ", toString()=" + super.toString() + "]";
	}

}