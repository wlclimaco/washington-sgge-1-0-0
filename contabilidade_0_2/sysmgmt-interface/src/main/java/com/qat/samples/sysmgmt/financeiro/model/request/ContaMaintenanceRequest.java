package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ContaMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Conta conta;

	/**
	 * The Constructor.
	 */
	public ContaMaintenanceRequest()
	{

	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "ContaMaintenanceRequest [getConta()=" + getConta() + ", toString()=" + super.toString() + "]";
	}

	

	
}