package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;

/**
 * The Class ConvertLifecycleState.
 * 
 * @author QAT Global.
 */
public class ConvertLifecycleState extends CellProcessorAdaptor implements StringCellProcessor
{

	/** The locale. */
	private final Locale locale;

	/**
	 * Instantiates a new convert lifecycle state.
	 * 
	 * @param localeParam the locale param
	 */
	public ConvertLifecycleState(Locale localeParam)
	{
		super();
		checkPreconditions(localeParam);
		locale = localeParam;
	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!Device.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(String.class, value, context, this);
		}

		String result = "";
		if (DeviceTypeEnum.ELECTRIC_METER.equals(((Device)value).getDeviceType()))
		{
			if (ValidationUtil.isNull(((ElectricMeter)value).getLifecycleStateEnum()))
			{
				result =
						SensusMessageUtil.getMessage(ElectricMeterLifecycleStateEnum.NOT_ASSIGNED.toString(), null,
								null,
								locale);
			}
			else
			{
				result =
						SensusMessageUtil.getMessage(((ElectricMeter)value).getLifecycleStateEnum().toString(), null,
								null,
								locale);
			}
		}
		else if (DeviceTypeEnum.LCM.equals(((Device)value).getDeviceType())
				&& !ValidationUtil.isNull(((LCM)value).getLifecycleStateEnum()))
		{
			result =
					SensusMessageUtil.getMessage(((LCM)value).getLifecycleStateEnum().toString(), null, null,
							locale);
		}
		else if (DeviceTypeEnum.HAN_DEVICE.equals(((Device)value).getDeviceType())
				&& !ValidationUtil.isNull(((HanDevice)value).getLifecycleStateEnum()))
		{
			result =
					SensusMessageUtil.getMessage(((HanDevice)value).getLifecycleStateEnum().toString(), null, null,
							locale);
		}

		return next.execute(result, context);
	}

	/**
	 * Check preconditions.
	 * 
	 * @param locale the locale
	 */
	private static void checkPreconditions(Locale locale)
	{
		if (locale == null)
		{
			throw new NullPointerException("locale should not be null");
		}
	}

}
