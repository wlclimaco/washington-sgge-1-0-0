package com.sensus.dm.common.base.util.csv.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.cbof.model.Device;
import com.sensus.dm.elec.device.model.ElectricMeter;

/**
 * The Class ConvertRemoteConnect.
 * 
 * @author QAT Global.
 */
public class ConvertRemoteConnect extends CellProcessorAdaptor implements StringCellProcessor
{

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{

		if (!Device.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(Device.class, value, context, this);
		}

		String result = remoteConnect(value);

		return next.execute(result, context);
	}

	/**
	 * Remote connect.
	 * 
	 * @param value the value
	 * @return the string
	 */
	private String remoteConnect(Object value)
	{
		if (!ElectricMeter.class.isAssignableFrom(value.getClass()))
		{
			return null;
		}

		return ((ElectricMeter)value).getRemoteConnectStatusEnum().toString();
	}

}
