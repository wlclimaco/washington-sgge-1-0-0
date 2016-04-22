package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class FilialMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Filial filial;

	/**
	 * The Constructor.
	 */
	public FilialMaintenanceRequest()
	{

	}

	/**
	 * @return the filial
	 */
	public Filial getFilial()
	{
		return filial;
	}

	/**
	 * @param filial the filial to set
	 */
	public void setFilial(Filial filial)
	{
		this.filial = filial;
	}

	@Override
	public String toString() {
		return "FilialMaintenanceRequest [getFilial()=" + getFilial() + ", toString()=" + super.toString() + "]";
	}

}