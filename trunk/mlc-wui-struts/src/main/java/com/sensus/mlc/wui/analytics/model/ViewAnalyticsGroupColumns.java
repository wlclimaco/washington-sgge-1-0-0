package com.sensus.mlc.wui.analytics.model;

/**
 * Atributes for AnalyticsGroupColumn.
 * 
 * @author Raphael Constantino
 */

public class ViewAnalyticsGroupColumns
{

	/** The date. */
	private String date;

	/** The value. */
	private String value;

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date the new date
	 */
	public void setDate(String date)
	{
		this.date = date;
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
	 * @param value the new value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ViewAnalyticsGroupColumns [getDate()=" + getDate() + ", getValue()=" + getValue() + "]";
	}

}
