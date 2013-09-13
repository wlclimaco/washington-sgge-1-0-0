package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.common.util.SensusMessageUtil;

/**
 * The Class ConvertEncryptionSupported.
 * 
 * @author QAT Global.
 */
public class ConvertEncryptionSupported extends CellProcessorAdaptor implements BoolCellProcessor
{

	/** The Constant ENABLED. */
	private static final String ENABLED = "dm.elec.device.csv.enabled";

	/** The Constant DISABLED. */
	private static final String DISABLED = "dm.elec.device.csv.disabled";

	/** The locale. */
	private final Locale locale;

	/**
	 * Instantiates a new convert encryption supported.
	 * 
	 * @param localeParam the locale param
	 */
	public ConvertEncryptionSupported(Locale localeParam)
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
		if (!Boolean.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(Boolean.class, value, context, this);
		}

		final String result = getEnabled((Boolean)value);
		return next.execute(result, context);
	}

	/**
	 * Gets the enabled.
	 * 
	 * @param bool the bool
	 * @return the enabled
	 */
	private String getEnabled(Boolean bool)
	{
		if (bool == null)
		{
			return null;
		}

		if (bool)
		{
			return SensusMessageUtil.getMessage(ENABLED, null, null, locale);
		}

		return SensusMessageUtil.getMessage(DISABLED, null, null, locale);
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
