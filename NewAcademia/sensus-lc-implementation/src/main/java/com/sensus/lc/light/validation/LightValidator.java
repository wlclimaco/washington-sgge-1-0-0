package com.sensus.lc.light.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;
import static com.sensus.lc.base.validation.util.LCValidationUtil.validateLifeCycleState;
import static com.sensus.lc.base.validation.util.LightLocationValidationUtil.validateInvalidLatitude;
import static com.sensus.lc.base.validation.util.LightLocationValidationUtil.validateInvalidLongitude;

import java.util.List;

import com.sensus.cbof.model.Radio;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.light.model.Light;

/**
 * Used to validate the LightRequest class.
 */
public class LightValidator implements IValidator
{
	/** Messages. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED =
			"sensus.mlc.ecomodevalidator.replacedtype.required";
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED =
			"sensus.mlc.ecomodevalidator.replacedwattage.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.lightvalidator.lightrniid.required";
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID =
			"sensus.mlc.ecomodevalidator.replacedwattage.invalid";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_ID_REQUIRED =
			"sensus.mlc.lightvalidator.id.required";

	/** Name classes. */
	private static final String LIGHT_CLASS_NAME = Light.class.getSimpleName();
	private static final String RADIO_CLASS_NAME = Radio.class.getSimpleName();

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		List<MessageInfo> list = context.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}

		// Check if light is not null and action
		Light light = (Light)context.getObjectToBeValidated(LIGHT_CLASS_NAME);

		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (!validateLight(list, light) || isNull(action))
		{
			return;
		}

		switch (action)
		{
			case GATEWAY_UNSOLICITED_STATUS:
			case GATEWAY_BINDING:
			case GATEWAY_ALARM:
			case GATEWAY_CHANNEL_SETUP_AUDIT:
			case GATEWAY_CLEAR_ALARMS:
			case GATEWAY_SETUP:
				validateLightFlexnetId(list, light);
				break;
			case UPDATE_LIGHT_POLE_ID:
				validateLightId(list, light);
				validatePoleId(list, light);
				break;
			case UPDATE_LIGHT_LAT_LNG:
				validateLightId(list, light);
				break;
			case UPDATE_LIGHT_STATUS:
				validateLightId(list, light);
				validateLifeCycleState(light.getLifeCycleState(), list);
				break;
			default:
				validateLatLng(list, light);
				break;
		}
	}

	/**
	 * Validate light.
	 *
	 * @param list the list
	 * @param light the light
	 * @return the boolean
	 */
	protected Boolean validateLight(List<MessageInfo> list, Light light)
	{
		if (isNull(light))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED, LIGHT_CLASS_NAME));
			return false;
		}
		return true;
	}

	/**
	 * Validate light flexnet id.
	 *
	 * @param list the list
	 * @param light the light
	 */
	protected void validateLightFlexnetId(List<MessageInfo> list, Light light)
	{
		if (!validateLight(list, light))
		{
			return;
		}

		Radio radio = light.getRadio();
		if (isNull(radio))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED, RADIO_CLASS_NAME));
			return;
		}

		if (isNull(radio.getFlexNetId()) || isNullOrZero(radio.getFlexNetId().intValue()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED));
		}
	}

	/**
	 * Validate lat lng.
	 *
	 * @param list the list
	 * @param light the light
	 */
	protected void validateLatLng(List<MessageInfo> list, Light light)
	{
		if (isNull(light.getRadio()) || isNull(light.getRadio().getLocation()))
		{
			return;
		}

		// When send Location object, validate lat and lng
		validateInvalidLatitude(light.getRadio().getLocation().getLatitude(), list);
		validateInvalidLongitude(light.getRadio().getLocation().getLongitude(), list);
	}

	/**
	 * Validate light id.
	 *
	 * @param list the list
	 * @param light the light
	 */
	protected void validateLightId(List<MessageInfo> list, Light light)
	{
		if (validateLight(list, light))
		{
			isNullOrZero(light.getId(), SENSUS_MLC_LIGHTVALIDATOR_ID_REQUIRED, list);
		}
	}

	/**
	 * Validate light id.
	 *
	 * @param list the list
	 * @param light the light
	 */
	protected void validateLightId(List<MessageInfo> list, Integer lightId)
	{
		isNullOrZero(lightId, SENSUS_MLC_LIGHTVALIDATOR_ID_REQUIRED, list);
	}

	/**
	 * Validate eco mode baseline.
	 *
	 * @param list the list
	 * @param light the light
	 */
	protected void validateEcoModeBaseline(List<MessageInfo> list, Light light)
	{
		validatePoleId(list, light);
		if (!isNullOrEmpty(list))
		{
			return;
		}

		EcoModeBaseline baseline = light.getEcoModeBaseline();
		if (isNull(baseline))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED));
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED));
			return;
		}

		baseline.setCalculateRetroactiveEcomode(true);
		validateReplacedType(list, baseline);
		validateReplacedWattage(list, baseline);
	}

	/**
	 * Validate pole id.
	 *
	 * @param list the list
	 * @param baseline the baseline
	 */
	protected void validatePoleId(List<MessageInfo> list, Light light)
	{
		if (validateLight(list, light) && isNull(light.getPoleId()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_POLEID_REQUIRED));
		}
	}

	/**
	 * Validate replaced type.
	 *
	 * @param list the list
	 * @param baseline the baseline
	 */
	protected void validateReplacedType(List<MessageInfo> list, EcoModeBaseline baseline)
	{
		isNull(baseline.getReplacedType(), SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED, list);
	}

	/**
	 * Validate replaced wattage.
	 *
	 * @param list the list
	 * @param baseline the baseline
	 */
	protected void validateReplacedWattage(List<MessageInfo> list, EcoModeBaseline baseline)
	{
		Double wattage = baseline.getReplacedWattage();
		if (isNull(wattage))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED));
			return;
		}

		if (wattage < 0)
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID));
		}
	}
}
