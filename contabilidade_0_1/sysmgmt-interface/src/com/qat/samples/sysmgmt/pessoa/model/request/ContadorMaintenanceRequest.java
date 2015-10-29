package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.Contador;

public class ContadorMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Contador contador;

	/**
	 * The Constructor.
	 */
	public ContadorMaintenanceRequest()
	{

	}

	/**
	 * @return the contador
	 */
	public Contador getContador()
	{
		return contador;
	}

	/**
	 * @param contador the contador to set
	 */
	public void setContador(Contador contador)
	{
		this.contador = contador;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContadorMaintenanceRequest [getContador()=" + getContador() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}