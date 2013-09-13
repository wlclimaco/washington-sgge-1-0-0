package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.process.model.ProcessItem;

/**
 * The Class ConvertProcessMessage.
 * 
 * @author QAT Global.
 */
public class ConvertProcessMessage extends CellProcessorAdaptor implements StringCellProcessor
{
	/** The locale. */
	private Locale locale;

	/**
	 * Instantiates a new convert process message.
	 * 
	 * @param localeParam the locale param
	 */
	public ConvertProcessMessage(Locale localeParam)
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
		if (!ProcessItem.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(ProcessItem.class, value, context, this);
		}

		String result = processMessage((ProcessItem)value);
		return next.execute(result, context);
	}

	/**
	 * Process message.
	 * 
	 * @param processItem the process item
	 * @return the string
	 */
	private String processMessage(ProcessItem processItem)
	{
		if (!ValidationUtil.isNullOrEmpty(processItem.getMessage()))
		{
			return DMUtil.getReturnMessage(processItem.getMessage(), locale);
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
