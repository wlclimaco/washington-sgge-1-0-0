package com.sensus.dm.elec.device.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class IntervalRead.
 * 
 * @author QAT Brazil.
 */
@SuppressWarnings("serial")
public class IntervalRead extends SensusModel
{
	/** The sample point. */
	private String samplePoint;

	/** The reading date. */
	private Date readingDate;

	/** The channels. */
	private List<HashMap<String, String>> channels;

	/** The channel size. */
	private Integer channelSize;

	/** The meter related to this Load Profile. */
	private Integer meterFlexnetId;

	/**
	 * Gets the sample point.
	 * 
	 * @return the sample point
	 */
	public String getSamplePoint()
	{
		return samplePoint;
	}

	/**
	 * Sets the sample point.
	 * 
	 * @param samplePoint the new sample point
	 */
	public void setSamplePoint(String samplePoint)
	{
		this.samplePoint = samplePoint;
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
	 * Gets the channel size.
	 * 
	 * @return the channel size
	 */
	public Integer getChannelSize()
	{
		return channelSize;
	}

	/**
	 * Sets the channel size.
	 * 
	 * @param channelSize the new channel size
	 */
	public void setChannelSize(Integer channelSize)
	{
		this.channelSize = channelSize;
	}

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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		int i = 0;

		if (readingDate != null)
		{
			i = readingDate.hashCode();
		}

		result = prime * result + i;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		IntervalRead other = (IntervalRead)obj;
		if (readingDate == null)
		{
			if (other.readingDate != null)
			{
				return false;
			}
		}
		else if (!readingDate.equals(other.readingDate))
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "IntervalRead [getSamplePoint()=" + getSamplePoint() + ", getChannels()=" + getChannels()
				+ ", getChannelSize()=" + getChannelSize() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
