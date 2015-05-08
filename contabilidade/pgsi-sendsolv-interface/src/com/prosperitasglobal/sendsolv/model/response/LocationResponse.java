package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Location;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class LocationResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class LocationResponse extends InquiryResponse
{

	/** Attributes */
	private List<Location> locationList;

	/**
	 * The Constructor.
	 */
	public LocationResponse()
	{

	}

	/**
	 * Gets the location list.
	 *
	 * @return the location list
	 */
	public List<Location> getLocationList()
	{
		return locationList;
	}

	/**
	 * Sets the location list.
	 *
	 * @param locationList the location list
	 */
	public void setLocationList(List<Location> locationList)
	{
		this.locationList = locationList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setLocationList((List<Location>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getLocationList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}