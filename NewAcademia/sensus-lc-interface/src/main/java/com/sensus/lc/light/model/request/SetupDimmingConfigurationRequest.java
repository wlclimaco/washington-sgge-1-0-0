package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.Request;
import com.sensus.lc.light.model.Light;

public class SetupDimmingConfigurationRequest extends Request
{
	/** Attributes. */
	private Light light;

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
}
