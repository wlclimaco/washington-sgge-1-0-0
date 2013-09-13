package com.sensus.dm.elec.device.validation;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.elec.device.model.LCM;

/**
 * The Class LCMValidator.
 * 
 * @author QAT Global.
 */
public class LCMValidator implements IValidator
{
	/** The Constant LCM_REQUIRED. */
	private static final String LCM_REQUIRED = "sensus.epm.lcmvalidator.lcm.required";

	/** The Constant LCM_TYPE_REQUIRED. */
	private static final String LCM_TYPE_REQUIRED = "sensus.epm.lcmvalidator.lcm.type.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		Object device = validationContext.getObjectToBeValidated(LCM.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(device)
				|| !LCM.class.isAssignableFrom(device.getClass()))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(LCM_REQUIRED));
			return;
		}

		LCM lcm = (LCM)device;

		if (ValidationUtil.isNull(lcm))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(LCM_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_ALL_MANUFACTURE_BY_HAN_DEVICE_TYPE:
			case FETCH_ALL_MODEL_BY_HAN_DEVICE_TYPE:
				ValidationUtil.isNull(lcm.getLcmTypeEnum(), LCM_TYPE_REQUIRED,
						validationContext.getMessages());
				break;
			default:
				break;
		}

	}

}
