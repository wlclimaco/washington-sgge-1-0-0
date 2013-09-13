package com.sensus.dm.common.property.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.dm.common.property.model.Property;

/**
 * The Class PropertyValidator.
 * 
 * @author QAT Brazil.
 */
public class PropertyValidator implements IValidator
{

	/** The Constant NAME_INVALID. */
	private static final String NAME_INVALID = "sensus.dm.elec.settingsvalidator.name.invalid";

	/** The Constant PROPERTY_VALIDATOR. */
	private static final String PROPERTY_VALIDATOR = "sensus.dm.elec.settingsvalidator.property.required";

	/** The Constant PROVIDER_INVALID. */
	private static final String PROVIDER_INVALID = "sensus.dm.elec.settingsvalidator.provider.invalid";

	/** The Constant SETTINGS_VALIDATOR. */
	private static final String SETTINGS_VALIDATOR = "sensus.dm.elec.settingsvalidator.settings.required";

	/** The Constant VALUE_INVALID. */
	private static final String VALUE_INVALID = "sensus.dm.elec.settingsvalidator.value.invalid";

	/** The Constant VALUE_VALIDATOR. */
	private static final String VALUE_VALIDATOR = "sensus.dm.elec.settingsvalidator.propertyvalue.required";

	/** The name length. */
	private int nameLength;

	/** The provider length. */
	private int providerLength;

	/** The value length. */
	private int valueLength;

	/**
	 * Gets the name length.
	 * 
	 * @return the name length
	 */
	public int getNameLength()
	{
		return nameLength;
	}

	/**
	 * Sets the name length.
	 * 
	 * @param nameLength the new name length
	 */
	public void setNameLength(int nameLength)
	{
		this.nameLength = nameLength;
	}

	/**
	 * Gets the provider length.
	 * 
	 * @return the provider length
	 */
	public int getProviderLength()
	{
		return providerLength;
	}

	/**
	 * Sets the provider length.
	 * 
	 * @param providerLength the new provider length
	 */
	public void setProviderLength(int providerLength)
	{
		this.providerLength = providerLength;
	}

	/**
	 * Gets the value length.
	 * 
	 * @return the value length
	 */
	public int getValueLength()
	{
		return valueLength;
	}

	/**
	 * Sets the value length.
	 * 
	 * @param valueLength the new value length
	 */
	public void setValueLength(int valueLength)
	{
		this.valueLength = valueLength;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<Property> propertyList =
				(List<Property>)validationContext.getObjectToBeValidated(Property.class.getSimpleName());

		String propertyProviderType =
				(String)validationContext.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.PROPERTY_PROVIDER_TYPE
						.getValue());

		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNullOrEmpty(propertyList))
		{
			ValidationUtil.isNullOrEmpty(propertyList, SETTINGS_VALIDATOR, validationContext.getMessages());
			return;
		}

		switch (action)
		{
			case UPSERT_SETTINGS:
				validateProviderType(validationContext.getMessages(), propertyProviderType);
				validateProperties(validationContext.getMessages(), propertyList);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate properties.
	 * 
	 * @param list the list
	 * @param propertyList the property list
	 */
	private void validateProperties(List<MessageInfo> list, List<Property> propertyList)
	{
		for (Property property : propertyList)
		{
			ValidationUtil.isNullOrEmpty(property.getPropertyName(), PROPERTY_VALIDATOR, list);

			if (!ValidationUtil.isNull(property.getPropertyName())
					&& (property.getPropertyName().length() > getNameLength()))
			{
				list.add(MessageInfo.createFieldValidationError(NAME_INVALID, getNameLength()));
			}

			ValidationUtil.isNullOrEmpty(property.getPropertyValue(), VALUE_VALIDATOR, list);

			if (!ValidationUtil.isNull(property.getPropertyValue())
					&& (property.getPropertyValue().length() > getValueLength()))
			{
				list.add(MessageInfo.createFieldValidationError(VALUE_INVALID, getValueLength()));
			}

			if (!ValidationUtil.isNull(property.getProviderType())
					&& !ValidationUtil.isNull(property.getProviderId())
					&& ((property.getProviderType().length() + property.getProviderId().length()) > getProviderLength()))
			{
				list.add(MessageInfo.createFieldValidationError(PROVIDER_INVALID, getProviderLength()));
			}
		}
	}

	/**
	 * Validate provider type.
	 * 
	 * @param list the list
	 * @param providerType the provider type
	 */
	private void validateProviderType(List<MessageInfo> list, String providerType)
	{
		if (!ValidationUtil.isNull(providerType) && (providerType.length() > getProviderLength()))
		{
			list.add(MessageInfo.createFieldValidationError(PROVIDER_INVALID, getProviderLength()));
		}
	}

}
