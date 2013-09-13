package com.sensus.dm.elec.device.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * * The Entity Model Class for Load Profile.
 * 
 * @author - QAT Global
 */
@SuppressWarnings("serial")
public class LoadProfile extends SensusModel
{
	/** The meter related to this Load Profile. */
	private Integer meterFlexnetId;

	/** The current reading. */
	private String currentReading;

	/** The current hour consumption. */
	private String currentHourConsumption;

	/** The current month consumption. */
	private String currentMonthConsumption;

	/** The current peak on consumption demand. */
	private String consumptionPeakDemand;

	/** The request time. */
	private Date requestTime;

	/** The hourly consumption start time. */
	private Date hourlyConsumptionStart;

	/** The hourly consumption end. */
	private Date hourlyConsumptionEnd;

	/** The hourly consumption start time. */
	private Date monthlyConsumptionStart;

	/** The monthly consumption end. */
	private Date monthlyConsumptionEnd;

	/** The last read value. */
	private String lastReadValue;

	/** The last read time. */
	private Date lastReadTime;

	/**
	 * Instantiates a new Load Profile.
	 */
	public LoadProfile()
	{
	}

	/**
	 * Instantiates a new load profile.
	 * 
	 * @param consumptionPeakDemandParam the consumption peak demand param
	 * @param currentReadingParam the current reading param
	 * @param currentHourConsumptionParam the current hour consumption param
	 * @param currentMonthConsumptionParam the current month consumption param
	 * @param lastReadValueParam the last read value param
	 */
	public LoadProfile(String consumptionPeakDemandParam, String currentReadingParam,
			String currentHourConsumptionParam, String currentMonthConsumptionParam, String lastReadValueParam)
	{
		setConsumptionPeakDemand(consumptionPeakDemandParam);
		setCurrentReading(currentReadingParam);
		setCurrentHourConsumption(currentHourConsumptionParam);
		setCurrentMonthConsumption(currentMonthConsumptionParam);
		setLastReadValue(lastReadValueParam);
	}

	/**
	 * Instantiates a new load profile.
	 * 
	 * @param lastReadTimeParam the last read time
	 */
	public LoadProfile(Date lastReadTimeParam)
	{
		setLastReadTime(lastReadTimeParam);
	}

	/**
	 * Gets the current reading.
	 * 
	 * @return the currentReading
	 */
	public String getCurrentReading()
	{
		return currentReading;
	}

	/**
	 * Sets the current reading.
	 * 
	 * @param currentReading the currentReading to set
	 */
	public void setCurrentReading(String currentReading)
	{
		this.currentReading = currentReading;
	}

	/**
	 * Gets the consumption peak demand.
	 * 
	 * @return the consumptionPeakDemand
	 */
	public String getConsumptionPeakDemand()
	{
		return consumptionPeakDemand;
	}

	/**
	 * Sets the consumption peak demand.
	 * 
	 * @param consumptionPeakDemand the consumptionPeakDemand to set
	 */
	public void setConsumptionPeakDemand(String consumptionPeakDemand)
	{
		this.consumptionPeakDemand = consumptionPeakDemand;
	}

	/**
	 * Gets the request time.
	 * 
	 * @return the requestTime
	 */
	public Date getRequestTime()
	{
		return requestTime;
	}

	/**
	 * Sets the request time.
	 * 
	 * @param requestTime the requestTime to set
	 */
	public void setRequestTime(Date requestTime)
	{
		this.requestTime = requestTime;
	}

	/**
	 * Gets the hourly consumption start.
	 * 
	 * @return the hourlyConsumptionStart
	 */
	public Date getHourlyConsumptionStart()
	{
		return hourlyConsumptionStart;
	}

	/**
	 * Sets the hourly consumption start.
	 * 
	 * @param hourlyConsumptionStart the hourlyConsumptionStart to set
	 */
	public void setHourlyConsumptionStart(Date hourlyConsumptionStart)
	{
		this.hourlyConsumptionStart = hourlyConsumptionStart;
	}

	/**
	 * Gets the monthly consumption start.
	 * 
	 * @return the monthlyConsumptionStart
	 */
	public Date getMonthlyConsumptionStart()
	{
		return monthlyConsumptionStart;
	}

	/**
	 * Sets the monthly consumption start.
	 * 
	 * @param monthlyConsumptionStart the monthlyConsumptionStart to set
	 */
	public void setMonthlyConsumptionStart(Date monthlyConsumptionStart)
	{
		this.monthlyConsumptionStart = monthlyConsumptionStart;
	}

	/**
	 * Gets the last read value.
	 * 
	 * @return the last read value
	 */
	public String getLastReadValue()
	{
		return lastReadValue;
	}

	/**
	 * Sets the last read value.
	 * 
	 * @param lastReadValue the new last read value
	 */
	public void setLastReadValue(String lastReadValue)
	{
		this.lastReadValue = lastReadValue;
	}

	/**
	 * Gets the last read time.
	 * 
	 * @return the last read time
	 */
	public Date getLastReadTime()
	{
		return lastReadTime;
	}

	/**
	 * Sets the last read time.
	 * 
	 * @param lastReadTime the new last read time
	 */
	public void setLastReadTime(Date lastReadTime)
	{
		this.lastReadTime = lastReadTime;
	}

	/**
	 * Gets the meter flexnet id.
	 * 
	 * @return the meter flexnet id
	 */
	public Integer getMeterFlexnetId()
	{
		return meterFlexnetId;
	}

	/**
	 * Sets the meter flexnet id.
	 * 
	 * @param meterFlexnetId the new meter flexnet id
	 */
	public void setMeterFlexnetId(Integer meterFlexnetId)
	{
		this.meterFlexnetId = meterFlexnetId;
	}

	/**
	 * Gets the current hour consumption.
	 * 
	 * @return the current hour consumption
	 */
	public String getCurrentHourConsumption()
	{
		return currentHourConsumption;
	}

	/**
	 * Sets the current hour consumption.
	 * 
	 * @param currentHourConsumption the new current hour consumption
	 */
	public void setCurrentHourConsumption(String currentHourConsumption)
	{
		this.currentHourConsumption = currentHourConsumption;
	}

	/**
	 * Gets the current month consumption.
	 * 
	 * @return the current month consumption
	 */
	public String getCurrentMonthConsumption()
	{
		return currentMonthConsumption;
	}

	/**
	 * Sets the current month consumption.
	 * 
	 * @param currentMonthConsumption the new current month consumption
	 */
	public void setCurrentMonthConsumption(String currentMonthConsumption)
	{
		this.currentMonthConsumption = currentMonthConsumption;
	}

	/**
	 * Gets the hourly consumption end.
	 * 
	 * @return the hourly consumption end
	 */
	public Date getHourlyConsumptionEnd()
	{
		return hourlyConsumptionEnd;
	}

	/**
	 * Sets the hourly consumption end.
	 * 
	 * @param hourlyConsumptionEnd the new hourly consumption end
	 */
	public void setHourlyConsumptionEnd(Date hourlyConsumptionEnd)
	{
		this.hourlyConsumptionEnd = hourlyConsumptionEnd;
	}

	/**
	 * Gets the monthly consumption end.
	 * 
	 * @return the monthly consumption end
	 */
	public Date getMonthlyConsumptionEnd()
	{
		return monthlyConsumptionEnd;
	}

	/**
	 * Sets the monthly consumption end.
	 * 
	 * @param monthlyConsumptionEnd the new monthly consumption end
	 */
	public void setMonthlyConsumptionEnd(Date monthlyConsumptionEnd)
	{
		this.monthlyConsumptionEnd = monthlyConsumptionEnd;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LoadProfile [getCurrentReading()=" + getCurrentReading() + ", getCurrentHourConsumption()="
				+ getCurrentHourConsumption() + ", getCurrentMonthConsumption()=" + getCurrentMonthConsumption()
				+ ", getConsumptionPeakDemand()=" + getConsumptionPeakDemand() + ", getRequestTime()="
				+ getRequestTime()
				+ ", getHourlyConsumptionStart()=" + getHourlyConsumptionStart() + ", getHourlyConsumptionEnd()="
				+ getHourlyConsumptionEnd()
				+ ", getMonthlyConsumptionStart()=" + getMonthlyConsumptionStart() + ", getMonthlyConsumptionEnd()="
				+ getMonthlyConsumptionEnd()
				+ ", getLastReadValue()=" + getLastReadValue() + ", getLastReadTime()=" + getLastReadTime()
				+ ", getMeterFlexnetId()=" + getMeterFlexnetId() + ", toString()=" + super.toString() + "]";
	}

}