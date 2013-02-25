package com.sensus.mlc.base.util;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.util.SensusConvertUtil;
import com.sensus.mlc.base.model.Parameter;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightSchedule;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;

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

	/** The Constant MAX_LONGITUDE. */
	private static final int MAX_LONGITUDE = 180;

	/** The Constant MIN_LONGITUDE. */
	private static final int MIN_LONGITUDE = -180;

	/** The Constant MIN_LATITUDE. */
	private static final int MIN_LATITUDE = -90;

	/** The Constant MAX_LATITUDE. */
	private static final int MAX_LATITUDE = 90;

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_HOUR. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_HOUR =
			"sensus.mlc.smartpointvalidator.invalid.hour";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_MINUTE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_MINUTE =
			"sensus.mlc.smartpointvalidator.invalid.minute";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_HOUR_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_HOUR_REQUIRED =
			"sensus.mlc.smartpointvalidator.hour.required";
	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_HOUR_REQUIRED. */

	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_MINUTE_REQUIRED =
			"sensus.mlc.smartpointvalidator.minute.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_CURRENT_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_CURRENT_REQUIRED =
			"sensus.mlc.smartpointvalidator.current.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_VOLTAGE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_VOLTAGE_REQUIRED =
			"sensus.mlc.smartpointvalidator.voltage.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_TEMPERATURE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_TEMPERATURE_REQUIRED =
			"sensus.mlc.smartpointvalidator.temperature.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE =
			"sensus.mlc.smartpointvalidator.invalid.time.zone";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_CONSUMPTION_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_CONSUMPTION_REQUIRED =
			"sensus.mlc.smartpointvalidator.consumption.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISE_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunrise.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSET_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSET_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunset.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED =
			"sensus.mlc.smartpointvalidator.schedule.properties.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME =
			"sensus.mlc.smartpointvalidator.invalid.time";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED =
			"sensus.mlc.smartpointvalidator.latitude.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED =
			"sensus.mlc.smartpointvalidator.longitude.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE =
			"sensus.mlc.smartpointvalidator.invalid.latitude";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE =
			"sensus.mlc.smartpointvalidator.invalid.longitude";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_TIME_ZONE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_TIME_ZONE_REQUIRED =
			"sensus.mlc.smartpointvalidator.time.zone.required";

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
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_HOUR_REQUIRED, new Object[] {hour,
							parmName}));
			return;
		}

		if ((hour > MAX_HOUR) || (hour < MIN_HOUR))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_HOUR, new Object[] {hour,
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
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_MINUTE_REQUIRED, new Object[] {minute,
							parmName}));
			return;
		}

		if ((minute > MAX_MIN) || (minute < MIN_MIN))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_MINUTE, new Object[] {minute,
							parmName}));
		}
	}

	/**
	 * Validate current.
	 * 
	 * @param current the current
	 * @param list the list
	 */
	public static void validateCurrent(Integer current, List<MessageInfo> list)
	{
		isNull(current, SENSUS_MLC_SMARTPOINTVALIDATOR_CURRENT_REQUIRED, list);
	}

	/**
	 * Validate current.
	 * 
	 * @param current the current
	 * @param list the list
	 */
	public static void validateCurrent(Float current, List<MessageInfo> list)
	{
		isNull(current, SENSUS_MLC_SMARTPOINTVALIDATOR_CURRENT_REQUIRED, list);
	}

	/**
	 * Validate voltage.
	 * 
	 * @param voltage the voltage
	 * @param list the list
	 */
	public static void validateVoltage(Integer voltage, List<MessageInfo> list)
	{
		isNull(voltage, SENSUS_MLC_SMARTPOINTVALIDATOR_VOLTAGE_REQUIRED, list);
	}

	/**
	 * Validate voltage.
	 * 
	 * @param voltage the voltage
	 * @param list the list
	 */
	public static void validateVoltage(Float voltage, List<MessageInfo> list)
	{
		isNull(voltage, SENSUS_MLC_SMARTPOINTVALIDATOR_VOLTAGE_REQUIRED, list);
	}

	/**
	 * Validate Temperature.
	 * 
	 * @param temperature the temperature
	 * @param list the list
	 */
	public static void validateTemperature(Integer temperature, List<MessageInfo> list)
	{
		isNull(temperature, SENSUS_MLC_SMARTPOINTVALIDATOR_TEMPERATURE_REQUIRED, list);
	}

	/**
	 * Validate time zone region.
	 * 
	 * @param param the param
	 * @param list the list
	 */
	public static void validateTimeZoneRegion(Parameter param, List<MessageInfo> list)
	{
		if (isNull(param))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_TIME_ZONE_REQUIRED));
			return;
		}
		validateTimeZoneRegion(param.getValue(), list);
	}

	/**
	 * Validate time zone region.
	 * 
	 * @param timeZoneRegion the time zone region
	 * @param list the list
	 */
	public static void validateTimeZoneRegion(String timeZoneRegion, List<MessageInfo> list)
	{
		if (isNull(timeZoneRegion))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE,
					timeZoneRegion));
			return;
		}

		TimeZone timeZone = TimeZone.getTimeZone(timeZoneRegion);
		if (isNull(timeZone))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME_ZONE, timeZone));
		}
	}

	/**
	 * Validate consumption.
	 * 
	 * @param consumption the consumption
	 * @param list the list
	 */
	public static void validateConsumption(Integer consumption, List<MessageInfo> list)
	{
		isNull(consumption, SENSUS_MLC_SMARTPOINTVALIDATOR_CONSUMPTION_REQUIRED, list);
	}

	/**
	 * Validate consumption.
	 * 
	 * @param consumption the consumption
	 * @param list the list
	 */
	public static void validateConsumption(Float consumption, List<MessageInfo> list)
	{
		isNull(consumption, SENSUS_MLC_SMARTPOINTVALIDATOR_CONSUMPTION_REQUIRED, list);
	}

	/**
	 * Validate status properties.
	 * 
	 * @param request the request
	 * @param list the list
	 */
	public static void validateStatusProperties(AlarmNotificationRequest request, List<MessageInfo> list)
	{
		Light light = request.getLight();
		if (isNull(light) || isNull(light.getLightSchedule()))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED));
			return;
		}

		LightSchedule schedule = light.getLightSchedule();
		validateTime(schedule.getSunriseTime(), SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISE_REQUIRED, "Sunrise Time", list);
		validateTime(schedule.getSunsetTime(), SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSET_REQUIRED, "Sunset Time", list);
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
					SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_TIME,
					value,
					messageField));
		}
	}

	/**
	 * Validate longitude.
	 * 
	 * @param param the param
	 * @param list the list
	 */
	public static void validateLongitude(Parameter param, List<MessageInfo> list)
	{
		if (isNull(param))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED));
			return;
		}
		validateLongitude(param.getValue(), list);
	}

	/**
	 * Validate longitude.
	 * 
	 * @param longitude the longitude
	 * @param list the list
	 */
	public static void validateLongitude(String longitude, List<MessageInfo> list)
	{
		if (isNull(longitude) || !NumberUtils.isNumber(longitude))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED));
			return;
		}

		Double lng = Double.valueOf(longitude);
		if ((lng > MAX_LONGITUDE) || (lng < MIN_LONGITUDE))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE));
		}
	}

	/**
	 * Validate latitude.
	 * 
	 * @param param the param
	 * @param list the list
	 */
	public static void validateLatitude(Parameter param, List<MessageInfo> list)
	{

		if (isNull(param))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED));
			return;
		}

		validateLatitude(param.getValue(), list);
	}

	/**
	 * Validate latitude.
	 * 
	 * @param latitude the latitude
	 * @param list the list
	 */
	public static void validateLatitude(String latitude, List<MessageInfo> list)
	{
		if (isNull(latitude) || !NumberUtils.isNumber(latitude))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED));
			return;
		}

		Double lat = Double.valueOf(latitude);
		if ((lat > MAX_LATITUDE) || (lat < MIN_LATITUDE))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE));
		}
	}

	/**
	 * Validate lat long.
	 * For Latitude:
	 * -90.00 to 90.00
	 * For Longitude:
	 * -180.00 to 180.00
	 * 
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param list the list
	 */
	public void validateLatLong(String latitude, String longitude, List<MessageInfo> list)
	{
		validateLatitude(latitude, list);
		validateLongitude(longitude, list);
	}
}
