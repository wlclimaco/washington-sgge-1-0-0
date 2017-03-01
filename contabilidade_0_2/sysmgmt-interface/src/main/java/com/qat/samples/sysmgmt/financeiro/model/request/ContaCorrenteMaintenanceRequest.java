package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ContaCorrenteMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private ContaCorrente contaCorrente;

	/**
	 * The Constructor.
	 */
	public ContaCorrenteMaintenanceRequest()
	{

	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	@Override
	public String toString() {
		return "ContaCorrenteMaintenanceRequest [getContaCorrente()=" + getContaCorrente() + ", toString()="
				+ super.toString() + "]";
	}

	
}