package com.qat.samples.sysmgmt.clinica.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.clinica.model.Exame;

/**
 * The Class ExameMaintenanceRequest.
 */
public class ExameMaintenanceRequest extends Request
{

	/** The exame. */
	@XmlElement(nillable = true)
	private Exame exame;

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
	 * Instantiates a new exame maintenance request.
	 *
	 * @param exame the exame
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public ExameMaintenanceRequest(Exame exame, Boolean returnList, Boolean returnListPaged)
	{
		this.exame = exame;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new exame maintenance request.
	 */
	public ExameMaintenanceRequest()
	{
	}

	/**
	 * Gets the exame.
	 *
	 * @return the exame
	 */
	public Exame getExame()
	{
		return exame;
	}

	/**
	 * Sets the exame.
	 *
	 * @param exame the new exame
	 */
	public void setExame(Exame exame)
	{
		this.exame = exame;
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
		return "ExameMaintenanceRequest [getExame()=" + getExame() + ", getReturnList()=" + getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged()
				+ ", getUserContext()=" + getRequestContext() + "]";
	}

}
