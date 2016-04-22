package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CondPagMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private CondPag financeiro;

	/**
	 * The Constructor.
	 */
	public CondPagMaintenanceRequest()
	{

	}

	/**
	 * @return the financeiro
	 */
	public CondPag getCondPag()
	{
		return financeiro;
	}

	/**
	 * @param financeiro the financeiro to set
	 */
	public void setCondPag(CondPag financeiro)
	{
		this.financeiro = financeiro;
	}

	@Override
	public String toString() {
		return "CondPagMaintenanceRequest [getCondPag()=" + getCondPag() + ", toString()=" + super.toString()
				+ "]";
	}
}