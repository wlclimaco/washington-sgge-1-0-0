package com.sensus.mlc.wui.base.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class LightJsonResult.
 */
@SuppressWarnings("serial")
public class LightJsonResult extends JsonResult
{

	/** The list light. */
	private List<Light> listLight = new ArrayList<Light>();

	/**
	 * Gets the list light.
	 * 
	 * @return the listLight
	 */
	public List<Light> getListLight()
	{
		return listLight;
	}

	/**
	 * Sets the list light.
	 * 
	 * @param listLight the new list light
	 */
	public void setListLight(List<Light> listLight)
	{
		this.listLight = listLight;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightJsonResult [getListLight()=" + getListLight() + "]";
	}

}
