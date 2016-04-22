package com.qat.samples.sysmgmt.util.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.model.County;

/**
 * The Class CountyMaintenanceRequest.
 */
public class UtilMaintenanceRequest extends Request
{

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
	 * Instantiates a new county maintenance request.
	 *
	 * @param county the county
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public UtilMaintenanceRequest(County county, Boolean returnList, Boolean returnListPaged)
	{
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new county maintenance request.
	 */
	public UtilMaintenanceRequest()
	{
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
		return "CountyMaintenanceRequest [getReturnList()=" + getReturnList() + ", getReturnListPaged()=" + getReturnListPaged()
				+ ", getUserContext()=" + getRequestContext() + "]";
	}

}
