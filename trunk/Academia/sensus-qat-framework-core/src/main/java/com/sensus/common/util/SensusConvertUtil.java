package com.sensus.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sensus.common.validation.ValidationUtil;

/**
 * Utility class to convert values. See the individual methods for details.
 */
public final class SensusConvertUtil
{

	/** The Constant H_MM_A. */
	private static final String H_MM_A = "h:mm a";

	/**
	 * Required to insure no one tries to use this class inappropriately and quites Checkstyle.
	 */
	private SensusConvertUtil()
	{
	}

	/** The Constant YES. */
	private static final String YES = "YES";

	/** The Constant Y. */
	private static final String Y = "Y";

	/** The Constant true. */
	private static final String TRUE = "TRUE";

	/** The Constant T. */
	private static final String T = "T";

	/** The Constant NO. */
	private static final String NO = "NO";

	/** The Constant N. */
	private static final String N = "N";

	/** The Constant false. */
	private static final String FALSE = "FALSE";

	/** The Constant F. */
	private static final String F = "F";

	/** The constant UTC_OFFSET. */
	private static final Integer UTC_OFFSET = 3600000;

	/**
	 * The Constant validValues.
	 *
	 * @param value the value
	 * @return the boolean
	 */
	// private static final String validValues = "Y, YES, N, NO, T, TRUE, F, FALSE";

	/**
	 * Convert input parameter to a Boolean value type if the value is recognizable. <br/>
	 * Valid values that are recognized include: Y, YES, N, NO, T, TRUE, F, FALSE<br/>
	 * The conversion is not case sensitive.
	 *
	 * @param value - The value to convert
	 *
	 * @return A Boolean type object if the input parameter value is recognizable, else return null.
	 */
	public static Boolean toBoolean(String value)
	{
		if (value == null)
		{
			return false;
		}
		else if (Y.equalsIgnoreCase(value) || YES.equalsIgnoreCase(value))
		{
			return true;
		}
		else if (N.equalsIgnoreCase(value) || NO.equalsIgnoreCase(value))
		{
			return false;
		}
		else if (T.equalsIgnoreCase(value) || TRUE.equalsIgnoreCase(value))
		{
			return true;
		}
		else if (F.equalsIgnoreCase(value) || FALSE.equalsIgnoreCase(value))
		{
			return false;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Convert from a Boolean value to the standard String value which is either a Y or N.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String fromBoolean(Boolean value)
	{
		if (value == null)
		{
			return N;
		}

		return value.booleanValue() ? Y : N;
	}

	/**
	 * Convert from the parameter type to a double.
	 *
	 * @param value the value
	 * @return the double
	 */
	public static Double toDouble(Integer value)
	{
		if (ValidationUtil.isNull(value))
		{
			return null;
		}
		return new Double(value);
	}

	/**
	 * Convert from the parameter type to a double.
	 *
	 * @param value the value
	 * @return the double
	 */
	public static Double toDouble(String value)
	{
		if (ValidationUtil.isNull(value))
		{
			return null;
		}

		try
		{
			return Double.parseDouble(value);
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}

	/**
	 * Convert the date given its format to a UTC date value.
	 *
	 * @param stringDate the string date
	 * @param dateFormat the date format
	 * @return the date
	 */
	public static Date toUTCDate(String stringDate, String dateFormat)
	{
		if (!ValidationUtil.isNullOrEmpty(stringDate))
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Calendar cal = Calendar.getInstance();
				TimeZone tz = TimeZone.getTimeZone("UTC");
				cal.setTimeZone(tz);
				cal.setTime(sdf.parse(stringDate));
				Date date = cal.getTime();

				return toDefaultUTC(date);
			}
			catch (ParseException e)
			{
				return null;
			}
		}
		return null;
	}

	/**
	 * Convert Date to default UTC.
	 *
	 * @param date the date
	 * @return date
	 */
	public static Date toDefaultUTC(Date date)
	{
		if (!ValidationUtil.isNull(date))
		{
			int diff = (TimeZone.getDefault().getOffset(date.getTime())) / UTC_OFFSET;
			try
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.HOUR_OF_DAY, -1 * diff);
				return calendar.getTime();
			}
			catch (Exception ex)
			{
				return null;
			}
		}
		return null;
	}

	/**
	 * Converts a GregorianCalendar to an XMLGregorianCalendar.
	 *
	 * @param calendar the calendar
	 * @return XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(GregorianCalendar calendar)
	{
		if (!ValidationUtil.isNull(calendar))
		{
			try
			{
				return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
			}
			catch (Exception e)
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * Convert date to server time zone.
	 *
	 * @param date the date
	 * @return the date
	 */
	public static Date toServerTimeZone(Date date)
	{
		if (!ValidationUtil.isNull(date))
		{
			int diff = (TimeZone.getDefault().getOffset(date.getTime())) / UTC_OFFSET;
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
		return null;
	}

	/**
	 * Convert date to specified time zone.
	 *
	 * @param timeZone the time zone
	 * @param date the date
	 * @return the date
	 */
	public static Date toServerTimeZone(String timeZone, Date date)
	{
		int diff2 = 0;
		if (!ValidationUtil.isNull(timeZone))
		{
			TimeZone tz = TimeZone.getTimeZone(TimeZone.getAvailableIDs(new Integer(timeZone) * UTC_OFFSET)[0]);
			diff2 = tz.getOffset(date.getTime()) / UTC_OFFSET;
		}

		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, diff2);
			return calendar.getTime();
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Convert string to date.
	 *
	 * @param stringDate the string date
	 * @param dateFormat the date format
	 * @return the date
	 */
	public static Date toDate(String stringDate, String dateFormat)
	{
		if (!ValidationUtil.isNullOrEmpty(stringDate))
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(stringDate));
				return cal.getTime();
			}
			catch (ParseException e)
			{
				return null;
			}
		}
		return null;
	}

	/**
	 * Converts the parameter string date, given its format, to a date and back to its string representation.
	 *
	 * @param stringDate the string date
	 * @param dateFormat the date format
	 * @return the string
	 */
	public static String toDateString(String stringDate, String dateFormat)
	{
		if (!ValidationUtil.isNullOrEmpty(stringDate))
		{
			Date myDate = toDate(stringDate, dateFormat);
			if (myDate != null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				return sdf.format(myDate);
			}
		}
		return null;
	}

	/**
	 * Convert date to string using the given format.
	 *
	 * @param stringDate the string date
	 * @param dateFormat the date format
	 * @return the string
	 */
	public static String toDateString(Date stringDate, String dateFormat)
	{
		if (!ValidationUtil.isNull(stringDate))
		{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Calendar cal = Calendar.getInstance();
			cal.setTime(stringDate);

			return sdf.format(cal.getTime());
		}
		return null;
	}

	/**
	 * Convert date to string with time zone info.
	 *
	 * @param date the date
	 * @param timeZone the time zone.
	 * @param dateFormat the date format
	 * @return the string
	 */
	public static String toDateString(Date date, String timeZone, String dateFormat)
	{
		if (!ValidationUtil.isNull(date))
		{
			return toDateString(toServerTimeZone(timeZone, date), dateFormat);
		}

		return null;
	}

	/**
	 * Applies the timezone to the date and returns only the time portion of the value.
	 *
	 * @param date the date
	 * @param timeZone the time zone.
	 * @return the string
	 */
	public static String toTimeString(Date date, String timeZone)
	{
		if (ValidationUtil.isNull(date))
		{
			return null;
		}

		Date myDate = toServerTimeZone(timeZone, date);
		SimpleDateFormat sdf = new SimpleDateFormat(H_MM_A);

		return sdf.format(myDate);
	}

	/**
	 * Applies the timezone to the date and returns only the time portion of the value using the given time format
	 * string.
	 *
	 * @param date the date
	 * @param timeZone the time zone
	 * @param timeFormat the time format
	 * @return the string
	 */
	public static String toTimeString(Date date, String timeZone, String timeFormat)
	{
		if (ValidationUtil.isNull(date))
		{
			return null;
		}

		Date myDate = toServerTimeZone(timeZone, date);
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);

		return sdf.format(myDate);
	}

	/**
	 * Return only the time portion of the given date value using an AM/PM format.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String toTimeString(Date date)
	{
		// if the date if null, return
		if (ValidationUtil.isNull(date))
		{
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(H_MM_A);

		return sdf.format(date);

	}
}
