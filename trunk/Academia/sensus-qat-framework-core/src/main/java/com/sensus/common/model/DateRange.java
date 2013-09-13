package com.sensus.common.model;

import java.util.Date;

/**
 * Used to hold a date range with the concept of a "start date" and an "end date". <br/>
 * Null values are allowed and are used to indicate no start or end date accordingly. <br/>
 * As a general rule a null start date indicates "the beginning of time" and a null end date indicates
 * "the end of time".
 */
public class DateRange
{
	// [start] private fields

	/** The start date. */
	private Date startDate = null;

	/** The end date. */
	private Date endDate = null;

	/** The start date. */
	// private Calendar startCal = null ; // Do not reference directly. Use the private get method.

	/** The end date. */
	// private Calendar endCal = null ; // Do not reference directly. Use the private get method

	// [end] private fields

	// [start] Constructor

	/**
	 * Instantiates a new date range with the start and end date values.
	 * 
	 * @param startDate The start date, or null if necessary.
	 * @param endDate The end date, or null if necessary.
	 */
	public DateRange(Date startDate, Date endDate)
	{
		// Validate the parameters
		if (startDate != null && endDate != null)
		{
			// Start date must be before end date.
			if (startDate.after(endDate))
			{
				throw new IllegalArgumentException("Start date must be less than or equal to End date");
			}
		}

		this.startDate = startDate;

		this.endDate = endDate;
	}

	// [end] Constructor

	// [start] private getters

	// /**
	// * Gets the start cal.
	// *
	// * @return the start cal
	// */
	// private Calendar getStartCal()
	// {
	// if (this.startDate == null)
	// return null;
	//
	// if (this.startCal == null)
	// {
	// this.startCal = Calendar.getInstance() ;
	// this.startCal.setTime(this.startDate);
	// }
	//
	// return this.startCal;
	// }
	//
	// /**
	// * Gets the end cal.
	// *
	// * @return the end cal
	// */
	// private Calendar getEndCal()
	// {
	// if (this.endDate == null)
	// return null;
	//
	// if (this.endCal == null)
	// {
	// this.endCal = Calendar.getInstance() ;
	// this.endCal.setTime(this.endDate);
	// }
	//
	// return this.endCal;
	// }

	// [end] private getters

	// [start] public properties

	/**
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
		// this.startCal = null ;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
		// this.endCal = null ;
	}

	// [end] public properties

	// [start] Comparison methods

	/**
	 * Return true if the given Date is after the start date and before the end date. If either date is null then that
	 * part of the test will automatically be true.
	 * 
	 * @param date The Date to test
	 * 
	 * @return true if the Date is between the start and end date
	 */
	public boolean contains(Date date)
	{
		if (startDate == null || date.after(startDate))
		{
			if (endDate == null || date.before(endDate))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the end date of this range occurs after the parameter date.<br/>
	 * 
	 * @param date the date
	 * 
	 * @return true, if successful
	 */
	public boolean after(java.util.Date date)
	{
		if (endDate == null || endDate.after(date))
		{
			return true;
		}

		return false;
	}

	/**
	 * Returns true if the earliest date in this range occurs after the latest date in parameter DateRange.<br/>
	 * 
	 * @param range the range
	 * 
	 * @return true, if successful
	 */
	public boolean after(DateRange range)
	{
		// If the start date is null, meaning the beginning of time this check can never be true.
		if (startDate == null)
		{
			return false;
		}

		if (startDate.after(range.getEndDate()))
		{
			return true;
		}

		return false;
	}

	/**
	 * Returns true if the start date of this range occurs before parameter date.<br/>
	 * 
	 * @param date the date
	 * 
	 * @return true, if successful
	 */
	public boolean before(java.util.Date date)
	{
		if (startDate == null || startDate.before(date))
		{
			return true;
		}

		return false;
	}

	/**
	 * Returns true if the end date in this range occurs before the earliest date in the parameter DateRange.<br/>
	 * 
	 * @param range the range
	 * 
	 * @return true, if successful
	 */
	public boolean before(DateRange range)
	{
		// If the end date is null, meaning the end of time this check can never be true.
		if (endDate == null)
		{
			return false;
		}

		return endDate.before(range.getStartDate());
	}

	/**
	 * Checks if this range is really a point in time indicating that the start date and the end date are equal.<br/>
	 * 
	 * @return true, if is point in time
	 */
	public boolean isPointInTime()
	{
		return startDate.equals(endDate);
	}

	// [end] Comparison methods

	// [start] Difference methods

	// /**
	// * Get a {@link com.sensus.common.model.DateDiff} instance for this date range.
	// *
	// * @return The {@link com.sensus.common.model.DateDiff} instance for this date range.
	// */
	// public DateDiff getDiff()
	// {
	// return QATDateUtil.getDateDiff(startDate, endDate);
	// }

	// [end] Difference methods
}
