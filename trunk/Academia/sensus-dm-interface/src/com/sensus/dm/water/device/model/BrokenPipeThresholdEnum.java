package com.sensus.dm.water.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum BrokenPipeThresholdEnum.
 * 
 * @author QAT Global
 */
public enum BrokenPipeThresholdEnum implements IIntegerEnum
{

	/** The zero. */
	ZERO(0, "(disabled)"),

	/** The one. */
	ONE(1, "500 counts"),

	/** The two. */
	TWO(2, "800 counts"),

	/** The three. */
	THREE(3, "1,350 counts"),

	/** The four. */
	FOUR(4, "4,500 counts");

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
	private BrokenPipeThresholdEnum(Integer valueParam, String descriptionParam)
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
	public static BrokenPipeThresholdEnum enumFromValue(Integer valueParam)
	{
		for (BrokenPipeThresholdEnum e : values())
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
		BrokenPipeThresholdEnum[] enums = BrokenPipeThresholdEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (BrokenPipeThresholdEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
