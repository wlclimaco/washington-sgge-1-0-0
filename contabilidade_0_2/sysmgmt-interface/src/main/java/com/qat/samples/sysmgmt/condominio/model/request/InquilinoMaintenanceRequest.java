package com.qat.samples.sysmgmt.condominio.model.request;

import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class InquilinoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "InquilinoMaintenanceRequest [getInquilino()=" + getInquilino() + ", toString()=" + super.toString()
				+ "]";
	}

}