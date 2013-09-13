package com.sensus.dm.elec.device.validation;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.elec.device.model.HanDevice;

/**
 * The Class HanDeviceValidator.
 * 
 * @author QAT Global.
 */
public class HanDeviceValidator implements IValidator
{

	/** The Constant HAN_DEVICES_REQUIRED. */
	private static final String HAN_DEVICES_REQUIRED = "sensus.epm.handevicevalidator.han.device.required";

	/** The Constant HAN_DEVICES_TYPE_REQUIRED. */
	private static final String HAN_DEVICES_TYPE_REQUIRED = "sensus.epm.handevicevalidator.han.devices.type.required";

	/** The Constant MAC_ADDRESS_REQUIRED. */
	private static final String MAC_ADDRESS_REQUIRED = "sensus.epm.devicevalidator.device.mac.address.required";

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

		Object device = validationContext.getObjectToBeValidated(HanDevice.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(device)
				|| !HanDevice.class.isAssignableFrom(device.getClass()))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(HAN_DEVICES_REQUIRED));
			return;
		}

		HanDevice hanDevice = (HanDevice)device;

		if (ValidationUtil.isNull(hanDevice))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(HAN_DEVICES_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_ALL_MANUFACTURE_BY_HAN_DEVICE_TYPE:
			case FETCH_ALL_MODEL_BY_HAN_DEVICE_TYPE:
				ValidationUtil.isNull(hanDevice.getHanDeviceTypeEnum(), HAN_DEVICES_TYPE_REQUIRED,
						validationContext.getMessages());
				break;
			case FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION:
				ValidationUtil.isNull(hanDevice.getMacAddress(), MAC_ADDRESS_REQUIRED, validationContext.getMessages());
				break;
			default:
				break;
		}
	}
}
