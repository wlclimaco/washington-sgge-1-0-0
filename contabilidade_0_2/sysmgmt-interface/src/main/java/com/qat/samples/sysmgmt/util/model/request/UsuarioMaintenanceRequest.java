package com.qat.samples.sysmgmt.util.model.request;

import com.qat.samples.sysmgmt.entidade.model.Usuario;

public class UsuarioMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Usuario cliente;

	/**
	 * The Constructor.
	 */
	public UsuarioMaintenanceRequest()
	{

	}

	/**
	 * @return the cliente
	 */
	public Usuario getUsuario()
	{
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setUsuario(Usuario cliente)
	{
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "UsuarioMaintenanceRequest [getUsuario()=" + getUsuario() + ", toString()=" + super.toString() + "]";
	}

}