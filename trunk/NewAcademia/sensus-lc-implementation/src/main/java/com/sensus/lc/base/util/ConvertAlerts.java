package com.sensus.lc.base.util;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.model.Light;

/**
 * The Class ConvertAlerts.
 */
public class ConvertAlerts extends CellProcessorAdaptor
{

	private Locale locale;

	/**
	 * Instantiates a new convert alerts.
	 * 
	 * @param locale the locale
	 */
	public ConvertAlerts(Locale locale)
	{
		super();
		this.locale = locale;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!Light.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(Light.class, value, context, this);
		}

		Light light = (Light)value;

		return next.execute(getAlert(light), context);
	}

	/**
	 * Gets the alert.
	 * 
	 * @param light the light
	 * @return the alert
	 */
	private String getAlert(Light light)
	{
		if (ValidationUtil.isNull(light.getLastNotificationHistory())
				|| ValidationUtil.isNullOrEmpty(light.getLastNotificationHistory().getAlertClassifications()))
		{
			return null;
		}

		// always return the first position;
		return SensusMessageUtil.getMessage(light.getLastNotificationHistory().getAlertClassifications().get(0)
				.getAlertSubType()
				.getAlertSubtTypeKey(), null, null, locale);

	}
}
