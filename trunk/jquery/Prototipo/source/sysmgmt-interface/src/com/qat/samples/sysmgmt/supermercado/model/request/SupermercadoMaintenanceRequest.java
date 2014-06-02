package com.qat.samples.sysmgmt.supermercado.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;

/**
 * The Class SupermercadoMaintenanceRequest.
 */
public class SupermercadoMaintenanceRequest extends MaintenanceRequest
{

	/** The supermercado. */
	@XmlElement(nillable = true)
	private Supermercado supermercado;

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
	 * Instantiates a new supermercado maintenance request.
	 * 
	 * @param supermercado the supermercado
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public SupermercadoMaintenanceRequest(Supermercado supermercado, Boolean returnList, Boolean returnListPaged)
	{
		this.supermercado = supermercado;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new supermercado maintenance request.
	 */
	public SupermercadoMaintenanceRequest()
	{
	}

	/**
	 * Gets the supermercado.
	 * 
	 * @return the supermercado
	 */
	public Supermercado getSupermercado()
	{
		return supermercado;
	}

	/**
	 * Sets the supermercado.
	 * 
	 * @param supermercado the new supermercado
	 */
	public void setSupermercado(Supermercado supermercado)
	{
		this.supermercado = supermercado;
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
		return "SupermercadoMaintenanceRequest [getSupermercado()=" + getSupermercado() + ", getReturnList()="
				+ getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged()
				+ ", getUserContext()=" + getUserContext() + "]";
	}

}
