package com.sensus.common.scheduler.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum DaysOfWeekEnum.
 * 
 * @author - QAT Brazil.
 */
public enum DaysOfWeekEnum implements IIntegerEnum
{

	/** Undefined. */
	UNDEFINED(0),

	/** Sunday. */
	SUNDAY(1),

	/** Monday. */
	MONDAY(2),

	/** Tuesday. */
	TUESDAY(3),

	/** Wednesday. */
	WEDNESDAY(4),

	/** Thursday. */
	THURSDAY(5),

	/** Friday. */
	FRIDAY(6),

	/** Saturday. */
	SATURDAY(7);

	/** The day of week. */
	private Integer dayOfWeek;

	/**
	 * Instantiates a new dayOfWeekEnum ind enum.
	 * 
	 * @param dayOfWeekInd the dayOfWeek ind
	 */
	private DaysOfWeekEnum(Integer dayOfWeekInd)
	{
		dayOfWeek = dayOfWeekInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Integer getValue()
	{
		return dayOfWeek;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * 
	 * @return DaysOfWeekEnum
	 */
	public static DaysOfWeekEnum enumForValue(Integer value)
	{
		for (DaysOfWeekEnum e : values())
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
	public static Integer getValidValues()
	{
		DaysOfWeekEnum[] enums = DaysOfWeekEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DaysOfWeekEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return null;
	}

}
