package com.sensus.dm.common.base.util.csv.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.process.model.DMProcess;

/**
 * The Class ConvertEventDescription.
 * 
 * @author QAT Global.
 */
public class ConvertEventDescription extends CellProcessorAdaptor implements StringCellProcessor
{
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

		final String result = DMUtil.formatDescription((DMProcess)value);

		return next.execute(result, context);

	}
}
