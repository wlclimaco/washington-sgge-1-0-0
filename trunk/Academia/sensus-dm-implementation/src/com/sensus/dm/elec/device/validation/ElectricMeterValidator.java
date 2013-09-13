package com.sensus.dm.elec.device.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.cbof.model.Device;
import com.sensus.dm.elec.device.model.ElectricMeter;

/**
 * The Class ElectricMeterValidator.
 * 
 * @author QAT Global.
 */
public class ElectricMeterValidator implements IValidator
{

	/** The Constant ELEC_METER_REQUIRED. */
	private static final String ELEC_METER_REQUIRED = "sensus.epm.electricmetervalidator.elecmeter.required";

	/** The Constant ELEC_CONFIGURATION_REQUIRED. */
	private static final String ELEC_CONFIGURATION_REQUIRED =
			"sensus.epm.electricmetervalidator.elecmeter.configuration.required";

	/** The Constant PREMISE_ID_REQUIRED. */
	private static final String PREMISE_ID_REQUIRED = "sensus.epm.devicevalidator.device.premise.id.required";

	/** The Constant DEVICE_ID_REQUIRED. */
	private static final String DEVICE_ID_REQUIRED = "sensus.epm.devicevalidator.device.id.required";

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

		ElectricMeter elecMeter =
				(ElectricMeter)validationContext.getObjectToBeValidated(ElectricMeter.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(elecMeter))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(ELEC_METER_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_ALL_BY_PREMISE_ID:
				validatePremiseId(validationContext.getMessages(), elecMeter);
				break;
				
			case FETCH_ALL:
				validateDeviceId(validationContext.getMessages(), elecMeter);
				break;
				
			default:
				break;
		}
	}

	/**
	 * Validate premise id.
	 * 
	 * @param list the list
	 * @param elecMeter the elec meter
	 */
	private void validatePremiseId(List<MessageInfo> list, ElectricMeter elecMeter)
	{
		if (ValidationUtil.isNull(elecMeter.getConfiguration()))
		{
			list.add(MessageInfo.createFieldValidationError(ELEC_CONFIGURATION_REQUIRED));
			return;
		}
		else
		{
			ValidationUtil.isNullOrEmpty(elecMeter.getConfiguration().getPremiseId(), PREMISE_ID_REQUIRED, list);
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
				&& (device.getDeviceId().length() > getDeviceIdLength()))
		{
			list.add(MessageInfo.createFieldValidationError(DEVICE_ID_REQUIRED, getDeviceIdLength()));
		}
	}

}
