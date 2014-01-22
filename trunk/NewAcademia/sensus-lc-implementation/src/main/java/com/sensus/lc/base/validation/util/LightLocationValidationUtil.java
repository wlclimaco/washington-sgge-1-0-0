package com.sensus.lc.base.validation.util;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;
import java.util.TimeZone;

import com.sensus.cbof.model.TimeZoneInfo;
import com.sensus.common.model.MessageInfo;

/**
 * The Class LCValidationUtil.
 */
public final class LightLocationValidationUtil
{

	/** The Constant MAX_LONGITUDE. */
	private static final int MAX_LONGITUDE = 180;

	/** The Constant MIN_LONGITUDE. */
	private static final int MIN_LONGITUDE = -180;

	/** The Constant MIN_LATITUDE. */
	private static final int MIN_LATITUDE = -90;

	/** The Constant MAX_LATITUDE. */
	private static final int MAX_LATITUDE = 90;

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_TIME_ZONE. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_TIME_ZONE =
			"sensus.mlc.lightvalidator.invalid.time.zone";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LATITUDE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LATITUDE_REQUIRED =
			"sensus.mlc.lightvalidator.latitude.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LONGITUDE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LONGITUDE_REQUIRED =
			"sensus.mlc.lightvalidator.longitude.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_LATITUDE. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_LATITUDE =
			"sensus.mlc.lightvalidator.invalid.latitude";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_LONGITUDE. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_LONGITUDE =
			"sensus.mlc.lightvalidator.invalid.longitude";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_TIME_ZONE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_TIME_ZONE_REQUIRED =
			"sensus.mlc.lightvalidator.time.zone.required";

	/**
	 * Instantiates a new lC validation util.
	 */
	private LightLocationValidationUtil()
	{
	}

	/**
	 * Validate time zone info.
	 *
	 * @param timeZoneInfo the time zone info
	 * @param list the list
	 */
	public static void validateTimeZoneInfo(TimeZoneInfo timeZoneInfo, List<MessageInfo> list)
	{
		if (isNull(timeZoneInfo) || isNull(timeZoneInfo.getTimeZone()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_TIME_ZONE_REQUIRED,
					timeZoneInfo));
			return;
		}

		if (isNull(timeZoneInfo.getDisplayNameShort()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_INVALID_TIME_ZONE,
					timeZoneInfo));
		}
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
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_TIME_ZONE_REQUIRED,
					timeZoneRegion));
			return;
		}

		TimeZone timeZone = TimeZone.getTimeZone(timeZoneRegion);
		if (isNull(timeZone))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_INVALID_TIME_ZONE, timeZone));
		}
	}

	/**
	 * Validate longitude.
	 *
	 * @param longitude the longitude
	 * @param list the list
	 */
	public static void validateLongitude(Double longitude, List<MessageInfo> list)
	{
		if (isNull(longitude))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_LONGITUDE_REQUIRED));
			return;
		}

		validateInvalidLongitude(longitude, list);
	}

	/**
	 * Validate latitude.
	 *
	 * @param param the param
	 * @param list the list
	 */
	public static void validateLatitude(Double latitude, List<MessageInfo> list)
	{

		if (isNull(latitude))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_LATITUDE_REQUIRED));
			return;
		}

		validateInvalidLatitude(latitude, list);
	}

	/**
	 * Validate invalid longitude.
	 *
	 * @param longitude the longitude
	 * @param list the list
	 */
	public static void validateInvalidLongitude(Double longitude, List<MessageInfo> list)
	{
		if (isNull(longitude))
		{
			return;
		}

		if ((longitude > MAX_LONGITUDE) || (longitude < MIN_LONGITUDE))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_INVALID_LONGITUDE));
		}
	}

	/**
	 * Validate invalid latitude.
	 *
	 * @param latitude the latitude
	 * @param list the list
	 */
	public static void validateInvalidLatitude(Double latitude, List<MessageInfo> list)
	{
		if (isNull(latitude))
		{
			return;
		}

		if ((latitude > MAX_LATITUDE) || (latitude < MIN_LATITUDE))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_INVALID_LATITUDE));
		}
	}

}
