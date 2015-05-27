package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.Date;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.dm.common.base.util.DMConvertUtil;

/**
 * The Class ConvertTimeZoneDateTime.
 * 
 * @author QAT Global.
 */
public class ConvertTimeZoneDateTime extends CellProcessorAdaptor implements DateCellProcessor
{

	/** The Constant HH_MM_A. */
	private static final String HH_MM_A = " hh:mm a";

	/** The date format. */
	private final String dateFormat;

	/** The time zone. */
	private final String timeZone;

	/**
	 * Instantiates a new convert time zone.
	 * 
	 * @param dateFormatParam the date format
	 * @param timeZoneParam the time zone
	 */
	public ConvertTimeZoneDateTime(String dateFormatParam, String timeZoneParam)
	{
		super();
		checkPreconditions(dateFormatParam);
		this.dateFormat = dateFormatParam;
		checkPreconditionsTimeZone(timeZoneParam);
		this.timeZone = timeZoneParam;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!Date.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(Date.class, value, context, this);
		}

		return next.execute(DMConvertUtil.convertDateToStringTimeOnly((Date)value, timeZone, dateFormat + HH_MM_A),
				context);
	}

	/**
	 * Check preconditions.
	 * 
	 * @param dateFormat the date format
	 */
	private static void checkPreconditions(String dateFormat)
	{
		if (dateFormat == null)
		{
			throw new NullPointerException("dateFormat should not be null");
		}
	}

	/**
	 * Check preconditions time zone.
	 * 
	 * @param timeZone the time zone
	 */
	private static void checkPreconditionsTimeZone(String timeZone)
	{
		if (timeZone == null)
		{
			throw new NullPointerException("time zone should not be null");
		}
	}

}