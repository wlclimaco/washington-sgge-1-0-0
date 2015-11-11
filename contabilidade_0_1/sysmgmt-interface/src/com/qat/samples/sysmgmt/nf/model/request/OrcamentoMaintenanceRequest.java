package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.Orcamento;

public class OrcamentoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Orcamento orcamento;

	/**
	 * The Constructor.
	 */
	public OrcamentoMaintenanceRequest()
	{

	}

	/**
	 * @return the orcamento
	 */
	public Orcamento getOrcamento()
	{
		return orcamento;
	}

	/**
	 * @param orcamento the orcamento to set
	 */
	public void setOrcamento(Orcamento orcamento)
	{
		this.orcamento = orcamento;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrcamentoMaintenanceRequest [getOrcamento()=" + getOrcamento() + ", getUserContext()="
				+ getUserContext()
				+ "]";
	}

}