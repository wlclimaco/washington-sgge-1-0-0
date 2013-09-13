package com.sensus.dm.water.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum WaterLeakDetectionThresholdEnum.
 * 
 * @author QAT Global
 */
public enum WaterLeakDetectionThresholdEnum implements IIntegerEnum
{

	/** The zero. */
	ZERO(0, "24"),

	/** The one. */
	ONE(1, "36"),

	/** The two. */
	TWO(2, "48"),

	/** The three. */
	THREE(3, "72");

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
	private WaterLeakDetectionThresholdEnum(Integer valueParam, String descriptionParam)
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
	public static WaterLeakDetectionThresholdEnum enumFromValue(Integer valueParam)
	{
		for (WaterLeakDetectionThresholdEnum e : values())
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
		WaterLeakDetectionThresholdEnum[] enums = WaterLeakDetectionThresholdEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (WaterLeakDetectionThresholdEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
