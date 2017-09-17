package com.qat.samples.sysmgmt.advocacia.request;

import com.qat.samples.sysmgmt.advocacia.ProcessoCliente;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ProcessoClientesMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private ProcessoCliente processoCliente;

	/**
	 * The Constructor.
	 */
	public ProcessoClientesMaintenanceRequest()
	{

	}

	public ProcessoCliente getProcessoCliente() {
		return processoCliente;
	}

	public void setProcessoCliente(ProcessoCliente processoCliente) {
		this.processoCliente = processoCliente;
	}

	@Override
	public String toString() {
		return "ProcessoClienteMaintenanceRequest [getProcessoCliente()=" + getProcessoCliente() + ", toString()=" + super.toString() + "]";
	}





}