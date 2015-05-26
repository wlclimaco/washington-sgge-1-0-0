package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.UniMed;
import com.qat.framework.model.request.MaintenanceRequest;

public class UniMedMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private UniMed uniMed;

	/**
	 * The Constructor.
	 */
	public UniMedMaintenanceRequest()
	{

	}

	/**
	 * @return the uniMed
	 */
	public UniMed getUniMed()
	{
		return uniMed;
	}

	/**
	 * @param uniMed the uniMed to set
	 */
	public void setUniMed(UniMed uniMed)
	{
		this.uniMed = uniMed;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UniMedMaintenanceRequest [getUniMed()=" + getUniMed() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}