package com.qat.samples.sysmgmt.util.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.Cliente;

public class UsuarioMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Cliente cliente;

	/**
	 * The Constructor.
	 */
	public UsuarioMaintenanceRequest()
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