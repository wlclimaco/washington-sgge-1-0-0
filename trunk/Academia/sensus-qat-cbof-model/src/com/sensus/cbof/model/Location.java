package com.sensus.cbof.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class Location.
 */
@SuppressWarnings("serial")
public class Location extends SensusModel
{

	/** The address. */
	private String address;

	/** The city. */
	private String city;

	/** The country. */
	private String country;

	/** The latitude. */
	private Double latitude;

	/** The longitude. */
	private Double longitude;

	/** The state. */
	private String state;

	private String county;

	/** The zip. */
	private String zip;

	/** The time zone info. */
	private TimeZoneInfo timeZoneInfo;

	/**
	 * Instantiates a new location.
	 */
	public Location()
	{
	}

	/**
	 * Instantiates a new location.
	 * 
	 * @param addressParam the address param
	 * @param cityParam the city param
	 * @param zipParam the zip param
	 */
	public Location(String addressParam, String cityParam, String zipParam)
	{
		setAddress(addressParam);
		setCity(cityParam);
		setZip(zipParam);
	}

	/**
	 * Instantiates a new location.
	 * 
	 * @param timeZoneInfoParam the time zone info param
	 */
	public Location(TimeZoneInfo timeZoneInfoParam)
	{
		setTimeZoneInfo(timeZoneInfoParam);
	}

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
	 * @param address the new address
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
	 * @param city the new city
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * Gets the country.
	 * 
	 * @return the country
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * Sets the country.
	 * 
	 * @param country the new country
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}

	/**
	 * Gets the latitude.
	 * 
	 * @return the latitude
	 */
	public Double getLatitude()
	{
		return latitude;
	}

	/**
	 * Sets the latitude.
	 * 
	 * @param latitude the new latitude
	 */
	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 * 
	 * @return the longitude
	 */
	public Double getLongitude()
	{
		return longitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param longitude the new longitude
	 */
	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
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
	 * @param state the new state
	 */
	public void setState(String state)
	{
		this.state = state;
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
	 * @param zip the new zip
	 */
	public void setZip(String zip)
	{
		this.zip = zip;
	}

	/**
	 * Gets the time zone info.
	 * 
	 * @return the time zone info
	 */
	public TimeZoneInfo getTimeZoneInfo()
	{
		return timeZoneInfo;
	}

	/**
	 * Sets the time zone info.
	 * 
	 * @param timeZoneInfo the new time zone info
	 */
	public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo)
	{
		this.timeZoneInfo = timeZoneInfo;
	}

	public String getCounty()
	{
		return county;
	}

	public void setCounty(String county)
	{
		this.county = county;
	}

	@Override
	public String toString()
	{
		return "Location [getAddress()=" + getAddress() + ", getCity()=" + getCity() + ", getCountry()=" + getCountry()
				+ ", getLatitude()=" + getLatitude() + ", getLongitude()=" + getLongitude() + ", getState()="
				+ getState() + ", getZip()=" + getZip() + ", getTimeZoneInfo()=" + getTimeZoneInfo() + ", getCounty()="
				+ getCounty() + "]";
	}

}
