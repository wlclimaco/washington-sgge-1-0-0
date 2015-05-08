package com.prosperitasglobal.sendsolv.integration.twilio.util;

import java.util.Calendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import com.qat.framework.util.QATDateUtil;

/**
 * A PGSi utility class for working with dates. See the individual methods for more information.
 * <p>
 * For more date utilities available for the PGSi application, see {@link QATDateUtil}.
 */
public final class PGSiDateUtil
{
	/** The Constant UTC_TZ. */
	private static final String UTC_TZ = "UTC";

	/** The UTC time zone. */
	private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone(UTC_TZ);

	/**
	 * Private default constructor to keep from having a new one created.
	 */
	private PGSiDateUtil()
	{
	}

	/**
	 * This method will add the number of day (<code></code>) to the date passed in and return the new dates UTC
	 * milliseconds. Date passed in is assumed to be UTC time zone.
	 *
	 * @param dateTime The date/time UTC represented in UTC milliseconds to add to.
	 * @param numberOfDays The number of days to add to the UTC date passed in.
	 * @return The UTC date/time milliseconds.
	 */
	public static long addDays(long dateTime, int numberOfDays)
	{
		return addDays(dateTime, UTC_TZ, numberOfDays);
	}

	/**
	 * This method will add the number of day (<code></code>) to the date passed in and return the new dates UTC
	 * milliseconds.
	 *
	 * @param dateTime The date/time UTC represented in UTC milliseconds to add to.
	 * @param timeZone The time zone of the date/time.
	 * @param numberOfDays The number of days to add to the UTC date passed in.
	 * @return The UTC date/time milliseconds.
	 */
	public static long addDays(long dateTime, String timeZone, int numberOfDays)
	{
		DateTime newDateTime = new DateTime(dateTime, DateTimeZone.forID(timeZone));
		newDateTime = newDateTime.plusDays(numberOfDays);
		return newDateTime.toDate().getTime();
	}

	/**
	 * Compares 2 different dates represented in UTC milliseconds as a {@link Long}.
	 * <p>
	 * NOTE: No time comparison is done. Use method {@link #compareDateTimesUTC(long, long)} if want the date and time
	 * compared.
	 *
	 * @param firstDateUTC The first date of the comparison.
	 * @param secondDateUTC The second date of the comparison.
	 * @return The return from this method conforms to the basic tertiary comparison process. If the 2 dates are the
	 *         same, the value <code>0</code> will be returned. If the first date is less than the second date, a
	 *         negative value will be returned. Finally, if the first date is greater than second date, a positive value
	 *         will be returned.
	 */
	public static long compareDates(long firstDateUTC, long secondDateUTC)
	{
		return compareDates(firstDateUTC, UTC_TZ, secondDateUTC, UTC_TZ);
	}

	/**
	 * Compares 2 different dates represented in UTC milliseconds as a {@link Long}.
	 * <p>
	 * NOTE: No time comparison is done. Use method {@link #compareDateTimesUTC(long, long)} if want the date and time
	 * compared.
	 *
	 * @param firstDateUTC The first date of the comparison.
	 * @param firstDateTimeZone The time zone of the first date.
	 * @param secondDateUTC The second date of the comparison.
	 * @param secondDateTimeZone The time zone of the second date.
	 * @return The return from this method conforms to the basic tertiary comparison process. If the 2 dates are the
	 *         same, the value <code>0</code> will be returned. If the first date is less than the second date, a
	 *         negative value will be returned. Finally, if the first date is greater than second date, a positive value
	 *         will be returned.
	 */
	public static long compareDates(long firstDateUTC, String firstDateTimeZone, long secondDateUTC,
			String secondDateTimeZone)
	{
		LocalDate localFirstDate = new LocalDate(firstDateUTC, DateTimeZone.forID(firstDateTimeZone));
		LocalDate localSecondDate = new LocalDate(secondDateUTC, DateTimeZone.forID(secondDateTimeZone));
		return localFirstDate.compareTo(localSecondDate);
	}

	/**
	 * Compares 2 different dates represented in UTC milliseconds as a {@link Long}.
	 * <p>
	 * NOTE: No time comparison is done. Use method {@link #compareDateTimesUTC(long, long)} if want the date and time
	 * compared.
	 *
	 * @param firstDateUTC The first date of the comparison.
	 * @param secondDateUTC The second date of the comparison.
	 * @return The return from this method conforms to the basic tertiary comparison process. If the 2 dates are the
	 *         same, the value <code>0</code> will be returned. If the first date is less than the second date, a
	 *         negative value will be returned. Finally, if the first date is greater than second date, a positive value
	 *         will be returned.
	 */
	public static long compareDatesUTC(long firstDateUTC, long secondDateUTC)
	{
		return truncateTimeUTC(firstDateUTC) - truncateTimeUTC(secondDateUTC);
	}

	/**
	 * Compares 2 different dates represented in UTC milliseconds as a {@link Long}.
	 * <p>
	 * NOTE: This method will compare using the date and time portion of the date. Use method
	 * {@link #compareDatesUTC(long, long)} if you only want to compare the dates.
	 *
	 * @param firstDateTimeUTC The first date of the comparison.
	 * @param secondDateTimeUTC The second date of the comparison.
	 * @return The return from this method conforms to the basic tertiary comparison process. If the 2 dates are the
	 *         same, the value <code>0</code> will be returned. If the first date is less than the second date, a
	 *         negative value will be returned. Finally, if the first date is greater than second date, a positive value
	 *         will be returned.
	 */
	public static long compareDateTimesUTC(long firstDateTimeUTC, long secondDateTimeUTC)
	{
		return firstDateTimeUTC - secondDateTimeUTC;
	}

	/**
	 * This method will return the current date and time as milliseconds.
	 *
	 * @return The current date and time as milliseconds.
	 */
	public static long getCurrentDateTimeMillis()
	{
		return System.currentTimeMillis();
	}

	/**
	 * This method will return the current date in UTC time at midnight. This represents midnight UTC on the current
	 * date.
	 *
	 * @return The date milliseconds for a date with time zone UTC with the date of the current date.
	 */
	public static long getCurrentDateMillisUTC()
	{
		DateTime dateTime = new DateTime(getCurrentDateTimeMillis());
		int dateTimeYear = dateTime.getYear();
		int dateTimeMonth = dateTime.getMonthOfYear();
		int dateTimeDay = dateTime.getDayOfMonth();
		DateTime newDateTime =
				new DateTime(dateTimeYear, dateTimeMonth, dateTimeDay, 0, 0, 0, DateTimeZone.forID(UTC_TZ));
		return newDateTime.getMillis();
	}

	/**
	 * This method will truncate the time portion from the date/time stamp represent in UTC milliseconds.
	 *
	 * @param dateTimeUTC The date/time UTC represented in UTC milliseconds to remove the time portion.
	 * @return The date/time UTC represented in milliseconds with a time of 00:00:00.
	 */
	public static long truncateTimeUTC(long dateTimeUTC)
	{
		Calendar timeStampCal = Calendar.getInstance(UTC_TIME_ZONE);
		timeStampCal.setTimeInMillis(dateTimeUTC);

		Calendar dateFromCal = QATDateUtil.truncateTime(timeStampCal);

		return dateFromCal.getTimeInMillis();
	}

	/**
	 * This method will extract the year from a date represent in UTC milliseconds.
	 *
	 * @param dateUTC The date represented in UTC milliseconds to extract the year.
	 * @return A string year from the current UTC date.
	 */
	public static String extractYearFromUTCDate(long dateUTC)
	{
		DateTime dateTime = new DateTime(dateUTC, DateTimeZone.forID(UTC_TZ));
		return Integer.toString(dateTime.getYear());
	}
}
