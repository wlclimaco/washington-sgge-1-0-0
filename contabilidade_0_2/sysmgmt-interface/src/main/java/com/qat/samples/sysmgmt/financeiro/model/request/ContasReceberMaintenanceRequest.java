package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ContasReceberMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private ContasReceber financeiro;

	/**
	 * The Constructor.
	 */
	public ContasReceberMaintenanceRequest()
	{

	}

	/**
	 * @return the financeiro
	 */
	public ContasReceber getContasReceber()
	{
		return financeiro;
	}

	/**
	 * @param financeiro the financeiro to set
	 */
	public void setContasReceber(ContasReceber financeiro)
	{
		this.financeiro = financeiro;
	}

	@Override
	public String toString() {
		return "ContasReceberMaintenanceRequest [getContasReceber()=" + getContasReceber() + ", toString()=" + super.toString()
				+ "]";
	}
}