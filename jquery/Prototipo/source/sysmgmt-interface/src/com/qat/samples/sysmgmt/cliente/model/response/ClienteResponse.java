package com.qat.samples.sysmgmt.cliente.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.cliente.model.Cliente;

/**
 * The Model Object ClienteResponse.
 */
public class ClienteResponse extends InquiryResponse
{

	/** The clientes. */
	@XmlElement(nillable = true)
	private List<Cliente> clientes;

	/**
	 * Gets the clientes.
	 * 
	 * @return the clientes
	 */
	public List<Cliente> getClientes()
	{
		return clientes;
	}

	/**
	 * Sets the clientes.
	 * 
	 * @param clientes the new clientes
	 */
	public void setClientes(List<Cliente> clientes)
	{
		this.clientes = clientes;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setClientes((List<Cliente>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "ClienteResponse [getClientes()=" + getClientes() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
