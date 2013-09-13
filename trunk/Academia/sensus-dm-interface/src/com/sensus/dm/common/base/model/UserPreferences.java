package com.sensus.dm.common.base.model;

import java.text.DateFormat;

import com.sensus.cbof.model.TimeZoneInfo;

/**
 * The Class UserPreferences.
 */
public class UserPreferences
{

	/** The date format. */
	private DateFormat dateFormat;

	/** The Language. */
	private String language;

	/** The time zone info. */
	private TimeZoneInfo timeZoneInfo;

	/** The monitor request status. */
	private MonitorRequestStatusEnum monitorRequestStatusEnum;

	/** The page size. */
	private Integer pageSize;

	/** The default lat. */
	private String defaultLat;

	/** The default lng. */
	private String defaultLng;

	/**
	 * Gets the date format.
	 * 
	 * @return the date format
	 */
	public DateFormat getDateFormat()
	{
		return dateFormat;
	}

	/**
	 * Sets the date format.
	 * 
	 * @param dateFormat the new date format
	 */
	public void setDateFormat(DateFormat dateFormat)
	{
		this.dateFormat = dateFormat;
	}

	/**
	 * Gets the language.
	 * 
	 * @return the language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Sets the language.
	 * 
	 * @param language the new language
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * Gets the time zone info.
	 * 
	 * @return the time zone info
	 */
	public TimeZoneInfo getTimeZoneInfo()
	{
		return timeZoneInfo;
	}

	/**
	 * Sets the time zone info.
	 * 
	 * @param timeZoneInfo the new time zone info
	 */
	public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo)
	{
		this.timeZoneInfo = timeZoneInfo;
	}

	/**
	 * Gets the monitor request status enum.
	 * 
	 * @return the monitor request status enum
	 */
	public MonitorRequestStatusEnum getMonitorRequestStatusEnum()
	{
		return monitorRequestStatusEnum;
	}

	/**
	 * Sets the monitor request status enum.
	 * 
	 * @param monitorRequestStatusEnum the new monitor request status enum
	 */
	public void setMonitorRequestStatusEnum(MonitorRequestStatusEnum monitorRequestStatusEnum)
	{
		this.monitorRequestStatusEnum = monitorRequestStatusEnum;
	}

	/**
	 * Gets the page size.
	 * 
	 * @return the page size
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize the new page size
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UserPreferences [getDateFormat()=" + getDateFormat() + ", getLanguage()=" + getLanguage()
				+ ", getTimeZoneInfo()=" + getTimeZoneInfo() + ", getMonitorRequestStatusEnum()="
				+ getMonitorRequestStatusEnum() + ", getPageSize()="
				+ getPageSize() + ", getDefaultLat()=" + getDefaultLat() + ", getDefaultLng()=" + getDefaultLng() + "]";
	}

	/**
	 * @return the defaultLat
	 */
	public String getDefaultLat()
	{
		return defaultLat;
	}

	/**
	 * @param defaultLat the defaultLat to set
	 */
	public void setDefaultLat(String defaultLat)
	{
		this.defaultLat = defaultLat;
	}

	/**
	 * @return the defaultLng
	 */
	public String getDefaultLng()
	{
		return defaultLng;
	}

	/**
	 * @param defaultLng the defaultLng to set
	 */
	public void setDefaultLng(String defaultLng)
	{
		this.defaultLng = defaultLng;
	}

}
