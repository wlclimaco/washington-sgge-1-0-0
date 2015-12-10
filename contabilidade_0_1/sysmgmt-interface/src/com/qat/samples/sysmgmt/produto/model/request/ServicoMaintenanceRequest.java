package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.Servico;

public class ServicoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Servico servico;

	/**
	 * The Constructor.
	 */
	public ServicoMaintenanceRequest()
	{

	}

	/**
	 * Gets the servico.
	 * 
	 * @return the servico
	 */
	public Servico getServico()
	{
		return servico;
	}

	/**
	 * Sets the servico.
	 * 
	 * @param servico the servico
	 */
	public void setServico(Servico servico)
	{
		this.servico = servico;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ServicoMaintenanceRequest [getServico()=" + getServico() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}