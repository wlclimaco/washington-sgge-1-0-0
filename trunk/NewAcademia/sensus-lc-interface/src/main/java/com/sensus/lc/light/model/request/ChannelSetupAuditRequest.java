package com.sensus.lc.light.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.tenant.model.Tenant;

/**
 * Request object for ChannelSetupAuditRequest.
 * 
 * @author Thiago - QAT
 */
public class ChannelSetupAuditRequest extends LightingControlRequest
{

	/** The light. */
	private Light light = new Light();

	/** The life cycle state. */
	private LifeCycleStateEnum lifeCycleState;

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
	 * @return the lifeCycleState
	 */
	public LifeCycleStateEnum getLifeCycleState()
	{
		return lifeCycleState;
	}

	/**
	 * @param lifeCycleState the lifeCycleState to set
	 */
	public void setLifeCycleState(LifeCycleStateEnum lifeCycleState)
	{
		this.lifeCycleState = lifeCycleState;
	}

}