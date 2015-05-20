package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.OrdemServico;

public class OrdemServicoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private OrdemServico ordemServico;

	/**
	 * The Constructor.
	 */
	public OrdemServicoMaintenanceRequest()
	{

	}

	/**
	 * Gets the ordemServico.
	 *
	 * @return the ordemServico
	 */
	public OrdemServico getOrdemServico()
	{
		return ordemServico;
	}

	/**
	 * Sets the ordemServico.
	 *
	 * @param ordemServico the ordemServico
	 */
	public void setOrdemServico(OrdemServico ordemServico)
	{
		this.ordemServico = ordemServico;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrdemServicoMaintenanceRequest [getOrdemServico()=" + getOrdemServico() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}