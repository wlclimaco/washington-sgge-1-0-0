package com.sensus.dm.common.device.validation;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.device.model.DeviceSearch;

/**
 * The Class DeviceSearchValidator.
 * 
 * @author QAT Global
 */
public class DeviceSearchValidator implements IValidator
{

	/** The Constant DEVICE_SEARCH_REQUIRED. */
	private static final String DEVICE_SEARCH_REQUIRED = "sensus.epm.devicesearchvalidator.device.search.required";

	/** The Constant HAN_DEVICE_SEARCH_REQUIRED. */
	private static final String HAN_DEVICE_SEARCH_REQUIRED =
			"sensus.epm.devicesearchvalidator.han.device.search.required";

	/** The Constant HAN_DEVICE_TYPE_REQUIRED. */
	private static final String HAN_DEVICE_TYPE_REQUIRED = "sensus.epm.devicesearchvalidator.han.device.type.required";

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

		DeviceSearch deviceSearch =
				(DeviceSearch)validationContext.getObjectToBeValidated(DeviceSearch.class.getSimpleName());

		if (ValidationUtil.isNull(action))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(DEVICE_SEARCH_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_ALL:
			case GENERATE_FILE_CSV:
			case ADD_SMP_TO_GRP:
			case DEL_SMP_FROM_GRP:
				validateHanDeviceType(validationContext, deviceSearch);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate han device type.
	 * 
	 * @param validationContext the validation context
	 * @param deviceSearch the device search
	 */
	private void validateHanDeviceType(ValidationContext validationContext, DeviceSearch deviceSearch)
	{
		if (ValidationUtil.isNull(deviceSearch.getHanDeviceSearch()))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(HAN_DEVICE_SEARCH_REQUIRED));
			return;
		}

		ValidationUtil.isNullOrEmpty(deviceSearch.getHanDeviceSearch().getHanDeviceTypeEnumList(),
				HAN_DEVICE_TYPE_REQUIRED, validationContext.getMessages());
	}
}
