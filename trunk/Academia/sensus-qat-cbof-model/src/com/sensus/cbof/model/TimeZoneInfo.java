package com.sensus.cbof.model;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.sensus.common.model.SensusModel;

/**
 * The Class TimeZoneInfo.
 * 
 */
public class TimeZoneInfo extends SensusModel implements Comparator<TimeZoneInfo>
{

	/** The Constant STRING_CLOSE_PARENTHESIS. */
	private static final String STRING_CLOSE_PARENTHESIS = ")";

	/** The Constant STRING_OPEN_PARENTHESIS. */
	private static final String STRING_OPEN_PARENTHESIS = " (";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant GMT. */
	private static final String GMT = "(GMT ";

	/** The Constant COLON. */
	private static final String COLON = ":";

	/** The Constant END_PARENTHESIS. */
	private static final String END_PARENTHESIS = ") ";

	/** The time zone. */
	private TimeZone timeZone;

	/** The display minutes. */
	private String displayMinutes;

	/** The display name. */
	private String displayName;

	/** The display name gmt. */
	private String displayNameGMT;

	/** The display name short. */
	private String displayNameShort;

	/** The CONST_00 constant. */
	private static final String CONST_00 = "00";

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
		final int msInMin = 60000;
		final int minInHr = 60;
		int hours;
		int minutes;

		setTimeZone(timeZoneParm);
		setDisplayName(timeZoneParm.getDisplayName());
		setDisplayNameShort(timeZoneParm.getDisplayName(timeZoneParm.inDaylightTime(new Date()), TimeZone.SHORT,
				Locale.getDefault()));

		minutes = timeZoneParm.getOffset(new Date().getTime()) / msInMin;
		hours = minutes / minInHr;
		minutes = Math.abs(minutes % minInHr);
		DecimalFormat formatter = new DecimalFormat(CONST_00);

		setDisplayNameGMT(GMT + hours + COLON + formatter.format(minutes) + END_PARENTHESIS
				+ timeZoneParm.getID() + STRING_OPEN_PARENTHESIS + timeZoneParm.getDisplayName()
				+ STRING_CLOSE_PARENTHESIS);
	}

	/**
	 * Instantiates a new time zone info.
	 * 
	 * @param timeZoneShort the time zone short
	 */
	public TimeZoneInfo(String timeZoneShort)
	{
		setTimeZone(TimeZone.getTimeZone(timeZoneShort));
		final int msInMin = 60000;
		final int minInHr = 60;
		int hours;
		int minutes;
		setDisplayName(getTimeZone().getDisplayName());
		setDisplayNameShort(getTimeZone().getDisplayName(getTimeZone().inDaylightTime(new Date()), TimeZone.SHORT,
				Locale.getDefault()));

		minutes = getTimeZone().getOffset(new Date().getTime()) / msInMin;
		hours = minutes / minInHr;
		minutes = Math.abs(minutes % minInHr);
		DecimalFormat formatter = new DecimalFormat(CONST_00);

		setDisplayNameGMT(GMT + hours + COLON + formatter.format(minutes) + END_PARENTHESIS
				+ getTimeZone().getID() + STRING_OPEN_PARENTHESIS + getTimeZone().getDisplayName()
				+ STRING_CLOSE_PARENTHESIS);
	}

	/**
	 * Return the offset in hours from this timezone using the current date/time.
	 * This method does take into consideration DST.
	 * 
	 * @return the raw offset
	 */
	public long getOffsetInHours()
	{
		if (getTimeZone() == null)
		{
			return 0;
		}

		return TimeUnit.MILLISECONDS.toHours(getTimeZone().getOffset(new Date().getTime()));
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

	/**
	 * @return the displayMinutes
	 */
	public String getDisplayMinutes()
	{
		return displayMinutes;
	}

	/**
	 * @param displayMinutes the displayMinutes to set
	 */
	public void setDisplayMinutes(String displayMinutes)
	{
		this.displayMinutes = displayMinutes;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TimeZoneInfo [getTimeZone()=" + getTimeZone() + ", getDisplayName()=" + getDisplayName()
				+ ", getDisplayNameGMT()=" + getDisplayNameGMT() + ", getDisplayNameShort()=" + getDisplayNameShort()
				+ ", getDisplayMinutes()=" + getDisplayMinutes() + "]";
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(TimeZoneInfo tz1, TimeZoneInfo tz2)
	{
		if (tz1.getTimeZone().getOffset(new Date().getTime()) > tz2.getTimeZone().getOffset(new Date().getTime()))
		{
			return 1;
		}
		else
		{
			if (tz1.getTimeZone().getOffset(new Date().getTime()) < tz2.getTimeZone().getOffset(new Date().getTime()))
			{
				return -1;
			}
			else
			{
				return tz1.getDisplayNameGMT().compareTo(tz2.getDisplayNameGMT());
			}
		}
	}

}
