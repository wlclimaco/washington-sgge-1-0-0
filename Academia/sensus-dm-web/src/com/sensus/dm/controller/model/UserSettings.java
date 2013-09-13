package com.sensus.dm.controller.model;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Represents User-specific locale and time information.
 * 
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class UserSettings
{
	/** The locale string to base the user localization settings on. */
	private String baseLocale;

	/** The language. */
	private String language;

	/** The timezone. */
	private String timezone;

	/** The timezone short. */
	private String timezoneShort;

	/** The locale as Java Object. */
	private Locale baseLocaleObject;

	/** The date format. */
	private DateFormat dateFormat;

	/** The Monitor. */
	private Integer monitor;

	/** The date format mask. */
	private String dateFormatMask;

	/** The page size. */
	private String pageSize;

	/** The show dialog polygon. */
	private String showDialogPolygon;

	/** The time zone hours. */
	private String timeZoneHours;

	/** The time zone minutes. */
	private String timeZoneMinutes;

	/** The default lat. */
	private String defaultLat;

	/** The default lng. */
	private String defaultLng;

	/** The service type. */
	private String serviceType;

	/** The customer id. */
	private String tenant;

	/** The temperature type. */
	private String temperatureType;

	/** The roles. */
	private List<String> roles;

	/**
	 * Default constructor for user locale settings.
	 */
	public UserSettings()
	{
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
	 * Get the language code.
	 * 
	 * @return the language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Set the language code, for example "en" for English.
	 * 
	 * @param languageIn the language
	 */
	public void setLanguage(String languageIn)
	{
		language = languageIn;
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
	 * Gets the page size.
	 * 
	 * @return the page size
	 */
	public String getPageSize()
	{
		return pageSize;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize the new page size
	 */
	public void setPageSize(String pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * Gets the time zone hours.
	 * 
	 * @return the time zone hours
	 */
	public String getTimeZoneHours()
	{
		return timeZoneHours;
	}

	/**
	 * Sets the time zone hours.
	 * 
	 * @param timeZoneHours the new time zone hours
	 */
	public void setTimeZoneHours(String timeZoneHours)
	{
		this.timeZoneHours = timeZoneHours;
	}

	/**
	 * Gets the time zone minutes.
	 * 
	 * @return the timeZoneMinutes
	 */
	public String getTimeZoneMinutes()
	{
		return timeZoneMinutes;
	}

	/**
	 * Sets the time zone minutes.
	 * 
	 * @param timeZoneMinutes the timeZoneMinutes to set
	 */
	public void setTimeZoneMinutes(String timeZoneMinutes)
	{
		this.timeZoneMinutes = timeZoneMinutes;
	}

	/**
	 * Gets the default lat.
	 * 
	 * @return the defaultLat
	 */
	public String getDefaultLat()
	{
		return defaultLat;
	}

	/**
	 * Sets the default lat.
	 * 
	 * @param defaultLat the defaultLat to set
	 */
	public void setDefaultLat(String defaultLat)
	{
		this.defaultLat = defaultLat;
	}

	/**
	 * Gets the default lng.
	 * 
	 * @return the defaultLng
	 */
	public String getDefaultLng()
	{
		return defaultLng;
	}

	/**
	 * Sets the default lng.
	 * 
	 * @param defaultLng the defaultLng to set
	 */
	public void setDefaultLng(String defaultLng)
	{
		this.defaultLng = defaultLng;
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
	 * @return the tenant
	 */
	public String getTenant()
	{
		return tenant;
	}

	/**
	 * @param tenant the tenant to set
	 */
	public void setTenant(String tenant)
	{
		this.tenant = tenant;
	}

	/**
	 * Gets the service type.
	 * 
	 * @return the serviceType
	 */
	public String getServiceType()
	{
		return serviceType;
	}

	/**
	 * Sets the service type.
	 * 
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType)
	{
		this.serviceType = serviceType;
	}

	/**
	 * Gets the temperature type.
	 * 
	 * @return the temperatureType
	 */
	public String getTemperatureType()
	{
		return temperatureType;
	}

	/**
	 * Sets the temperature type.
	 * 
	 * @param temperatureType the temperatureType to set
	 */
	public void setTemperatureType(String temperatureType)
	{
		this.temperatureType = temperatureType;
	}

	/**
	 * @return the roles
	 */
	public List<String> getRoles()
	{
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles)
	{
		this.roles = roles;
	}

	/**
	 * @return the showDialogPolygon
	 */
	public String getShowDialogPolygon()
	{
		return showDialogPolygon;
	}

	/**
	 * @param showDialogPolygon the showDialogPolygon to set
	 */
	public void setShowDialogPolygon(String showDialogPolygon)
	{
		this.showDialogPolygon = showDialogPolygon;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UserSettings [getBaseLocale()=" + getBaseLocale() + ", getLanguage()=" + getLanguage()
				+ ", getTimezone()=" + getTimezone() + ", getBaseLocaleObject()=" + getBaseLocaleObject()
				+ ", getDateFormat()=" + getDateFormat() + ", getMonitor()=" + getMonitor() + ", getDateFormatMask()="
				+ getDateFormatMask() + ", getPageSize()=" + getPageSize() + ", getTimeZoneHours()="
				+ getTimeZoneHours() + ", getTimeZoneMinutes()=" + getTimeZoneMinutes() + ", getDefaultLat()="
				+ getDefaultLat() + ", getDefaultLng()=" + getDefaultLng() + ", getTimezoneShort()="
				+ getTimezoneShort() + ", getTenant()=" + getTenant() + ", getServiceType()=" + getServiceType()
				+ ", getTemperatureType()=" + getTemperatureType() + ", getRoles()=" + getRoles()
				+ ", getShowDialogPolygon()=" + getShowDialogPolygon() + "]";
	}

}