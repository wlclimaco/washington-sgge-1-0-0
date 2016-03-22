package com.qat.samples.sysmgmt.condominio.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.Avisos;

public class AvisoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Avisos Aviso;

	/**
	 * The Constructor.
	 */
	public AvisoMaintenanceRequest()
	{

	}

	/**
	 * @return the Aviso
	 */
	public Avisos getAviso()
	{
		return Aviso;
	}

	/**
	 * @param Aviso the Aviso to set
	 */
	public void setAviso(Avisos Aviso)
	{
		this.Aviso = Aviso;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AvisoMaintenanceRequest [getAviso()=" + getAviso() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}