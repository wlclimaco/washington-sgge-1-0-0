package com.qat.samples.sysmgmt.agencia.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.agencia.Agencia;

public class AgenciaMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AgenciaMaintenanceRequest [getAgencia()=" + getAgencia() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}