package com.sensus.mlc.cliente.model.reponse;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.cliente.model.Cliente;

/**
 * The Class DashBoardResponse.
 *
 * @author QAT Brazil.
 */
public class ClienteResponse extends Response
{
	/** The actions. */
	private List<Cliente> clientes;

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}