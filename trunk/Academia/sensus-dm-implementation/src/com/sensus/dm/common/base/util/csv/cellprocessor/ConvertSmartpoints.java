package com.sensus.dm.common.base.util.csv.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.dm.common.base.util.csv.model.CsvColumnEnum;
import com.sensus.dm.common.process.model.DMProcess;

/**
 * The Class ConvertTotalSmartpoints.
 * 
 * @author QAT Global.
 */
public class ConvertSmartpoints extends CellProcessorAdaptor implements StringCellProcessor
{

	/** The Constant COLUMN_OFFSET. */
	private static final String COLUMN_OFFSET = "0";

	/** The unreachable device ids. */
	private final Integer unreachableDeviceIds;

	/** The column. */
	private final String column;

	/**
	 * Gets the unreachable device ids.
	 * 
	 * @return the unreachable device ids
	 */
	public Integer getUnreachableDeviceIds()
	{
		return unreachableDeviceIds;
	}

	/**
	 * Gets the column.
	 * 
	 * @return the column
	 */
	public String getColumn()
	{
		return column;
	}

	/**
	 * Instantiates a new convert internationalization.
	 * 
	 * @param unreachableDeviceIdsParam the unreachable device ids param
	 * @param columnParam the column param
	 */
	public ConvertSmartpoints(Integer unreachableDeviceIdsParam, String columnParam)
	{
		super();
		unreachableDeviceIds = unreachableDeviceIdsParam;
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
			throw new SuperCsvCellProcessorException(String.class, value, context, this);
		}
		DMProcess process = (DMProcess)value;

		String result = "";

		if (CsvColumnEnum.DEVICES_TOTAL.getValue().equals(getColumn()))
		{
			result = String.valueOf(Integer.parseInt(getValueAsString(process.getTotalSmartpoints(), COLUMN_OFFSET))
					+ getUnreachableDeviceIds());

		}
		else if (CsvColumnEnum.DEVICES_FAILED.getValue().equals(getColumn()))
		{
			result = String.valueOf(Integer.parseInt(
					getValueAsString(process.getFailedSmartpoints(), COLUMN_OFFSET)) + getUnreachableDeviceIds());
		}

		return next.execute(result, context);

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
