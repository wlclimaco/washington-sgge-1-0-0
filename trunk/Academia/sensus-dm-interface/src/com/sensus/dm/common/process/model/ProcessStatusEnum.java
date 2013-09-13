package com.sensus.dm.common.process.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ProcessStatusEnum.
 */
public enum ProcessStatusEnum implements IIntegerEnum
{
	/** The UNDEFINED. */
	UNDEFINED(0),

	/** The I n_ process. */
	IN_PROCESS(1),

	/** The SCHEDULED. */
	SCHEDULED(2),

	/** The COMPLETED. */
	COMPLETED(3),

	/** The ABORTED. */
	ABORTED(4),

	/** The CANCELLED. */
	CANCELLED(5),

	/** The SCHEDULE d_ paused. */
	SCHEDULED_PAUSED(6),

	/** The received. */
	RECEIVED(7),
	
	/** The started. */
	STARTED(8),
	
	/** The COMMAND_ SENT. */
	COMMAND_SENT(9);

	/** The process result. */
	private Integer processResult;

	/**
	 * Instantiates a new process status enum.
	 * 
	 * @param processResultEnumInd the process result enum ind
	 */
	private ProcessStatusEnum(Integer processResultEnumInd)
	{
		processResult = processResultEnumInd;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return processResult;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the process status enum
	 */
	public static ProcessStatusEnum enumForValue(Integer value)
	{
		for (ProcessStatusEnum e : values())
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

		for (ProcessStatusEnum i : ProcessStatusEnum.class.getEnumConstants())
		{
			enumValue.append(comma).append(i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}
}
