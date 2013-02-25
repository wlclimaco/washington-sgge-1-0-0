package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum SmartPointTypeEnum for .
 * 
 * @author - Gustavo - QAT
 */
public enum SmartPointTypeEnum implements IIntegerEnum
{

	/** NONE. */
	NONE(0),
	/** LIGHT. */
	LIGHT(1),
	/** MOTION. */
	MOTIONSENSOR(2);

	/** The day of week. */
	private Integer smartPointType;

	/**
	 * Instantiates a new smartTypeEnum ind enum.
	 * 
	 * @param smartPointTypeInd the smart point type ind
	 */
	private SmartPointTypeEnum(Integer smartPointTypeInd)
	{
		smartPointType = smartPointTypeInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return smartPointType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * 
	 * @return SmartPointTypeEnum
	 */
	public static SmartPointTypeEnum enumForValue(Integer value)
	{
		for (SmartPointTypeEnum e : values())
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
		SmartPointTypeEnum[] enums = SmartPointTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (SmartPointTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
