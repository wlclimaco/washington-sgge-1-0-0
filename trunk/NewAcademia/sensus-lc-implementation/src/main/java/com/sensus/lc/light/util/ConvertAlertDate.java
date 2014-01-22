package com.sensus.lc.light.util;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.cbof.model.Location;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;

/**
 * The Class ConvertAlertDate.
 */
public class ConvertAlertDate extends CellProcessorAdaptor implements DateCellProcessor
{
	/** The date format. */
	private String dateFormat;

	/** The time zone. */
	private String timeZone;

	/** The property path. */
	private String propertyPath;

	/** The Constant GET_PREFIX. */
	private static final String GET_PREFIX = "get";

	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(ConvertAlertDate.class);

	/**
	 * Instantiates a new convert alert date.
	 * 
	 * @param dateFormatParam the date format param
	 * @param propertyPathParam the property path param
	 */
	public ConvertAlertDate(String dateFormatParam, String propertyPathParam)
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
		if (!Light.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(Light.class, value, context, this);
		}

		Light light = (Light)value;
		Location location = light.getRadio().getLocation();
		timeZone = location.getTimeZoneInfo().getTimeZone().getID();
		date = getDate(light);

		return next
				.execute(
						checkAlertDateToGenerateCsv(light.getLastNotificationHistory(), date), context);
	}

	/**
	 * Check alert date to generate csv.
	 * 
	 * @param notificationHistory the notification history
	 * @return the date
	 */
	public String checkAlertDateToGenerateCsv(NotificationHistory notificationHistory, Date date)
	{
		if (!ValidationUtil.isNull(notificationHistory)
				&& ValidationUtil.isNullOrEmpty(notificationHistory.getAlertClassifications()))
		{
			return null;
		}

		return LCDateUtil.convertDateToString(date, timeZone, dateFormat);
	}

	/**
	 * Gets the date.
	 * 
	 * @param light the light
	 * @return the date
	 */
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

}
