package com.sensus.mlc.wui.base.model;

import java.text.DateFormat;
import java.util.Locale;

// TODO: Auto-generated Javadoc
/**
 * Represents User-specific locale and time information.
 * 
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class UserSettings
{
	/**
	 * The locale string to base the user localization settings on.
	 */
	private String baseLocale;
	/**
	 * The timezone.
	 */
	private String timezone;
	/**
	 * The locale as Java Object.
	 */
	private Locale baseLocaleObject;

	/** The date format. */
	private DateFormat dateFormat;

	/** The Monitor. */
	private Integer monitor;

	/** The date format mask. */
	private String dateFormatMask;

	/** The convert unit. */
	private String convertUnit;

	/** The page size. */
	private Integer pageSize;

	/** The page size list. */
	private String pageSizeList;

	/** The page size show dialog. */
	private Integer pageSizeShowDialog;

	/** The polygon dialog. */
	private String polygonDialog;

	/** The timezone short. */
	private String timezoneShort;

	/** The date now. */
	private String dateNow;

	/** The time zone hours. */
	private String timeZoneHours;

	/**
	 * Default constructor for user locale settings.
	 */
	public UserSettings()
	{
	}

	/**
	 * Gets the page size list.
	 * 
	 * @return the page size list
	 */
	public String getPageSizeList()
	{
		return pageSizeList;
	}

	/**
	 * Sets the page size list.
	 * 
	 * @param pageSizeList the new page size list
	 */
	public void setPageSizeList(String pageSizeList)
	{
		this.pageSizeList = pageSizeList;
	}

	/**
	 * Get the base locale code.
	 * 
	 * @return the base locale code
	 */
	public String getBaseLocale()
	{
		return baseLocale;
	}

	/**
	 * Set the base locale from a code string. For example, use "en_US" for English/United States.
	 * 
	 * @param baseLocaleIn the base locale code
	 */
	public void setBaseLocale(String baseLocaleIn)
	{
		baseLocale = baseLocaleIn;
	}

	/**
	 * Get the user's time zone.
	 * 
	 * @return the time zone
	 */
	public String getTimezone()
	{
		return timezone;
	}

	/**
	 * Set the user's timezone. Use standard names like "Asia/Kuwait".
	 * 
	 * @param timeZoneIn the time zone
	 */
	public void setTimezone(String timeZoneIn)
	{
		timezone = timeZoneIn;
	}

	/**
	 * Set the base locale as Locale object.
	 * 
	 * @param baseLocaleObjectIn the locale
	 */
	public void setBaseLocaleObject(Locale baseLocaleObjectIn)
	{
		baseLocaleObject = baseLocaleObjectIn;
	}

	/**
	 * Get the base locale as Locale object.
	 * 
	 * @return the baseLocaleObject
	 * 
	 */
	public Locale getBaseLocaleObject()
	{
		return baseLocaleObject;
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
	 * Gets the date format.
	 * 
	 * @return the date format
	 */
	public DateFormat getDateFormat()
	{
		return dateFormat;
	}

	/**
	 * Gets the monitor.
	 * 
	 * @return the monitor
	 */
	public Integer getMonitor()
	{
		return monitor;
	}

	/**
	 * Sets the monitor.
	 * 
	 * @param monitor the new monitor
	 */
	public void setMonitor(Integer monitor)
	{
		this.monitor = monitor;
	}

	/**
	 * Sets the date format mask.
	 * 
	 * @param dateFormatMask the dateFormatMask to set
	 */
	public void setDateFormatMask(String dateFormatMask)
	{
		this.dateFormatMask = dateFormatMask;
	}

	/**
	 * Gets the date format mask.
	 * 
	 * @return the dateFormatMask
	 */
	public String getDateFormatMask()
	{
		return dateFormatMask;
	}

	/**
	 * Sets the convert unit.
	 * 
	 * @param convertUnit the new convert unit
	 */
	public void setConvertUnit(String convertUnit)
	{
		this.convertUnit = convertUnit;
	}

	/**
	 * Gets the convert unit.
	 * 
	 * @return the convert unit
	 */
	public String getConvertUnit()
	{
		return convertUnit;
	}

	/**
	 * Gets the page size.
	 * 
	 * @return the pageSize
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * Gets the page size show dialog.
	 * 
	 * @return the pageSizeShowDialog
	 */
	public Integer getPageSizeShowDialog()
	{
		return pageSizeShowDialog;
	}

	/**
	 * Sets the page size show dialog.
	 * 
	 * @param pageSizeShowDialog the pageSizeShowDialog to set
	 */
	public void setPageSizeShowDialog(Integer pageSizeShowDialog)
	{
		this.pageSizeShowDialog = pageSizeShowDialog;
	}

	/**
	 * Gets the timezone short.
	 * 
	 * @return the timezoneShort
	 */
	public String getTimezoneShort()
	{
		return timezoneShort;
	}

	/**
	 * Sets the timezone short.
	 * 
	 * @param timezoneShort the timezoneShort to set
	 */
	public void setTimezoneShort(String timezoneShort)
	{
		this.timezoneShort = timezoneShort;
	}

	/**
	 * Gets the date now.
	 * 
	 * @return the dateNow
	 */
	public String getDateNow()
	{
		return dateNow;
	}

	/**
	 * Sets the date now.
	 * 
	 * @param dateNow the dateNow to set
	 */
	public void setDateNow(String dateNow)
	{
		this.dateNow = dateNow;
	}

	/**
	 * Gets the time zone hours.
	 * 
	 * @return the timeZoneHours
	 */
	public String getTimeZoneHours()
	{
		return timeZoneHours;
	}

	/**
	 * Sets the time zone hours.
	 * 
	 * @param timeZoneHours the timeZoneHours to set
	 */
	public void setTimeZoneHours(String timeZoneHours)
	{
		this.timeZoneHours = timeZoneHours;
	}

	/**
	 * @return the polygonDialog
	 */
	public String getPolygonDialog()
	{
		return polygonDialog;
	}

	/**
	 * @param polygonDialog the polygonDialog to set
	 */
	public void setPolygonDialog(String polygonDialog)
	{
		this.polygonDialog = polygonDialog;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UserSettings [getBaseLocale()=" + getBaseLocale() + ", getPolygonDialog()=" + getPolygonDialog()
				+ ", getTimezone()=" + getTimezone()
				+ ", getBaseLocaleObject()=" + getBaseLocaleObject() + ", getDateFormat()=" + getDateFormat()
				+ ", getMonitor()=" + getMonitor() + ", getDateFormatMask()=" + getDateFormatMask()
				+ ", getConvertUnit()=" + getConvertUnit() + ", getPageSize()=" + getPageSize()
				+ ", getPageSizeShowDialog()=" + getPageSizeShowDialog() + ", getTimezoneShort()=" + getTimezoneShort()
				+ ", getDateNow()=" + getDateNow() + ", getTimeZoneHours()=" + getTimeZoneHours() + "]";
	}

}
