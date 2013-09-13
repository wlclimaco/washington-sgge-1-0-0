package com.sensus.common.beans.factory;

import java.util.Calendar;

import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * A Spring bean factory implementation supporting the creation of java.util.Calendar types.
 */
public class CalendarFactoryBean extends AbstractFactoryBean
{
	// [start] Private fields

	/**
	 * Year; defaults to zero.
	 */
	private int year = 0;

	/**
	 * Month; defaults to zero.
	 */
	private int month = 0;

	/**
	 * Date; defaults to zero.
	 */
	private int date = 0;

	/**
	 * Number of hour of day (24 hour); defaults to zero.
	 */
	private int hourOfDay = 0;

	/**
	 * Number of minutes; defaults to zero.
	 */
	private int minute = 0;

	/**
	 * Number of seconds; defaults to zero.
	 */
	private int second = 0;

	// [end] Private fields

	// [start] public setters

	/**
	 * Sets the year.
	 * 
	 * @param year the new year
	 */
	public void setYear(int year)
	{
		this.year = year;
	}

	/**
	 * Sets the month.
	 * 
	 * @param month the new month
	 */
	public void setMonth(int month)
	{
		this.month = month;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date the new date
	 */
	public void setDate(int date)
	{
		this.date = date;
	}

	/**
	 * Sets the hour of day.
	 * 
	 * @param hourOfDay the new hour of day
	 */
	public void setHourOfDay(int hourOfDay)
	{
		this.hourOfDay = hourOfDay;
	}

	/**
	 * Sets the minute.
	 * 
	 * @param minute the new minute
	 */
	public void setMinute(int minute)
	{
		this.minute = minute;
	}

	/**
	 * Sets the second.
	 * 
	 * @param second the new second
	 */
	public void setSecond(int second)
	{
		this.second = second;
	}

	// [end] public setters

	/**
	 * Create an instance of the Calendar object using the set values.
	 * 
	 * @return A new instance of the Calendar object initialized based on the properties.
	 * @throws Exception Throws when the Calendar object can not be created or set given the property values.
	 * @see org.springframework.beans.factory.config.AbstractFactoryBean#createInstance()
	 */
	@Override
	protected Object createInstance() throws Exception
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(this.year, this.month, this.date, this.hourOfDay, this.minute, this.second);
		return calendar;
	}

	/**
	 * Return the type of object this factory creates.
	 * 
	 * @return The Class object for Calendar
	 * @see org.springframework.beans.factory.config.AbstractFactoryBean#getObjectType()
	 */
	@SuppressWarnings("unchecked")
	public Class getObjectType()
	{
		return Calendar.class;
	}

}
