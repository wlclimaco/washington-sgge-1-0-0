package com.sensus.lc.light.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.GeocodeLightInfo;

/**
 * The Class GeocodeLightInfoResponse.
 */
public class GeocodeLightInfoResponse extends InquiryResponse
{

	/** The geocode light list. */
	private List<GeocodeLightInfo> geocodeLightList;

	/**
	 * Gets the geocode light list.
	 *
	 * @return the geocode light list
	 */
	public List<GeocodeLightInfo> getGeocodeLightList()
	{
		return geocodeLightList;
	}

	/**
	 * Sets the geocode light list.
	 *
	 * @param geocodeLightList the new geocode light list
	 */
	public void setGeocodeLightList(List<GeocodeLightInfo> geocodeLightList)
	{
		this.geocodeLightList = geocodeLightList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setGeocodeLightList(new ArrayList<GeocodeLightInfo>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryGeocodeLightInfoResponse [getGeocodeLightList()=" + getGeocodeLightList()
				+ ", toString()=" + super.toString() + "]";
	}
}
