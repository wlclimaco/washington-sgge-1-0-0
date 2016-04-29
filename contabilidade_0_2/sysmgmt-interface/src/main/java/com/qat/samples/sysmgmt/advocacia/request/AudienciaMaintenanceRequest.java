package com.qat.samples.sysmgmt.advocacia.request;

import com.qat.samples.sysmgmt.advocacia.Audiencia;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class AudienciaMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Audiencia Audiencia;

	/**
	 * The Constructor.
	 */
	public AudienciaMaintenanceRequest()
	{

	}

	/**
	 * @return the Audiencia
	 */
	public Audiencia getAudiencia()
	{
		return Audiencia;
	}

	/**
	 * @param Audiencia the Audiencia to set
	 */
	public void setAudiencia(Audiencia Audiencia)
	{
		this.Audiencia = Audiencia;
	}

	@Override
	public String toString() {
		return "AudienciaMaintenanceRequest [getAudiencia()=" + getAudiencia() + ", toString()=" + super.toString() + "]";
	}




}