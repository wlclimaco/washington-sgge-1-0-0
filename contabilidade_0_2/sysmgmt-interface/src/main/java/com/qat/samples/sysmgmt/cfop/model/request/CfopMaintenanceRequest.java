package com.qat.samples.sysmgmt.cfop.model.request;

import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CfopMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "CfopMaintenanceRequest [getCfop()=" + getCfop() + ", toString()=" + super.toString() + "]";
	}

}