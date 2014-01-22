package com.sensus.lc.analytics.model;

import java.util.Date;

/**
 * * The Model Object Analytics Group.
 * 
 * @author QAT Brazil
 */
@SuppressWarnings("serial")
public class AnalyticsGroupEcoMode extends AnalyticsGroup
{
	/** The date. */
	private Date date;

	/** The baseline consumption. */
	private Double baselineConsumption;

	/** The measured consumption. */
	private Double measuredConsumption;

	/** The total. */
	private Double ecoModePercent;

	/**
	 * Instantiates a new analytics group eco mode.
	 */
	public AnalyticsGroupEcoMode()
	{
	}

	/**
	 * Instantiates a new analytics group eco mode.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param baseline the baseline consumption
	 * @param measured the measured consumption
	 * @param ecoMode the eco mode percent
	 */
	public AnalyticsGroupEcoMode(Integer id, String name, Double baseline, Double measured, Double ecoMode)
	{
		super(id, name);
		setBaselineConsumption(baseline);
		setMeasuredConsumption(measured);
		setEcoModePercent(ecoMode);
	}

	/**
	 * Gets the baseline consumption.
	 * 
	 * @return the baseline consumption
	 */
	public Double getBaselineConsumption()
	{
		return this.baselineConsumption;
	}

	/**
	 * Sets the baseline consumption.
	 * 
	 * @param baselineConsumption the new baseline consumption
	 */
	public void setBaselineConsumption(Double baselineConsumption)
	{
		this.baselineConsumption = baselineConsumption;
	}

	/**
	 * Gets the measured consumption.
	 * 
	 * @return the measured consumption
	 */
	public Double getMeasuredConsumption()
	{
		return this.measuredConsumption;
	}

	/**
	 * Sets the measured consumption.
	 * 
	 * @param measuredConsumption the new measured consumption
	 */
	public void setMeasuredConsumption(Double measuredConsumption)
	{
		this.measuredConsumption = measuredConsumption;
	}

	/**
	 * Gets the eco mode percent.
	 * 
	 * @return the eco mode percent
	 */
	public Double getEcoModePercent()
	{
		return this.ecoModePercent;
	}

	/**
	 * Sets the eco mode percent.
	 * 
	 * @param ecoModePercent the new eco mode percent
	 */
	public void setEcoModePercent(Double ecoModePercent)
	{
		this.ecoModePercent = ecoModePercent;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate()
	{
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date the new date
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	@Override
	public String toString()
	{
		return "AnalyticsGroupEcoMode [getBaselineConsumption()=" + getBaselineConsumption()
				+ ", getMeasuredConsumption()=" + getMeasuredConsumption() + ", getEcoModePercent()="
				+ getEcoModePercent() + ", getDate()=" + getDate() + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getColumns()=" + getColumns() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
