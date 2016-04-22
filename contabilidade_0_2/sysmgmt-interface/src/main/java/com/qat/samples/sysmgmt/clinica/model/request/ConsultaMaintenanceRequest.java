package com.qat.samples.sysmgmt.clinica.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.clinica.model.Consulta;

/**
 * The Class ConsultaMaintenanceRequest.
 */
public class ConsultaMaintenanceRequest extends Request
{

	/** The consulta. */
	@XmlElement(nillable = true)
	private Consulta consulta;

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
	 * Instantiates a new consulta maintenance request.
	 *
	 * @param consulta the consulta
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public ConsultaMaintenanceRequest(Consulta consulta, Boolean returnList, Boolean returnListPaged)
	{
		this.consulta = consulta;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new consulta maintenance request.
	 */
	public ConsultaMaintenanceRequest()
	{
	}

	/**
	 * Gets the consulta.
	 *
	 * @return the consulta
	 */
	public Consulta getConsulta()
	{
		return consulta;
	}

	/**
	 * Sets the consulta.
	 *
	 * @param consulta the new consulta
	 */
	public void setConsulta(Consulta consulta)
	{
		this.consulta = consulta;
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
		return "ConsultaMaintenanceRequest [getConsulta()=" + getConsulta() + ", getReturnList()=" + getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged()
				+ ", getUserContext()=" + getRequestContext() + "]";
	}

}
