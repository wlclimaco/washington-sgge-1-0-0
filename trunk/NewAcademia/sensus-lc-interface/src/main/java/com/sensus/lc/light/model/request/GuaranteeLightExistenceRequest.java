package com.sensus.lc.light.model.request;

/**
 * The Class SetLightIntensityRequest.
 * 
 * @author - Gustavo Peres - QAT Brazil
 */

public class GuaranteeLightExistenceRequest extends LightRequest
{

	/** The latitude. */
	private Double latitude;

	/** The longitude. */
	private Double longitude;

	/**
	 * Instantiates a new guarantee light existence request.
	 */
	public GuaranteeLightExistenceRequest()
	{

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

	@Override
	public String toString()
	{
		return "GuaranteeLightExistenceRequest [getLatitude()=" + getLatitude() + ", getLongitude()=" + getLongitude()
				+ "]";
	}

}