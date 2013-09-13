package com.sensus.common.model;

/**
 * This class is used to hold various values representing differences between two dates.<br/>
 * See the {@link com.sensus.common.util.SensusDateUtil} for more information on working with dates and comparing dates.
 */
public class DateDiff
{

	/** The number of years related to the difference of the dates. */
	public int years;

	/** The number of months related to the difference of the dates. */
	public int months;

	/** The number of days related to the difference of the dates. */
	public int days;

	/** The total number of months between the dates. */
	public int totalMonths;

	/** The total number of days between the dates. */
	public int totalDays;

	/*
	 * Default string representation of all the values contained in this class.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Years: ").append(years).append("  ");
		sb.append("Months: ").append(months).append("  ");
		sb.append("Days: ").append(days).append("  ");
		sb.append("Total Days: ").append(totalDays).append("  ");
		sb.append("Total Months: ").append(totalMonths).append("  ");
		if (sb.length() > 0)
			sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}
}
