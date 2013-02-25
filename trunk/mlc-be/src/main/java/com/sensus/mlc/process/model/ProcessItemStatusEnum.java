package com.sensus.mlc.process.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ProcessResultEnum.
 */
public enum ProcessItemStatusEnum implements IIntegerEnum
{

	/** The PENDING. */
	PENDING(1),

	/** The SUCCESS. */
	SUCCESS(2),

	/** The MLCFAILURE. */
	MLCFAILURE(3),

	/** The RNISYNCFAILURE. */
	RNISYNCFAILURE(4),

	/** The RNIASYNCFAILURE. */
	RNIASYNCFAILURE(5),

	/** The ABORTED. */
	ABORTED(6);

	/** The process result. */
	private Integer processResult;

	/**
	 * Instantiates a new process result enum.
	 * 
	 * @param processResultEnumInd the process result enum ind
	 */
	private ProcessItemStatusEnum(Integer processResultEnumInd)
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
	 * @return the process result enum
	 */
	public static ProcessItemStatusEnum enumForValue(Integer value)
	{
		for (ProcessItemStatusEnum e : values())
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
		ProcessItemStatusEnum[] enums = ProcessItemStatusEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ProcessItemStatusEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
