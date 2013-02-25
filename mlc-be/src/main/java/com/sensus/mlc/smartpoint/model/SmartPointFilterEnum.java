package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum FilterEnum.
 * 
 * @author QAT Brazil.
 */
public enum SmartPointFilterEnum implements IStringEnum
{
	/** The ADDRESS. */
	ADDRESS("ADDRESS"),

	/** The ALAR m_ type. */
	ALARM_TYPE("ALARM_TYPE"),

	/** The BUL b_ seria l_ number. */
	BULB_SERIAL_NUMBER("BULB_SERIAL_NUMBER"),

	/** The COLO r_ temperature. */
	COLOR_TEMPERATURE("COLOR_TEMPERATURE"),

	/** The CONFIGURATION. */
	CONFIGURATION("CONFIGURATION"),

	/** The DAT e_ added. */
	DATE_ADDED("DATE_ADDED"),

	/** The EC o_ mode. */
	ECOMODE("ECOMODE"),

	/** The EVEN t_ schedule. */
	EVENT_SCHEDULE("EVENT_SCHEDULE"),

	/** The FIRMWAR e_ version. */
	FIRMWARE_VERSION("FIRMWARE_VERSION"),

	/** The GROUPS. */
	GROUPS("GROUPS"),

	/** The HOUSIN g_ color. */
	HOUSING_COLOR("HOUSING_COLOR"),

	/** The LIGH t_ drive r_ seria l_ number. */
	LIGHT_DRIVER_SERIAL_NUMBER("LIGHT_DRIVER_SERIAL_NUMBER"),

	/** The LIGH t_ type. */
	LIGHT_TYPES("LIGHT_TYPES"),

	/** The LOWE r_ assembl y_ seria l_ number. */
	LOWER_ASSEMBLY_SERIAL_NUMBER("LOWER_ASSEMBLY_SERIAL_NUMBER"),

	/** The MODE l_ number. */
	MODEL_NUMBER("MODEL_NUMBER"),

	/** The OFFSE t_ schedule. */
	OFFSET_SCHEDULE("OFFSET_SCHEDULE"),

	/** The STATUS. */
	STATUS("STATUS"),

	/** The TAG. */
	TAGS("TAGS"),

	/** The UPPE r_ assembl y_ seria l_ number. */
	UPPER_ASSEMBLY_SERIAL_NUMBER("UPPER_ASSEMBLY_SERIAL_NUMBER"),

	/** The VOLTAG e_ range. */
	VOLTAGE_RANGE("VOLTAGE_RANGE"),

	/** The WARININ g_ type. */
	WARNING_TYPE("WARNING_TYPE");

	/** The property Id. */
	private String filterValue;

	/**
	 * Instantiates a new property enum.
	 * 
	 * @param paramFilterValue the param filter value
	 */
	private SmartPointFilterEnum(String paramFilterValue)
	{
		filterValue = paramFilterValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return filterValue;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the property enum
	 */
	public static SmartPointFilterEnum enumForValue(String value)
	{
		for (SmartPointFilterEnum e : values())
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
		SmartPointFilterEnum[] enums = SmartPointFilterEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (SmartPointFilterEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
