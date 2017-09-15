package com.qat.samples.sysmgmt.advocacia.request;

import com.qat.samples.sysmgmt.util.model.Compromisso;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CompromissoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Compromisso processo;

	/**
	 * The Constructor.
	 */
	public CompromissoMaintenanceRequest()
	{

	}

	public Compromisso getCompromisso() {
		return processo;
	}

	public void setCompromisso(Compromisso processo) {
		this.processo = processo;
	}

	@Override
	public String toString() {
		return "CompromissoMaintenanceRequest [getCompromisso()=" + getCompromisso() + ", toString()=" + super.toString() + "]";
	}





}