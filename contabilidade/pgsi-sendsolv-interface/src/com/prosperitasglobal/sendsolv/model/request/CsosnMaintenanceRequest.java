package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Csosn;
import com.qat.framework.model.request.MaintenanceRequest;

public class CsosnMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Csosn csosn;

	/**
	 * The Constructor.
	 */
	public CsosnMaintenanceRequest()
	{

	}

	/**
	 * @return the csosn
	 */
	public Csosn getCsosn()
	{
		return csosn;
	}

	/**
	 * @param csosn the csosn to set
	 */
	public void setCsosn(Csosn csosn)
	{
		this.csosn = csosn;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CsosnMaintenanceRequest [getCsosn()=" + getCsosn() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}