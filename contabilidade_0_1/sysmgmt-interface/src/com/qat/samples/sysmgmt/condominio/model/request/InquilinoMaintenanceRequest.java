package com.qat.samples.sysmgmt.condominio.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;

public class InquilinoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Inquilino Inquilino;

	/**
	 * The Constructor.
	 */
	public InquilinoMaintenanceRequest()
	{

	}

	/**
	 * @return the Inquilino
	 */
	public Inquilino getInquilino()
	{
		return Inquilino;
	}

	/**
	 * @param Inquilino the Inquilino to set
	 */
	public void setInquilino(Inquilino Inquilino)
	{
		this.Inquilino = Inquilino;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquilinoMaintenanceRequest [getInquilino()=" + getInquilino() + ", getUserContext()="
				+ getUserContext()
				+ "]";
	}

}