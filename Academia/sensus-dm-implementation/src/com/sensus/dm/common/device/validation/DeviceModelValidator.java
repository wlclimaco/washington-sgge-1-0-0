package com.sensus.dm.common.device.validation;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.device.model.DeviceModel;

/**
 * The Class DeviceModelValidator.
 * 
 * @author QAT Global.
 */
public class DeviceModelValidator implements IValidator
{

	/** The Constant HAN_DEVICES_TYPE_REQUIRED. */
	private static final String HAN_DEVICES_TYPE_MANUFACTURE_REQUIRED =
			"sensus.epm.devicemodelvalidator.manufacture.required";

	/** The Constant DEVICE_MODEL_REQUIRED. */
	private static final String DEVICE_MODEL_REQUIRED =
			"sensus.epm.devicemodelvalidator.required";

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

		DeviceModel deviceModel =
				(DeviceModel)validationContext.getObjectToBeValidated(DeviceModel.class.getSimpleName());

		if ((ValidationUtil.isNull(action))
				|| ValidationUtil.isNull(deviceModel))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(DEVICE_MODEL_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_ALL_MODEL_BY_HAN_DEVICE_TYPE:
				ValidationUtil.isNull(deviceModel.getManufacture(), HAN_DEVICES_TYPE_MANUFACTURE_REQUIRED,
						validationContext.getMessages());
				break;
			default:
				break;
		}
	}
}
