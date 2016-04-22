package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.produto.model.Servico;

public class ServicoMaintenanceRequest extends Request
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