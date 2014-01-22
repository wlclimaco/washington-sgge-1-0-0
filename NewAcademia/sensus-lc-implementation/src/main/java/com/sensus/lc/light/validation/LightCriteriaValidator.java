package com.sensus.lc.light.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.light.model.criteria.LightCriteria;

/**
 * The Class LightCriteriaValidator.
 */
public class LightCriteriaValidator implements IValidator
{

	/** The Constant NOTIFICATION_HISTORY_REQUEST_NAME. */
	private static final String LIGHT_CRITERIA_NAME = LightCriteria.class.getSimpleName();

	/** The Constant LIGHT_ID_REQUIRED. */
	private static final String LIGHT_ID_REQUIRED = "sensus.mlc.lightvalidator.light.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/* (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		if (isNull(context.getObjectsToBeValidated()))
		{
			return;
		}

		List<MessageInfo> list = context.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}
		LightCriteria lightCriteria =
				(LightCriteria)context.getObjectToBeValidated(LIGHT_CRITERIA_NAME);

		if (isNull(lightCriteria))
		{
			context.getMessages().add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED, "Light"));
			return;
		}
		validateLightCriteria(list, lightCriteria);
	}

	/**
	 * Validate light criteria.
	 *
	 * @param list the list
	 * @param lightCriteria the light criteria
	 */
	private void validateLightCriteria(List<MessageInfo> list, LightCriteria lightCriteria)
	{
		if(isNullOrEmpty(lightCriteria.getLightIdList()))
		{
			list.add(MessageInfo.createObjectError(LIGHT_ID_REQUIRED));
		}
	}
}
