package com.sensus.dm.common.base.util.csv.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.dm.common.base.util.DMConvertUtil;

/**
 * The Class ConvertDateLeak.
 * 
 * @author QAT Global.
 */
public class ConvertDateLeak extends CellProcessorAdaptor implements DateCellProcessor
{

	/** The Constant FORMATTED_HOUR_LEAK_REPORT. */
	private static final String FORMATTED_HOUR_LEAK_REPORT = " h:mma";

	/** The Constant FORMATTED_DATE_LEAK_REPORT. */
	private static final String FORMATTED_DATE_LEAK_REPORT = "yyyy-MM-dd" + FORMATTED_HOUR_LEAK_REPORT;

	/** The Constant PRIOR_TO. */
	private static final String PRIOR_TO = "Prior to ";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The date format. */
	private final String dateFormat;

	/**
	 * Instantiates a new convert date leak.
	 * 
	 * @param dateFormatParam the date format
	 */
	public ConvertDateLeak(String dateFormatParam)
	{
		super();
		checkPreconditions(dateFormatParam);
		this.dateFormat = dateFormatParam;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!String.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(String.class, value, context, this);
		}

		String formattedDateTime = value.toString();

		if (formattedDateTime.indexOf(PRIOR_TO) == ZERO)
		{
			formattedDateTime =
					PRIOR_TO
							+ DMConvertUtil.convertDateToString(DMConvertUtil.convertStringToDate(
									formattedDateTime.substring(PRIOR_TO.length()),
									FORMATTED_DATE_LEAK_REPORT),
									dateFormat);
		}
		else
		{
			formattedDateTime =
					DMConvertUtil.convertDateToString(
							DMConvertUtil
									.convertStringToDate(formattedDateTime, FORMATTED_DATE_LEAK_REPORT),
							dateFormat);
		}

		return next.execute(formattedDateTime, context);
	}

	/**
	 * Check preconditions.
	 * 
	 * @param dateFormat the date format
	 */
	private static void checkPreconditions(final String dateFormat)
	{
		if (dateFormat == null)
		{
			throw new NullPointerException("dateFormat should not be null");
		}
	}

}
