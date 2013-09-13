package com.sensus.dm.common.schedule.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum ScheduleOrderByEnum.
 */
public enum ScheduleOrderByEnum implements IStringEnum
{
	/** The schedule id. */
	SCHEDULE_ID("schedule_id"),

	/** The event name. */
	EVENT_NAME("name"),

	/** The action name. */
	ACTION_NAME("action_description"),

	/** The action type. */
	ACTION_TYPE("action_Category_name"),

	/** The scheduled by. */
	SCHEDULED_BY("modified_user"),

	/** The event date. */
	EVENT_DATE("start_time"),

	/** The action date. */
	ACTION_DATE("action_date"),

	/** The deliver on. */
	DELIVER_ON("next_execution"),

	/** The frequency name. */
	FREQUENCY_NAME("frequency_name"),

	/** The total smartpoints. */
	TOTAL_SMARTPOINTS("total_smartpoint"),

	/** The status. */
	STATUS("status_desc");

	/** The colum name. */
	private String columName;

	/**
	 * Instantiates a new schedule order by enum.
	 * 
	 * @param columNameParam the colum name
	 */
	private ScheduleOrderByEnum(String columNameParam)
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
}
