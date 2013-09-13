package com.sensus.dm.common.process.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum ProcessItemHistoryStatusEnum.
 * 
 * @author QAT Global.
 */
public enum ProcessItemHistoryStatusEnum implements IStringEnum
{

	/** The UNDEFINED. */
	UNDEFINED("undefined"),

	/** The EVENTRECEIVED. */
	EVENTRECEIVED("EventReceived"),

	/** The EVENTSTARTED. */
	EVENTSTARTED("EventStarted"),

	/** The EVENTCOMPLETED. */
	EVENTCOMPLETED("EventCompleted"),

	/** The EVENTCANCELLED. */
	EVENTCANCELLED("EventCancelled");

	/** The status id. */
	private String statusId;

	/**
	 * Instantiates a new process item history status enum.
	 * 
	 * @param paramType the param type
	 */
	private ProcessItemHistoryStatusEnum(String paramType)
	{
		statusId = paramType;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
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
	public static ProcessItemHistoryStatusEnum enumForValue(String value)
	{
		for (ProcessItemHistoryStatusEnum e : values())
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
		ProcessItemHistoryStatusEnum[] enums = ProcessItemHistoryStatusEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ProcessItemHistoryStatusEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
