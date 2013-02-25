package com.sensus.mlc.settings.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.settings.model.Setting;

/**
 * The Class SettingValidator.
 */
public class SettingValidator implements IValidator
{
	/** The Constant SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED =
			"sensus.mlc.validator.property.required";

	/** The Constant SENSUS_MLC_SETTINGS_PROPERTY_VALUE_VALIDATOR. */
	private static final String SENSUS_MLC_SETTINGS_PROPERTY_VALUE_VALIDATOR =
			"sensus.mlc.settingsvalidator.propertyvalue.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

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
		Setting setting = (Setting)context.getObjectToBeValidated(Setting.class.getSimpleName());

		if (isNull(setting))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED,
					Setting.class.getSimpleName()));
			return;
		}

		switch (action)
		{
			case UPSERT_SETTINGS:
			case UPDATE_SYSTEM_SETTINGS:
				validateSettingPropertyEnum(list, setting);
				validateSettingPropertyValue(list, setting);
				return;
			default:
				return;
		}
	}

	/**
	 * Validate setting property enum.
	 * 
	 * @param list the list
	 * @param setting the setting
	 */
	private void validateSettingPropertyEnum(List<MessageInfo> list, Setting setting)
	{
		ValidationUtil.isNull(setting.getPropertyEnum(), SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED, list);
	}

	/**
	 * Validate setting property value.
	 * 
	 * @param list the list
	 * @param setting the setting
	 */
	private void validateSettingPropertyValue(List<MessageInfo> list, Setting setting)
	{
		ValidationUtil.isNull(setting.getPropertyValue(), SENSUS_MLC_SETTINGS_PROPERTY_VALUE_VALIDATOR, list);
	}
}
