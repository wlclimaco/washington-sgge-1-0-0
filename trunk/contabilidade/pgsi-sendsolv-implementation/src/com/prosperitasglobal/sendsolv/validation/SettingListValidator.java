package com.prosperitasglobal.sendsolv.validation;

import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;

/**
 * The Class SettingsValidator.
 */
public class SettingListValidator implements IValidator
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
		// MLCPersistanceActionEnum action =
		// (MLCPersistanceActionEnum)context.getValidationArguments().get(
		// MLCPersistanceActionEnum.getSlcActionName());
		//
		// if (isNull(action))
		// {
		// return;
		// }
		//
		// switch (action)
		// {
		// case UPSERT_SETTINGS:
		// case UPDATE_SYSTEM_SETTINGS:
		// validateProperties(context);
		// return;
		// default:
		// return;
		// }

	}

	/**
	 * Validate properties.
	 *
	 * @param context the context
	 */
	private void validateProperties(ValidationContext context)
	{
		// @SuppressWarnings("unchecked")
		// List<Setting> settingList =
		// (List<Setting>)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.SETTING_LIST.getValue());
		// String actualPassword =
		// (String)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.ACTUAL_PASSWORD.getValue());
		// String newPassword =
		// (String)context.getObjectToBeValidated(ObjectToBeValidatedKeyEnum.NEW_PASSWORD.getValue());
		// List<MessageInfo> list = context.getMessages();
		//
		// if (!isNull(settingList))
		// {
		// for (Setting setting : settingList)
		// {
		// context.putObjectToBeValidated(Setting.class.getSimpleName(), setting);
		// super.validate(context);
		// }
		// return;
		// }
		//
		// if (!isNullOrEmpty(actualPassword)
		// || !isNullOrEmpty(newPassword))
		// {
		// return;
		// }
		//
		// isNullOrEmpty(settingList, SENSUS_MLC_SETTINGS_SETTINGS_VALIDATOR, list);
	}
}
