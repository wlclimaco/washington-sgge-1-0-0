package com.sensus.mlc.ecomode.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class LightConsumption.
 */
@SuppressWarnings("serial")
public class LightConsumption extends SensusModel
{
	/** The day. */
	private Date day;

	private Double ecoMode;

	/** The consumption. */
	private Double consumption;

	/** The ecomode baseline. */
	private Double ecomodeBaseline;

	/** The light. */
	private Integer lightId;

	/**
	 * Instantiates a new light consumption.
	 */
	public LightConsumption()
	{
	}

	/**
	 * Instantiates a new light consumption.
	 * 
	 * @param dayTarget the day
	 * @param ecoModeTarget the ecomode
	 * @param consumptionTarget the consumption
	 * @param ecomodeBaselineTarget the ecomode baseline
	 * @param lightTarget the light
	 */
	public LightConsumption(
			Date dayTarget,
			Double ecoModeTarget,
			Double consumptionTarget,
			Double ecomodeBaselineTarget,
			Light lightTarget)
	{
		setDay(dayTarget);
		setEcoMode(ecoModeTarget);
		setConsumption(consumptionTarget);
		setEcomodeBaseline(ecomodeBaselineTarget);
		setLightId(lightTarget.getId());
	}

	/**
	 * Gets the day.
	 * 
	 * @return the day
	 */
	public Date getDay()
	{
		return this.day;
	}

	/**
	 * Sets the day.
	 * 
	 * @param day the new day
	 */
	public void setDay(Date day)
	{
		this.day = day;
	}

	/**
	 * Gets the ecomode.
	 * 
	 * @return the ecomode
	 */
	public Double getEcoMode()
	{
		return this.ecoMode;
	}

	/**
	 * Sets the ecomode.
	 * 
	 * @param ecoMode the new ecomode
	 */
	public void setEcoMode(Double ecoMode)
	{
		this.ecoMode = ecoMode;
	}

	/**
	 * Gets the consumption.
	 * 
	 * @return the consumption
	 */
	public Double getConsumption()
	{
		return this.consumption;
	}

	/**
	 * Sets the consumption.
	 * 
	 * @param consumption the new consumption
	 */
	public void setConsumption(Double consumption)
	{
		this.consumption = consumption;
	}

	/**
	 * Gets the ecomode baseline.
	 * 
	 * @return the ecomode baseline
	 */
	public Double getEcomodeBaseline()
	{
		return this.ecomodeBaseline;
	}

	/**
	 * Sets the ecomode baseline.
	 * 
	 * @param ecomodeBaseline the new ecomode baseline
	 */
	public void setEcomodeBaseline(Double ecomodeBaseline)
	{
		this.ecomodeBaseline = ecomodeBaseline;
	}

	/**
	 * Gets the light.
	 * 
	 * @return the light
	 */
	public Integer getLightId()
	{
		return this.lightId;
	}

	/**
	 * Sets the light.
	 * 
	 * @param light the new light
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	@Override
	public String toString()
	{
		return "LightConsumption [day=" + day + ", ecoMode=" + ecoMode + ", consumption=" + consumption
				+ ", ecomodeBaseline=" + ecomodeBaseline + ", lightId=" + lightId + "]";
	}
}
