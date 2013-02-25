package com.sensus.mlc.smartpoint.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.util.LCValidationUtil.validateStatusProperties;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.smartpoint.model.LightDetailDataTypeEnum;
import com.sensus.mlc.smartpoint.model.LightSchedule;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;

/**
 * The Class ForcedLightStatusNotificationRequestValidator.
 */
public class ForcedLightStatusNotificationRequestValidator implements IValidator
{
	/** The Constant FORCED_LIGHT_STATUS_NOTIFICATION_REQUEST_NAME. */
	private static final String FORCED_LIGHT_STATUS_NOTIFICATION_REQUEST_NAME =
			ForcedLightStatusNotificationRequest.class.getSimpleName();

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED =
			"sensus.mlc.smartpointvalidator.schedule.properties.required";

	/** The Constant SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED. */
	private static final String SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED =
			"sensus.mlc.gateway.alarm.warning.subtype.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.smartpointvalidator.light.top.level.state.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISEOFFSET_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISEOFFSET_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunrise.offset.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSETOFFSET_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSETOFFSET_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunset.offset.required";

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
		ForcedLightStatusNotificationRequest request = (ForcedLightStatusNotificationRequest)
				validationContext.getObjectToBeValidated(FORCED_LIGHT_STATUS_NOTIFICATION_REQUEST_NAME);

		List<MessageInfo> list = validationContext.getMessages();

		switch (action)
		{
			case GATEWAY_FORCED_STATUS:
				validateStatusProperties(request, list);
				validateStatusExceptionInfo(request, list);
				validateLightStatus(request, list);
				validateOffsetProperties(request, list);
				break;

			default:
				break;
		}
	}

	/**
	 * Validate status exception info.
	 * 
	 * @param request the request
	 * @param list the list
	 */
	private void validateStatusExceptionInfo(ForcedLightStatusNotificationRequest request, List<MessageInfo> list)
	{
		if (isNullOrEmpty(request.getStatusException()))
		{
			return;
		}

		// The list of Alarms can be empty, for instance in the case the light comes back from failure
		for (StatusException alarmWarningInfo : request.getStatusException())
		{
			isNull(alarmWarningInfo.getStatusExceptionTypeEnum(),
					SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED, list);
		}
	}

	/**
	 * Validate light status.
	 * 
	 * @param request the request
	 * @param list the list
	 */
	private void validateLightStatus(ForcedLightStatusNotificationRequest request, List<MessageInfo> list)
	{
		isNull(request.getLightStatusEnum(), SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED, list);
	}

	/**
	 * Validate offset properties.
	 * 
	 * @param request the request
	 * @param list the list
	 */
	private void validateOffsetProperties(ForcedLightStatusNotificationRequest request, List<MessageInfo> list)
	{
		if (!request.getLightDetailDataType().contains(LightDetailDataTypeEnum.CONFIGURATION))
		{
			return;
		}

		Integer listSize = list.size();
		LightSchedule schedule = request.getLight().getLightSchedule();
		isNull(schedule, SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED, list);

		if (list.size() > listSize)
		{
			return;
		}

		isNull(schedule.getSunriseOffset(), SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISEOFFSET_REQUIRED, list);
		isNull(schedule.getSunsetOffset(), SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSETOFFSET_REQUIRED, list);
	}

}
