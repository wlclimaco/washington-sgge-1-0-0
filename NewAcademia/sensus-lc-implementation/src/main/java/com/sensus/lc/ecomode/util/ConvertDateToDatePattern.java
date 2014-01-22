package com.sensus.lc.ecomode.util;

import java.util.Date;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.common.util.SensusConvertUtil;
import com.sensus.lc.light.model.Consumption;

/**
 * The Class ConvertDateToDatePattern.
 * 
 * @author QAT Global.
 */
public class ConvertDateToDatePattern extends CellProcessorAdaptor
{
	/** The date format. */
	private String dateFormat;

	/**
	 * Instantiates a new convert date to date pattern.
	 * 
	 * @param dateFormatParam the date format param
	 */
	public ConvertDateToDatePattern(String dateFormatParam)
	{
		super();
		dateFormat = dateFormatParam;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		Date date = null;
		if (value instanceof Consumption)
		{
			if (!Consumption.class.isAssignableFrom(value.getClass()))
			{
				throw new SuperCsvCellProcessorException(Consumption.class, value, context, this);
			}

			Consumption consumption = (Consumption)value;
			date = consumption.getDay();
		}
		return next.execute(SensusConvertUtil.toDateString(date, dateFormat), context);
	}

}
