package com.sensus.mlc.smartpoint.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.util.LCValidationUtil.getParameterByProperty;
import static com.sensus.mlc.base.util.LCValidationUtil.validateConsumption;
import static com.sensus.mlc.base.util.LCValidationUtil.validateCurrent;
import static com.sensus.mlc.base.util.LCValidationUtil.validateLatitude;
import static com.sensus.mlc.base.util.LCValidationUtil.validateLongitude;
import static com.sensus.mlc.base.util.LCValidationUtil.validateStatusProperties;
import static com.sensus.mlc.base.util.LCValidationUtil.validateTimeZoneRegion;
import static com.sensus.mlc.base.util.LCValidationUtil.validateVoltage;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.Parameter;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.OperationalDataTypeEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;

/**
 * The Class LightStatusNotificationRequestValidator.
 */
public class LightStatusNotificationRequestValidator implements IValidator
{
	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED =
			"sensus.mlc.smartpointvalidator.properties.required";

	/** The Constant SENSUS_MLC_VALIDATOR_POLEID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED =
			"sensus.mlc.smartpointvalidator.firmware.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)validationContext.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			return;
		}

		// Cast the object so it's easy to work with.
		LightStatusNotificationRequest lightStatusNotificationRequest =
				(LightStatusNotificationRequest)validationContext
						.getObjectToBeValidated(LightStatusNotificationRequest.class.getSimpleName());

		List<MessageInfo> list = validationContext.getMessages();

		switch (action)
		{
			case GATEWAY_UNSOLICITED_STATUS:
				validateStatusProperties(lightStatusNotificationRequest, list);
				validateOperationalData(list, lightStatusNotificationRequest.getOperationalData());
				break;

			case GATEWAY_BINDING:
				validateBindingProperties(list, lightStatusNotificationRequest.getLightParameters());
				break;

			default:
				break;
		}
	}

	/**
	 * Validate operational data.
	 * 
	 * @param list the list
	 * @param operationalData the operational data
	 */
	private void validateOperationalData(List<MessageInfo> list, List<OperationalData> operationalData)
	{
		if (isNull(operationalData))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED));
			return;
		}

		for (OperationalData opData : operationalData)
		{
			if (OperationalDataTypeEnum.CUMULATIVECONSUMPTION.equals(
					opData.getOperationalDataType()))
			{
				validateConsumption(opData.getValue(), list);
			}

			if (OperationalDataTypeEnum.CURRENT.equals(opData.getOperationalDataType()))
			{
				validateCurrent(opData.getValue(), list);
			}

			if (OperationalDataTypeEnum.VOLTAGE.equals(opData.getOperationalDataType()))
			{
				validateVoltage(opData.getValue(), list);
			}
		}
	}

	/**
	 * Validate binding properties.
	 * 
	 * @param list the list
	 * @param parameters the parameters
	 */
	private void validateBindingProperties(List<MessageInfo> list, List<LightParameter> parameters)
	{
		// if properties list is not Null
		if (isNullOrEmpty(parameters))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED));
			return;
		}

		validateLatitude(getParameterByProperty(parameters, PropertyEnum.LATITUDE), list);
		validateLongitude(getParameterByProperty(parameters, PropertyEnum.LONGITUDE), list);
		validateTimeZoneRegion(getParameterByProperty(parameters, PropertyEnum.TIME_ZONE), list);
		validateParam(
				getParameterByProperty(parameters, PropertyEnum.POLE_ID),
				list,
				SENSUS_MLC_VALIDATOR_POLEID_REQUIRED);
		validateParam(
				getParameterByProperty(parameters, PropertyEnum.FIRMWARE_VERSION),
				list,
				SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED);
	}

	/**
	 * Validate param.
	 * 
	 * @param param the param
	 * @param list the list
	 * @param message the message
	 */
	private void validateParam(Parameter param, List<MessageInfo> list, String message)
	{
		if (isNull(param) || isNullOrEmpty(param.getValue()))
		{
			list.add(MessageInfo.createFieldValidationError(message));
		}
	}
}
