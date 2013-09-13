package com.sensus.dm.common.device.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;

/**
 * The Class ColumnFilterRequestValidator.
 * 
 * @author QAT Global.
 */
public class ColumnFilterRequestValidator implements IValidator
{
	/** The Constant SENSUS_EPM_PROPERTYVALIDATOR_PROPERTY_REQUIRED. */
	private static final String SENSUS_EPM_PROPERTYVALIDATOR_PROPERTY_REQUIRED =
			"sensus.epm.propertyvalidator.property.required";

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

		if (ValidationUtil.isNull(action))
		{
			return;
		}

		List<MessageInfo> list = validationContext.getMessages();

		ColumnFilterRequest request =
				(ColumnFilterRequest)validationContext
						.getObjectToBeValidated(ColumnFilterRequest.class.getSimpleName());

		switch (action)
		{
			case UPDATE_COLUMN_FILTERS:
				validatePropertyEnum(list, request.getPropertyEnum());
				break;
			case FETCH_ALL_COLUMN_FILTERS:
				validateProperty(list, request.getProperties());
				break;
			default:
				break;
		}
	}

	/**
	 * Validate property.
	 * 
	 * @param list the list
	 * @param properties the properties
	 */
	private void validateProperty(List<MessageInfo> list, List<Property> properties)
	{
		ValidationUtil.isNullOrEmpty(properties, SENSUS_EPM_PROPERTYVALIDATOR_PROPERTY_REQUIRED, list);
	}

	/**
	 * Validate property enum.
	 * 
	 * @param list the list
	 * @param propertyEnum the property enum
	 */
	private void validatePropertyEnum(List<MessageInfo> list, PropertyEnum propertyEnum)
	{
		ValidationUtil.isNull(propertyEnum, SENSUS_EPM_PROPERTYVALIDATOR_PROPERTY_REQUIRED, list);
	}
}
