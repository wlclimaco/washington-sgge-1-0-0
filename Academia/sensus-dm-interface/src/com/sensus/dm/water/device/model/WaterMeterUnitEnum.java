package com.sensus.dm.water.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum WaterMeterUnitEnum.
 * 
 * @author QAT Global
 */
public enum WaterMeterUnitEnum implements IIntegerEnum
{
	/** The gallons. */
	GALLONS(0, "Gallons"),

	/** The liters. */
	LITERS(1, "Liters"),

	/** The cubic feet. */
	CUBIC_FEET(2, "Cubic Feet"),

	/** The cubic meters. */
	CUBIC_METERS(3, "Cubic Meters"),

	/** The acre feet. */
	ACRE_FEET(4, "Acre Feet");

	/** The value. */
	private Integer value;

	/** The description. */
	private String description;

	/**
	 * Instantiates a new water history scale enum.
	 * 
	 * @param valueParam the value param
	 * @param descriptionParam the description param
	 */
	private WaterMeterUnitEnum(Integer valueParam, String descriptionParam)
	{
		value = valueParam;
		description = descriptionParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return value;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Enum from value.
	 * 
	 * @param valueParam the value param
	 * @return the water history scale enum
	 */
	public static WaterMeterUnitEnum enumFromValue(Integer valueParam)
	{
		for (WaterMeterUnitEnum e : values())
		{
			if (e.getValue().equals(valueParam))
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
		WaterMeterUnitEnum[] enums = WaterMeterUnitEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (WaterMeterUnitEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
