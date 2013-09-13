package com.sensus.dm.common.base.util.csv.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.util.CsvContext;

/**
 * The Class ConvertTouRead.
 * 
 * @author QAT Global.
 */
public class ConvertTouRead extends CellProcessorAdaptor implements StringCellProcessor
{

	/** The column. */
	private final Integer column;

	/**
	 * Instantiates a new convert TOU read.
	 * 
	 * @param columnParam the column
	 */
	public ConvertTouRead(Integer columnParam)
	{
		super();
		this.column = columnParam;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		String[] touRead = (String[])value;
		return next.execute(touRead[column], context);

	}

}
