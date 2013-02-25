package com.sensus.mlc.smartpoint.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.StatusMessage;

/**
 * The Class LightValidator.
 */
public class LightValidator implements IValidator
{

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightrniid.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED =
			"sensus.mlc.smartpointvalidator.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED =
			"sensus.mlc.validator.property.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_PROTECT_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_PROTECT_REQUIRED =
			"sensus.mlc.smartpointvalidator.protect.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_ID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_ID_REQUIRED =
			"sensus.mlc.smartpointvalidator.statusmessage.id.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_REQUIRED =
			"sensus.mlc.smartpointvalidator.statusmessage.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED =
			"sensus.mlc.smartpointvalidator.light.state.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			return;
		}

		List<MessageInfo> list = context.getMessages();

		Light light = (Light)context.getObjectToBeValidated(Light.class.getSimpleName());

		Integer lightId = (Integer)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.LIGHT_ID.getValue());

		@SuppressWarnings("unchecked")
		List<LightParameter> lightParameters = (List<LightParameter>)context.getObjectToBeValidated(
				ObjectToBeValidatedKeyEnum.LIGHT_PARAMETERS.getValue());

		Boolean lightProtected = (Boolean)context.getObjectToBeValidated(
				ObjectToBeValidatedKeyEnum.LIGHT_PROTECTED.getValue());

		StatusMessage lightStatusMessage = (StatusMessage)context.getObjectToBeValidated(
				StatusMessage.class.getSimpleName());

		switch (action)
		{
			case FETCH_BY_RNI_ID:
			case GATEWAY_SETUP:
			case GATEWAY_CHANNEL_SETUP_AUDIT:
			case GATEWAY_UNSOLICITED_STATUS:
			case GATEWAY_BINDING:
			case ADD_SMP_TO_TAG:
				validateLightRniId(list, light);
				return;

			case FETCH_LIGTHING_CONFIGURATIONS_BY_PART_NUMBER:
			case FETCH_STATUS_MESSAGE_BY_LIGHT:
			case FETCH_GROUPS_BY_SMARTPOINT:
			case FETCH_GROUPS_BY_LIGHT:
			case FETCH_BY_LIGHT:
				validateLightId(list, light);
				return;

			case FETCH_AVERAGE_LIGHT_CONSUMPTION:
			case FETCH_AVERAGE_LIGHT_VOLTAGE:
			case FETCH_LIGHT_CONSUMPTION:
				validateLightId(list, lightId);
				return;

			case GATEWAY_UPSERT_LIGHT_PROPERTY:
				validateLightRniId(list, light);
				validateLightParameters(list, lightParameters);
				return;

			case UPDATE_LIGHT_PROTECTED:
				validateLightProtected(list, lightProtected);
				return;

			case UPDATE_LIGHT_LAT_LNG:
				validateLightLatLng(list, light);
				return;

			case FETCH_STATUS_MESSAGE_BY_ID:
				validateLightStatusMessage(list, lightStatusMessage);
				return;

			case GATEWAY_ALARM:
			case GATEWAY_FORCED_STATUS:
				validateLightRniId(list, light);
				validateLightState(list, light);
				return;

			case FETCH_BY_SMARTPOINT:
				validateLight(list, light);
				return;

			default:
				return;
		}
	}

	/**
	 * Validate light lat lng.
	 * 
	 * @param list the list
	 * @param light the light
	 */
	private void validateLightLatLng(List<MessageInfo> list, Light light)
	{
		// FIXME - properties
		/*
		 * if (validateLight(list, light))
		 * {
		 * validateLightId(list, light.getId());
		 * validateLightParameters(list, light.getParameters());
		 * return;
		 * }
		 */
	}

	/**
	 * Validate light status message.
	 * 
	 * @param list the list
	 * @param lightStatusMessage the light status message
	 */
	private void validateLightStatusMessage(List<MessageInfo> list, StatusMessage lightStatusMessage)
	{
		if (!isNull(lightStatusMessage))
		{
			isNullOrZero(lightStatusMessage.getId(), SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_ID_REQUIRED,
					list);
			return;
		}
		list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_REQUIRED));
	}

	/**
	 * Validate light rni id.
	 * 
	 * @param list the list
	 * @param light the light
	 */
	public void validateLightRniId(List<MessageInfo> list, Light light)
	{
		if (validateLight(list, light))
		{
			checkAndValidateLightRniId(list, light.getRniId());
		}
	}

	public boolean checkAndValidateLightRniId(List<MessageInfo> list, Integer rniId)
	{
		if (isNullOrZero(rniId))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED));
			return false;
		}
		return true;
	}

	/**
	 * Validate light id.
	 * 
	 * @param list the list
	 * @param light the light
	 */
	public void validateLightId(List<MessageInfo> list, Light light)
	{
		if (validateLight(list, light))
		{
			validateLightId(list, light.getId());
		}
	}

	/**
	 * Validate light id.
	 * 
	 * @param list the list
	 * @param lightId the light id
	 */
	private void validateLightId(List<MessageInfo> list, Integer lightId)
	{
		isNullOrZero(lightId, SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED, list);
	}

	/**
	 * Validate properties.
	 * 
	 * @param list the list
	 * @param lightParameters the light parameters
	 */
	private void validateLightParameters(List<MessageInfo> list, List<LightParameter> lightParameters)
	{
		isNullOrEmpty(lightParameters, SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED, list);
	}

	/**
	 * Validate light protected.
	 * 
	 * @param list the list
	 * @param lightProtected the light protected
	 */
	private void validateLightProtected(List<MessageInfo> list, Boolean lightProtected)
	{
		isNull(lightProtected, SENSUS_MLC_SMARTPOINTVALIDATOR_PROTECT_REQUIRED, list);
	}

	/**
	 * Validate light state.
	 * 
	 * @param list the list
	 * @param light the light
	 */
	private void validateLightState(List<MessageInfo> list, Light light)
	{
		isNull(light.getLightStateEnum(), SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED, list);
	}

	/**
	 * Validate light.
	 * 
	 * @param list the list
	 * @param light the light
	 * @return the boolean
	 */
	private Boolean validateLight(List<MessageInfo> list, Light light)
	{
		if (isNull(light))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED,
					Light.class.getSimpleName()));
			return false;
		}
		return true;
	}
}
