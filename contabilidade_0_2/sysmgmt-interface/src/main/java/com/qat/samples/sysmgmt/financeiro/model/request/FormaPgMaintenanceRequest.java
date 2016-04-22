package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class FormaPgMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private FormaPg financeiro;

	/**
	 * The Constructor.
	 */
	public FormaPgMaintenanceRequest()
	{

	}

	/**
	 * @return the financeiro
	 */
	public FormaPg getFormaPg()
	{
		return financeiro;
	}

	/**
	 * @param financeiro the financeiro to set
	 */
	public void setFormaPg(FormaPg financeiro)
	{
		this.financeiro = financeiro;
	}

	@Override
	public String toString() {
		return "FormaPgMaintenanceRequest [getFormaPg()=" + getFormaPg() + ", toString()=" + super.toString()
				+ "]";
	}
}