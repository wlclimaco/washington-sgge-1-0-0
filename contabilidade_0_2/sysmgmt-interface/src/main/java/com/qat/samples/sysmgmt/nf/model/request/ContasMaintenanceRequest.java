package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.nf.model.Contas;

public class ContasMaintenanceRequest extends Request
{

	/** Attributes */
	private Contas contas;

	/**
	 * The Constructor.
	 */
	public ContasMaintenanceRequest()
	{

	}

	/**
	 * @return the contas
	 */
	public Contas getContas()
	{
		return contas;
	}

	/**
	 * @param contas the contas to set
	 */
	public void setContas(Contas contas)
	{
		this.contas = contas;
	}

	@Override
	public String toString() {
		return "ContasMaintenanceRequest [getContas()=" + getContas() + ", toString()=" + super.toString() + "]";
	}

}