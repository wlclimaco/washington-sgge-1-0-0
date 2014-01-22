package com.sensus.lc.base.util;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.cbof.model.TimeZoneInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.process.model.Process;

/**
 * The Class ConvertTimeZone.
 * 
 * @author QAT Global.
 */
public class ConvertTimeZone extends CellProcessorAdaptor
{

	/** The date format. */
	private String dateFormat;

	/** The time zone. */
	private String timeZone;

	/** The property path. */
	private String propertyPath;

	private static final String GET_PREFIX = "get";

	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(ConvertTimeZone.class);

	/**
	 * Instantiates a new convert time zone.
	 * 
	 * @param dateFormatParam the date format param
	 * @param timeZoneParam the time zone param
	 */
	public ConvertTimeZone(String dateFormatParam, String timeZoneParam, String propertyPathParam)
	{
		super();
		checkPreconditions(dateFormatParam);
		dateFormat = dateFormatParam;
		checkPreconditionsTimeZone(timeZoneParam);
		timeZone = timeZoneParam;
		propertyPath = propertyPathParam;
	}

	public ConvertTimeZone(String dateFormatParam, String propertyPathParam)
	{
		super();
		checkPreconditions(dateFormatParam);
		dateFormat = dateFormatParam;
		propertyPath = propertyPathParam;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		Date date = null;
		if (value instanceof Light)
		{
			if (!Light.class.isAssignableFrom(value.getClass()))
			{
				throw new SuperCsvCellProcessorException(Light.class, value, context, this);
			}

			Light light = (Light)value;
			TimeZoneInfo tzi = light.getRadio().getLocation().getTimeZoneInfo();
			timeZone = tzi.getTimeZone().getID();
			date = getDate(light);
		}

		if (value instanceof LightHistory)
		{
			if (!LightHistory.class.isAssignableFrom(value.getClass()))
			{
				throw new SuperCsvCellProcessorException(Light.class, value, context, this);
			}

			LightHistory lightHistory = (LightHistory)value;
			date = lightHistory.getCreateDate();
		}

		if (value instanceof Process)
		{
			if (!Process.class.isAssignableFrom(value.getClass()))
			{
				throw new SuperCsvCellProcessorException(Process.class, value, context, this);
			}

			Process process = (Process)value;
			date = process.getStartTime();
		}

		return next.execute(LCDateUtil.convertDateToString(date, timeZone, dateFormat), context);
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

	private Date getDate(Light light)
	{
		try
		{
			if (ValidationUtil.isNullOrEmpty(propertyPath))
			{
				return null;
			}

			String[] fields = StringUtils.splitByWholeSeparator(propertyPath, ".");
			Object object = light;
			for (String field : fields)
			{
				String methodName = GET_PREFIX + StringUtils.capitalize(field);
				Method method = object.getClass().getDeclaredMethod(methodName);
				object = method.invoke(object);
			}

			if (!ValidationUtil.isNull(object) && Date.class.isAssignableFrom(object.getClass()))
			{
				return (Date)object;
			}
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(e.getMessage());
			}
			return null;
		}

		return null;
	}
}
