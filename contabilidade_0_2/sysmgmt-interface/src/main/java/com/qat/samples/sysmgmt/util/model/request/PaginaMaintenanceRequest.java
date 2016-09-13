package com.qat.samples.sysmgmt.util.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.entidade.model.Pagina;

/**
 * The Model Object PaginaRequest.
 */
public class PaginaMaintenanceRequest extends Request
{

	/** The pagina. */
	@XmlElement(nillable = true)
	private Pagina pagina;

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
	 * Instantiates a new pagina maintenance request.
	 *
	 * @param pagina the pagina
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public PaginaMaintenanceRequest(Pagina pagina, Boolean returnList, Boolean returnListPaged)
	{
		this.pagina = pagina;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new pagina maintenance request.
	 */
	public PaginaMaintenanceRequest()
	{

	}

	/**
	 * Gets the pagina.
	 *
	 * @return the pagina
	 */
	public Pagina getPagina()
	{
		return pagina;
	}

	/**
	 * Sets the pagina.
	 *
	 * @param pagina the new pagina
	 */
	public void setPagina(Pagina pagina)
	{
		this.pagina = pagina;
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
		return "PaginaMaintenanceRequest [getPagina()=" + getPagina() + ", getReturnList()=" + getReturnList() + ", getReturnListPaged()=" + getReturnListPaged() + ", getUserContext()="
				+ getRequestContext() + "]";
	}

}