package com.sensus.dm.common.process.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum ProcessOrderByEnum.
 * 
 * @author QAT Global.
 */
public enum ProcessOrderByEnum implements IStringEnum
{
	/** The process id. */
	PROCESS_ID("process_id"),

	/** The action type. */
	ACTION_TYPE("name"),

	/** The action name. */
	ACTION_NAME("description"),

	/** The smartpoint count. */
	SMARTPOINT_COUNT("items_count"),

	/** The modified date. */
	MODIFIED_DATE("modified_date"),

	/** The start time day. */
	START_TIME_DAY("start_time_date"),

	/** The status. "Case" for "order by" on SQL */
	STATUS("(CASE " +
			"WHEN status = 4 AND action_type_id IN (1,6) THEN 0 " + // ABORTED - CANCELLED
			"WHEN status = 5 THEN 1 " + // CANCELLED
			"WHEN status = 9 THEN 2 " + // COMMAND_SENT
			"WHEN status = 3 THEN 3 " + // COMPLETED
			"WHEN status = 4 AND action_type_id NOT IN (1,6) THEN 4 " + // EXPIRED
			"WHEN status = 1 THEN 5 " + // IN_PROCESS
			"WHEN status = 7 THEN 6 " + // RECEIVED
			"WHEN status = 2 THEN 7 " + // SCHEDULED
			"WHEN status = 6 THEN 8 " + // SCHEDULED_PAUSED
			"WHEN status = 8 THEN 9 " + // STARTED
			"ELSE 10 END)"), // UNDEFINED

	/** The start time. */
	START_TIME("start_time"),

	/** The modified user. */
	MODIFIED_USER("modified_user"),

	/** The failed. */
	FAILED("failed_count");

	/** The colum name. */
	private String columName;

	/**
	 * Instantiates a new process order by enum.
	 * 
	 * @param columNameParam the colum name
	 */
	private ProcessOrderByEnum(String columNameParam)
	{
		columName = columNameParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return columName;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the process order by enum
	 */
	public static ProcessOrderByEnum enumForValue(String value)
	{
		for (ProcessOrderByEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}
}
