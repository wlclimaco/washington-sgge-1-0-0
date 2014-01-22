package com.sensus.lc.light.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;

/**
 * The Class AlarmNotificationRequestValidator.
 */
public class AlarmNotificationRequestValidator implements IValidator
{

	/** The Constant ALARM_NOTIFICATION_REQUEST_NAME. */
	private static final String ALARM_NOTIFICATION_REQUEST_NAME = AlarmNotificationRequest.class.getSimpleName();

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.lightvalidator.light.top.level.state.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.AbstractValidator#validate(java.lang.Object, java.util.List,
	 * java.lang.Object[])
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<MessageInfo> list = validationContext.getMessages();
		if (!isNullOrEmpty(list))
		{
			return;
		}

		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)validationContext.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		AlarmNotificationRequest request =
				(AlarmNotificationRequest)validationContext.getObjectToBeValidated(ALARM_NOTIFICATION_REQUEST_NAME);

		if (isNull(action) || isNull(request))
		{
			return;
		}

		switch (action)
		{
			case GATEWAY_ALARM:
				validateLifeCycleState(request.getLifeCycleState(), list);
				break;

			default:
				break;
		}
	}

	/**
	 * Validate light status.
	 * 
	 * @param lightTopLevelState the light top level state
	 * @param list the list
	 */
	private void validateLifeCycleState(LifeCycleStateEnum lightTopLevelState, List<MessageInfo> list)
	{
		isNull(lightTopLevelState, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED, list);
	}
}
