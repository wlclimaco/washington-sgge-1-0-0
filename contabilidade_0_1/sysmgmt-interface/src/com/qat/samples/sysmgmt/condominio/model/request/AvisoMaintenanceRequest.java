package com.qat.samples.sysmgmt.advocacia.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.model.Aviso;

public class AvisoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Aviso Aviso;

	/**
	 * The Constructor.
	 */
	public AvisoMaintenanceRequest()
	{

	}

	/**
	 * @return the Aviso
	 */
	public Aviso getAviso()
	{
		return Aviso;
	}

	/**
	 * @param Aviso the Aviso to set
	 */
	public void setAviso(Aviso Aviso)
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