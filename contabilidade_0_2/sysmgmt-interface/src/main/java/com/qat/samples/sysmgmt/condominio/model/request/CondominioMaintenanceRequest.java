package com.qat.samples.sysmgmt.condominio.model.request;

import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CondominioMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Condominio Condominio;

	/**
	 * The Constructor.
	 */
	public CondominioMaintenanceRequest()
	{

	}

	/**
	 * @return the Condominio
	 */
	public Condominio getCondominio()
	{
		return Condominio;
	}

	/**
	 * @param Condominio the Condominio to set
	 */
	public void setCondominio(Condominio Condominio)
	{
		this.Condominio = Condominio;
	}

	@Override
	public String toString() {
		return "CondominioMaintenanceRequest [getCondominio()=" + getCondominio() + ", toString()=" + super.toString()
				+ "]";
	}

}