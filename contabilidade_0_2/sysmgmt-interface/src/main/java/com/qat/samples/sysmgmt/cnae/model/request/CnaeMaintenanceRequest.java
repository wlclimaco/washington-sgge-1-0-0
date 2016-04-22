package com.qat.samples.sysmgmt.cnae.model.request;

import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CnaeMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Cnae cnae;

	/**
	 * The Constructor.
	 */
	public CnaeMaintenanceRequest()
	{

	}

	/**
	 * @return the cnae
	 */
	public Cnae getCnae()
	{
		return cnae;
	}

	/**
	 * @param cnae the cnae to set
	 */
	public void setCnae(Cnae cnae)
	{
		this.cnae = cnae;
	}

	@Override
	public String toString() {
		return "CnaeMaintenanceRequest [getCnae()=" + getCnae() + ", toString()=" + super.toString() + "]";
	}

}