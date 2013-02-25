package com.sensus.mlc.base.util;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.xwork.StringUtils;

import com.sensus.common.util.SensusConvertUtil;

/**
 * The Class LCConstants.
 */
public final class LCDateUtil
{
	/** The constant UTC_OFFSET. */
	private static final Integer UTC_OFFSET = 3600000;

	/** The Constant ZERO. */
	private static final String ZERO = "0";

	/** The Constant DEFAULT_DATE_PATTERN_TO_FILENAME. */
	public static final String DEFAULT_DATE_PATTERN_TO_FILENAME = "yyyy_MM_dd_HH_mm_ss_SSS_z";

	/** The Constant DATE_FORMAT. */
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

	/** The Constant HOUR_FORMAT. */
	public static final String DEFAULT_HOUR_FORMAT = "h:mm:ss.SSS a";

	/** The Constant DEFAULT_DATETIME_FORMAT. */
	public static final String DEFAULT_DATETIME_FORMAT = DEFAULT_DATE_FORMAT + " " + DEFAULT_HOUR_FORMAT;

	/** The Constant DEFAULT_SYSTEM_DATE_PATTERN. */
	public static final String DEFAULT_TIMEZONE_DATE_PATTERN = "EEE MMM dd HH:mm:ss z yyyy";

	/**
	 * Instantiates a new lc date util.
	 */
	private LCDateUtil()
	{
	}

	/**
	 * Convert string to date.<br>
	 * Use pattern: EEE MMM dd HH:mm:ss z yyyy
	 *
	 * @param dateString the date string
	 * @return the date
	 */
	public static Date convertStringToDate(String dateString)
	{
		return SensusConvertUtil.toDate(dateString, DEFAULT_TIMEZONE_DATE_PATTERN);
	}

	/**
	 * Conver date to string.
	 * Use pattern: EEE MMM dd HH:mm:ss z yyyy
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String converDateToString(Date date)
	{
		return SensusConvertUtil.toDateString(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * Gets the new date utc.
	 *
	 * @return the new date utc
	 *         This method is needed for unify the shape of instantiate a new date.
	 */
	public static Date getNewDateUTC()
	{
		return convertDateToDefaultUTC(new Date());
	}

	/**
	 * Convert Date to default UTC.
	 *
	 * @param date the date
	 * @return date
	 */
	public static Date convertDateToDefaultUTC(Date date)
	{
		if (isNull(date))
		{
			return null;
		}

		int diff = (TimeZone.getDefault().getOffset(date.getTime())) / UTC_OFFSET;
		try
		{
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, -1 * diff);
			return calendar.getTime();
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Convert date utc to timezone.
	 *
	 * @param date the date
	 * @param timezone the timezone
	 * @return the date
	 */
	public static Date convertDateUTCToTimezone(Date date, String timezone)
	{
		if (isNull(date) || isNull(timezone))
		{
			return null;
		}

		int diff = (TimeZone.getTimeZone(timezone)).getOffset(date.getTime()) / UTC_OFFSET;
		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, diff);
			return calendar.getTime();
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Convert sunrise sunset to default utc.
	 *
	 * @param hour the hour
	 * @param min the min
	 * @param lightTimeZone the light time zone
	 * @return the string
	 */
	public static String convertSunriseSunsetToDefaultUTC(Integer hour, Integer min, String lightTimeZone)
	{

		int diff = (TimeZone.getTimeZone(lightTimeZone)).getOffset(new Date().getTime()) / UTC_OFFSET;

		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY,hour);
		c.set(Calendar.MINUTE,min);

		c.add(Calendar.HOUR_OF_DAY, -1 * diff);

		String hourText = StringUtils.leftPad(String.valueOf(c.get(Calendar.HOUR_OF_DAY)), 2, ZERO);
		String minText = StringUtils.leftPad(String.valueOf(c.get(Calendar.MINUTE)), 2, ZERO);
		String time = String.format("%s:%s:00", hourText, minText);


		return time;
	}
}
