package com.sensus.lc.process.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum GroupOrderByEnum.
 * 
 * @author - Cristiane Cobo - QAT Brazil
 */
public enum ProcessOrderByEnum implements IStringEnum
{
	/** The ID_COLUMN. */
	ID_COLUMN("process_id"),

	/** The ACTION_COLUMN. */
	ACTION_COLUMN("lc_action_description"),

	/** The LIGHTS_COLUMN. */
	LIGHTS_COLUMN("light_count"),

	/** The LIGHTS_COLUMN_FAILED. */
	LIGHTS_COLUMN_FAILED("light_failed_count"),

	/** The DESCRIPTION_COLUMN. */
	DESCRIPTION_COLUMN("description"),

	/** The START_TIME__COLUMN. */
	START_TIME_COLUMN("start_datetime"),

	/** The COMPLETE_IN_COLUMN. */
	COMPLETE_IN_COLUMN("estimated_seconds_to_complete"),

	/** The STATUS_COLUMN. */
	STATUS_COLUMN("is_process_complete"),

	/** The USER_COLUMN. */
	USER_COLUMN("create_user");

	/** The column name. */
	private String columnName;

	/**
	 * Instantiates a new order by enum.
	 * 
	 * @param columnNameInd the column name ind
	 */
	private ProcessOrderByEnum(String columnNameInd)
	{
		columnName = columnNameInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
	{
		return columnName;
	}

}
