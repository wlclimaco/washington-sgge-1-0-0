package com.sensus.lc.light.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class LightSetupNotificationRequest.
 */
public class LightSetupNotificationRequest extends LightingControlRequest
{
	/** The light. */
	private Light light = new Light();

	/**
	 * Instantiates a new light setup notification request.
	 */
	public LightSetupNotificationRequest()
	{
	}

	/**
	 * Instantiates a new light setup notification request.
	 * 
	 * @param userContext the user context
	 */
	public LightSetupNotificationRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new light setup notification request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LightSetupNotificationRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Gets the light.
	 * 
	 * @return the light
	 */
	public Light getLight()
	{
		return light;
	}

	/**
	 * Sets the light.
	 * 
	 * @param light the new light
	 */
	public void setLight(Light light)
	{
		this.light = light;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LightSetupNotificationRequest [getLight()=" + getLight() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}
