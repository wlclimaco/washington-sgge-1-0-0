package com.sensus.dm.common.base.util.csv.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.cbof.model.Device;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class ConvertPremiseId.
 * 
 * @author QAT Global.
 */
public class ConvertPremiseId extends CellProcessorAdaptor implements StringCellProcessor
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

		String result = premiseId(value);

		return next.execute(result, context);
	}

	/**
	 * Premise id.
	 * 
	 * @param value the value
	 * @return the string
	 */
	private String premiseId(Object value)
	{
		if (ElectricMeter.class.isAssignableFrom(value.getClass())
				&& !ValidationUtil.isNull(((ElectricMeter)value).getConfiguration()))
		{
			return ((ElectricMeter)value).getConfiguration().getPremiseId();
		}

		if (WaterMeter.class.isAssignableFrom(value.getClass())
				&& !ValidationUtil.isNull(((WaterMeter)value).getConfiguration()))
		{
			return ((WaterMeter)value).getConfiguration().getPremiseId();
		}

		if (GasMeter.class.isAssignableFrom(value.getClass())
				&& !ValidationUtil.isNull(((GasMeter)value).getConfiguration()))
		{
			return ((GasMeter)value).getConfiguration().getPremiseId();
		}

		return null;
	}
}
