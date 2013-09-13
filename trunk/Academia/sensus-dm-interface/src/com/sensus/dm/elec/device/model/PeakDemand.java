package com.sensus.dm.elec.device.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class PeakDemand.
 * 
 * @author QAT Brazil.
 */

@SuppressWarnings("serial")
public class PeakDemand extends SensusModel
{

	/** The reading date. */
	private Date readingDate;

	/** The reset date. */
	private Date resetDate;

	/** The peak time. */
	private Date peakTime;

	/** The peak demand. */
	private Double peakDemand;

	/** The counter. */
	private Integer counter;

	/** The damand units. */
	private String demandUnits;

	/** The channels. */
	private List<HashMap<String, String>> channels;

	/** The tier. */
	private String tier;

	/**
	 * Gets the reading date.
	 * 
	 * @return the reading date
	 */
	public Date getReadingDate()
	{
		return readingDate;
	}

	/**
	 * Sets the reading date.
	 * 
	 * @param readingDate the new reading date
	 */
	public void setReadingDate(Date readingDate)
	{
		this.readingDate = readingDate;
	}

	/**
	 * Gets the reset date.
	 * 
	 * @return the reset date
	 */
	public Date getResetDate()
	{
		return resetDate;
	}

	/**
	 * Sets the reset date.
	 * 
	 * @param resetDate the new reset date
	 */
	public void setResetDate(Date resetDate)
	{
		this.resetDate = resetDate;
	}

	/**
	 * Gets the peak time.
	 * 
	 * @return the peak time
	 */
	public Date getPeakTime()
	{
		return peakTime;
	}

	/**
	 * Sets the peak time.
	 * 
	 * @param peakTime the new peak time
	 */
	public void setPeakTime(Date peakTime)
	{
		this.peakTime = peakTime;
	}

	/**
	 * Gets the peak demand.
	 * 
	 * @return the peak demand
	 */
	public Double getPeakDemand()
	{
		return peakDemand;
	}

	/**
	 * Sets the peak demand.
	 * 
	 * @param peakDemand the new peak demand
	 */
	public void setPeakDemand(Double peakDemand)
	{
		this.peakDemand = peakDemand;
	}

	/**
	 * Gets the counter.
	 * 
	 * @return the counter
	 */
	public Integer getCounter()
	{
		return counter;
	}

	/**
	 * Sets the counter.
	 * 
	 * @param counter the new counter
	 */
	public void setCounter(Integer counter)
	{
		this.counter = counter;
	}

	/**
	 * Gets the damand units.
	 * 
	 * @return the damand units
	 */
	public String getDemandUnits()
	{
		return demandUnits;
	}

	/**
	 * Sets the damand units.
	 * 
	 * @param damandUnits the new damand units
	 */
	public void setDemandUnits(String demandUnits)
	{
		this.demandUnits = demandUnits;
	}

	/**
	 * Gets the channels.
	 * 
	 * @return the channels
	 */
	public List<HashMap<String, String>> getChannels()
	{
		return channels;
	}

	/**
	 * Sets the channels.
	 * 
	 * @param channels the channels
	 */
	public void setChannels(List<HashMap<String, String>> channels)
	{
		this.channels = channels;
	}

	/**
	 * Gets the tier.
	 * 
	 * @return the tier
	 */
	public String getTier()
	{
		return tier;
	}

	/**
	 * Sets the tier.
	 * 
	 * @param tier the new tier
	 */
	public void setTier(String tier)
	{
		this.tier = tier;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "PeakDemand [getReadingDate()=" + getReadingDate() + ", getResetDate()=" + getResetDate()
				+ ", getPeakTime()=" + getPeakTime() + ", getPeakDemand()=" + getPeakDemand() + ", getCounter()="
				+ getCounter() + ", getDemandUnits()=" + getDemandUnits() + ", getChannels()=" + getChannels()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + ", getTier()=" + getTier() + "]";
	}

}
