package com.sensus.mlc.smartpoint.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class SetLightIntensityRequest.
 * 
 * @author - Alex Barros - QAT Omaha
 */
public class ProcessLightsRequest extends LightingControlRequest
{

	/** The lights. */
	private List<Light> lights;

	public ProcessLightsRequest()
	{
		setLights(new ArrayList<Light>());
	}

	/**
	 * Instantiates a new process lights request.
	 * 
	 * @param userContext the user context
	 */
	public ProcessLightsRequest(UserContext userContext)
	{
		super(userContext);
		setLights(new ArrayList<Light>());
	}

	/**
	 * Instantiates a new process lights request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public ProcessLightsRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
		setLights(new ArrayList<Light>());
	}

	/**
	 * Sets the lights.
	 * 
	 * @param lights the new lights
	 */
	public void setLights(List<Light> lights)
	{
		this.lights = lights;
	}

	/**
	 * Gets the lights.
	 * 
	 * @return the lights
	 */
	public List<Light> getLights()
	{
		return lights;
	}

	/**
	 * Get the first light in the List.
	 * 
	 * @return the light
	 */
	public Light getFirstLight()
	{
		if (!getLights().isEmpty())
		{
			return getLights().get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessLightsRequest [getLights()=" + getLights() + ", getFirstLight()=" + getFirstLight()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}

}