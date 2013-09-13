package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.csv.model.CsvColumnEnum;
import com.sensus.dm.common.process.model.DMProcess;

/**
 * The Class ConvertAction.
 * 
 * @author QAT Global.
 */
public class ConvertProcessAction extends CellProcessorAdaptor implements StringCellProcessor
{

	/** The locale. */
	private final Locale locale;

	/** The column. */
	private final String column;

	/**
	 * Instantiates a new convert internationalization.
	 * 
	 * @param localeParam the locale param
	 * @param columnParam the column param
	 */
	public ConvertProcessAction(Locale localeParam, String columnParam)
	{
		super();
		checkPreconditions(localeParam);
		locale = localeParam;
		column = columnParam;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!(DMProcess.class.isAssignableFrom(value.getClass())))
		{
			throw new SuperCsvCellProcessorException(DMProcess.class, value, context, this);
		}
		DMProcess process = (DMProcess)value;

		String result = "";

		if (CsvColumnEnum.ACTION_NAME.getValue().equals(column))
		{
			if (!ValidationUtil.isNull(process.getAction())
					&& !ValidationUtil.isNull(process.getAction().getActionType())
					&& !ValidationUtil.isNull(process.getAction().getActionType().getActionTypeEnum()))
			{
				result = getLocalizedValueAsString(process.getAction().getActionType().getActionTypeEnum()
						.getActionTypeName(), null);
			}
			else if (!ValidationUtil.isNull(process.getProcessType()))
			{
				result = getLocalizedValueAsString(process.getProcessType().getDescription(), null);
			}

		}
		else if (CsvColumnEnum.ACTION_TYPE.getValue().equals(column))
		{
			if (!ValidationUtil.isNull(process.getAction())
					&& !ValidationUtil.isNull(process.getAction().getActionType())
					&& !ValidationUtil.isNull(process.getAction().getActionType().getActionTypeEnum()))
			{
				result = getLocalizedValueAsString(process.getAction().getActionType().getActionTypeEnum()
						.getActionCategoryEnum().getActionTypeCategoryName(), null);
			}
			else if (!ValidationUtil.isNull(process.getProcessType())
					&& !ValidationUtil.isNull(process.getProcessType().getProcessCategory()))
			{
				result = getLocalizedValueAsString(process.getProcessType().getProcessCategory().getName(), null);
			}

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

	/**
	 * A simple utility method to get the value of an object.toString() which will be localized
	 * or return a default value if it's null.
	 * 
	 * @param obj the object
	 * @param defVal the default value
	 * @return the obj.toString()
	 */
	protected String getLocalizedValueAsString(Object obj, String defVal)
	{
		String val = getValueAsString(obj, defVal);

		if (val != null)
		{
			return SensusMessageUtil.getMessage(val, null, null, locale);
		}

		return null;
	}

	/**
	 * A simple utility method to get the value of an object.toString()
	 * or return a default value if it's null.
	 * 
	 * @param obj the object
	 * @param defVal the default value
	 * @return the obj.toString()
	 */
	protected String getValueAsString(Object obj, String defVal)
	{
		if (obj == null)
		{
			return defVal;
		}

		return obj.toString();
	}
}
