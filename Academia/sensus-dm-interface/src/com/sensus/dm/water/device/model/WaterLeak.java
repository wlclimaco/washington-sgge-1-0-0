package com.sensus.dm.water.device.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class WaterLeak.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class WaterLeak extends SensusModel
{

	/** The water meter. */
	private WaterMeter waterMeter;

	/** The leak time. */
	private String leakTime;

	/** The recent consumption. */
	private String recentConsumption;

	/** The recent consumption percentage. */
	private String recentConsumptionPercentage;

	/** The prior consumption. */
	private String priorConsumption;

	/** The prior consumption percentage. */
	private String priorConsumptionPercentage;

	/** The daily consumption average. */
	private String dailyConsumptionAverage;

	/**
	 * Instantiates a new water leak.
	 */
	public WaterLeak()
	{

	}

	/**
	 * Instantiates a new water leak.
	 * 
	 * @param waterMeterParam the water meter param
	 * @param leakTimeParam the leak time param
	 * @param recentConsumptionParam the recent consumption param
	 * @param recentConsumptionPercentageParam the recent consumption percentage param
	 * @param priorConsumptionParam the prior consumption param
	 * @param priorConsumptionPercentageParam the prior consumption percentage param
	 * @param dailyConsumptionAverageParam the daily consumption average param
	 */
	public WaterLeak(WaterMeter waterMeterParam, String leakTimeParam,
			String recentConsumptionParam,
			String recentConsumptionPercentageParam, String priorConsumptionParam,
			String priorConsumptionPercentageParam,
			String dailyConsumptionAverageParam)
	{
		super();
		setWaterMeter(waterMeterParam);
		setLeakTime(leakTimeParam);
		setRecentConsumption(recentConsumptionParam);
		setRecentConsumptionPercentage(recentConsumptionPercentageParam);
		setPriorConsumption(priorConsumptionParam);
		setPriorConsumptionPercentage(priorConsumptionPercentageParam);
		setDailyConsumptionAverage(dailyConsumptionAverageParam);
	}

	/**
	 * Gets the water meter.
	 * 
	 * @return the water meter
	 */
	public WaterMeter getWaterMeter()
	{
		return waterMeter;
	}

	/**
	 * Sets the water meter.
	 * 
	 * @param waterMeter the new water meter
	 */
	public void setWaterMeter(WaterMeter waterMeter)
	{
		this.waterMeter = waterMeter;
	}

	/**
	 * Gets the leak time.
	 * 
	 * @return the leak time
	 */
	public String getLeakTime()
	{
		return leakTime;
	}

	/**
	 * Sets the leak time.
	 * 
	 * @param leakTime the new leak time
	 */
	public void setLeakTime(String leakTime)
	{
		this.leakTime = leakTime;
	}

	/**
	 * Gets the recent consumption.
	 * 
	 * @return the recent consumption
	 */
	public String getRecentConsumption()
	{
		return recentConsumption;
	}

	/**
	 * Sets the recent consumption.
	 * 
	 * @param recentConsumption the new recent consumption
	 */
	public void setRecentConsumption(String recentConsumption)
	{
		this.recentConsumption = recentConsumption;
	}

	/**
	 * Gets the recent consumption percentage.
	 * 
	 * @return the recent consumption percentage
	 */
	public String getRecentConsumptionPercentage()
	{
		return recentConsumptionPercentage;
	}

	/**
	 * Sets the recent consumption percentage.
	 * 
	 * @param recentConsumptionPercentage the new recent consumption percentage
	 */
	public void setRecentConsumptionPercentage(String recentConsumptionPercentage)
	{
		this.recentConsumptionPercentage = recentConsumptionPercentage;
	}

	/**
	 * Gets the prior consumption.
	 * 
	 * @return the prior consumption
	 */
	public String getPriorConsumption()
	{
		return priorConsumption;
	}

	/**
	 * Sets the prior consumption.
	 * 
	 * @param priorConsumption the new prior consumption
	 */
	public void setPriorConsumption(String priorConsumption)
	{
		this.priorConsumption = priorConsumption;
	}

	/**
	 * Gets the prior consumption percentage.
	 * 
	 * @return the prior consumption percentage
	 */
	public String getPriorConsumptionPercentage()
	{
		return priorConsumptionPercentage;
	}

	/**
	 * Sets the prior consumption percentage.
	 * 
	 * @param priorConsumptionPercentage the new prior consumption percentage
	 */
	public void setPriorConsumptionPercentage(String priorConsumptionPercentage)
	{
		this.priorConsumptionPercentage = priorConsumptionPercentage;
	}

	/**
	 * Gets the daily consumption average.
	 * 
	 * @return the daily consumption average
	 */
	public String getDailyConsumptionAverage()
	{
		return dailyConsumptionAverage;
	}

	/**
	 * Sets the daily consumption average.
	 * 
	 * @param dailyConsumptionAverage the new daily consumption average
	 */
	public void setDailyConsumptionAverage(String dailyConsumptionAverage)
	{
		this.dailyConsumptionAverage = dailyConsumptionAverage;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "WaterLeak [getWaterMeter()=" + getWaterMeter() + ", getLeakTime()=" + getLeakTime()
				+ ", getRecentConsumption()=" + getRecentConsumption() + ", getRecentConsumptionPercentage()="
				+ getRecentConsumptionPercentage() + ", getPriorConsumption()=" + getPriorConsumption()
				+ ", getPriorConsumptionPercentage()=" + getPriorConsumptionPercentage()
				+ ", getDailyConsumptionAverage()=" + getDailyConsumptionAverage() + ", toString()="
				+ super.toString() + "]";
	}
}
