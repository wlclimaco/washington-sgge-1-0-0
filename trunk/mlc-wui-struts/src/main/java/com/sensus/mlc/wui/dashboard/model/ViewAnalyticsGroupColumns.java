package com.sensus.mlc.wui.dashboard.model;

import java.io.Serializable;

/**
 * Atributes for AnalyticsGroupsColumn.
 * 
 * @author Alexandre Tiveron
 */

@SuppressWarnings("serial")
public class ViewAnalyticsGroupColumns implements Serializable
{

	/** The description. */
	private String description;

	/** The value. */
	private String value;

	/** The average. */
	private String average;

	/** The trend. */
	private String trends;

	/** The change. */
	private String change;

	/** The date. */
	private String date;

	/** The is positive. */
	private Boolean isPositive;

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Gets the average.
	 * 
	 * @return the average
	 */
	public String getAverage()
	{
		return average;
	}

	/**
	 * Sets the average.
	 * 
	 * @param average the average to set
	 */
	public void setAverage(String average)
	{
		this.average = average;
	}

	/**
	 * Gets the trends.
	 * 
	 * @return the trends
	 */
	public String getTrends()
	{
		return trends;
	}

	/**
	 * Sets the trends.
	 * 
	 * @param trends the trends to set
	 */
	public void setTrends(String trends)
	{
		this.trends = trends;
	}

	/**
	 * Gets the change.
	 * 
	 * @return the change
	 */
	public String getChange()
	{
		return change;
	}

	/**
	 * Sets the change.
	 * 
	 * @param change the change to set
	 */
	public void setChange(String change)
	{
		this.change = change;
	}

	/**
	 * Sets the checks if is positive.
	 * 
	 * @param isPositive the isPositive to set
	 */
	public void setIsPositive(Boolean isPositive)
	{
		this.isPositive = isPositive;
	}

	/**
	 * Gets the checks if is positive.
	 * 
	 * @return the isPositive
	 */
	public Boolean getIsPositive()
	{
		return isPositive;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date the date to set
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ViewAnalyticsGroupColumns [description=" + description
				+ ", value=" + value + ", average=" + average + ", trends="
				+ trends + ", change=" + change + ", date=" + date
				+ ", isPositive=" + isPositive + "]";
	}

}
