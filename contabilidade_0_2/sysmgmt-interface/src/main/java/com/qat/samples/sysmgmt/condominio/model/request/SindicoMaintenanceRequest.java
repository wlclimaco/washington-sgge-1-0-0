package com.qat.samples.sysmgmt.condominio.model.request;

import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class SindicoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Sindico Sindico;

	/**
	 * The Constructor.
	 */
	public SindicoMaintenanceRequest()
	{

	}

	/**
	 * @return the Sindico
	 */
	public Sindico getSindico()
	{
		return Sindico;
	}

	/**
	 * @param Sindico the Sindico to set
	 */
	public void setSindico(Sindico Sindico)
	{
		this.Sindico = Sindico;
	}

	@Override
	public String toString() {
		return "SindicoMaintenanceRequest [getSindico()=" + getSindico() + ", toString()=" + super.toString() + "]";
	}

}