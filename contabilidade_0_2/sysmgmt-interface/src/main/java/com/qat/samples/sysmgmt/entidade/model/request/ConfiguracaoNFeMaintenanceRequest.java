package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.ConfiguracaoNFe;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfiguracaoNFeMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ConfiguracaoNFe configuracaoNFe;

	/**
	 * The Constructor.
	 */
	public ConfiguracaoNFeMaintenanceRequest()
	{

	}

	/**
	 * @return the configuracaoNFe
	 */
	public ConfiguracaoNFe getConfiguracaoNFe()
	{
		return configuracaoNFe;
	}

	/**
	 * @param configuracaoNFe the configuracaoNFe to set
	 */
	public void setConfiguracaoNFe(ConfiguracaoNFe configuracaoNFe)
	{
		this.configuracaoNFe = configuracaoNFe;
	}

	@Override
	public String toString() {
		return "ConfiguracaoNFeMaintenanceRequest [getConfiguracaoNFe()=" + getConfiguracaoNFe() + ", toString()=" + super.toString() + "]";
	}

}