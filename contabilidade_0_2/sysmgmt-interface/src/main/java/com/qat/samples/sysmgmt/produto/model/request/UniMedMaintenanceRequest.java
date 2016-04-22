package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class UniMedMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "UniMedMaintenanceRequest [getUniMed()=" + getUniMed() + ", toString()=" + super.toString() + "]";
	}

}