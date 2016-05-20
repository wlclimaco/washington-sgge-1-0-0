package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ServicoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "ServicoMaintenanceRequest [getServico()=" + getServico() + ", toString()=" + super.toString() + "]";
	}
}