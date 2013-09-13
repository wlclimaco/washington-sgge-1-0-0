package com.sensus.dm.elec.device.model;

import java.math.BigDecimal;

/**
 * The Class UnitOfMeasureTotal.
 * 
 * @author QAT Brazil
 */
public class UnitOfMeasureTotal
{
	// -------------------------------------------------------------------------
	// Fields:

	// /** The unit of measure. */
	// private UnitOfMeasure unitOfMeasure;

	/** The channel name. */
	private String channelName;

	/** The total. */
	private BigDecimal total = new BigDecimal(0);

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new unit of measure total.
	 * 
	 * @param channelName the channel name
	 */
	public UnitOfMeasureTotal(String channelNameParam)
	{
		setChannelName(channelNameParam);
	}

	/**
	 * Instantiates a new unit of measure total.
	 * 
	 * @param channelName the channel name
	 * @param total the total
	 */
	public UnitOfMeasureTotal(String channelNameParam, BigDecimal totalParam)
	{
		setChannelName(channelNameParam);
		setTotal(totalParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the total.
	 * 
	 * @return the total
	 */
	public BigDecimal getTotal()
	{
		return total;
	}

	/**
	 * Sets the total.
	 * 
	 * @param total the new total
	 */
	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}

	/**
	 * Gets the channel name.
	 * 
	 * @return the channel name
	 */
	public String getChannelName()
	{
		return channelName;
	}

	/**
	 * Sets the channel name.
	 * 
	 * @param channelName the new channel name
	 */
	public void setChannelName(String channelName)
	{
		this.channelName = channelName;
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

		if (channelName != null)
		{
			i = channelName.hashCode();
		}
		result = (prime * result) + i;
		
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
		UnitOfMeasureTotal other = (UnitOfMeasureTotal)obj;
		if (channelName == null)
		{
			if (other.channelName != null)
			{
				return false;
			}
		}
		else if (!channelName.equals(other.channelName))
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UnitOfMeasureTotal [getTotal()=" + getTotal() + ", getChannelName()=" + getChannelName()
				+ ", hashCode()=" + hashCode() + "]";
	}

	// -------------------------------------------------------------------------
}
