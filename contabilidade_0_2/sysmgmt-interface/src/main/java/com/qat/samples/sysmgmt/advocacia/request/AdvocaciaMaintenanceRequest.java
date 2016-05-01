package com.qat.samples.sysmgmt.advocacia.request;

import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class AdvocaciaMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Advocacia advocacia;

	/**
	 * The Constructor.
	 */
	public AdvocaciaMaintenanceRequest()
	{

	}

	public Advocacia getAdvocacia() {
		return advocacia;
	}

	public void setAdvocacia(Advocacia advocacia) {
		this.advocacia = advocacia;
	}

	@Override
	public String toString() {
		return "AdvocaciaMaintenanceRequest [getAdvocacia()=" + getAdvocacia() + ", toString()=" + super.toString()
				+ "]";
	}

}