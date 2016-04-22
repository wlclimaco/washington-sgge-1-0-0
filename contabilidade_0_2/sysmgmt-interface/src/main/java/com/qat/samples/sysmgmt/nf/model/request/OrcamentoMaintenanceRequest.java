package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class OrcamentoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "OrcamentoMaintenanceRequest [getOrcamento()=" + getOrcamento() + ", toString()=" + super.toString()
				+ "]";
	}

}