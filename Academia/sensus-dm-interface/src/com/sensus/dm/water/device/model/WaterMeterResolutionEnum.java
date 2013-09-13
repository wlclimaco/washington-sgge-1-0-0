package com.sensus.dm.water.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum WaterMeterResolutionEnum.
 * 
 * @author QAT Global
 */
public enum WaterMeterResolutionEnum implements IIntegerEnum
{

	/** The zero. */
	ZERO(0, "0.1x"),

	/** The one. */
	ONE(1, "1x"),

	/** The two. */
	TWO(2, "10x"),

	/** The three. */
	THREE(3, "100x"),

	/** The four. */
	FOUR(4, "1000x"),

	/** The five. */
	FIVE(5, "0.01x"),

	/** The six. */
	SIX(6, "10000x"),

	/** The seven. */
	SEVEN(7, "0.001x"),

	/** The eight. */
	EIGHT(8, "0.0001x"),

	/** The nine. */
	NINE(9, "0.00001x");

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
	private WaterMeterResolutionEnum(Integer valueParam, String descriptionParam)
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
	public static WaterMeterResolutionEnum enumFromValue(Integer valueParam)
	{
		for (WaterMeterResolutionEnum e : values())
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
		WaterMeterResolutionEnum[] enums = WaterMeterResolutionEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (WaterMeterResolutionEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
