package com.sensus.lc.ecomode.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.Light;

/**
 * The Class EcoModeResponse.
 */
public class EcoModeResponse extends InquiryResponse
{
	/** The lights. */
	private List<Light> lights;

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
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setLights(new ArrayList<Light>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EcoModeResponse [getLights()=" + getLights() + ", toString()=" + super.toString() + "]";
	}
}
