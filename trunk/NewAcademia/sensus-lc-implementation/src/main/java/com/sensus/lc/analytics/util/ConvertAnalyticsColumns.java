package com.sensus.lc.analytics.util;

import java.util.List;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.lc.analytics.model.AnalyticsGroupColumns;

/**
 * The Class ConvertAnalyticsColumns.
 * 
 * @author QAT Brazil.
 */
public class ConvertAnalyticsColumns extends CellProcessorAdaptor
{

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!List.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(List.class, value, context, this);
		}

		String text = getValue((List<AnalyticsGroupColumns>)value, context.getColumnNumber() - 2);

		return next.execute(text, context);
	}

	/**
	 * Gets the value.
	 * 
	 * @param lists the lists
	 * @param col the col
	 * @return the value
	 */
	private String getValue(List<AnalyticsGroupColumns> lists, Integer col)
	{
		return String.valueOf(lists.get(col).getValue());
	}

}
