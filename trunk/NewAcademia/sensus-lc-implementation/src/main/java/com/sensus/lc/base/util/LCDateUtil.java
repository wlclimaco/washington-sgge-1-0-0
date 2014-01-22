package com.sensus.lc.base.util;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.util.SensusConvertUtil;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.common.validation.ValidationUtil;

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

	/** The Constant DEFAULT_HOUR_FORMAT_TO_CSV. */
	public static final String DEFAULT_HOUR_FORMAT_TO_CSV = "h:mm:ss:SSS a";

	/** The Constant DEFAULT_DATETIME_FORMAT. */
	public static final String DEFAULT_DATETIME_FORMAT = DEFAULT_DATE_FORMAT + " " + DEFAULT_HOUR_FORMAT;

	/** The Constant DEFAULT_DATETIME_FORMAT_TO_CSV. */
	public static final String DEFAULT_DATETIME_FORMAT_TO_CSV = DEFAULT_DATE_FORMAT + " " + DEFAULT_HOUR_FORMAT_TO_CSV;

	/** The Constant DEFAULT_SYSTEM_DATE_PATTERN. */
	public static final String DEFAULT_TIMEZONE_DATE_PATTERN = "EEE MMM dd HH:mm:ss z yyyy";

	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(LCDateUtil.class);

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
	public static String convertDateToString(Date date)
	{
		return SensusConvertUtil.toDateString(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * Convert date to string.
	 * 
	 * @param date the date
	 * @param timeZone the time zone
	 * @param dateFormat the date format
	 * @return the string
	 */
	public static String convertDateToString(Date date, String timeZone, String dateFormat)
	{
		if (ValidationUtil.isNull(date))
		{
			return null;
		}

		return convertDateToString(LCDateUtil.convertDateUTCToTimezone(date, timeZone), dateFormat);
	}

	/**
	 * Convert date to string.
	 * 
	 * @param strDate the str date
	 * @param dateFormat the date format
	 * @return the string
	 */
	public static String convertDateToString(Date strDate, String dateFormat)
	{
		if (!ValidationUtil.isNull(strDate))
		{
			DateFormat sdf = new SimpleDateFormat(dateFormat);
			Calendar cal = Calendar.getInstance();
			cal.setTime(strDate);

			return sdf.format(cal.getTime());
		}
		return null;
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

		int diff = TimeZone.getDefault().getOffset(date.getTime()) / UTC_OFFSET;
		try
		{
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, -1 * diff);
			return calendar.getTime();
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ex.getMessage());
			}
			return null;
		}
	}

	public static Date convertDateUTCToTimezone(Date date, String timeZone)
	{
		if (isNull(date) || isNull(timeZone))
		{
			return null;
		}

		int diff = TimeZone.getTimeZone(timeZone).getOffset(date.getTime()) / UTC_OFFSET;
		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, diff);
			return calendar.getTime();
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ex.getMessage());
			}
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
		if (ValidationUtil.isNull(hour)
				|| ValidationUtil.isNull(min)
				|| ValidationUtil.isNull(lightTimeZone))
		{
			return null;
		}

		int diff = TimeZone.getTimeZone(lightTimeZone).getOffset(new Date().getTime()) / UTC_OFFSET;

		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, min);

		c.add(Calendar.HOUR_OF_DAY, -1 * diff);

		String hourText = StringUtils.leftPad(String.valueOf(c.get(Calendar.HOUR_OF_DAY)), 2, ZERO);
		String minText = StringUtils.leftPad(String.valueOf(c.get(Calendar.MINUTE)), 2, ZERO);
		String time = String.format("%s:%s:00", hourText, minText);

		return time;
	}

	/**
	 * Gets the month days.
	 * 
	 * @param endDate the end date
	 * @return the month days
	 */
	public static long getMonthDaysAmount(Date endDate)
	{
		if (ValidationUtil.isNull(endDate))
		{
			return 0;
		}

		Calendar initial = Calendar.getInstance();
		initial.setTime(endDate);
		initial.add(java.util.Calendar.MONDAY, -1);

		return SensusDateUtil.getDaysDiff(initial.getTime(), endDate);
	}

}
