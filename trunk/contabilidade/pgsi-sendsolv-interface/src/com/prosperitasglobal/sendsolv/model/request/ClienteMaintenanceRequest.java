package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Cliente;

public class ClienteMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ClienteMaintenanceRequest [getCliente()=" + getCliente() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}