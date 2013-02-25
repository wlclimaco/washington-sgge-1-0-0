package com.sensus.mlc.smartpoint.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class LightListValidator.
 */
public class LightListValidator extends LightValidator implements IValidator
{
	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHTLIST_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHTLIST_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightlist.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.validation.LightValidator#validate(com.sensus.common.validation.ValidationContext)
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

		@SuppressWarnings("unchecked")
		List<Light> lightList =
				(List<Light>)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.LIGHT_LIST.getValue());

		if (isNull(lightList))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHTLIST_REQUIRED));
			return;
		}

		switch (action)
		{
			case UPSERT_LIGHT_PROPERTY:
			case INITIATE_UPDATE:
			case GATEWAY_CLEAR_SCHEDULE:
			case GATEWAY_APPLY_SCHEDULE:
				validateListLightId(list, lightList);
				break;

			case CLEAR_SMP_SCHEDULE:
			case APPLY_SMP_SCHEDULE:
			case GATEWAY_SET_LIGHT_INTENSITY:
			case GATEWAY_APPLY_SMARTPOINT_PROPERTIES:
			case ADD_SMP_TO_TAG:
				validateLightListRniId(list, lightList);
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
	private void validateLightListRniId(List<MessageInfo> list, List<Light> lightList)
	{
		for (Light light : lightList)
		{
			validateLightRniId(list, light);
		}
	}

	/**
	 * Validate list light id.
	 * 
	 * @param list the list
	 * @param lightList the light list
	 */
	private void validateListLightId(List<MessageInfo> list, List<Light> lightList)
	{
		for (Light light : lightList)
		{
			validateLightId(list, light);
		}
	}
}
