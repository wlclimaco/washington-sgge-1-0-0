package com.sensus.mlc.smartpoint.model;

import org.apache.commons.lang.xwork.StringUtils;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum SmartPointColumnEnum.
 */
public enum SmartPointColumnEnum implements IStringEnum
{

	/** The eco mode. */
	ECOMODE("ECOMODE"),

	/** The Light ID. */
	LIGHT_ID("light.light_id"),

	/** The RNI ID. */
	RNI_ID("RNI_id"),

	/** STATUS. */
	STATUS("lp_cur_stat.property_value"),

	/** DATE_ADDED. */
	DATE_ADDED("13"),

	/** LAMP_TYPE. */
	LAMP_TYPE("6"),

	/** CITY. */
	CITY_NAME("29"),

	/** The PROTECTED. */
	PROTECTED("protected"),

	/** POLE_ID. */
	POLE_ID("12"),

	/** The BUL b_ seria l_ number. */
	BULB_SERIAL_NUMBER("47"),

	/** The COLO r_ temperature. */
	COLOR_TEMPERATURE("9"),

	/** The FIRMWAR e_ version. */
	FIRMWARE_VERSION("15"),

	/** The HOUSIN g_ color. */
	HOUSING_COLOR("75"),

	/** The LIGH t_ drive r_ seria l_ number. */
	SERIAL_NUMBER("5"),

	/** The LOWE r_ assembl y_ seria l_ number. */
	LOWER_ASSEMBLY_SERIAL_NUMBER("45"),

	/** The MA p_ it. */
	MAP_IT("MAP_IT"),

	/** The MODE l_ number. */
	MODEL_NUMBER("44"),

	/** The UPPE r_ assembl y_ seria l_ number. */
	UPPER_ASSEMBLY_SERIAL_NUMBER("46"),

	/** The VOLTAG e_ range. */
	INPUT_VOLTAGE_RANGE("8");

	private static final String COMMA = ",";
	/** The property Id. */
	private String columnValue;

	/**
	 * Instantiates a new smart point column enum.
	 *
	 * @param paramColumnValue the param column value
	 */
	private SmartPointColumnEnum(String paramColumnValue)
	{
		columnValue = paramColumnValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return columnValue;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the smart point column enum
	 */
	public static SmartPointColumnEnum enumForValue(String value)
	{
		for (SmartPointColumnEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values.
	 *
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		SmartPointColumnEnum[] enums = SmartPointColumnEnum.values();

		StringBuilder enumValue = new StringBuilder();

		for (SmartPointColumnEnum i : enums)
		{
			enumValue.append(COMMA + i.getValue());
		}

		return StringUtils.substringAfter(enumValue.toString(), COMMA);
	}

	/**
	 * Checks if is valid value.
	 *
	 * @param value the value
	 * @return true, if is valid value
	 */
	public static boolean isValidValue(String value)
	{
		SmartPointColumnEnum[] enums = SmartPointColumnEnum.values();

		for (SmartPointColumnEnum e : enums)
		{
			if (StringUtils.isNotBlank(e.getValue()) && StringUtils.isNumeric(e.getValue())
					&& StringUtils.contains(value, e.getValue()))
			{
				return true;
			}
		}

		return false;
	}

}
