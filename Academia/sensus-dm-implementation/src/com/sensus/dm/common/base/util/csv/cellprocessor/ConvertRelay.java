package com.sensus.dm.common.base.util.csv.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.cbof.model.Device;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.elec.device.model.LCM;

/**
 * The Class ConvertRelay.
 * 
 * @author QAT Global.
 */
public class ConvertRelay extends CellProcessorAdaptor implements StringCellProcessor
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

		String result = relay(value);

		return next.execute(result, context);
	}

	/**
	 * Relay.
	 * 
	 * @param value the value
	 * @return the string
	 */
	private String relay(Object value)
	{
		if (LCM.class.isAssignableFrom(value.getClass())
				&& !ValidationUtil.isNullOrEmpty(((LCM)value).getLcmRelays())
				&& !ValidationUtil.isNull(((LCM)value).getFirstLCMRelay().getRelay()))
		{
			return ((LCM)value).getFirstLCMRelay().getRelay().toString();
		}

		return null;
	}
}
