package com.sensus.lc.light.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.lc.light.model.Light;

/**
 * The Class LightListValidator.
 */
public class LightListValidator extends LightValidator implements IValidator
{

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHTLIST_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHTLIST_REQUIRED =
			"sensus.mlc.lightvalidator.lightlist.required";


	/* (non-Javadoc)
	 * @see com.sensus.mlc.light.validation.LightValidator#validate(com.sensus.common.validation.ValidationContext)
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

		List<?> lightList =
				(List<?>)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.LIGHT_LIST.getValue());

		if (isNullOrEmpty(lightList))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_LIGHTLIST_REQUIRED));
			return;
		}

		switch (action)
		{
			case INITIATE_UPDATE:
			case GATEWAY_CLEAR_SCHEDULE:
			case GATEWAY_APPLY_SCHEDULE:
			case APPLY_LIGHT_SCHEDULE:
			case CLEAR_LIGHT_SCHEDULE:
				validateLightListId(list, lightList);
				break;

			case GATEWAY_SET_LIGHT_INTENSITY:
			case GATEWAY_APPLY_LIGHT_PROPERTIES:
			case ADD_LIGHT_TO_TAG:
				validateLightListRniId(list, lightList);
				break;

			case UPSERT_ECOMODE:
				validateLightListId(list, lightList);
				validateLightListEcoModeBaseline(list, lightList);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate light list rni id.
	 *
	 * @param list the list
	 * @param lightList the light list
	 */
	private void validateLightListRniId(List<MessageInfo> list, List<?> lightList)
	{
		for (Object light : lightList)
		{
			if (Light.class.isAssignableFrom(light.getClass()))
			{
				validateLightFlexnetId(list, (Light)light);
			}
		}
	}

	/**
	 * Validate light list id.
	 *
	 * @param list the list
	 * @param lightList the light list
	 */
	private void validateLightListId(List<MessageInfo> list, List<?> lightList)
	{
		for (Object light : lightList)
		{
			if (Light.class.isAssignableFrom(light.getClass()))
			{
				validateLightId(list, (Light)light);
				continue;
			}
			validateLightId(list, (Integer)light);
		}
	}

	/**
	 * Validate light list eco mode baseline.
	 *
	 * @param list the list
	 * @param lightList the light list
	 */
	private void validateLightListEcoModeBaseline(List<MessageInfo> list, List<?> lightList)
	{
		for (Object light : lightList)
		{
			if (Light.class.isAssignableFrom(light.getClass()))
			{
				validateEcoModeBaseline(list, (Light)light);
			}
		}
	}
}
