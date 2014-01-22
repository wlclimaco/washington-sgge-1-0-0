package com.sensus.lc.settings.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.lc.settings.model.Setting;

/**
 * The Class SettingsValidator.
 */
public class SettingListValidator extends SettingValidator implements IValidator
{
	/** The Constant SENSUS_MLC_SETTINGS_SETTINGS_VALIDATOR. */
	private static final String SENSUS_MLC_SETTINGS_SETTINGS_VALIDATOR =
			"sensus.mlc.settingsvalidator.settings.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
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

		switch (action)
		{
			case UPSERT_SETTINGS:
			case UPDATE_SYSTEM_SETTINGS:
				validateProperties(context);
				return;
			default:
				return;
		}
	}

	/**
	 * Validate properties.
	 * 
	 * @param context the context
	 */
	private void validateProperties(ValidationContext context)
	{
		@SuppressWarnings("unchecked")
		List<Setting> settingList =
				(List<Setting>)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.SETTING_LIST.getValue());
		String actualPassword =
				(String)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.ACTUAL_PASSWORD.getValue());
		String newPassword = (String)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.NEW_PASSWORD.getValue());
		List<MessageInfo> list = context.getMessages();

		if (!isNull(settingList))
		{
			for (Setting setting : settingList)
			{
				context.putObjectToBeValidated(Setting.class.getSimpleName(), setting);
				super.validate(context);
			}
			return;
		}

		if (!isNullOrEmpty(actualPassword)
				|| !isNullOrEmpty(newPassword))
		{
			return;
		}

		isNullOrEmpty(settingList, SENSUS_MLC_SETTINGS_SETTINGS_VALIDATOR, list);
	}
}
