package com.sensus.common.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Utility class for working with dates. See the individual methods for more information.<br/>
 * Note that most dates will be truncated using the {@link #truncateTime(Date)} method and their time portion removed so
 * that comparisons will work properly.<br/>
 * This class may also be configured as a Spring bean setting the various minimum and maximum dates. <b>This should be
 * done at a global Spring configuration level and not within a given business are or batch job.</b><br/>
 * The default minimum date is January 1, 1600 - 01/01/1600<br/>
 * The default maximum date is December 31, 9999 - 12/31/9999<br/>
 */
public final class SensusDateUtil
{

	/** The min date. */
	private static Calendar minDate = null;

	/** The max date. */
	private static Calendar maxDate = null;

	/** The min date list. */
	private static List<Calendar> minDateList = new ArrayList<Calendar>();

	/** The max date list. */
	private static List<Calendar> maxDateList = new ArrayList<Calendar>();

	/** One day in milliseconds. */
	private static long ONE_DAY = 1000 * 60 * 60 * 24;

	/** **/
	private SensusDateUtil()
	{
	}

	/**
	 * Static initialize which set the default values for min and max dates.
	 */
	static
	{
		// Build a calendar so I can build the default min and max values.
		Calendar cal = Calendar.getInstance();

		// Define the default min.
		cal.set(1600, 00, 01);
		// Remove the time portion.
		minDate = truncateTime(cal);
		// Add to list so compare will work.
		minDateList.add(minDate);

		// Repeat for max date value.
		cal.set(9999, 11, 31);
		maxDate = truncateTime(cal);
		maxDateList.add(maxDate);
	}

	// [start] Property setters

	/**
	 * Sets the minimum date list. This should <B>ONLY</B> be injected from Spring. <br/>
	 * This is a List of Dates that should be used to compare against when determining if a date is considered a "min"
	 * date. The default minimum date specified in the class documentation will also be added to this list.
	 * 
	 * @param list A List of dates injected from Spring
	 */
	public void setMinDateList(List<Calendar> list)
	{
		// Need to remove the time portion of the Date so the comparison will work.
		for (Calendar d : list)
		{
			minDateList.add(truncateTime(d));
		}
	}

	/**
	 * Sets the max date list. This should <B>ONLY</B> be injected from Spring.<br/>
	 * This is a List of Dates that should be used to compare against when determining if a date is considered a "max"
	 * date. The default maximum date specified in the class documentation will also be added to this list.
	 * 
	 * @param list A List of dates injected from Spring
	 */
	public void setMaxDateList(List<Calendar> list)
	{
		// Need to remove the time portion of the Date so the comparison will work.
		for (Calendar d : list)
		{
			maxDateList.add(truncateTime(d));
		}
	}

	/**
	 * Sets the min date. This should <B>ONLY</B> be injected from Spring. This value will be added to the
	 * minimum-date-list and if this method is invoked several times each date will in turn be added to the list. This
	 * may cause unexpected results so be careful not to invoke this method outside of Spring injection.<br/>
	 * This is the value that will be returned when invoking the {@link #getMinDate()} method.
	 * 
	 * @param dte the new min date
	 */
	public void setMinDate(Calendar dte)
	{
		minDate = truncateTime(dte);
		minDateList.add(dte);
	}

	/**
	 * Sets the max date. This should <B>ONLY</B> be injected from Spring. This value will be added to the
	 * maximum-date-list and if this method is invoked several times each date will in turn be added to the list. This
	 * may cause unexpected results so be careful not to invoke this method outside of Spring injection.<br/>
	 * This is the value that will be returned when invoking the {@link #getMaxDate()} method.
	 * 
	 * @param dte the new max date
	 */
	public void setMaxDate(Calendar dte)
	{
		maxDate = truncateTime(dte);
		maxDateList.add(dte);
	}

	// [end] Property setters

	// [start] Min and Max methods and properties

	/**
	 * Gets the maximum date value. This will be the default maximum date as specified in the class documentation or if
	 * this class we configured using a Spring bean definition it will be the value specified using the maxDate
	 * property.
	 * 
	 * @return The maximum date value.
	 */
	public static Date getMaxDate()
	{
		return maxDate.getTime();
	}

	/**
	 * Gets the maximum date in the form of a java.util.Calendar instance. See the {@link #getMaxDate()} method for more
	 * information.
	 * 
	 * @return the max calendar date
	 */
	public static Calendar getMaxCalendarDate()
	{
		return maxDate;
	}

	/**
	 * Gets the minimum date value. This will be the default minimum date as specified in the class documentation or if
	 * this class we configured using a Spring bean definition it will be the value specified using the minDate
	 * property.
	 * 
	 * @return The minimum date value.
	 */
	public static Date getMinDate()
	{
		return minDate.getTime();
	}

	/**
	 * Gets the minimum date in the form of a java.util.Calendar. See the {@link #getMinDate()} method for more
	 * information.
	 * 
	 * @return the min calendar date
	 */
	public static Calendar getMinCalendarDate()
	{
		return minDate;
	}

	/**
	 * Checks the argument Date to see if it's considered a minimum date. Note the argument date will have it's time
	 * portion truncated to insure a proper comparison. Note the minimum date list is used for this check.
	 * 
	 * @param inDate The date to compare with.
	 * @return true, If the argument date is considered a minimum date.
	 */
	public static boolean isMinDate(Date inDate)
	{
		// Make sure we remove the time portion of the Date so the comparison works.
		Date dte = truncateTime(inDate);

		// Spin the list looking for a match
		for (Calendar d : minDateList)
		{
			if (dte.equals(truncateTime(d.getTime())))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * See the {@link #isMinDate(Date)} method for more information. This method performs the same task only using a
	 * Calendar instance.
	 * 
	 * @param inDate The date to compare with.
	 * @return true, If the argument date is considered a minimum date.
	 */
	public static boolean isMinDate(Calendar inDate)
	{
		return isMinDate(inDate.getTime());
	}

	/**
	 * Checks the argument Date to see if it's considered a maximum date. Note the argument date will have it's time
	 * portion truncated to insure a proper comparison. Note the maximum date list is used for this check.
	 * 
	 * @param inDate The date to compare with.
	 * @return true, If the argument date is considered a maximum date.
	 */
	public static boolean isMaxDate(Date inDate)
	{
		// Make sure we remove the time portion of the Date so the comparison works.
		Date dte = truncateTime(inDate);

		// Spin the list looking for a match.
		for (Calendar d : maxDateList)
		{
			if (dte.equals(truncateTime(d.getTime())))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * See the {@link #isMaxDate(Date)} method for more information. This method performs the same task only using a
	 * Calendar instance.
	 * 
	 * @param inDate The date to compare with.
	 * @return true, If the argument date is considered a maximum date.
	 */
	public static boolean isMaxDate(Calendar inDate)
	{
		return isMaxDate(inDate.getTime());
	}

	// [end] Min and Max methods and properties

	// [start] getDateDiff

	// /**
	// * Returns an instance of DateDiff using the {@link #getDateDiff(Date,Date)} method. Converting the input
	// * Calendar type parameters to java.util.Date types.
	// *
	// * @param startDate The start date
	// * @param endDate The end date
	// * @return An instance of {@link DateDiff}
	// */
	// public static final DateDiff getDateDiff(Calendar startDate, Calendar endDate)
	// {
	// return getDateDiff(startDate.getTime(), endDate.getTime());
	// }

	public static final long getDaysDiff(Date startDate, Date endDate)
	{
		long diff = 0;

		if (startDate.getTime() > endDate.getTime())
		{
			diff = startDate.getTime() - endDate.getTime();
		}
		else
		{
			diff = endDate.getTime() - startDate.getTime();
		}

		return diff / ONE_DAY;
	}

	// /**
	// * Returns an instance of {@link DateDiff } that contains various difference values based on the input
	// parameters.<br/>
	// * If one of the parameter dates is NULL then the current date will be substituted.<br/>
	// *
	// * @param startDate The start date
	// * @param endDate The end date
	// *
	// * @return An instance of {@link DateDiff}
	// */
	// public static final DateDiff getDateDiff(Date startDate, Date endDate)
	// {
	// DateDiff dateDiff = new DateDiff();
	// final int startMonth;
	// final int startDay;
	// GregorianCalendar startGC = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
	// GregorianCalendar endGC = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
	//
	// if (startDate == null)
	// {
	// startDate = new Date();
	// }
	// if (endDate == null)
	// {
	// endDate = new Date();
	// }
	//
	// /*
	// * Handle endDate after startDate
	// */
	// if (!startDate.before(endDate))
	// {
	// startGC.setTimeInMillis(startDate.getTime());
	// endGC.setTimeInMillis(endDate.getTime());
	// }
	// else
	// {
	// startGC.setTimeInMillis(endDate.getTime());
	// endGC.setTimeInMillis(startDate.getTime());
	// }
	//
	// // Handle total days
	// long diff = startGC.getTimeInMillis() - endGC.getTimeInMillis();
	// // dateDiff.totalDays = (int)(diff / ONE_DAY) + 1;
	// dateDiff.totalDays = (int)(diff / ONE_DAY);
	//
	// /*
	// * Special case: same year and month
	// */
	// if (startGC.get(Calendar.YEAR) == endGC.get(Calendar.YEAR)
	// && startGC.get(Calendar.MONTH) == endGC.get(Calendar.MONTH))
	// {
	// // dateDiff.days = startGC.get(Calendar.DATE) - endGC.get(Calendar.DATE);
	// dateDiff.days = dateDiff.totalDays;
	// }
	// else if (startGC.get(Calendar.MONTH) == endGC.get(Calendar.MONTH)
	// && startGC.get(Calendar.DATE) == endGC.get(Calendar.DATE))
	// {
	// dateDiff.years = startGC.get(Calendar.YEAR) - endGC.get(Calendar.YEAR);
	// }
	// else
	// {
	// /*
	// * Capture the start day. This is necessary because incrementing the month moves the day back to the last
	// * day of that month.
	// */
	// startDay = endGC.get(Calendar.DATE);
	// /*
	// * If the day of the end month is less than the day of the start month, increment the start date until the
	// * beginning of the next month, count the days
	// */
	// if (startGC.get(Calendar.DATE) < endGC.get(Calendar.DATE))
	// {
	// startMonth = endGC.get(Calendar.MONTH);
	// while (startMonth == endGC.get(Calendar.MONTH))
	// {
	// dateDiff.days++;
	// endGC.add(Calendar.DATE, 1);
	// }
	// }
	//
	// /* Handle years */
	// do
	// {
	// dateDiff.years++;
	// endGC.add(Calendar.YEAR, 1);
	// } while (!startGC.before(endGC));
	// dateDiff.years--;
	// endGC.add(Calendar.YEAR, -1);
	//
	// /* Handle months */
	// do
	// {
	// dateDiff.months++;
	// endGC.add(Calendar.MONTH, 1);
	// } while (!startGC.before(endGC));
	// dateDiff.months--;
	// endGC.add(Calendar.MONTH, -1);
	//
	// /* Handle days */
	// if (startGC.get(Calendar.DATE) < startDay)
	// {
	// dateDiff.days += startGC.get(Calendar.DATE) - 1;
	// }
	// else
	// {
	// dateDiff.days = startGC.get(Calendar.DATE) - startDay;
	// }
	// }
	//
	// // Handle total years
	// dateDiff.totalMonths = dateDiff.years * 12 + dateDiff.months;
	//
	// return dateDiff;
	// }

	// [end] getDateDiff

	// [start] getYearsDiff

	/**
	 * Gets the years difference using the {@link #getYearsDiff(Date,Date)} method. Converting the input Calendar type
	 * parameters to java.util.Date types.
	 * 
	 * @param startDate The start date
	 * @param endDate The end date
	 * @return The years difference
	 */
	public static final int getYearsDiff(Calendar startDate, Calendar endDate)
	{
		return getYearsDiff(startDate.getTime(), endDate.getTime());
	}

	/**
	 * Gets the number of years between the given dates.<br/>
	 * If one of the parameter dates is NULL then the current date will be substituted.<br/>
	 * For example: If you want to determine how old someone is invoke this method as follows: getYearsDiff( birthDate,
	 * null ) ;
	 * 
	 * @param startDate The start date
	 * @param endDate The end date
	 * 
	 * @return The number of years between the given dates.
	 */
	public static final int getYearsDiff(Date startDate, Date endDate)
	{
		int years = 0;
		GregorianCalendar startGC = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		GregorianCalendar endGC = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

		if (startDate == null)
		{
			startDate = new Date();
		}
		if (endDate == null)
		{
			endDate = new Date();
		}

		/*
		 * Handle endDate after startDate
		 */
		if (!startDate.before(endDate))
		{
			startGC.setTimeInMillis(startDate.getTime());
			endGC.setTimeInMillis(endDate.getTime());
		}
		else
		{
			startGC.setTimeInMillis(endDate.getTime());
			endGC.setTimeInMillis(startDate.getTime());
		}

		if ((startGC.get(Calendar.MONTH) == endGC.get(Calendar.MONTH))
				&& (startGC.get(Calendar.DATE) == endGC.get(Calendar.DATE)))
		{
			years = startGC.get(Calendar.YEAR) - endGC.get(Calendar.YEAR);
		}
		else
		{
			/* Handle years. */
			do
			{
				years++;
				endGC.add(Calendar.YEAR, 1);
			} while (!startGC.before(endGC));
			years--;
		}

		return years;
	}

	// [end] getYearsDiff

	// [start] Miscellaneous utility methods.

	/**
	 * Compare to dates for ordering. Note both argument dates will have their time portion truncated to insure a proper
	 * comparison.
	 * 
	 * @param date1 The first date to use in the compare.
	 * @param date2 The second date to use in the compare.
	 * @return The value 0 if the argument Date1 is equal to the argument Date2; a value less than 0 if Date1 is before
	 *         the Date2 argument; and a value greater than 0 if Date1 is after the Date2 argument.
	 */
	public static int compare(Date date1, Date date2)
	{
		return truncateTime(date1).compareTo(truncateTime(date2));
	}

	/**
	 * Compare to dates for ordering. Note both argument dates will have their time portion truncated to insure a proper
	 * comparison.
	 * 
	 * @param date1 The first date to use in the compare.
	 * @param date2 The second date to use in the compare.
	 * @return The value 0 if the argument Date1 is equal to the argument Date2; a value less than 0 if Date1 is before
	 *         the Date2 argument; and a value greater than 0 if Date1 is after the Date2 argument.
	 */
	public static int compare(Calendar date1, Calendar date2)
	{
		return truncateTime(date1).compareTo(truncateTime(date2));
	}

	/**
	 * Truncate the time portion of the java.util.Date instance. This is useful when comparing to Date objects for
	 * equality.
	 * 
	 * @param dte The date to be truncated.
	 * @return The truncated Date value.
	 */
	public static Date truncateTime(Date dte)
	{
		return DateUtils.truncate(dte, Calendar.DATE);
	}

	/**
	 * Truncate the time portion of the java.util.Calendar instance. This is useful when comparing to Calendar objects
	 * for equality.
	 * 
	 * @param cal The Calendar to be truncated.
	 * @return The truncated Calendar value.
	 */
	public static Calendar truncateTime(Calendar cal)
	{
		return DateUtils.truncate(cal, Calendar.DATE);
	}

	// [end] Miscellaneous utility methods.
}
