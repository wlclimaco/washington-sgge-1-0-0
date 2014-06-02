package com.qat.samples.sysmgmt.cliente.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.cliente.model.Cliente;

/**
 * The Class ClienteMaintenanceRequest.
 */
public class ClienteMaintenanceRequest extends MaintenanceRequest
{

	/** The cliente. */
	@XmlElement(nillable = true)
	private Cliente cliente;

	/**
	 * The return list.
	 * Indicate true to return a list of objects or false not to return any objects
	 * */
	@XmlElement(nillable = true)
	private Boolean returnList;

	/**
	 * The return list paged.
	 * Indicate true to return the list of objects paged or false to return all the objects at once
	 * you must set returnList to true for this to work
	 * */
	@XmlElement(nillable = true)
	private Boolean returnListPaged;

	/**
	 * Instantiates a new cliente maintenance request.
	 * 
	 * @param cliente the cliente
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public ClienteMaintenanceRequest(Cliente cliente, Boolean returnList, Boolean returnListPaged)
	{
		this.cliente = cliente;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new cliente maintenance request.
	 */
	public ClienteMaintenanceRequest()
	{
	}

	/**
	 * Gets the cliente.
	 * 
	 * @return the cliente
	 */
	public Cliente getCliente()
	{
		return cliente;
	}

	/**
	 * Sets the cliente.
	 * 
	 * @param cliente the new cliente
	 */
	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	/**
	 * Gets the return list.
	 * 
	 * @return the return list
	 */
	public Boolean getReturnList()
	{
		return (returnList == null) ? false : returnList;
	}

	/**
	 * Sets the return list.
	 * 
	 * @param returnList the new return list
	 */
	public void setReturnList(Boolean returnList)
	{
		this.returnList = returnList;
	}

	/**
	 * Gets the return list paged.
	 * 
	 * @return the return list paged
	 */
	public Boolean getReturnListPaged()
	{
		return (returnListPaged == null) ? false : returnListPaged;
	}

	/**
	 * Sets the return list paged.
	 * 
	 * @param returnListPaged the new return list paged
	 */
	public void setReturnListPaged(Boolean returnListPaged)
	{
		this.returnListPaged = returnListPaged;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ClienteMaintenanceRequest [getCliente()=" + getCliente() + ", getReturnList()=" + getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged()
				+ ", getUserContext()=" + getUserContext() + "]";
	}

}
