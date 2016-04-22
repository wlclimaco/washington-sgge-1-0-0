package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.pessoa.model.Contador;

public class ContadorMaintenanceRequest extends Request
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

	@Override
	public String toString() {
		return "ContadorMaintenanceRequest [getContador()=" + getContador() + ", toString()=" + super.toString() + "]";
	}

}