package com.sensus.dm.common.device.validation;

import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;

/**
 * The Class DeviceValidator.
 * 
 * @author QAT Global
 */
public class DeviceValidator implements IValidator
{

	/** The Constant DEVICE_REQUIRED. */
	private static final String DEVICE_REQUIRED = "sensus.epm.devicevalidator.device.required";

	/** The Constant DEVICE_ID_REQUIRED. */
	private static final String DEVICE_ID_REQUIRED = "sensus.epm.devicevalidator.device.id.required";

	/** The Constant FLEXNET_ID_REQUIRED. */
	private static final String FLEXNET_ID_REQUIRED = "sensus.epm.radiovalidator.flexnet.id.required";

	/** The Constant DEVICE_TYPE_ENUM_REQUIRED. */
	private static final String DEVICE_TYPE_ENUM_REQUIRED = "sensus.epm.devicevalidator.devicetype.required";

	/** The id length. */
	private int deviceIdLength;

	/**
	 * Gets the device id length.
	 * 
	 * @return the device id length
	 */
	public int getDeviceIdLength()
	{
		return deviceIdLength;
	}

	/**
	 * Sets the device id length.
	 * 
	 * @param deviceIdLength the new device id length
	 */
	public void setDeviceIdLength(int deviceIdLength)
	{
		this.deviceIdLength = deviceIdLength;
	}

	/**
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		Device device = (Device)validationContext.getObjectToBeValidated(Device.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(device))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(DEVICE_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_UPDATED_LOAD_PROFILE:
			case FETCH_ALL_WATER_GAS_DATA_READ:
			case FETCH_ALL_INTERVAL_READ:
			case FETCH_ALL_LOAD_PROFILE_READ:
			case FETCH_ALL_TOU_READ:
			case FETCH_ALL_SNAPSHOT:
			case GENERATE_FILE_CSV_WATER_GAS_DATA_READ:
			case GENERATE_FILE_CSV_INTERVAL_READ:
			case GENERATE_FILE_CSV_LOAD_PROFILE_READ:
			case GENERATE_FILE_CSV_SNAPSHOT:
			case GENERATE_FILE_CSV_TOU:
				ValidationUtil.isNullOrEmpty(device.getDeviceId(), DEVICE_ID_REQUIRED, validationContext.getMessages());
				ValidationUtil.isNull(device.getDeviceType(), DEVICE_TYPE_ENUM_REQUIRED,
						validationContext.getMessages());
				break;
			case FETCH_ALL:
				validateDeviceId(validationContext.getMessages(), device);
				break;
			case FETCH_DEVICE_BY_ID:
				validateDeviceIdOrFlexNetIdRequired(validationContext.getMessages(), device);
				ValidationUtil.isNull(device.getDeviceType(), DEVICE_TYPE_ENUM_REQUIRED,
						validationContext.getMessages());
				break;
			case FETCH_DEVICE_BY_ID_IMPORT:
				validateDeviceIdOrFlexNetIdRequired(validationContext.getMessages(), device);
				break;
			case FETCH_ALL_MODEL_BY_HAN_DEVICE_TYPE:
			case FETCH_ALL_MANUFACTURE_BY_HAN_DEVICE_TYPE:
				ValidationUtil.isNull(device.getDeviceType(), DEVICE_TYPE_ENUM_REQUIRED,
						validationContext.getMessages());
				break;
			case FETCH_ALL_PEAK_DEMAND:
			case GENERATE_FILE_CSV_PEAK_READ:
				ValidationUtil.isNullOrEmpty(device.getDeviceId(), DEVICE_ID_REQUIRED, validationContext.getMessages());
				break;
			default:
				break;
		}
	}

	/**
	 * Validate device id.
	 * 
	 * @param list the list
	 * @param device the device
	 */
	private void validateDeviceId(List<MessageInfo> list, Device device)
	{
		if (!ValidationUtil.isNull(device.getDeviceId())
				&& device.getDeviceId().length() > getDeviceIdLength())
		{
			list.add(MessageInfo.createFieldValidationError(DEVICE_ID_REQUIRED, getDeviceIdLength()));
		}
	}

	/**
	 * Validate device id.
	 * 
	 * @param list the list
	 * @param device the device
	 */
	public void validateDeviceIdOrFlexNetIdRequired(List<MessageInfo> list, Device device)
	{
		if ((device.getRadio() == null || ValidationUtil.isNull(device.getRadio().getFlexNetId()))
				&& ValidationUtil.isNull(device.getDeviceId()))
		{
			list.add(MessageInfo.createFieldValidationError(DEVICE_ID_REQUIRED));
			list.add(MessageInfo.createFieldValidationError(FLEXNET_ID_REQUIRED));
		}
	}
}
