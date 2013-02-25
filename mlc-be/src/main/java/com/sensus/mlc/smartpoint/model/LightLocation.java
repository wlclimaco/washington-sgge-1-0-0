package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.base.model.TimeZoneInfo;

/**
 * Model object that contains light location properties.
 * 
 * @see Light for more details about the light object and its relationships.
 * 
 * @author Thiago Silva - QAT
 * 
 */
@SuppressWarnings("serial")
public class LightLocation extends SensusModel
{

	/**
	 * Attributes.
	 */
	private Integer lightId;

	/** The time zone. */
	private String timeZone;

	/** The time zone info. */
	private TimeZoneInfo timeZoneInfo;

	/** The latitude. */
	private Double latitude;

	/** The longitude. */
	private Double longitude;

	/** The street name. */
	private String streetName;

	/** The city name. */
	private String cityName;

	/** The county name. */
	private String countyName;

	/** The state name. */
	private String stateName;

	/** The zip code. */
	private String zipCode;

	/**
	 * Instantiates a new light location.
	 */
	public LightLocation()
	{
	}

	/**
	 * Instantiates a new light location.
	 * 
	 * @param lat the lat
	 * @param lng the lng
	 */
	public LightLocation(Double lat, Double lng)
	{
		setLatitude(lat);
		setLongitude(lng);
	}

	/**
	 * Instantiates a new light location.
	 * 
	 * @param street the street
	 * @param city the city
	 * @param county the county
	 * @param state the state
	 * @param zipcode the zipcode
	 */
	public LightLocation(String street, String city, String county, String state, String zipcode)
	{
		setStreetName(street);
		setCityName(city);
		setCountyName(county);
		setStateName(state);
		setZipCode(zipcode);
	}

	/**
	 * Gets the time zone info.
	 * 
	 * @return the timeZoneInfo
	 */
	public TimeZoneInfo getTimeZoneInfo()
	{
		return this.timeZoneInfo;
	}

	/**
	 * Sets the time zone info.
	 * 
	 * @param timeZoneInfo the timeZoneInfo to set
	 */
	public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo)
	{
		this.timeZoneInfo = timeZoneInfo;
	}

	/**
	 * Gets the light id.
	 * 
	 * @return the lightId
	 */
	public Integer getLightId()
	{
		return this.lightId;
	}

	/**
	 * Sets the light id.
	 * 
	 * @param lightId the lightId to set
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * Gets the time zone.
	 * 
	 * @return the timeZone
	 */
	public String getTimeZone()
	{
		return this.timeZone;
	}

	/**
	 * Sets the time zone.
	 * 
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone)
	{
		this.timeZone = timeZone;
	}

	/**
	 * Gets the latitude.
	 * 
	 * @return the latitude
	 */
	public Double getLatitude()
	{
		return this.latitude;
	}

	/**
	 * Sets the latitude.
	 * 
	 * @param latitude the latitude to set
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
		return this.longitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * Gets the street name.
	 * 
	 * @return the streetName
	 */
	public String getStreetName()
	{
		return this.streetName;
	}

	/**
	 * Sets the street name.
	 * 
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	/**
	 * Gets the city name.
	 * 
	 * @return the cityName
	 */
	public String getCityName()
	{
		return this.cityName;
	}

	/**
	 * Sets the city name.
	 * 
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	/**
	 * Gets the county name.
	 * 
	 * @return the countyName
	 */
	public String getCountyName()
	{
		return this.countyName;
	}

	/**
	 * Sets the county name.
	 * 
	 * @param countyName the countyName to set
	 */
	public void setCountyName(String countyName)
	{
		this.countyName = countyName;
	}

	/**
	 * Gets the state name.
	 * 
	 * @return the stateName
	 */
	public String getStateName()
	{
		return this.stateName;
	}

	/**
	 * Sets the state name.
	 * 
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	/**
	 * Gets the zip code.
	 * 
	 * @return the zipCode
	 */
	public String getZipCode()
	{
		return this.zipCode;
	}

	/**
	 * Sets the zip code.
	 * 
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

}
