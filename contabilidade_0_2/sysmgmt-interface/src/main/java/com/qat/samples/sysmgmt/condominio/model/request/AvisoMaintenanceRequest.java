package com.qat.samples.sysmgmt.condominio.model.request;

import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class AvisoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "AvisoMaintenanceRequest [getAviso()=" + getAviso() + ", toString()=" + super.toString() + "]";
	}

}