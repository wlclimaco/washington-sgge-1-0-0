package com.sensus.dm.controller.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.sensus.cbof.model.Device;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.gateway.type.converter.XMLGregorianCalendarConverter;

/**
 * The Class EPMConvertUtil.
 */
public final class DMConvertUtil
{

	/** The Constant DATE_FORMAT_EN. */
	private static final String DATE_FORMAT_EN = "MM/dd/yyyy";

	/** The Constant DATE_FORMAT_PT. */
	private static final String DATE_FORMAT_PT = "dd/MM/yyyy";

	/** The constant UTC_OFFSET. */
	private static final Integer UTC_OFFSET = 3600000;

	/** The Constant THIRTEEN. */
	private static final int THIRTEEN = 13;

	/** The Constant STRING_ZERO. */
	private static final String STRING_ZERO = "0";

	/** The Constant TEN. */
	private static final int TEN = 10;

	/**
	 * Gets the locale date format.
	 * 
	 * @param dateFormat the date format
	 * @param request the request
	 * @return the locale date format
	 */
	public static String getDateFormat(String dateFormat, HttpServletRequest request)
	{
		if (dateFormat.equalsIgnoreCase(DATE_FORMAT_EN))
		{
			return DATE_FORMAT_EN;
		}
		else if (dateFormat.equalsIgnoreCase(DATE_FORMAT_PT))
		{
			return DATE_FORMAT_PT;
		}

		return null;
	}

	/**
	 * Convert date to default utc.
	 * 
	 * @param date the date
	 * @return the date
	 */
	public static Date convertDateToDefaultUTC(Date date)
	{
		int diff = TimeZone.getDefault().getOffset(date.getTime()) / 60000;

		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, -1 * diff);
			return calendar.getTime();
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Convert date to utc.
	 * 
	 * @param date the date
	 * @param offset the offset - time zone of system in minutes
	 * @return the date - date passed to UTC according system time zone
	 */
	public static Date convertDateToUTC(Date date, Integer offset)
	{
		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, -1 * offset);
			return calendar.getTime();
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Convert date to server time zone.
	 * 
	 * @param date the date
	 * @param timezone the timezone
	 * @return the date
	 */
	public static Date convertDateToServerTimeZone(Date date, String timezone)
	{
		int diff2 = 0;
		int diff = TimeZone.getDefault().getOffset(date.getTime()) / UTC_OFFSET;

		if (!ValidationUtil.isNull(timezone))
		{
			TimeZone tz = TimeZone.getTimeZone(timezone);
			diff2 = tz.getOffset(date.getTime()) / UTC_OFFSET;
		}

		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, diff - diff2);
			return calendar.getTime();
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Convert date time zone.
	 * 
	 * @param date the date
	 * @param timezone the timezone
	 * @return the date
	 */
	public static Date convertDateTimeZone(Date date, String timezone)
	{
		return convertDateToDefaultUTC(convertDateToServerTimeZone(date, timezone));
	}

	/**
	 * Convert date to long.
	 * 
	 * @param date the date
	 * @return the long
	 */
	public static Long convertDateToLong(Date date)
	{
		return convertDateToLong(date, Calendar.getInstance().getTimeZone().getID());
	}

	/**
	 * Convert date to long.
	 * 
	 * @param date the date
	 * @param timeZone the time zone
	 * @return the long
	 */
	public static Long convertDateToLong(Date date, String timeZone)
	{
		long longDate = date.getTime();
		String timeStamp = String.valueOf(longDate + TimeZone.getTimeZone(timeZone).getOffset(longDate));

		return Long.parseLong(timeStamp);
	}

	/**
	 * Convert date to integer.
	 * 
	 * @param date the date
	 * @return the long
	 */
	public static int convertDateToInteger(Date date)
	{
		return convertDateToInteger(date, Calendar.getInstance().getTimeZone().getID());
	}

	/**
	 * Convert date to integer.
	 * 
	 * @param date the date
	 * @param timeZone the time zone
	 * @return the long
	 */
	public static int convertDateToInteger(Date date, String timeZone)
	{
		long longDate = date.getTime();
		String timeStamp = String.valueOf(longDate + TimeZone.getTimeZone(timeZone).getOffset(longDate));

		return Integer.parseInt(StringUtils.mid(timeStamp, 0, TEN));
	}

	/**
	 * Convert integer to date.
	 * 
	 * @param value the value
	 * @return the date
	 */
	public static Date convertIntegerToDate(long value)
	{
		String str = String.valueOf(value);

		if (str.length() < THIRTEEN)
		{
			value = Long.parseLong(StringUtils.rightPad(str, THIRTEEN, STRING_ZERO));
		}

		XMLGregorianCalendarConverter calendarConverter = new XMLGregorianCalendarConverter();
		GregorianCalendar gregorianCalendar = calendarConverter.convert(value).toGregorianCalendar();

		return gregorianCalendar.getTime();
	}

	/**
	 * Gets the end of day.
	 * 
	 * @param date the date
	 * @return the end of day
	 */
	public static Date getEndOfDay(Date date, String timeZoneMinut)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		if (Integer.parseInt(timeZoneMinut) < 0)
		{
			calendar.setTimeInMillis(calendar.getTimeInMillis() + (Integer.parseInt(timeZoneMinut) * -1 * 60 * 1000));
		}
		else
		{
			calendar.setTimeInMillis(calendar.getTimeInMillis() - (Integer.parseInt(timeZoneMinut) * 60 * 1000));
		}
		return calendar.getTime();
	}

	/**
	 * Gets the start of day.
	 * 
	 * @param date the date
	 * @return the start of day
	 */
	public static Date getStartOfDay(Date date, String timeZoneMinut)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if (Integer.parseInt(timeZoneMinut) < 0)
		{
			calendar.setTimeInMillis(calendar.getTimeInMillis() + (Integer.parseInt(timeZoneMinut) * -1 * 60 * 1000));
		}
		else
		{
			calendar.setTimeInMillis(calendar.getTimeInMillis() - (Integer.parseInt(timeZoneMinut) * 60 * 1000));
		}
		return calendar.getTime();
	}

	/**
	 * Convert MAC Address.
	 * 
	 * @param deviceResponse the device response
	 */
	public static void convertMacAddress(DeviceResponse deviceResponse)
	{
		if (!ValidationUtil.isNull(deviceResponse.getDevices()))
		{
			for (Device device : deviceResponse.getDevices())
			{
				DMUtil.convertEletricMacAddress(device);
			}
		}
	}

	/**
	 * Convert time zone to minutes.
	 * 
	 * @param deviceResponse the device response
	 */
	public static void convertTimeZoneToMinutes(List<Device> deviceList)
	{
		if (!ValidationUtil.isNull(deviceList))
		{
			for (Device device : deviceList)
			{
				DMUtil.convertTimeZoneToMinutes(device);
			}
		}
	}
}