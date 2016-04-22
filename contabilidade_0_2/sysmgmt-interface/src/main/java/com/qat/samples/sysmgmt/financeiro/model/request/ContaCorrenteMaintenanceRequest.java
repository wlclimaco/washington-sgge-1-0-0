package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ContaCorrenteMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private ContaCorrente financeiro;

	/**
	 * The Constructor.
	 */
	public ContaCorrenteMaintenanceRequest()
	{

	}

	/**
	 * @return the financeiro
	 */
	public ContaCorrente getContaCorrente()
	{
		return financeiro;
	}

	/**
	 * @param financeiro the financeiro to set
	 */
	public void setContaCorrente(ContaCorrente financeiro)
	{
		this.financeiro = financeiro;
	}

	@Override
	public String toString() {
		return "ContaCorrenteMaintenanceRequest [getContaCorrente()=" + getContaCorrente() + ", toString()=" + super.toString()
				+ "]";
	}
}