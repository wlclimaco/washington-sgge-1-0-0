package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Contas;
import com.qat.framework.model.request.MaintenanceRequest;

public class ContasMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Contas contas;

	/**
	 * The Constructor.
	 */
	public ContasMaintenanceRequest()
	{

	}

	/**
	 * @return the contas
	 */
	public Contas getContas()
	{
		return contas;
	}

	/**
	 * @param contas the contas to set
	 */
	public void setContas(Contas contas)
	{
		this.contas = contas;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContasMaintenanceRequest [getContas()=" + getContas() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}