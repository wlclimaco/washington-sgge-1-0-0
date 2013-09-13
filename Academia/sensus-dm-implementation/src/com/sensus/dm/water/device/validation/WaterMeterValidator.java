package com.sensus.dm.water.device.validation;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class WaterMeterValidator.
 * 
 * @author QAT Global.
 */
public class WaterMeterValidator implements IValidator
{

	/** The Constant WATER_METER_REQUIRED. */
	private static final String WATER_METER_REQUIRED = "sensus.epm.watermetervalidator.watermeter.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{

		WaterMeter device = (WaterMeter)validationContext.getObjectToBeValidated(WaterMeter.class.getSimpleName());

		if (ValidationUtil.isNull(device))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(WATER_METER_REQUIRED));
		}
	}

}
