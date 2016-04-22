package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ClienteMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Cliente cliente;

	/**
	 * The Constructor.
	 */
	public ClienteMaintenanceRequest()
	{

	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente()
	{
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "ClienteMaintenanceRequest [getCliente()=" + getCliente() + ", toString()=" + super.toString() + "]";
	}

}