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
 * The Class ConvertRandomizeDuration.
 * 
 * @author QAT Global.
 */
public class ConvertRandomizeDuration extends CellProcessorAdaptor implements StringCellProcessor
{
	/** The locale. */
	private final Locale locale;

	/** The Constant RANDOMIZE_DURATION_UNITS. */
	private static final String RANDOMIZE_DURATION_UNITS = "dm.common.lcm.csv.randomize.duration.units";

	/** The Constant SLASH. */
	private static final String SLASH = "/";

	/**
	 * Instantiates a new convert device class.
	 * 
	 * @param localeParam the locale param
	 */
	public ConvertRandomizeDuration(final Locale localeParam)
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

		String result = randomizeDuration(value);

		return next.execute(result, context);
	}

	/**
	 * Randomize duration.
	 * 
	 * @param value the value
	 * @return the string
	 */
	private String randomizeDuration(Object value)
	{
		if (LCM.class.isAssignableFrom(value.getClass())
				&& !ValidationUtil.isNullOrEmpty(((LCM)value).getLcmRelays()))
		{
			String randomizeDuration = "";
			if (!ValidationUtil.isNull(((LCM)value).getFirstLCMRelay().getStartMinutes()))
			{
				randomizeDuration = ((LCM)value).getFirstLCMRelay().getStartMinutes().toString();
			}
			randomizeDuration += SLASH;
			if (!ValidationUtil.isNull(((LCM)value).getFirstLCMRelay().getEndMinutes()))
			{
				randomizeDuration += ((LCM)value).getFirstLCMRelay().getEndMinutes().toString();
			}

			if (randomizeDuration.equals(SLASH))
			{
				return randomizeDuration;
			}
			return randomizeDuration + " " + SensusMessageUtil.getMessage(RANDOMIZE_DURATION_UNITS, null,
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
