package com.sensus.dm.controller.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sensus.cbof.model.TimeZoneInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.MonitorRequestStatusEnum;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.controller.model.UserSettings;

/**
 * The Class PreferencesSessionUtil.
 */
public class PreferencesSessionUtil
{

	/** The Constant I18N_LOCALE. */
	private static final String I18N_LOCALE = "WW_TRANS_I18N_LOCALE";

	/** The Constant DATE_FORMAT_PT. */
	private static final String DATE_FORMAT_PT = "dd/mm/yyyy";

	/** The Constant DATE_FORMAT_EN. */
	private static final String DATE_FORMAT_EN = "mm/dd/yyyy";

	/** The Constant USER_LOCAL_SETTINGS. */
	public static final String USER_SETTINGS = "userSettings";

	/** The constant UTC_OFFSET. */
	private static final Integer UTC_OFFSET = 3600000;

	private static final Integer UTC_OFFSET_MINUTES = 60000;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/** The locale language. */
	private String localeLanguage;

	/**
	 * Gets the locale date format.
	 * 
	 * @param dateFormat the date format
	 * @return the locale date format
	 */
	private static DateFormat getLocaleDateFormat(String dateFormat)
	{
		if (dateFormat.equalsIgnoreCase(DATE_FORMAT_EN))
		{
			return new SimpleDateFormat(DATE_FORMAT_EN);
		}
		else if (dateFormat.equalsIgnoreCase(DATE_FORMAT_PT))
		{
			return new SimpleDateFormat(DATE_FORMAT_PT);
		}

		return null;
	}

	/**
	 * Gets the time zone.
	 * 
	 * @return the time zone
	 */
	public static String getTimeZone(String timeZone)
	{
		TimeZone tz = TimeZone.getTimeZone(timeZone);
		return String.valueOf(tz.getOffset(new Date().getTime()) / UTC_OFFSET);
	}

	/**
	 * Gets the time zone.
	 * 
	 * @return the time zone
	 */
	public static String getTimeZoneMinutes(String timeZone)
	{
		TimeZone tz = TimeZone.getTimeZone(timeZone);
		return String.valueOf(tz.getOffset(new Date().getTime()) / UTC_OFFSET_MINUTES);
	}

	/**
	 * Sets the session.
	 * 
	 * @param properties the properties
	 * @param servletRequest the servlet request
	 * @param storeLanguage the store language
	 */
	public static void setSession(List<Property> properties, HttpServletRequest servletRequest,
			String serviceType, String tenant, List<String> roles, Boolean storeLanguage)
	{

		HttpSession session = servletRequest.getSession(true);
		UserSettings userSettings;

		if (!ValidationUtil.isNullOrEmpty(properties))
		{
			userSettings = new UserSettings();

			for (Property property : properties)
			{
				if (property.getPropertyName().equals(PropertyEnum.LANGUAGE.toString()))
				{
					String language = property.getPropertyValue();

					// Language
					userSettings.setLanguage(language);
					// Base Locale
					userSettings.setBaseLocale(language);
					// BaseLocaleObject
					userSettings.setBaseLocaleObject(new Locale(language.substring(0, 2), language.substring(
							THREE,
							FIVE)));

					continue;
				}

				if (property.getPropertyName().equals(PropertyEnum.MONITOR_REQUEST.toString()))
				{

					MonitorRequestStatusEnum monitorRequestEnum = MonitorRequestStatusEnum.enumForValue(Integer
							.parseInt(property.getPropertyValue()));

					if (!ValidationUtil.isNull(monitorRequestEnum))
					{
						userSettings.setMonitor(monitorRequestEnum.getValue()); // Monitor
						continue;
					}

					userSettings.setMonitor(MonitorRequestStatusEnum.PROMPT.getValue()); // Default Monitor Value
					continue;

				}

				if (property.getPropertyName().equals(PropertyEnum.DATE_FORMAT.toString()))
				{
					userSettings.setDateFormat(getLocaleDateFormat(property.getPropertyValue())); // Date Format
					userSettings.setDateFormatMask(property.getPropertyValue().toLowerCase()); // Date Format Mask

					continue;
				}

				if (property.getPropertyName().equals(PropertyEnum.TIME_ZONE.toString()))
				{
					TimeZoneInfo timeZoneInfo = new TimeZoneInfo(property.getPropertyValue());
					String timezoneShort = new TimeZoneInfo(property.getPropertyValue()).getDisplayNameShort();

					userSettings.setTimezone(timeZoneInfo.getTimeZone().getID()); // Time Zone
					userSettings.setTimezoneShort(timezoneShort); // Time Zone Short
					userSettings.setTimeZoneHours(getTimeZone(timeZoneInfo.getTimeZone().getID())); // Time Zone
																									// Hours
					userSettings.setTimeZoneMinutes(getTimeZoneMinutes(timeZoneInfo.getTimeZone().getID())); // Time
																												// Zone
					// Minutes

					continue;
				}

				if (property.getPropertyName().equals(PropertyEnum.PAGE_SIZE.toString()))
				{
					userSettings.setPageSize(property.getPropertyValue()); // Page Size
					continue;
				}

				if (property.getPropertyName().equals(PropertyEnum.LATITUDE.toString()))
				{
					userSettings.setDefaultLat(property.getPropertyValue()); // Latitude
					continue;
				}

				if (property.getPropertyName().equals(PropertyEnum.LONGITUDE.toString()))
				{
					userSettings.setDefaultLng(property.getPropertyValue()); // Longitude
					continue;
				}

				if (property.getPropertyName().equals(PropertyEnum.TEMPERATURE.toString()))
				{
					userSettings.setTemperatureType(property.getPropertyValue()); // Temperature Format
					continue;
				}

				if (property.getPropertyName().equals(PropertyEnum.SHOW_DIALOG_POLYGON.toString()))
				{
					userSettings.setShowDialogPolygon(property.getPropertyValue()); // Dialog Polygon
				}

			}
		}
		else
		{
			userSettings = (UserSettings)RequestContextHolder.currentRequestAttributes().getAttribute(
					USER_SETTINGS, RequestAttributes.SCOPE_SESSION);
		}

		// Set Service Type
		if (!ValidationUtil.isNullOrEmpty(serviceType))
		{
			userSettings.setServiceType(serviceType);
		}

		// Set Tenant
		if (!ValidationUtil.isNullOrEmpty(tenant))
		{
			userSettings.setTenant(tenant);
		}

		// Set Roles
		if (!ValidationUtil.isNullOrEmpty(roles))
		{
			userSettings.setRoles(roles);
		}

		// put the user preferences object in the session
		session.setAttribute(USER_SETTINGS, userSettings);
		session.setAttribute(I18N_LOCALE, userSettings.getBaseLocaleObject());

	}

	/**
	 * Gets the locale language.
	 * 
	 * @return the localeLanguage
	 */
	public String getLocaleLanguage()
	{
		return localeLanguage;
	}

	/**
	 * Sets the locale language.
	 * 
	 * @param localeLanguage the localeLanguage to set
	 */
	public void setLocaleLanguage(String localeLanguage)
	{
		this.localeLanguage = localeLanguage;
	}
}
