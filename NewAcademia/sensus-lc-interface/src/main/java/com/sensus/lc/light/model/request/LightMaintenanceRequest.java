package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.criteria.ProcessCriteria;

/**
 * Used for Inserting, Updating and Deleting Light instances.
 */
public class LightMaintenanceRequest extends MaintenanceRequest
{

	/** The light. */
	private Light light; // Object being maintained
	private ProcessCriteria processCriteria;

	/**
	 * Instantiates a new light maintenance request.
	 */
	public LightMaintenanceRequest()
	{

	}

	/**
	 * Instantiates a new light maintenance request.
	 * 
	 * @param newLight the new light
	 */
	public LightMaintenanceRequest(Light newLight)
	{
		light = newLight;
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
	 * @param light The Light being maintained.
	 */
	public void setLight(Light light)
	{
		this.light = light;
	}

	/**
	 * @return the processCriteria
	 */
	public ProcessCriteria getProcessCriteria()
	{
		return processCriteria;
	}

	/**
	 * @param processCriteria the processCriteria to set
	 */
	public void setProcessCriteria(ProcessCriteria processCriteria)
	{
		this.processCriteria = processCriteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightMaintenanceRequest [getLight()=" + getLight() + ", toString()=" + super.toString() + "]";
	}

}
