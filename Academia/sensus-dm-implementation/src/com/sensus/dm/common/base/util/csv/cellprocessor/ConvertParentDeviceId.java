package com.sensus.dm.common.base.util.csv.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.cbof.model.Device;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;

/**
 * The Class ConvertParentDeviceId.
 * 
 * @author QAT Global.
 */
public class ConvertParentDeviceId extends CellProcessorAdaptor implements StringCellProcessor
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

		String result = parentDeviceId((Device)value);

		return next.execute(result, context);
	}

	/**
	 * Parent device id.
	 * 
	 * @param device the device
	 * @return the string
	 */
	private String parentDeviceId(Device device)
	{
		if (HanDevice.class.isAssignableFrom(device.getClass()))
		{
			return String.valueOf(((HanDevice)device).getElectricMeterFlexNetId());
		}

		if (LCM.class.isAssignableFrom(device.getClass()))
		{
			return String.valueOf(((LCM)device).getElectricMeterFlexNetId());
		}

		return null;
	}

}
