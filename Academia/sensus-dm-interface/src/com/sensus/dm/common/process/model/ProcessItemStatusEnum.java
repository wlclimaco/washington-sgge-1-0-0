package com.sensus.dm.common.process.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ProcessItemStatusEnum.
 * 
 * @author QAT Brazil
 */
public enum ProcessItemStatusEnum implements IIntegerEnum
{

	/** The UNDEFINED. */
	UNDEFINED(0),

	/** Running. */
	RUNNING(1),

	/** Failed. */
	FAILED(2),

	/** Canceled. */
	CANCELLED(3),

	/** Timed Out. */
	TIMED_OUT(4),

	/** Completed. */
	COMPLETED(5),

	/** Not Started. */
	NOT_STARTED(6),

	/** Interrupted. */
	INTERRUPTED(7),

	/** Paused. */
	PAUSED(8),

	/** Aborted. */
	ABORTED(9),

	/** The UNREACHABLE. */
	UNREACHABLE(10),

	/** Too many attempts. */
	TOO_MANY_ATTEMPTS(11),

	/** Device does not accept future time. */
	DEVICE_DOES_NOT_ACCEPT_FUTURE_TIME(12),

	/** The REPEATED. */
	REPEATED(13),

	/** The INVALID_TARGET. */
	INVALID_TARGET(14),

	/** The invalid id. */
	INVALID_ID(15),

	/** The opt out. */
	OPT_OUT(16),

	EXPIRED(17);

	/** The status id. */
	private Integer statusId;

	/**
	 * Instantiates a new type enum.
	 * 
	 * @param paramType the param type
	 */
	private ProcessItemStatusEnum(Integer paramType)
	{
		statusId = paramType;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return statusId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * 
	 * @return TypeEnum
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
