package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.Filial;

public class FilialMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FilialMaintenanceRequest [getFilial()=" + getFilial() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}