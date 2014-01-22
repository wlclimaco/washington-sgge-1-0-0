package com.sensus.lc.controller.util;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sensus.cbof.model.TimeZoneInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.MonitorRequestStatusEnum;
import com.sensus.lc.controller.model.UserSettings;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.settings.model.Setting;

/**
 * The Class PreferencesSessionUtil.
 */
public class PreferencesSessionUtil
{

	/** The Constant USER_CONTEXT. */
	private static final String USER_CONTEXT = "userContext";

	/** The Constant USER_LOCAL_SETTINGS. */
	private static final String USER_SETTINGS = "userSettings";

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
	 * @param settings the properties
	 * @param servletRequest the servlet request
	 * @param storeLanguage the store language
	 */
	public static void setSession(List<Setting> settings, HttpServletRequest servletRequest,
			String serviceType, String tenant, List<String> roles, Boolean storeLanguage)
	{

		HttpSession session = servletRequest.getSession(true);
		UserSettings userSettings = new UserSettings();
		if (!ValidationUtil.isNullOrEmpty(settings))
		{
			for (Setting setting : settings)
			{
				if (setting.getPropertyEnum().equals(PropertyEnum.LANGUAGE))
				{
					String language = setting.getPropertyValue();

					// Language
					userSettings.setLanguage(language);
					// Base Locale
					userSettings.setBaseLocale(language);
					// BaseLocaleObject
					userSettings.setBaseLocaleObject(new Locale(language.substring(0, 2), language.substring(
							THREE,
							FIVE)));

					UserContext userContext = (UserContext)session.getAttribute(USER_CONTEXT);
					userContext.setLocaleString(userSettings.getLanguage());
					session.setAttribute(USER_CONTEXT, userContext);

					continue;
				}

				if (setting.getPropertyEnum().equals(PropertyEnum.MONITOR_REQUEST))
				{
					MonitorRequestStatusEnum monitorRequestEnum = MonitorRequestStatusEnum.enumForValue(Integer
							.parseInt(setting.getPropertyValue()));

					if (!ValidationUtil.isNull(monitorRequestEnum))
					{
						userSettings.setMonitor(monitorRequestEnum.getValue()); // Monitor
						continue;
					}

					userSettings.setMonitor(MonitorRequestStatusEnum.PROMPT.getValue()); // Default Monitor Value
					continue;
				}

				if (setting.getPropertyEnum().equals(PropertyEnum.DATE_FORMAT))
				{
					userSettings.setDateFormatMask(setting.getPropertyValue().toLowerCase()); // Date Format Mask

					continue;
				}

				if (setting.getPropertyEnum().equals(PropertyEnum.TIME_ZONE))
				{
					TimeZoneInfo timeZoneInfo = new TimeZoneInfo(setting.getPropertyValue());
					String timezoneShort = new TimeZoneInfo(setting.getPropertyValue()).getDisplayNameShort();

					userSettings.setTimezone(timeZoneInfo.getTimeZone().getID()); // Time Zone
					userSettings.setTimezoneShort(timezoneShort); // Time Zone Short
					userSettings.setTimeZoneHours(getTimeZone(timeZoneInfo.getTimeZone().getID())); // Time Zone
																									// Hours
					userSettings.setTimeZoneMinutes(getTimeZoneMinutes(timeZoneInfo.getTimeZone().getID())); // Time
																												// Zone
					continue;
				}

				if (setting.getPropertyEnum().equals(PropertyEnum.PAGE_SIZE))
				{
					userSettings.setPageSize(Integer.parseInt(setting.getPropertyValue())); // Page Size
					continue;
				}

				if (setting.getPropertyEnum().equals(PropertyEnum.LATITUDE))
				{
					userSettings.setDefaultLat(setting.getPropertyValue()); // Latitude
					continue;
				}

				if (setting.getPropertyEnum().equals(PropertyEnum.LONGITUDE))
				{
					userSettings.setDefaultLng(setting.getPropertyValue()); // Longitude
					continue;
				}

			}
		}
		else
		{
			userSettings =
					(UserSettings)RequestContextHolder.currentRequestAttributes().getAttribute(
							USER_SETTINGS, RequestAttributes.SCOPE_SESSION);
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
		if (!ValidationUtil.isNull(userSettings.getBaseLocaleObject()))
		{
			session.setAttribute("WW_TRANS_I18N_LOCALE", userSettings.getBaseLocaleObject());
		}

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
