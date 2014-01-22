package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNull;

/**
 * Criteria for Light address search.
 *
 * @author Thiago - QAT
 */
public class AddressCriteria
{

	/**
	 * Constructor.
	 */
	public AddressCriteria()
	{
	}

	/**
	 * Attributes.
	 */
	private String address;

	/** The city. */
	private String city;

	/** The state. */
	private String state;

	/** The county. */
	private String county;

	/** The zip. */
	private String zip;

	/** The latitude. */
	private String latitude;

	/** The longitude. */
	private String longitude;

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the state to set
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * Gets the county.
	 *
	 * @return the county
	 */
	public String getCounty()
	{
		return county;
	}

	/**
	 * Sets the county.
	 *
	 * @param county the county to set
	 */
	public void setCounty(String county)
	{
		this.county = county;
	}

	/**
	 * Gets the zip.
	 *
	 * @return the zip
	 */
	public String getZip()
	{
		return zip;
	}

	/**
	 * Sets the zip.
	 *
	 * @param zip the zip to set
	 */
	public void setZip(String zip)
	{
		this.zip = zip;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public String getLatitude()
	{
		return latitude;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public String getLongitude()
	{
		return longitude;
	}

	/**
	 * Sets the longitude.
	 *
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * Checks for filter.
	 *
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return !isNull(getAddress()) ||
				!isNull(getCity()) ||
				!isNull(getState()) ||
				!isNull(getCounty()) ||
				!isNull(getZip()) ||
				!isNull(getLatitude()) ||
				!isNull(getLongitude());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AddressCriteria [getAddress()=" + getAddress() + ", getCity()=" + getCity() + ", getState()="
				+ getState() + ", getCounty()=" + getCounty() + ", getZip()=" + getZip() + ", getLatitude()="
				+ getLatitude() + ", getLongitude()=" + getLongitude() + ", hasFilter()=" + hasFilter() + "]";
	}


}
