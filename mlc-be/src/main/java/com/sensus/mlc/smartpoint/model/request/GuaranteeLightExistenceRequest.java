package com.sensus.mlc.smartpoint.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class SetLightIntensityRequest.
 * 
 * @author - Alex Barros - QAT Omaha
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
	 * Instantiates a new guarantee light existence request.
	 * 
	 * @param userContext the user context
	 */
	public GuaranteeLightExistenceRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new guarantee light existence request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public GuaranteeLightExistenceRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
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