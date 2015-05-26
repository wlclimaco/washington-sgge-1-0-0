package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Cfop;
import com.qat.framework.model.request.MaintenanceRequest;

public class CfopMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Cfop cfop;

	/**
	 * The Constructor.
	 */
	public CfopMaintenanceRequest()
	{

	}

	/**
	 * @return the cfop
	 */
	public Cfop getCfop()
	{
		return cfop;
	}

	/**
	 * @param cfop the cfop to set
	 */
	public void setCfop(Cfop cfop)
	{
		this.cfop = cfop;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CfopMaintenanceRequest [getCfop()=" + getCfop() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}