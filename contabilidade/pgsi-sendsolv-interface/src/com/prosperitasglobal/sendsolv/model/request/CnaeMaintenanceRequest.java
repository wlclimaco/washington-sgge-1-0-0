package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Cnae;
import com.qat.framework.model.request.MaintenanceRequest;

public class CnaeMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CnaeMaintenanceRequest [getCnae()=" + getCnae() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}