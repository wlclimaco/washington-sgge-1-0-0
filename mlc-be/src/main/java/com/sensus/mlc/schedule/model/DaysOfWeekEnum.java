package com.sensus.mlc.schedule.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum DaysOfWeekEnum for .
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public enum DaysOfWeekEnum implements IIntegerEnum
{

	/** Sunday. */
	SUNDAY(0),
	/** Monday. */
	MONDAY(1),
	/** Tuesday. */
	TUESDAY(2),
	/** Wednesday. */
	WEDNESDAY(3),
	/** Thursday. */
	THURSDAY(4),
	/** Friday. */
	FRIDAY(5),
	/** Saturday. */
	SATURDAY(6);

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
	@Override
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
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DaysOfWeekEnum i : values())
		{
			enumValue.append(comma).append(i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
