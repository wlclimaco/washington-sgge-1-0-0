package com.sensus.mlc.smartpoint.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.smartpoint.model.GeocodeSmartpointInfo;

/**
 * The Class InquiryGeocodeSmartpointInfoResponse.
 */
public class InquiryGeocodeSmartpointInfoResponse extends InquiryResponse
{

	/** The geocode smartpoint list. */
	private List<GeocodeSmartpointInfo> geocodeSmartpointList;

	/**
	 * Gets the geocode smartpoint list.
	 * 
	 * @return the geocode smartpoint list
	 */
	public List<GeocodeSmartpointInfo> getGeocodeSmartpointList()
	{
		return geocodeSmartpointList;
	}

	/**
	 * Sets the geocode smartpoint list.
	 * 
	 * @param geocodeSmartpointList the new geocode smartpoint list
	 */
	public void setGeocodeSmartpointList(List<GeocodeSmartpointInfo> geocodeSmartpointList)
	{
		this.geocodeSmartpointList = geocodeSmartpointList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryGeocodeSmartpointInfoResponse [geocodeSmartpointList=" + geocodeSmartpointList
				+ ", getGeocodeSmartpointList()=" + getGeocodeSmartpointList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", getResponseTime()="
				+ getResponseTime() + "]";
	}
}
