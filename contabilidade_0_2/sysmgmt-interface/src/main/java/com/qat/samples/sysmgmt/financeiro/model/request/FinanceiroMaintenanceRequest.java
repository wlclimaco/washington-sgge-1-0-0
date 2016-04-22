package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.financeiro.model.Financeiro;

public class FinanceiroMaintenanceRequest extends Request
{

	/** Attributes. */
	private Financeiro financeiro;

	/**
	 * The Constructor.
	 */
	public FinanceiroMaintenanceRequest()
	{

	}

	/**
	 * @return the financeiro
	 */
	public Financeiro getFinanceiro()
	{
		return financeiro;
	}

	/**
	 * @param financeiro the financeiro to set
	 */
	public void setFinanceiro(Financeiro financeiro)
	{
		this.financeiro = financeiro;
	}

	@Override
	public String toString() {
		return "FinanceiroMaintenanceRequest [getFinanceiro()=" + getFinanceiro() + ", toString()=" + super.toString()
				+ "]";
	}
}