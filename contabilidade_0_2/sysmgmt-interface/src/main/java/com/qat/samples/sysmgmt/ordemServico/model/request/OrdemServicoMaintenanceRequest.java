package com.qat.samples.sysmgmt.ordemServico.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class OrdemServicoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "OrdemServicoMaintenanceRequest [getOrdemServico()=" + getOrdemServico() + ", toString()="
				+ super.toString() + "]";
	}
}