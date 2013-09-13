package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.HashMap;
import java.util.List;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.util.CsvContext;

/**
 * The Class ConvertChannel.
 * 
 * @author QAT Global.
 */
public class ConvertChannel extends CellProcessorAdaptor implements StringCellProcessor
{

	/** The column. */
	private final Integer column;

	/**
	 * Instantiates a new convert time zone.
	 * 
	 * @param columnParam the column
	 */
	public ConvertChannel(Integer columnParam)
	{
		super();
		this.column = columnParam;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(Object value, CsvContext context)
	{
		List<HashMap<String, String>> channels = (List<HashMap<String, String>>)value;

		HashMap<String, String> map = channels.get(column);
		String key = map.keySet().iterator().next();

		return next.execute(map.get(key), context);

	}

}
