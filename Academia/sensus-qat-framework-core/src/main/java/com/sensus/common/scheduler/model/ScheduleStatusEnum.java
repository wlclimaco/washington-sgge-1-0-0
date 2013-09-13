package com.sensus.common.scheduler.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ScheduleStatusEnum.
 * 
 * @author QAT Global
 */
public enum ScheduleStatusEnum implements IIntegerEnum
{

	/** Disabled. */
	DISABLED(0),

	/** Enabled. */
	ENABLED(1),

	/** Paused. */
	PAUSED(2),

	/** The failed. */
	FAILED(3);

	/** The property Id. */
	private Integer frequency;

	/**
	 * Instantiates a new property enum.
	 * 
	 * @param frequencyIdParam the frequency id param
	 */
	private ScheduleStatusEnum(Integer frequencyIdParam)
	{
		frequency = frequencyIdParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return frequency;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the property enum
	 */
	public static ScheduleStatusEnum enumForValue(Integer value)
	{
		for (ScheduleStatusEnum e : values())
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
		ScheduleStatusEnum[] enums = ScheduleStatusEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ScheduleStatusEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
