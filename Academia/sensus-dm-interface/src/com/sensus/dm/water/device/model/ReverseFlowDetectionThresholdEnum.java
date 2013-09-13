package com.sensus.dm.water.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum WaterMeterResolutionEnum.
 * 
 * @author QAT Global
 */
public enum ReverseFlowDetectionThresholdEnum implements IIntegerEnum
{

	/** The zero. */
	ZERO(0, "(disabled)"),

	/** The one. */
	ONE(1, "1 count"),

	/** The two. */
	TWO(2, "2 counts"),

	/** The three. */
	THREE(3, "5 counts"),

	/** The four. */
	FOUR(4, "10 counts"),

	/** The five. */
	FIVE(5, "100 counts");

	/** The value. */
	private Integer value;

	/** The description. */
	private String description;

	/**
	 * Instantiates a new reverse flow detection threshold enum.
	 * 
	 * @param valueParam the value param
	 * @param descriptionParam the description param
	 */
	private ReverseFlowDetectionThresholdEnum(Integer valueParam, String descriptionParam)
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
	 * @return the reverse flow detection threshold enum
	 */
	public static ReverseFlowDetectionThresholdEnum enumFromValue(Integer valueParam)
	{
		for (ReverseFlowDetectionThresholdEnum e : values())
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
