package com.qat.samples.sysmgmt.agencia.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.agencia.model.Agencia;

public class AgenciaMaintenanceRequest extends Request
{

	/** Attributes */
	private Agencia agencia;

	/**
	 * The Constructor.
	 */
	public AgenciaMaintenanceRequest()
	{

	}

	/**
	 * @return the agencia
	 */
	public Agencia getAgencia()
	{
		return agencia;
	}

	/**
	 * @param agencia the agencia to set
	 */
	public void setAgencia(Agencia agencia)
	{
		this.agencia = agencia;
	}

	@Override
	public String toString() {
		return "AgenciaMaintenanceRequest [getAgencia()=" + getAgencia() + ", toString()=" + super.toString() + "]";
	}

}