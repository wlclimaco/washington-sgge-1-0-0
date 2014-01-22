package com.sensus.lc.light.model.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.lc.light.model.Light;

/**
 *
 * Used to request that the collection of Lights encapsulated in this request be converted to a CSV file.
 *
 */
public class LightSummaryCSVRequest extends CSVRequest
{
	private List<Light> lights;

	/**
	 * Adds the lights.
	 *
	 * @param lightsParam the lights param
	 */
	public void addLights(Collection<Light> lightsParam)
	{
		getLights().addAll(lightsParam);
	}

	/**
	 * @return the lights
	 */
	public List<Light> getLights()
	{
		if (lights == null)
		{
			lights = new ArrayList<Light>();
		}

		return lights;
	}

	/**
	 * @param lights the lights to set
	 */
	public void setLights(List<Light> lights)
	{
		this.lights = lights;
	}

}