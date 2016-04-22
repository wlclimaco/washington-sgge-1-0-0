package com.qat.samples.sysmgmt.advocacia.request;

import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ProcessoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Processo processo;

	/**
	 * The Constructor.
	 */
	public ProcessoMaintenanceRequest()
	{

	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	@Override
	public String toString() {
		return "ProcessoMaintenanceRequest [getProcesso()=" + getProcesso() + ", toString()=" + super.toString() + "]";
	}





}