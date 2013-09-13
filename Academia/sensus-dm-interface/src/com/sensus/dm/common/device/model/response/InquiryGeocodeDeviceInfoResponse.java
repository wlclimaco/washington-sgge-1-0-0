package com.sensus.dm.common.device.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;

/**
 * The Class InquiryGeocodeDeviceInfoResponse.
 * 
 * * @author - QAT Brazil
 */
public class InquiryGeocodeDeviceInfoResponse extends InquiryResponse
{

	/** The geocode device info. */
	private List<GeocodeDeviceInfo> geocodeDeviceInfo;

	/**
	 * Gets the geocode device info.
	 * 
	 * @return the geocode device info
	 */
	public List<GeocodeDeviceInfo> getGeocodeDeviceInfo()
	{
		return geocodeDeviceInfo;
	}

	/**
	 * Sets the geocode device info.
	 * 
	 * @param geocodeDeviceInfo the new geocode device info
	 */
	public void setGeocodeDeviceInfo(List<GeocodeDeviceInfo> geocodeDeviceInfo)
	{
		this.geocodeDeviceInfo = geocodeDeviceInfo;
	}

	/**
	 * Gets the first geocode device info.
	 * 
	 * @return the first geocode device info
	 */
	public GeocodeDeviceInfo getFirstGeocodeDeviceInfo()
	{
		List<GeocodeDeviceInfo> list = getGeocodeDeviceInfo();

		if ((list != null) && !list.isEmpty())
		{
			return list.get(0);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setGeocodeDeviceInfo(new ArrayList<GeocodeDeviceInfo>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryGeocodeDeviceInfoResponse [getGeocodeDeviceInfo()=" + getGeocodeDeviceInfo() + "toString()="
				+ super.toString() + "]";
	}
}
