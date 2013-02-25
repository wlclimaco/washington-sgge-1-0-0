package com.sensus.mlc.wui.base.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.wui.base.model.UserSettings;

/**
 * Utility for localizing dates, numbers and other information.
 * 
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public final class LocalizationUtil
{
	/**
	 * Private constructor to prevent instantiation.
	 */
	private LocalizationUtil()
	{
	}

	/**
	 * Return the user's locale settings based on application defaults and user-session specific settings.
	 * 
	 * @param request the request
	 * @return the resulting locale settings
	 */
	@SuppressWarnings("unchecked")
	public static UserSettings getUserLocaleSettings(HttpServletRequest request)
	{
		HttpSession session = request.getSession(true);
		UserSettings userLocaleSettings =
				(UserSettings)session.getAttribute(Constants.SESSION_USER_LOCALE_SETTINGS);
		return userLocaleSettings;
	}

	/**
	 * Obtain a localized Date formatter for formatting dates in the "SHORT" form based on application defaults and
	 * user-session specific settings.
	 * 
	 * @param request the request
	 * @return the localized date formatter
	 * @see DateFormat.SHORT
	 */
	@SuppressWarnings("unchecked")
	public static DateFormat getUIDateFormat(HttpServletRequest request)
	{
		if (!ValidationUtil.isNull(getUserLocaleSettings(request).getDateFormat()))
		{
			return getUserLocaleSettings(request).getDateFormat();
		}
		else
		{
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			return df;
		}
	}

	/**
	 * Gets the uI prompt monitor.
	 * 
	 * @param request the request
	 * @return the uI prompt monitor
	 */
	@SuppressWarnings("unchecked")
	public static Integer getUIPromptMonitor(HttpServletRequest request)
	{
		return getUserLocaleSettings(request).getMonitor();
	}

	/**
	 * Obtain the active time zone based on application defaults and user-session specific settings.
	 * 
	 * @param request the request
	 * @return the active time zone
	 */
	@SuppressWarnings("unchecked")
	public static TimeZone getUITimezone(HttpServletRequest request)
	{
		return TimeZone.getTimeZone(getUserLocaleSettings(request)
				.getTimezone());
	}

	/**
	 * Obtain a localized number formatter for formatting dates in the "SHORT" form based on application defaults and
	 * user-session specific settings.
	 * 
	 * @param request the request
	 * @param type the number type to instantiate the formatter for
	 * @return the uI number format
	 */
	@SuppressWarnings("unchecked")
	public static NumberFormat getUINumberFormat(HttpServletRequest request, NumberFormatType type)
	{
		if (type == NumberFormatType.DECIMAL)
		{
			return NumberFormat.getInstance(getUserLocaleSettings(request).getBaseLocaleObject());
		}
		if (type == NumberFormatType.INTEGER)
		{
			return NumberFormat.getIntegerInstance(getUserLocaleSettings(request).getBaseLocaleObject());
		}
		if (type == NumberFormatType.PERCENT)
		{
			return NumberFormat.getPercentInstance(getUserLocaleSettings(request).getBaseLocaleObject());
		}
		if (type == NumberFormatType.CURRENCY)
		{
			return NumberFormat.getCurrencyInstance(getUserLocaleSettings(request).getBaseLocaleObject());
		}
		return NumberFormat.getInstance(getUserLocaleSettings(request).getBaseLocaleObject());
	}

	/**
	 * Create the locale string from language and country.
	 * 
	 * @param language the language code (for example, "en")
	 * @param country the language code (for example, "US"). Can be null.
	 * @return the locale string
	 */
	public static String getLocaleString(String language, String country)
	{
		if (ValidationUtil.isNullOrEmpty(country))
		{
			return language;
		}
		return String.format("%s_%s", language, country);
	}

	/**
	 * Represents the different number formats that this utility can return.
	 * 
	 * @author Anke Doerfel-Parker
	 * 
	 */
	public enum NumberFormatType
	{

		/** The DECIMAL. */
		DECIMAL,
		/** The INTEGER. */
		INTEGER,
		/** The CURRENCY. */
		CURRENCY,
		/** The PERCENT. */
		PERCENT;
	}

}
