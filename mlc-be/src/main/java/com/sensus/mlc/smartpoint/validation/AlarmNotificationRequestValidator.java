package com.sensus.mlc.smartpoint.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;

/**
 * The Class AlarmNotificationRequestValidator.
 */
public class AlarmNotificationRequestValidator implements IValidator
{

	/** The Constant ALARM_NOTIFICATION_REQUEST_NAME. */
	private static final String ALARM_NOTIFICATION_REQUEST_NAME = AlarmNotificationRequest.class.getSimpleName();

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.smartpointvalidator.light.top.level.state.required";

	/** The Constant SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED. */
	private static final String SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED =
			"sensus.mlc.gateway.alarm.warning.subtype.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)validationContext.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			return;
		}

		// Cast the object so it's easy to work with.
		AlarmNotificationRequest alarmNotificationRequest =
				(AlarmNotificationRequest)validationContext.getObjectToBeValidated(ALARM_NOTIFICATION_REQUEST_NAME);

		List<MessageInfo> list = validationContext.getMessages();

		switch (action)
		{
			case GATEWAY_ALARM:
				validateStatusExceptionInfo(list, alarmNotificationRequest.getStatusException());
				validateLightStatus(alarmNotificationRequest.getLightStatusEnum(), list);
				break;

			default:
				break;
		}
	}

	/**
	 * Validate status exception info.
	 * 
	 * @param list the list
	 * @param alarmWarningMessages the alarm warning messages
	 */
	private void validateStatusExceptionInfo(List<MessageInfo> list, List<StatusException> alarmWarningMessages)
	{
		// The list of Alarms can be empty, for instance in the case the light comes back from failure
		for (StatusException alarmWarningInfo : alarmWarningMessages)
		{
			isNull(alarmWarningInfo.getStatusExceptionTypeEnum(),
					SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED, list);
		}
	}

	/**
	 * Validate light status.
	 * 
	 * @param lightTopLevelState the light top level state
	 * @param list the list
	 */
	private void validateLightStatus(LightStatusEnum lightTopLevelState, List<MessageInfo> list)
	{
		isNull(lightTopLevelState, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED, list);
	}
}
