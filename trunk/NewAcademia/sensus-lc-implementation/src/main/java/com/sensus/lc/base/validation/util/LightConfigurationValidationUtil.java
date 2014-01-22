package com.sensus.lc.base.validation.util;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;

/**
 * The Class LCValidationUtil.
 */
public final class LightConfigurationValidationUtil
{
	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_TEMPERATURE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_TEMPERATURE_REQUIRED =
			"sensus.mlc.lightvalidator.temperature.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED =
			"sensus.mlc.lightvalidator.firmware.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED =
			"sensus.mlc.lightvalidator.lower.case.serial.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED =
			"sensus.mlc.lightvalidator.upper.case.serial.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_BULB_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_BULB_SERIAL_REQUIRED =
			"sensus.mlc.lightvalidator.bulb.serial.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_BALLAST_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_BALLAST_SERIAL_REQUIRED =
			"sensus.mlc.lightvalidator.ballast.serial.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_PRODUCT_NUMBER_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_PRODUCT_NUMBER_REQUIRED =
			"sensus.mlc.lightvalidator.product.number.required";

	/**
	 * Instantiates a new lC validation util.
	 */
	private LightConfigurationValidationUtil()
	{
	}

	/**
	 * Validate Temperature.
	 *
	 * @param temperature the temperature
	 * @param list the list
	 */
	public static void validateTemperature(Integer temperature, List<MessageInfo> list)
	{
		isNull(temperature, SENSUS_MLC_LIGHTVALIDATOR_TEMPERATURE_REQUIRED, list);
	}

	/**
	 * Validate firmware version.
	 *
	 * @param firmwareVersion the firmware version
	 * @param list the list
	 */
	public static void validateFirmwareVersion(String firmwareVersion, List<MessageInfo> list)
	{
		if (isNull(firmwareVersion))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED));
		}

	}

	/**
	 * Validate lower case serial.
	 *
	 * @param lowerCaseSerial the lower case serial
	 * @param list the list
	 */
	public static void validateLowerCaseSerial(String lowerCaseSerial, List<MessageInfo> list)
	{
		if (isNull(lowerCaseSerial))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED));
		}

	}

	/**
	 * Validate upper case serial.
	 *
	 * @param upperCaseSerial the upper case serial
	 * @param list the list
	 */
	public static void validateUpperCaseSerial(String upperCaseSerial, List<MessageInfo> list)
	{
		if (isNull(upperCaseSerial))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED));
		}

	}

	/**
	 * Validate bulb serial.
	 *
	 * @param bulbSerial the bulb serial
	 * @param list the list
	 */
	public static void validateBulbSerial(String bulbSerial, List<MessageInfo> list)
	{
		if (isNull(bulbSerial))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_BULB_SERIAL_REQUIRED));
		}

	}

	/**
	 * Validate ballast serial.
	 *
	 * @param ballastSerial the ballast serial
	 * @param list the list
	 */
	public static void validateBallastSerial(String ballastSerial, List<MessageInfo> list)
	{
		if (isNull(ballastSerial))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_BALLAST_SERIAL_REQUIRED));
		}

	}

	/**
	 * Validate product number.
	 *
	 * @param productNumber the product number
	 * @param list the list
	 */
	public static void validateProductNumber(String productNumber, List<MessageInfo> list)
	{
		if (isNull(productNumber))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_PRODUCT_NUMBER_REQUIRED));
		}

	}

}
