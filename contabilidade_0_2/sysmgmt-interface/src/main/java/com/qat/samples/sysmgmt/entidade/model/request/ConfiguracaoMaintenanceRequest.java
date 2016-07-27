package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.Configuracao;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ConfiguracaoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Configuracao configuracao;

	/**
	 * The Constructor.
	 */
	public ConfiguracaoMaintenanceRequest()
	{

	}

	/**
	 * @return the configuracao
	 */
	public Configuracao getConfiguracao()
	{
		return configuracao;
	}

	/**
	 * @param configuracao the configuracao to set
	 */
	public void setConfiguracao(Configuracao configuracao)
	{
		this.configuracao = configuracao;
	}

	@Override
	public String toString() {
		return "ConfiguracaoMaintenanceRequest [getConfiguracao()=" + getConfiguracao() + ", toString()=" + super.toString() + "]";
	}

}