package com.sensus.dm.gas.device.validation;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.gas.device.model.GasMeter;

/**
 * The Class GasMeterValidator.
 * 
 * @author QAT Global.
 */
public class GasMeterValidator implements IValidator
{

	/** The Constant GAS_METER_REQUIRED. */
	private static final String GAS_METER_REQUIRED = "sensus.epm.gasmetervalidator.gasmeter.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{

		GasMeter device = (GasMeter)validationContext.getObjectToBeValidated(GasMeter.class.getSimpleName());

		if (ValidationUtil.isNull(device))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(GAS_METER_REQUIRED));
		}
	}

}
