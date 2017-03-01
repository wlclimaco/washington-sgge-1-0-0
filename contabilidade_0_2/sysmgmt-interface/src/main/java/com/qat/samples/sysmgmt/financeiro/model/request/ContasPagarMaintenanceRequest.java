package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ContasPagarMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private ContasPagar contasPagar;

	/**
	 * The Constructor.
	 */
	public ContasPagarMaintenanceRequest()
	{

	}

	public ContasPagar getContasPagar() {
		return contasPagar;
	}

	public void setContasPagar(ContasPagar contasPagar) {
		this.contasPagar = contasPagar;
	}

	@Override
	public String toString() {
		return "ContasPagarMaintenanceRequest [getContasPagar()=" + getContasPagar() + ", toString()="
				+ super.toString() + "]";
	}

}