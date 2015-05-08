package com.prosperitasglobal.sendsolv.unittest.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;

/**
 * A JUNIT for testing the PGSiDateUtil utility class.
 */
public class PGSiDateUtilTest
{
	/** The default time zone. */
	private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();

	/** The default time zone id. */
	private static final String DEFAULT_TIME_ZONE_ID = DEFAULT_TIME_ZONE.getID();

	/** The default time zone display name. */
	private static final String DEFAULT_TIME_ZONE_NAME = DEFAULT_TIME_ZONE.getDisplayName();

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(PGSiDateUtilTest.class);

	/** 10 days. */
	private static final int TEN_DAYS = 10;

	/** The UTC time zone. */
	private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

	/** The UTC time zone id. */
	private static final String UTC_TIME_ZONE_ID = UTC_TIME_ZONE.getID();

	/** The UTC time zone display name. */
	private static final String UTC_TIME_ZONE_NAME = UTC_TIME_ZONE.getDisplayName();

	/** The date/time format. */
	private static final String YYYY_MM_DD_HH_MM_SS_SSS_Z = "yyyy-MM-dd HH:mm:ss.SSS z";

	/**
	 * Convert a {@link String} date to a {@link Long} date.<br>
	 * <br>
	 * {@link String} format must be in the following format: yyyy-MM-dd HH:mm:ss.SSS zS<br>
	 * <br>
	 * <code>
	 * yyyy - century and year<br>
	 * MM   - month<br>
	 * dd   - day of the month<br>
	 * HH	- hour of the day<br>
	 * mm	- minute of the day<br>
	 * ss	- seconds of the day<br>
	 * SSS	- milliseconds of the day<br>
	 * z	- Time zone name<br>
	 * </code>
	 *
	 * @param date The date/time as a {@link String}.
	 * @return The date/time as a {@link Long}.
	 */
	protected Long convertDate(String date)
	{
		if ((date == null) || (date.length() == 0))
		{
			return null;
		}

		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_SSS_Z);
			formatter.setLenient(false);
			return formatter.parse(date).getTime();
		}

		catch (Exception ex)
		{
			Assert.assertFalse("Error converting Date " + ex.getMessage(), true);
			return null;
		}
	}

	/**
	 * Log the 2 dates for extra research.
	 *
	 * @param date1 The first date.
	 * @param date2 The second date.
	 */
	private void logDates(Long date1, Long date2)
	{
		LOG.debug("Date1: " + date1 + " Date2: " + date2);
	}

	/** Test that the compare for 2 dates are equal (even though the time zone is different). */
	@Test
	public void testAddDaysUTC()
	{
		Long dateTime1 = convertDate("2015-02-01 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-02-11 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		int daysToAdd = TEN_DAYS;
		Assert.assertEquals(dateTime2, new Long(PGSiDateUtil.addDays(dateTime1, daysToAdd)));
	}

	/** Test that the compare for 2 dates are equal (even though the time zone is different). */
	@Test
	public void testAddDaysLocal()
	{
		Long dateTime1 = convertDate("2015-02-02 00:00:00.000 " + DEFAULT_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-02-12 00:00:00.000 " + DEFAULT_TIME_ZONE_NAME);
		int daysToAdd = TEN_DAYS;
		Assert.assertEquals(dateTime2, new Long(PGSiDateUtil.addDays(dateTime1, DEFAULT_TIME_ZONE_ID, daysToAdd)));
	}

	/** Test that the compare for 2 dates are equal (even though the time zone is different). */
	@Test
	public void testCompareDatesDate1UTCEqualsDate2Local()
	{
		Long dateTime1 = convertDate("2015-01-01 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-01 23:59:59.999 " + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDates(dateTime1, UTC_TIME_ZONE_ID, dateTime2, DEFAULT_TIME_ZONE_ID) == 0);
	}

	/** Test that the compare for 2 dates are equal (even though the time zone is different). */
	@Test
	public void testCompareDatesDate1UTCEqualsDate2UTC()
	{
		Long dateTime1 = convertDate("2015-01-01 06:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-01 20:00:00.000 " + UTC_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDates(dateTime1, dateTime2) == 0);
	}

	/** Test that the compare for 2 dates are equal (even though the time zone is different). */
	@Test
	public void testCompareDatesDate1UTCEqualsDate2LocalAgain()
	{
		String date = "2015-01-02 00:00:00.000 ";
		Long dateTime1 = convertDate(date + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate(date + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDates(dateTime1, UTC_TIME_ZONE_ID, dateTime2, DEFAULT_TIME_ZONE_ID) == 0);
	}

	/**
	 * Test that the compare for 2 dates, where first date is greater than 2nd date (even though the time zone is
	 * different).
	 */
	@Test
	public void testCompareDatesDate1UTCGreaterThanDate2UTC()
	{
		Long dateTime1 = convertDate("2015-01-04 06:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-03 06:00:00.000 " + UTC_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDates(dateTime1, dateTime2) > 0);
	}

	/**
	 * Test that the compare for 2 dates, where first date is greater than 2nd date (even though the time zone is
	 * different).
	 */
	@Test
	public void testCompareDatesDate1UTCGreaterThanDate2Local()
	{
		Long dateTime1 = convertDate("2015-01-04 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-03 23:59:59.999 " + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDates(dateTime1, UTC_TIME_ZONE_ID, dateTime2, DEFAULT_TIME_ZONE_ID) > 0);
	}

	/**
	 * Test that the compare for 2 dates, where first date is less than 2nd date (even though the time zone is
	 * different).
	 */
	@Test
	public void testCompareDatesDate1UTCLessThanDate2Local()
	{
		Long dateTime1 = convertDate("2015-01-05 23:59:59.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-06 00:00:00.999 " + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDates(dateTime1, UTC_TIME_ZONE_ID, dateTime2, DEFAULT_TIME_ZONE_ID) < 0);
	}

	/**
	 * Test that the compare for 2 dates, where first date is less than 2nd date (even though the time zone is
	 * different).
	 */
	@Test
	public void testCompareDatesDate1UTCLessThanDate2UTC()
	{
		Long dateTime1 = convertDate("2015-01-05 23:59:59.999 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-06 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDates(dateTime1, dateTime2) < 0);
	}

	/** Test that the first date is less than the second date. */
	@Test
	public void testCompareDatesUTCDate1EqualsDate2()
	{
		Long dateTime1 = convertDate("2015-01-12 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-11 18:00:00.000 " + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDatesUTC(dateTime1, dateTime2) == 0);
	}

	/** Test that the compare for 2 date/times are equal (even the time). */
	@Test
	public void testCompareDatesUTCEqual()
	{
		Long dateTime1 = convertDate("2015-01-08 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-07 18:00:00.000 " + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDatesUTC(dateTime1, dateTime2) == 0);
	}

	/** Test that the first date is greater than the second date. */
	@Test
	public void testCompareDatesUTCFirstGreaterThanSecond()
	{
		Long dateTime1 = convertDate("2015-01-10 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-09 15:59:59.999 " + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDatesUTC(dateTime1, dateTime2) > 0);
	}

	/** Test that the first date is less than the second date. */
	@Test
	public void testCompareDatesUTCFirstLessThanSecond()
	{
		Long dateTime1 = convertDate("2015-01-13 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-13 23:59:59.999 " + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDatesUTC(dateTime1, dateTime2) < 0);
	}

	/** Test that the first date/time is greater than the second date/time. */
	@Test
	public void testCompareDateTimesUTCFirstGreaterThanSecond()
	{
		Long dateTime1 = convertDate("2015-01-15 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-14 17:59:59.999 " + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDateTimesUTC(dateTime1, dateTime2) > 0);
	}

	/** Test that the first date/time is less than the second date/time. */
	@Test
	public void testCompareDateTimesUTCFirstLessThanSecond()
	{
		Long dateTime1 = convertDate("2015-01-16 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-01-16 18:00:00.999 " + DEFAULT_TIME_ZONE_NAME);
		logDates(dateTime1, dateTime2);
		Assert.assertTrue(PGSiDateUtil.compareDateTimesUTC(dateTime1, dateTime2) < 0);
	}

	/** Test the extraction of the year from the UTC Long date. */
	@Test
	public void testExtractYearFromDate1()
	{
		Long dateTime = convertDate("1964-12-31 23:59:59.999 " + UTC_TIME_ZONE_NAME);
		String year = PGSiDateUtil.extractYearFromUTCDate(dateTime);
		LOG.debug("Date1 Year: " + year);
		Assert.assertEquals("", "1964", year);
	}

	/** Test the extraction of the year from the UTC Long date. */
	@Test
	public void testExtractYearFromDate2()
	{
		Long dateTime = convertDate("1965-01-01 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		String year = PGSiDateUtil.extractYearFromUTCDate(dateTime);
		LOG.debug("Date2 Year: " + year);
		Assert.assertEquals("", "1965", year);
	}

	/** Test that getting of the current date/time in milliseconds. */
	@Test
	public void testGetCurrentDateMillisecondsUTC()
	{
		Long currentDate = PGSiDateUtil.getCurrentDateMillisUTC();
		Assert.assertTrue("Current date millis UTC invalid!", PGSiDateUtil.compareDates(
				PGSiDateUtil.getCurrentDateTimeMillis(), TimeZone.getDefault().getID(), currentDate,
				TimeZone.getTimeZone(UTC_TIME_ZONE_ID).getID()) == 0);
		LOG.debug("Current Date Milliseconds UTC: " + currentDate);
	}

	/** Test that getting of the current date/time in milliseconds. */
	@Test
	public void testGetCurrentDateTimeMilliseconds()
	{
		Long currentDate = PGSiDateUtil.getCurrentDateTimeMillis();
		Assert.assertNotNull("Current date is null!", currentDate);
		LOG.debug("Current Date/Time Milliseconds: " + currentDate);
	}

	/** Test the subtraction of a number of days is successful. */
	@Test
	public void testSubtractDaysLocal()
	{
		Long dateTime1 = convertDate("2015-03-30 00:00:00.000 " + DEFAULT_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-03-20 00:00:00.000 " + DEFAULT_TIME_ZONE_NAME);
		int daysToSubtract = TEN_DAYS;
		Assert.assertEquals(dateTime2,
				new Long(PGSiDateUtil.subtractDays(dateTime1, DEFAULT_TIME_ZONE_ID, daysToSubtract)));
	}

	/** Test the subtraction of a number of days is successful. */
	@Test
	public void testSubtractDaysUTC()
	{
		Long dateTime1 = convertDate("2015-03-11 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		Long dateTime2 = convertDate("2015-03-01 00:00:00.000 " + UTC_TIME_ZONE_NAME);
		int daysToSubtract = TEN_DAYS;
		Assert.assertEquals(dateTime2, new Long(PGSiDateUtil.subtractDays(dateTime1, daysToSubtract)));
	}
}
