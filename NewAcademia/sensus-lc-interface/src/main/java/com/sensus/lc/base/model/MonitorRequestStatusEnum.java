package com.sensus.lc.base.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum MonitorRequestStatusEnum.
 */
public enum MonitorRequestStatusEnum implements IIntegerEnum
{

	// Always monitor Status as a System Message
	/** The ALWAY s_ monitor. */
	ALWAYS_MONITOR(1),

	// Always dismiss Status to Event History
	/** The ALWAY s_ dismiss. */
	ALWAYS_DISMISS(2),

	// Prompt me with each status request
	/** The PROMPT. */
	PROMPT(3);

	/** The monitorRequestStatusEnumInd . */
	private Integer value;

	/**
	 * Instantiates a new lC action type enum.
	 * 
	 * @param monitorRequestStatusEnumInd the monitor request status enum ind
	 */
	private MonitorRequestStatusEnum(Integer monitorRequestStatusEnumInd)
	{
		value = monitorRequestStatusEnumInd;
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
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the lC action type enum
	 */
	public static MonitorRequestStatusEnum enumForValue(Integer value)
	{
		for (MonitorRequestStatusEnum e : values())
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
		MonitorRequestStatusEnum[] enums = MonitorRequestStatusEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (MonitorRequestStatusEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
