package com.sensus.mlc.schedule.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ScheduleTypeEnum.
 */
public enum ScheduleTypeEnum implements IIntegerEnum
{

	/** The OFFSET. */
	OFFSET(1),

	/** The EVENT. */
	EVENT(2),

	/** The OFFSET. */
	UNDETERMINED_OFFSET(3),

	/** The OFFSET. */
	UNDETERMINED_EVENT(4);

	/** The schedule type. */
	private Integer scheduleType;

	/**
	 * Instantiates a new schedule type enum.
	 * 
	 * @param scheduleTypeInd the schedule type ind
	 */
	private ScheduleTypeEnum(Integer scheduleTypeInd)
	{
		scheduleType = scheduleTypeInd;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return scheduleType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the schedule type enum
	 */
	public static ScheduleTypeEnum enumForValue(Integer value)
	{
		for (ScheduleTypeEnum e : values())
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
		for (ScheduleTypeEnum i : values())
		{
			enumValue.append(comma).append(i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
