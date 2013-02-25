package com.sensus.mlc.ecomode.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;

/**
 * The Class EcoModeBaselineValidator.
 */
public class EcoModeBaselineValidator implements IValidator
{
	/** The Constant ECOMODE_BASELINE_NAME. */
	private static final String ECOMODE_BASELINE_NAME = EcoModeBaseline.class.getSimpleName();

	/** The Constant SENSUS_MLC_VALIDATOR_POLEID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED =
			"sensus.mlc.ecomodevalidator.replacedtype.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED =
			"sensus.mlc.ecomodevalidator.replacedwattage.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID =
			"sensus.mlc.ecomodevalidator.replacedwattage.invalid";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)validationContext.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		List<MessageInfo> list = validationContext.getMessages();
		if (isNull(action) || !isNullOrEmpty(list))
		{
			return;
		}

		EcoModeBaseline baseline = (EcoModeBaseline)validationContext.getObjectToBeValidated(ECOMODE_BASELINE_NAME);
		switch (action)
		{
			case UPSERT_ECOMODE:

				validateEcoModeBaseline(list, baseline);
				return;

			default:
				break;
		}
	}

	/**
	 * Validate eco mode baseline.
	 *
	 * @param list the list
	 * @param baseline the baseline
	 */
	private void validateEcoModeBaseline(List<MessageInfo> list, EcoModeBaseline baseline)
	{
		if (isNull(baseline))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_POLEID_REQUIRED));
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED));
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED));
			return;
		}

		baseline.setCalculateRetroactiveEcomode(true);
		validatePoleId(list, baseline);
		validateReplacedType(list, baseline);
		validateReplacedWattage(list, baseline);
	}

	/**
	 * Validate pole id.
	 *
	 * @param list the list
	 * @param baseline the baseline
	 */
	private void validatePoleId(List<MessageInfo> list, EcoModeBaseline baseline)
	{

		// FIXME - properties
		/*if (isNull(light) || isNull(light.getParameterValue(PropertyEnum.POLE_ID)))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_POLEID_REQUIRED));
		}*/
	}

	/**
	 * Validate replaced type.
	 *
	 * @param list the list
	 * @param baseline the baseline
	 */
	private void validateReplacedType(List<MessageInfo> list, EcoModeBaseline baseline)
	{
		isNull(baseline.getReplacedType(), SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED, list);
	}

	/**
	 * Validate replaced wattage.
	 *
	 * @param list the list
	 * @param baseline the baseline
	 */
	private void validateReplacedWattage(List<MessageInfo> list, EcoModeBaseline baseline)
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
