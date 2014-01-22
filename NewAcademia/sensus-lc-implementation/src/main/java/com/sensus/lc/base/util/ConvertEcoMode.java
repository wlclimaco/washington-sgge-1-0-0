package com.sensus.lc.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

/**
 * The Class ConvertEcoMode.
 * 
 * @author QAT Global.
 */
public class ConvertEcoMode extends CellProcessorAdaptor implements DoubleCellProcessor
{

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!Double.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(String.class, value, context, this);
		}

		String result = new BigDecimal(String.valueOf(value)).setScale(0, RoundingMode.HALF_EVEN).intValue() + "%";

		return next.execute(result, context);
	}
}
