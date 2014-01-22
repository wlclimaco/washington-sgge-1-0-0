package com.sensus.lc.light.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * The Class LightConsumption.
 */
@SuppressWarnings("serial")
public class Consumption extends SensusModel
{

	/** The parent id. */
	private Integer parentId;

	/** The day. */
	private Date day;

	/** The eco mode. */
	private Double ecoMode;

	/** The consumption. */
	private Double consumption;

	/** The ecomode baseline. */
	private Double ecomodeBaseline;

	/**
	 * Instantiates a new light consumption.
	 */
	public Consumption()
	{
	}

	/**
	 * Instantiates a new consumption.
	 *
	 * @param dayTarget the day target
	 * @param ecoModeTarget the eco mode target
	 * @param consumptionTarget the consumption target
	 * @param ecomodeBaselineTarget the ecomode baseline target
	 */
	public Consumption(
			Date dayTarget,
			Double ecoModeTarget,
			Double consumptionTarget,
			Double ecomodeBaselineTarget)
	{
		setDay(dayTarget);
		setEcoMode(ecoModeTarget);
		setConsumption(consumptionTarget);
		setEcomodeBaseline(ecomodeBaselineTarget);
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public Date getDay()
	{
		return day;
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
		return ecoMode;
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
		return consumption;
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
		return ecomodeBaseline;
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
	 * Gets the parent id.
	 *
	 * @return the parentId
	 */
	public Integer getParentId()
	{
		return parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Consumption [getDay()=" + getDay() + ", getEcoMode()=" + getEcoMode() + ", getConsumption()="
				+ getConsumption() + ", getEcomodeBaseline()=" + getEcomodeBaseline() + ", getParentId()="
				+ getParentId() + ", toString()=" + super.toString() + "]";
	}
}
