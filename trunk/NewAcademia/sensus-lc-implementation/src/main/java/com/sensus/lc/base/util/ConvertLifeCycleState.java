package com.sensus.lc.base.util;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.model.Light;

public class ConvertLifeCycleState extends CellProcessorAdaptor
{

	private Locale locale;

	/**
	 * Instantiates a new convert life cycle state.
	 * 
	 * @param locale the locale
	 */
	public ConvertLifeCycleState(Locale locale)
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

		return next.execute(getLifeCycleState(light), context);
	}

	/**
	 * Gets the life cycle state.
	 * 
	 * @param light the light
	 * @return the life cycle state
	 */
	private String getLifeCycleState(Light light)
	{
		if (ValidationUtil.isNull(light.getLifeCycleState()))
		{
			return null;
		}
		return SensusMessageUtil.getMessage(light.getLifeCycleState().getLifeCycleStateKey(), null, null, locale);
	}
}
