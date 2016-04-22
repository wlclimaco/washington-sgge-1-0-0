package com.qat.samples.sysmgmt.estado.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class EstadoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Estado Estado;

	/**
	 * The Constructor.
	 */
	public EstadoMaintenanceRequest()
	{

	}

	/**
	 * @return the Estado
	 */
	public Estado getEstado()
	{
		return Estado;
	}

	/**
	 * @param Estado the Estado to set
	 */
	public void setEstado(Estado Estado)
	{
		this.Estado = Estado;
	}

	@Override
	public String toString() {
		return "EstadoMaintenanceRequest [getEstado()=" + getEstado() + ", toString()=" + super.toString() + "]";
	}

}