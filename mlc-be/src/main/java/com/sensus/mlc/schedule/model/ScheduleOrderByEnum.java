package com.sensus.mlc.schedule.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum ScheduleOrderByEnum.
 * 
 * @author - Cristiane Cobo - QAT Brazil
 */
public enum ScheduleOrderByEnum implements IStringEnum
{
	/** The Schedule Name. */
	ID_COLUMN("schedule_id"),

	/** The Schedule Name. */
	NAME_COLUMN("name"),

	/** The Schedule Type. */
	TYPE_COLUMN("schedule_type"),

	/** The Smartpoint Count. */
	SMARTPOINTS_COLUMN("smartpoint_count"),

	/** The Create Date. */
	DATE_ADDED_COLUMN("create_date");

	/** The colum name. */
	private String columName;

	/**
	 * Instantiates a new order by enum.
	 * 
	 * @param columNameInd the colum name ind
	 */
	private ScheduleOrderByEnum(String columNameInd)
	{
		columName = columNameInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue()
	{
		return columName;
	}

}
