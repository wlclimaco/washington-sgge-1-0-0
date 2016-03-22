package com.qat.samples.sysmgmt.condominio.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;

public class CondominioMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CondominioMaintenanceRequest [getCondominio()=" + getCondominio() + ", getUserContext()="
				+ getUserContext()
				+ "]";
	}

}