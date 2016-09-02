package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Tributacao;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class TributacaoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Tributacao tributacao;

	/**
	 * The Constructor.
	 */
	public TributacaoMaintenanceRequest()
	{

	}

	public Tributacao getTributacao() {
		return tributacao;
	}

	public void setTributacao(Tributacao tributacao) {
		this.tributacao = tributacao;
	}

	@Override
	public String toString() {
		return "TributacaoMaintenanceRequest [getTributacao()=" + getTributacao() + ", toString()=" + super.toString()
				+ "]";
	}

	
}