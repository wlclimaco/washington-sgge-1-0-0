package com.sensus.mlc.wui.base.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MonitorRequestStatusEnum;
import com.sensus.mlc.base.model.PageSizeDialogEnum;
import com.sensus.mlc.base.model.TimeZoneInfo;
import com.sensus.mlc.base.model.UserPreferences;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.wui.base.model.UserSettings;

public class PreferencesSessionUtil
{

	/** The Constant DATE_FORMAT_PT. */
	private static final String DATE_FORMAT_PT = "dd/MM/yyyy";

	/** The Constant DATE_FORMAT_EN. */
	private static final String DATE_FORMAT_EN = "MM/dd/yyyy";

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

	public static void setSession(List<Setting> settings, HttpServletRequest servletRequest, Boolean storeLanguage)
	{

		String dateNow = null;
		String timezoneShort = null;
		String dateFormatMask = null;
		String diff = "";
		UserPreferences userPreferences = new UserPreferences();

		for (Setting setting : settings)
		{
			if (setting.getPropertyEnum().equals(PropertyEnum.LANGUAGE) && storeLanguage)
			{
				userPreferences.setLanguage(setting.getPropertyValue());
			}
			else if (setting.getPropertyEnum().equals(PropertyEnum.MONITOR_REQUEST))
			{
				userPreferences.setMonitorRequestStatusEnum(MonitorRequestStatusEnum
						.enumForValue(Integer
								.parseInt(setting.getPropertyValue())));
			}
			else if (setting.getPropertyEnum().equals(PropertyEnum.DATE_FORMAT))
			{
				userPreferences.setDateFormat(getLocaleDateFormat(setting.getPropertyValue()));
				dateFormatMask = setting.getPropertyValue().toLowerCase().replace("yyyy", "yy");
			}
			else if (setting.getPropertyEnum().equals(PropertyEnum.TIME_ZONE))
			{
				userPreferences.setTimeZoneInfo(new TimeZoneInfo(setting.getPropertyValue()));

				timezoneShort = new TimeZoneInfo(setting.getPropertyValue()).getDisplayNameShort();

				Date date = new Date();
				DateFormat tzFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
				TimeZone tzTime = TimeZone.getTimeZone(setting.getPropertyValue());
				tzFormat.setTimeZone(tzTime);

				TimeZone tz = TimeZone.getTimeZone(setting.getPropertyValue());
				diff = String.valueOf(tz.getOffset((new Date()).getTime()) / 3600000);

				dateNow = tzFormat.format(date);
			}
			else if (setting.getPropertyEnum().equals(PropertyEnum.CONVERT_ENERGY_UNIT))
			{
				userPreferences.setConvertUnit(setting.getPropertyValue());
			}
			else if (setting.getPropertyEnum().equals(PropertyEnum.PAGE_SIZE))
			{
				userPreferences.setPageSize(Integer.valueOf(setting.getPropertyValue()));
			}
			else if (setting.getPropertyEnum().equals(PropertyEnum.PAGE_SIZE_LIST))
			{
				userPreferences.setPageSizeList(setting.getPropertyValue());
			}
			else if (setting.getPropertyEnum().equals(PropertyEnum.PAGE_SIZE_SHOW_DIALOG))
			{
				userPreferences.setPageSizeDialogEnum(PageSizeDialogEnum
						.enumForValue(Integer
								.parseInt(setting.getPropertyValue())));
			}
			else if (setting.getPropertyEnum().equals(PropertyEnum.SHOW_DIALOG_POLYGON))
			{

				userPreferences.setPolygonDialog(setting.getPropertyValue());
			}

		}

		UserSettings userSettings = new UserSettings();

		// Save Language at session
		if (storeLanguage && !ValidationUtil.isNull(userPreferences.getLanguage()))
		{
			userSettings.setBaseLocale(userPreferences.getLanguage());
			userSettings.setBaseLocaleObject(new Locale(userPreferences.getLanguage().substring(0, 2),
					userPreferences.getLanguage().substring(THREE, FIVE)));
		}
		else
		{
			Map<String, Object> baseLocaleSession = ActionContext.getContext().getSession();

			UserSettings userSettingsSession = (UserSettings)baseLocaleSession.get("userLocaleSettings");

			if (!ValidationUtil.isNull(userSettingsSession))
			{
				userSettings.setBaseLocale(userSettingsSession.getBaseLocale());
				userSettings.setBaseLocaleObject(userSettingsSession.getBaseLocaleObject());
			}

		}

		TimeZoneInfo timeZoneInfo = userPreferences.getTimeZoneInfo();
		if (!ValidationUtil.isNull(timeZoneInfo) && !ValidationUtil.isNull(timeZoneInfo.getTimeZone()))
		{
			userSettings.setTimezone(timeZoneInfo.getTimeZone().getID());
		}

		if (!ValidationUtil.isNull(userPreferences.getMonitorRequestStatusEnum()))
		{
			userSettings.setMonitor(userPreferences.getMonitorRequestStatusEnum().getValue());
		}

		userSettings.setDateFormat(userPreferences.getDateFormat());
		userSettings.setDateFormatMask(dateFormatMask);
		userSettings.setConvertUnit(userPreferences.getConvertUnit());
		userSettings.setPageSize(userPreferences.getPageSize());

		if (!ValidationUtil.isNull(userPreferences.getPolygonDialog()))
		{
			userSettings.setPolygonDialog(userPreferences.getPolygonDialog());
		}

		if (!ValidationUtil.isNull(userPreferences.getPageSizeList()))
		{
			userSettings.setPageSizeList(userPreferences.getPageSizeList());
		}

		if (!ValidationUtil.isNull(userPreferences.getPageSizeDialogEnum()))
		{
			userSettings.setPageSizeShowDialog(userPreferences.getPageSizeDialogEnum().getValue());// I need
																									// PageSizeDialogEnum.PROMPT
		}

		userSettings.setTimeZoneHours(diff);

		if (!ValidationUtil.isNull(timezoneShort))
		{
			userSettings.setTimezoneShort(timezoneShort);
		}

		userSettings.setDateNow(dateNow);

		// put the user preferences object in the session
		HttpSession session = servletRequest.getSession(true);
		session.setAttribute("userLocaleSettings", userSettings);
		session.setAttribute("WW_TRANS_I18N_LOCALE", userSettings.getBaseLocaleObject());
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
