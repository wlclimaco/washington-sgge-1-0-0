package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CaixaMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Caixa financeiro;

	/**
	 * The Constructor.
	 */
	public CaixaMaintenanceRequest()
	{

	}

	/**
	 * @return the financeiro
	 */
	public Caixa getCaixa()
	{
		return financeiro;
	}

	/**
	 * @param financeiro the financeiro to set
	 */
	public void setCaixa(Caixa financeiro)
	{
		this.financeiro = financeiro;
	}

	@Override
	public String toString() {
		return "CaixaMaintenanceRequest [getCaixa()=" + getCaixa() + ", toString()=" + super.toString()
				+ "]";
	}
}