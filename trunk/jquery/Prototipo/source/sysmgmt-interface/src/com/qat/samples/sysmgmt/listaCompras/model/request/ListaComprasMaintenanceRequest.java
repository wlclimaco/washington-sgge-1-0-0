package com.qat.samples.sysmgmt.listaCompras.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.listaCompras.model.ListaCompras;

/**
 * The Class ListaComprasMaintenanceRequest.
 */
public class ListaComprasMaintenanceRequest extends MaintenanceRequest
{

	/** The listaCompras. */
	@XmlElement(nillable = true)
	private ListaCompras listaCompras;

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
	 * Instantiates a new listaCompras maintenance request.
	 * 
	 * @param listaCompras the listaCompras
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public ListaComprasMaintenanceRequest(ListaCompras listaCompras, Boolean returnList, Boolean returnListPaged)
	{
		this.listaCompras = listaCompras;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new listaCompras maintenance request.
	 */
	public ListaComprasMaintenanceRequest()
	{
	}

	/**
	 * Gets the listaCompras.
	 * 
	 * @return the listaCompras
	 */
	public ListaCompras getListaCompras()
	{
		return listaCompras;
	}

	/**
	 * Sets the listaCompras.
	 * 
	 * @param listaCompras the new listaCompras
	 */
	public void setListaCompras(ListaCompras listaCompras)
	{
		this.listaCompras = listaCompras;
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
		return "ListaComprasMaintenanceRequest [getListaCompras()=" + getListaCompras() + ", getReturnList()="
				+ getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged() + ", toString()=" + super.toString() + "]";
	}

}
