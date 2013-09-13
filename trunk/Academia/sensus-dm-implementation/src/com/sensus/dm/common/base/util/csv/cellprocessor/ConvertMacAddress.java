package com.sensus.dm.common.base.util.csv.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMTypeEnum;

/**
 * The Class ConvertMacAddress.
 * 
 * @author QAT Global.
 */
public class ConvertMacAddress extends CellProcessorAdaptor implements StringCellProcessor, LongCellProcessor
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

		final String result = convertMacAddress((Device)value);
		return next.execute(result, context);
	}

	/**
	 * Convert mac address.
	 * 
	 * @param device the device
	 * @return the string
	 */
	private String convertMacAddress(Device device)
	{
		if (DeviceTypeEnum.HAN_DEVICE.equals(device.getDeviceType()))
		{
			if (!ValidationUtil.isNull(device.getRadio())
					&& !HanDevice.class.isAssignableFrom(device.getClass()))
			{
				return String.valueOf(device.getRadio().getFlexNetId());
			}

			if (ValidationUtil.isNull(device.getRadio())
					|| ValidationUtil.isNull(device.getRadio().getFlexNetId()))
			{
				return ((HanDevice)device).getMacAddress();
			}

			return DMConvertUtil.convertMacAddress(device.getRadio().getFlexNetId());

		}
		if (DeviceTypeEnum.LCM.equals(device.getDeviceType()))
		{
			if (!ValidationUtil.isNull(device.getRadio())
					&& !LCM.class.isAssignableFrom(device.getClass()))
			{
				return String.valueOf(device.getRadio().getFlexNetId());
			}

			if ((LCMTypeEnum.FLEXNET_LCM.equals(((LCM)device).getLcmTypeEnum())
					&& !ValidationUtil.isNull(device.getRadio()))
					&& !ValidationUtil.isNull(device.getRadio().getFlexNetId()))
			{
				return String.valueOf(device.getRadio().getFlexNetId());
			}

			if (ValidationUtil.isNull(device.getRadio())
					|| ValidationUtil.isNull(device.getRadio().getFlexNetId()))
			{
				// If LCM is unreachable.
				return ((LCM)device).getMacAddress();
			}

			return DMConvertUtil.convertMacAddress(device.getRadio().getFlexNetId());
		}

		if (!ValidationUtil.isNull(device.getRadio()))
		{
			return String.valueOf(device.getRadio().getFlexNetId());
		}
		return null;
	}
}
