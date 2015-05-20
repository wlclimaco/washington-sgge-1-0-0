package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Orcamento;

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