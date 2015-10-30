package com.qat.samples.sysmgm.notafiscal.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.model.Bundle;

// TODO: Auto-generated Javadoc
/**
 * The Class BundleMaintenanceRequest.
 */
public class BundleMaintenanceRequest extends MaintenanceRequest
{

	/** The bundle. */
	@XmlElement(nillable = true)
	private Bundle bundle;

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
	 * Instantiates a new bundle maintenance request.
	 * 
	 * @param bundle the bundle
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public BundleMaintenanceRequest(Bundle bundle, Boolean returnList, Boolean returnListPaged)
	{
		this.bundle = bundle;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new bundle maintenance request.
	 */
	public BundleMaintenanceRequest()
	{
	}

	/**
	 * Gets the bundle.
	 * 
	 * @return the bundle
	 */
	public Bundle getBundle()
	{
		return bundle;
	}

	/**
	 * Sets the bundle.
	 * 
	 * @param bundle the new bundle
	 */
	public void setBundle(Bundle bundle)
	{
		this.bundle = bundle;
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
		return "BundleMaintenanceRequest [getBundle()=" + getBundle() + ", getReturnList()=" + getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged() + ", toString()=" + super.toString() + "]";
	}

}
