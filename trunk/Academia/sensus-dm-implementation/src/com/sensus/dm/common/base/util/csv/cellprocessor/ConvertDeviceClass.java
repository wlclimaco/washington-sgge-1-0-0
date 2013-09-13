package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.cbof.model.Device;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.elec.device.model.LCM;

/**
 * The Class ConvertDeviceClass.
 * 
 * @author QAT Global.
 */
public class ConvertDeviceClass extends CellProcessorAdaptor implements StringCellProcessor
{
	/** The locale. */
	private final Locale locale;

	/**
	 * Instantiates a new convert device class.
	 * 
	 * @param localeParam the locale param
	 */
	public ConvertDeviceClass(final Locale localeParam)
	{
		super();
		checkPreconditions(localeParam);
		locale = localeParam;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{

		if (!Device.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(Device.class, value, context, this);
		}

		String result = deviceClass(value);

		return next.execute(result, context);
	}

	/**
	 * Device class.
	 * 
	 * @param value the value
	 * @return the string
	 */
	private String deviceClass(Object value)
	{
		if (LCM.class.isAssignableFrom(value.getClass())
				&& !ValidationUtil.isNullOrEmpty(((LCM)value).getLcmRelays())
				&& !ValidationUtil.isNull(((LCM)value).getFirstLCMRelay().getDeviceClass()))
		{
			return SensusMessageUtil.getMessage(((LCM)value).getFirstLCMRelay().getDeviceClass().toString(), null,
					null, locale);
		}

		return null;
	}

	/**
	 * Check preconditions.
	 * 
	 * @param locale the locale
	 */
	private static void checkPreconditions(Locale locale)
	{
		if (locale == null)
		{
			throw new NullPointerException("locale should not be null");
		}
	}
}
