package com.sensus.lc.light.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.lc.base.validation.util.LCValidationUtil.validateLightScheduleTime;
import static com.sensus.lc.base.validation.util.LCValidationUtil.validatePoleId;
import static com.sensus.lc.base.validation.util.LightConfigurationValidationUtil.validateFirmwareVersion;
import static com.sensus.lc.base.validation.util.LightLastOpDateValidationUtil.validateConsumption;
import static com.sensus.lc.base.validation.util.LightLastOpDateValidationUtil.validateCurrent;
import static com.sensus.lc.base.validation.util.LightLastOpDateValidationUtil.validateVoltage;
import static com.sensus.lc.base.validation.util.LightLocationValidationUtil.validateLatitude;
import static com.sensus.lc.base.validation.util.LightLocationValidationUtil.validateLongitude;
import static com.sensus.lc.base.validation.util.LightLocationValidationUtil.validateTimeZoneInfo;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightDetailDataTypeEnum;
import com.sensus.lc.light.model.LightSchedule;
import com.sensus.lc.light.model.OperationalData;
import com.sensus.lc.light.model.OperationalDataTypeEnum;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;

/**
 * The Class LightStatusNotificationRequestValidator.
 */
public class LightStatusNotificationRequestValidator implements IValidator
{
	/** Messages. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED =
			"sensus.mlc.lightvalidator.properties.required";

	private static final String SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED =
			"sensus.mlc.lightvalidator.schedule.properties.required";

	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.lightvalidator.light.top.level.state.required";

	private static final String SENSUS_MLC_LIGHTVALIDATOR_SUNRISEOFFSET_REQUIRED =
			"sensus.mlc.lightvalidator.sunrise.offset.required";

	private static final String SENSUS_MLC_LIGHTVALIDATOR_SUNSETOFFSET_REQUIRED =
			"sensus.mlc.lightvalidator.sunset.offset.required";

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

		LightStatusNotificationRequest request = (LightStatusNotificationRequest)validationContext
				.getObjectToBeValidated(LightStatusNotificationRequest.class.getSimpleName());

		if (isNull(action) || isNull(request))
		{
			return;
		}

		switch (action)
		{
			case GATEWAY_UNSOLICITED_STATUS:
				validateOperationalData(list, request.getOperationalData());
				break;

			case GATEWAY_BINDING:
				validateBindingProperties(list, request.getLight());
				break;
			case GATEWAY_FORCED_STATUS:
				validateLifeCycleState(request.getLifeCycleState(), list);
				validateSchedules(request, list);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate operational data.
	 *
	 * @param list the list
	 * @param operationalData the operational data
	 */
	private void validateOperationalData(List<MessageInfo> list, List<OperationalData> operationalData)
	{
		if (isNullOrEmpty(operationalData))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED));
			return;
		}

		for (OperationalData opData : operationalData)
		{
			if (OperationalDataTypeEnum.CUMULATIVECONSUMPTION.equals(
					opData.getOperationalDataType()))
			{
				validateConsumption(opData.getValue(), list);
				continue;
			}

			if (OperationalDataTypeEnum.CURRENT.equals(opData.getOperationalDataType()))
			{
				validateCurrent(opData.getValue(), list);
				continue;
			}

			if (OperationalDataTypeEnum.VOLTAGE.equals(opData.getOperationalDataType()))
			{
				validateVoltage(opData.getValue(), list);
			}
		}
	}

	/**
	 * Validate binding properties.
	 *
	 * @param list the list
	 * @param parameters the parameters
	 */
	private void validateBindingProperties(List<MessageInfo> list, Light light)
	{
		// if properties list is not Null
		if (isNull(light)
				|| isNull(light.getRadio())
				|| isNull(light.getRadio().getLocation())
				|| isNull(light.getConfiguration()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED));
			return;
		}

		validateLatitude(light.getRadio().getLocation().getLatitude(), list);
		validateLongitude(light.getRadio().getLocation().getLongitude(), list);
		validateTimeZoneInfo(light.getRadio().getLocation().getTimeZoneInfo(), list);
		validateFirmwareVersion(light.getConfiguration().getFirmwareVersion(), list);
		validatePoleId(light.getPoleId(), list);
	}

	/**
	 * Validate life cycle state.
	 *
	 * @param lightTopLevelState the light top level state
	 * @param list the list
	 */
	private void validateLifeCycleState(LifeCycleStateEnum lightTopLevelState, List<MessageInfo> list)
	{
		isNull(lightTopLevelState, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED, list);
	}

	/**
	 * Validate offset properties.
	 *
	 * @param request the request
	 * @param list the list
	 */
	private void validateSchedules(LightStatusNotificationRequest request, List<MessageInfo> list)
	{
		if (!request.getLightDetailDataType().contains(LightDetailDataTypeEnum.CONFIGURATION))
		{
			return;
		}

		Integer listSize = list.size();
		LightSchedule schedule = request.getLight().getLightSchedule();
		isNull(schedule, SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED, list);

		if (list.size() > listSize)
		{
			return;
		}

		isNull(schedule.getSunriseOffset(), SENSUS_MLC_LIGHTVALIDATOR_SUNRISEOFFSET_REQUIRED, list);
		isNull(schedule.getSunsetOffset(), SENSUS_MLC_LIGHTVALIDATOR_SUNSETOFFSET_REQUIRED, list);
		validateLightScheduleTime(request, list);
	}
}
