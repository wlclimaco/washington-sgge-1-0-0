package com.sensus.mlc.smartpoint.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class AlarmNotificationRequest.
 * 
 * @author - Alex Barros - QAT Omaha
 */
public class ChannelSetupAuditRequest extends LightingControlRequest
{
	/** The light. */
	private Light light = new Light();

	/** The light status enum. */
	private LightStatusEnum lightStatusEnum;

	/**
	 * Instantiates a new channel setup audit request.
	 */
	public ChannelSetupAuditRequest()
	{
	}

	/**
	 * Instantiates a new channel setup audit request.
	 * 
	 * @param userContext the user context
	 */
	public ChannelSetupAuditRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new channel setup audit request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public ChannelSetupAuditRequest(UserContext userContext, Tenant tenant)
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

	/**
	 * Gets the light status enum.
	 * 
	 * @return the light status enum
	 */
	public LightStatusEnum getLightStatusEnum()
	{
		return lightStatusEnum;
	}

	/**
	 * Sets the light status enum.
	 * 
	 * @param lightStatusEnum the new light status enum
	 */
	public void setLightStatusEnum(LightStatusEnum lightStatusEnum)
	{
		this.lightStatusEnum = lightStatusEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "ChannelSetupAuditRequest [getLight()=" + getLight() + ", getLightStatusEnum()=" + getLightStatusEnum()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}

}