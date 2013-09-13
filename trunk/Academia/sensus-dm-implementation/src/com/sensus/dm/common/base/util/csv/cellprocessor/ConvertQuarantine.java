package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;

/**
 * The Class ConvertQuarantine.
 * 
 * @author QAT Global.
 */
public class ConvertQuarantine extends CellProcessorAdaptor implements BoolCellProcessor
{
	/** The Constant QUARANTINED. */
	private static final String QUARANTINED = "QUARANTINED";

	/** The locale. */
	private final Locale locale;

	/**
	 * Instantiates a new convert quarantine.
	 * 
	 * @param localeParam the locale param
	 */
	public ConvertQuarantine(final Locale localeParam)
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

		final String result = getQuaratine((Boolean)value);
		return next.execute(result, context);
	}

	/**
	 * Gets the quaratine.
	 * 
	 * @param bool the bool
	 * @return the quaratine
	 */
	private String getQuaratine(Boolean bool)
	{
		if (!ValidationUtil.isNull(bool) && bool)
		{
			return SensusMessageUtil.getMessage(QUARANTINED, null, null, locale);
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
