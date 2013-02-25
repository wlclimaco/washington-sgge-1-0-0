package com.sensus.mlc.base.model;

import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.sensus.common.model.SensusModel;

/**
 * The Class TimeZoneInfo.
 */
public class TimeZoneInfo extends SensusModel implements Comparator<TimeZoneInfo>
{
	private static final long serialVersionUID = 1L;

	private static final String GMT = "(GMT ";
	private static final String MINUTES = ":00) ";

	private static final int MS_IN_MIN = 60000;
	private static final int MIN_IN_HR = 60;

	/** The time zone. */
	private TimeZone timeZone;

	/** The display name. */
	private String displayName;

	/** The display name gmt. */
	private String displayNameGMT;

	/** The display name short. */
	private String displayNameShort;

	/** The hours. */
	private Integer hours;

	/**
	 * Instantiates a new time zone info.
	 */
	public TimeZoneInfo()
	{
	}

	/**
	 * Instantiates a new time zone info.
	 * 
	 * @param timeZoneParm the time zone parm
	 */
	public TimeZoneInfo(TimeZone timeZoneParm)
	{
		setTimeZone(timeZoneParm);
		setDisplayName(timeZoneParm.getDisplayName());
		setDisplayNameShort(timeZoneParm.getDisplayName(timeZoneParm.inDaylightTime(new Date()), TimeZone.SHORT,
				Locale.getDefault()));

		int minutes = timeZoneParm.getOffset(new Date().getTime()) / MS_IN_MIN;
		setHours(minutes / MIN_IN_HR);

		setDisplayNameGMT(GMT + getHours() + MINUTES
				+ timeZoneParm.getDisplayName());
	}

	/**
	 * Instantiates a new time zone info.
	 * 
	 * @param timeZoneShort the time zone short
	 */
	public TimeZoneInfo(String timeZoneShort)
	{
		setTimeZone(TimeZone.getTimeZone(timeZoneShort));
		setDisplayName(getTimeZone().getDisplayName());
		setDisplayNameShort(getTimeZone().getDisplayName(getTimeZone().inDaylightTime(new Date()), TimeZone.SHORT,
				Locale.getDefault()));

		int minutes = getTimeZone().getOffset(new Date().getTime()) / MS_IN_MIN;
		setHours(minutes / MIN_IN_HR);

		setDisplayNameGMT(GMT + getHours() + MINUTES
				+ getTimeZone().getDisplayName());
	}

	/**
	 * Gets the time zone.
	 * 
	 * @return the time zone
	 */
	public TimeZone getTimeZone()
	{
		return timeZone;
	}

	/**
	 * Sets the time zone.
	 * 
	 * @param timeZone the new time zone
	 */
	public void setTimeZone(TimeZone timeZone)
	{
		this.timeZone = timeZone;
	}

	/**
	 * Gets the display name.
	 * 
	 * @return the display name
	 */
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	 * Sets the display name.
	 * 
	 * @param displayName the new display name
	 */
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	/**
	 * Gets the display name gmt.
	 * 
	 * @return the display name gmt
	 */
	public String getDisplayNameGMT()
	{
		return displayNameGMT;
	}

	/**
	 * Sets the display name gmt.
	 * 
	 * @param displayNameGMT the new display name gmt
	 */
	public void setDisplayNameGMT(String displayNameGMT)
	{
		this.displayNameGMT = displayNameGMT;
	}

	/**
	 * Gets the display name short.
	 * 
	 * @return the display name short
	 */
	public String getDisplayNameShort()
	{
		return displayNameShort;
	}

	/**
	 * Sets the display name short.
	 * 
	 * @param displayNameShort the new display name short
	 */
	public void setDisplayNameShort(String displayNameShort)
	{
		this.displayNameShort = displayNameShort;
	}

	public Integer getHours()
	{
		return hours;
	}

	public void setHours(Integer hours)
	{
		this.hours = hours;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TimeZoneInfo [getTimeZone()=" + getTimeZone() + ", getDisplayName()=" + getDisplayName()
				+ ", getDisplayNameGMT()=" + getDisplayNameGMT() + ", getHours()=" + getHours()
				+ ", getDisplayNameShort()=" + getDisplayNameShort()
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(TimeZoneInfo tz1, TimeZoneInfo tz2)
	{
		return tz1.getDisplayName().compareTo(tz2.getDisplayName());
	}

}
