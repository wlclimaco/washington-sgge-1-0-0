package com.sensus.mlc.smartpoint.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class AlarmNotificationRequest.
 * 
 * @author - Alex Barros - QAT Omaha
 */

public class LightStatusNotificationRequest extends AlarmNotificationRequest
{

	/** The properties. */
	private List<LightParameter> lightParameters = new ArrayList<LightParameter>();

	/**
	 * Instantiates a new light status notification request.
	 */
	public LightStatusNotificationRequest()
	{
	}

	/**
	 * Instantiates a new light status notification request.
	 * 
	 * @param userContext the user context
	 */
	public LightStatusNotificationRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new light status notification request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LightStatusNotificationRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Sets the light parameters.
	 * 
	 * @param lightParameters the new light parameters
	 */
	public void setLightParameters(List<LightParameter> lightParameters)
	{
		this.lightParameters = lightParameters;
	}

	/**
	 * Gets the light parameters.
	 * 
	 * @return the light parameters
	 */
	public List<LightParameter> getLightParameters()
	{
		return lightParameters;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LightStatusNotificationRequest [getLightParameters()=" + getLightParameters()
				+ ", getOperationalData()=" + getOperationalData() + ", getStatusException()=" + getStatusException()
				+ ", getLight()=" + getLight() + ", getLightStatusEnum()=" + getLightStatusEnum() + ", getTenant()="
				+ getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}

}