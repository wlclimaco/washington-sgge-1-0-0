package com.sensus.lc.base.validation.util;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.Date;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.util.SensusConvertUtil;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightParameter;
import com.sensus.lc.light.model.LightSchedule;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;

/**
 * The Class LCValidationUtil.
 */
public final class LCValidationUtil
{
	/** The Constant SUNRISE_SUNSET_FORMAT. */
	private static final String SUNRISE_SUNSET_FORMAT = "HH:mm";

	/** The Constant MAX_HOUR. */
	private static final Integer MAX_HOUR = 24;

	/** The Constant MIN_HOUR. */
	private static final Integer MIN_HOUR = 0;

	/** The Constant MAX_MIN. */
	private static final Integer MAX_MIN = 59;

	/** The Constant MIN_MIN. */
	private static final Integer MIN_MIN = 0;

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR =
			"sensus.mlc.lightvalidator.invalid.hour";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE =
			"sensus.mlc.lightvalidator.invalid.minute";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED =
			"sensus.mlc.lightvalidator.hour.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_HOUR_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED =
			"sensus.mlc.lightvalidator.minute.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_SUNRISE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SUNRISE_REQUIRED =
			"sensus.mlc.lightvalidator.sunrise.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_SUNSET_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SUNSET_REQUIRED =
			"sensus.mlc.lightvalidator.sunset.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED =
			"sensus.mlc.lightvalidator.schedule.properties.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_TIME. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_TIME =
			"sensus.mlc.lightvalidator.invalid.time";

	/** The Constant SENSUS_MLC_VALIDATOR_POLEID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.lightvalidator.light.top.level.state.required";

	/**
	 * Instantiates a new lC validation util.
	 */
	private LCValidationUtil()
	{
	}

	/**
	 * Validate hour.
	 *
	 * @param hour the hour
	 * @param parmName the parm name
	 * @param list the list
	 */
	public static void validateHour(Integer hour, String parmName, List<MessageInfo> list)
	{
		if (isNull(hour))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED, new Object[] {hour,
							parmName}));
			return;
		}

		if ((hour > MAX_HOUR) || (hour < MIN_HOUR))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR, new Object[] {hour,
							parmName}));
		}
	}

	/**
	 * Validate minute.
	 *
	 * @param minute the minute
	 * @param parmName the parm name
	 * @param list the list
	 */
	public static void validateMinute(Integer minute, String parmName, List<MessageInfo> list)
	{
		if (isNull(minute))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED, new Object[] {minute,
							parmName}));
			return;
		}

		if ((minute > MAX_MIN) || (minute < MIN_MIN))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE, new Object[] {minute,
							parmName}));
		}
	}

	/**
	 * Validate status properties.
	 *
	 * @param request the request
	 * @param list the list
	 */
	public static void validateLightScheduleTime(AlarmNotificationRequest request, List<MessageInfo> list)
	{
		Light light = request.getLight();
		if (isNull(light) || isNull(light.getLightSchedule()))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED));
			return;
		}

		LightSchedule schedule = light.getLightSchedule();
		validateTime(schedule.getSunriseTime(), SENSUS_MLC_LIGHTVALIDATOR_SUNRISE_REQUIRED, "Sunrise Time", list);
		validateTime(schedule.getSunsetTime(), SENSUS_MLC_LIGHTVALIDATOR_SUNSET_REQUIRED, "Sunset Time", list);
	}

	/**
	 * Gets the parameter by property.
	 *
	 * @param parameters the parameters
	 * @param property the property
	 * @return the parameter by property
	 */
	public static LightParameter getParameterByProperty(List<LightParameter> parameters, PropertyEnum property)
	{
		for (LightParameter param : parameters)
		{
			if (property.equals(param.getPropertyEnum()))
			{
				return param;
			}
		}

		return null;
	}

	/**
	 * Validate time.
	 *
	 * @param value the value
	 * @param messageRequired the message required
	 * @param messageField the message field
	 * @param list the list
	 */
	public static void validateTime(String value, String messageRequired, String messageField,
			List<MessageInfo> list)
	{
		if (isNullOrEmpty(value))
		{
			list.add(MessageInfo.createFieldValidationError(messageRequired));
			return;
		}

		Integer listSize = list.size();
		isNull(value, messageRequired, list);

		Date date = SensusConvertUtil.toDate(value, SUNRISE_SUNSET_FORMAT);
		if ((listSize == list.size()) && isNull(date))
		{
			list.add(MessageInfo.createFieldValidationError(
					SENSUS_MLC_LIGHTVALIDATOR_INVALID_TIME,
					value,
					messageField));
		}
	}

	/**
	 * Validate pole id.
	 *
	 * @param poleId the pole id
	 * @param list the list
	 */
	public static void validatePoleId(String poleId, List<MessageInfo> list)
	{
		if (isNull(poleId))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_POLEID_REQUIRED));
		}

	}

	/**
	 * Validate life cycle state.
	 *
	 * @param lightTopLevelState the light top level state
	 * @param list the list
	 */
	public static void validateLifeCycleState(LifeCycleStateEnum lightTopLevelState, List<MessageInfo> list)
	{
		isNull(lightTopLevelState, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED, list);
	}
}
