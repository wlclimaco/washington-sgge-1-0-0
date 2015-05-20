package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Financeiro;

public class FinanceiroMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProdutoMaintenanceRequest [getFinanceiro()=" + getFinanceiro() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}