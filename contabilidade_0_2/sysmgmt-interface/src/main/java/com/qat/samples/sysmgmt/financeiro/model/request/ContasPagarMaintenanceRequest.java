package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ContasPagarMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private ContasPagar financeiro;

	/**
	 * The Constructor.
	 */
	public ContasPagarMaintenanceRequest()
	{

	}

	/**
	 * @return the financeiro
	 */
	public ContasPagar getContasPagar()
	{
		return financeiro;
	}

	/**
	 * @param financeiro the financeiro to set
	 */
	public void setContasPagar(ContasPagar financeiro)
	{
		this.financeiro = financeiro;
	}

	@Override
	public String toString() {
		return "ContasPagarMaintenanceRequest [getContasPagar()=" + getContasPagar() + ", toString()=" + super.toString()
				+ "]";
	}
}