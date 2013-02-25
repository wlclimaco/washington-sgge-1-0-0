package com.sensus.mlc.cliente.model.request;

import com.sensus.mlc.cliente.model.Cliente;
import com.sensus.mlc.tenant.model.request.TenantRequest;



/**
 * The Model Object Action.
 * 
 * @author - QAT Brazil
 */
public class ClienteRequest extends TenantRequest
{

	/** The action. */
	private Cliente cliente;

	/** The parent retry process. */
	private Integer parentRetry;

	/**
	 * @return the parentRetry
	 */
	public Integer getParentRetry()
	{
		return parentRetry;
	}

	/**
	 * @param parentRetry the parentRetry to set
	 */
	public void setParentRetry(Integer parentRetry)
	{
		this.parentRetry = parentRetry;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
