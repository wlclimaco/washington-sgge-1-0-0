package com.sensus.lc.ecomode.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

/**
 * The Class ConvertWattageToKWattage.
 */
public class ConvertWattageToKWattage extends CellProcessorAdaptor implements DoubleCellProcessor
{
	/** The Constant SCALE. */
	public static final int SCALE = 3;

	/** The Constant ROUNDING_MODE. */
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

	/** The Constant THOUSAND. */
	private static final Integer THOUSAND = 1000;

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!Double.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(Double.class, value, context, this);
		}

		return next.execute(convertWattage(String.valueOf(value)), context);
	}

	private String convertWattage(String value)
	{
		String result = String.valueOf(
				new BigDecimal(Double.valueOf(value) / THOUSAND).setScale(SCALE, ROUNDING_MODE)
						.doubleValue());
		return result;
	}
}
