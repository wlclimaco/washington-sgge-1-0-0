package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class FormaPgMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private FormaPg formaPg;

	/**
	 * The Constructor.
	 */
	public FormaPgMaintenanceRequest()
	{

	}

	public FormaPg getFormaPg() {
		return formaPg;
	}

	public void setFormaPg(FormaPg formaPg) {
		this.formaPg = formaPg;
	}

	@Override
	public String toString() {
		return "FormaPgMaintenanceRequest [getFormaPg()=" + getFormaPg() + ", toString()=" + super.toString() + "]";
	}

	
}