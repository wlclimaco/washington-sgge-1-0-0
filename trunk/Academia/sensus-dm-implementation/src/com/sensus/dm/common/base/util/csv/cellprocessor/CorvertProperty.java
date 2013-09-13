package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.csv.model.CsvColumnEnum;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;

/**
 * The Class CorvertProperty.
 * 
 * @author QAT Global.
 */
public class CorvertProperty extends CellProcessorAdaptor implements DateCellProcessor
{
	/** The column. */
	private final String column;

	/** The date format. */
	private final String dateFormat;

	/** The time zone. */
	private final String timeZone;

	/**
	 * Instantiates a new corvert property.
	 * 
	 * @param dateFormatParam the date format
	 * @param timeZoneParam the time zone
	 * @param col the col
	 */
	public CorvertProperty(String dateFormatParam, String timeZoneParam, String col)
	{
		super();
		column = col;
		checkPreconditions(dateFormatParam);
		this.dateFormat = dateFormatParam;
		checkPreconditionsTimeZone(timeZoneParam);
		this.timeZone = timeZoneParam;
	}

	/**
	 * Instantiates a new corvert property.
	 * 
	 * @param col the col
	 */
	public CorvertProperty(String col)
	{
		super();
		column = col;
		dateFormat = null;
		timeZone = null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!ArrayList.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(Property.class, value, context, this);
		}
		String result = convertProperty((List<Property>)value);
		return next.execute(result, context);
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

	/**
	 * Gets the action date.
	 * 
	 * @param properties the properties
	 * @return the action date
	 */
	private String convertProperty(List<Property> properties)
	{
		for (Property property : properties)
		{
			if ((CsvColumnEnum.ACTION_DATE.getValue().equals(column) || CsvColumnEnum.ACTION_DATE_SCH.getValue()
					.equals(column))
					&& PropertyEnum.ACTION_DATE.getValue().equals(property.getPropertyName()))
			{
				return formatDate(Integer.parseInt(property.getPropertyValue()));
			}
			else if (CsvColumnEnum.READ_TIME.getValue().equals(column)
					&& PropertyEnum.LAST_READ_TIME.getValue().equals(property.getPropertyName()))
			{
				return formatDate(Integer.parseInt(property.getPropertyValue()));
			}
			else if (CsvColumnEnum.READ_VALUE.getValue().equals(column)
					&& PropertyEnum.LAST_READ_VALUE.getValue().equals(property.getPropertyName()))
			{
				return property.getPropertyValue();
			}
		}

		return null;
	}

	/**
	 * Format date.
	 * 
	 * @param date the date
	 * @return the string
	 */
	private String formatDate(Integer date)
	{
		return DMConvertUtil.convertDateToString(
				DMConvertUtil.convertDateToDefaultUTC(DMConvertUtil
						.convertIntegerToDate(date)),
				timeZone, dateFormat);
	}

}
