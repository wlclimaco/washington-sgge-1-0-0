package com.sensus.lc.base.validation.util;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.List;

import com.sensus.common.model.MessageInfo;

/**
 * The Class LCValidationUtil.
 */
public final class LightLastOpDateValidationUtil
{

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_CURRENT_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_CURRENT_REQUIRED =
			"sensus.mlc.lightvalidator.current.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_VOLTAGE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_VOLTAGE_REQUIRED =
			"sensus.mlc.lightvalidator.voltage.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_CONSUMPTION_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_CONSUMPTION_REQUIRED =
			"sensus.mlc.lightvalidator.consumption.required";

	/**
	 * Instantiates a new lC validation util.
	 */
	private LightLastOpDateValidationUtil()
	{
	}

	/**
	 * Validate current.
	 *
	 * @param current the current
	 * @param list the list
	 */
	public static void validateCurrent(Integer current, List<MessageInfo> list)
	{
		isNull(current, SENSUS_MLC_LIGHTVALIDATOR_CURRENT_REQUIRED, list);
	}

	/**
	 * Validate current.
	 *
	 * @param current the current
	 * @param list the list
	 */
	public static void validateCurrent(Float current, List<MessageInfo> list)
	{
		isNull(current, SENSUS_MLC_LIGHTVALIDATOR_CURRENT_REQUIRED, list);
	}

	/**
	 * Validate voltage.
	 *
	 * @param voltage the voltage
	 * @param list the list
	 */
	public static void validateVoltage(Integer voltage, List<MessageInfo> list)
	{
		isNull(voltage, SENSUS_MLC_LIGHTVALIDATOR_VOLTAGE_REQUIRED, list);
	}

	/**
	 * Validate voltage.
	 *
	 * @param voltage the voltage
	 * @param list the list
	 */
	public static void validateVoltage(Float voltage, List<MessageInfo> list)
	{
		isNull(voltage, SENSUS_MLC_LIGHTVALIDATOR_VOLTAGE_REQUIRED, list);
	}

	/**
	 * Validate consumption.
	 *
	 * @param consumption the consumption
	 * @param list the list
	 */
	public static void validateConsumption(Integer consumption, List<MessageInfo> list)
	{
		isNull(consumption, SENSUS_MLC_LIGHTVALIDATOR_CONSUMPTION_REQUIRED, list);
	}

	/**
	 * Validate consumption.
	 *
	 * @param consumption the consumption
	 * @param list the list
	 */
	public static void validateConsumption(Float consumption, List<MessageInfo> list)
	{
		isNull(consumption, SENSUS_MLC_LIGHTVALIDATOR_CONSUMPTION_REQUIRED, list);
	}

}
