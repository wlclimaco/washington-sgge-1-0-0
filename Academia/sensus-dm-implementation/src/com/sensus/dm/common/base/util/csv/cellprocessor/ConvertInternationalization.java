package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;

/**
 * The Class ConvertInternationalization.
 *
 * @author QAT Global.
 */
public class ConvertInternationalization extends CellProcessorAdaptor implements StringCellProcessor
{

	/** The locale. */
	private final Locale locale;

	/**
	 * Instantiates a new convert internationalization.
	 *
	 * @param locale the locale
	 */
	public ConvertInternationalization(Locale localeParam)
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
		if (!(String.class.isAssignableFrom(value.getClass())) && !(Enum.class.isAssignableFrom(value.getClass())))
		{
			throw new SuperCsvCellProcessorException(String.class, value, context, this);
		}

		String result = "";
		if (!ValidationUtil.isNull(value))
		{
			result = SensusMessageUtil.getMessage(value.toString(), null, null, locale);
		}
		return next.execute(result, context);
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
