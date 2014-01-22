package com.sensus.lc.light.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.lc.light.model.Light;

/**
 * The Class LightResponse.
 */
public class LightResponse extends Response
{

	/** The light. */
	private Light light;

	/** The light. */
	private List<Light> lights = new ArrayList<Light>();

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
	 * @param light the light to set
	 */
	public void setLight(Light light)
	{
		this.light = light;
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
	 * Sets the lights.
	 * 
	 * @param lights the new lights
	 */
	public void setLights(List<Light> lights)
	{
		this.lights = lights;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightResponse [getLight()=" + getLight() + ", getLights()=" + getLights() + ", toString()="
				+ super.toString() + "]";
	}

}
