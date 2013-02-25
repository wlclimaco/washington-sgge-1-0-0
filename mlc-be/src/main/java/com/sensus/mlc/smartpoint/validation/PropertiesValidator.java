package com.sensus.mlc.smartpoint.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;

/**
 * The Class PropertiesValidator.
 */
public class PropertiesValidator implements IValidator
{
	/** The Constant SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED =
			"sensus.mlc.validator.property.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@SuppressWarnings("unchecked")
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
		List<PropertyEnum> properties =
				(List<PropertyEnum>)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.PROPERTIES.getValue());

		switch (action)
		{
			case UPSERT_LIGHT_PROPERTY:
			case FETCH_BY_PROPERTY_ID:
				isNullOrEmpty(properties, SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED, list);
				return;
			default:
		}
	}
}
