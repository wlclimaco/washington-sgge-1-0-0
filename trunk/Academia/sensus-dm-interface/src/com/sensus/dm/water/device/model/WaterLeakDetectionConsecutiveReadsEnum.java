package com.sensus.dm.water.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum WaterLeakDetectionThresholdEnum.
 * 
 * @author QAT Global
 */
public enum WaterLeakDetectionConsecutiveReadsEnum implements IIntegerEnum
{

	/** The zero. */
	ZERO(0, "Disabled"),

	/** The one. */
	ONE(1, "1 count"),

	/** The two. */
	TWO(2, "2 counts"),

	/** The three. */
	THREE(3, "10 counts");

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
	private WaterLeakDetectionConsecutiveReadsEnum(Integer valueParam, String descriptionParam)
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
	public static WaterLeakDetectionConsecutiveReadsEnum enumFromValue(Integer valueParam)
	{
		for (WaterLeakDetectionConsecutiveReadsEnum e : values())
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
		WaterLeakDetectionConsecutiveReadsEnum[] enums =
				WaterLeakDetectionConsecutiveReadsEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (WaterLeakDetectionConsecutiveReadsEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
