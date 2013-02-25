package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LightStateEnum.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public enum LightStateEnum implements IIntegerEnum
{

	/** The OFF. */
	OFF(0),

	/** The ON. */
	ON(1),

	/** The BLINK. */
	BLINK(2),

	/** Maintenance Mode. */
	MAINTENANCE(3),

	/** Deactivated (Idle) Mode. */
	DEACTIVATED(4),

	/** Don't know the Light Status. */
	UNKNOWN(5);

	/** The light status enum. */
	private Integer lightStatusEnum;

	/**
	 * Instantiates a new light status enum.
	 * 
	 * @param lightStatusEnumInd the light status enum ind
	 */
	private LightStateEnum(Integer lightStatusEnumInd)
	{
		lightStatusEnum = lightStatusEnumInd;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return lightStatusEnum;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the light status enum
	 */
	public static LightStateEnum enumForValue(Integer value)
	{
		for (LightStateEnum e : values())
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
		LightStateEnum[] enums = LightStateEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LightStateEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
