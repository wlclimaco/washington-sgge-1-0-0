package com.qat.samples.sysmgmt.cfop.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.cfop.Cfop;

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