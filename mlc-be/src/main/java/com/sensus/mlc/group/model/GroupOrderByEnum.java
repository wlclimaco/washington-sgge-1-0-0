package com.sensus.mlc.group.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum GroupOrderByEnum.
 * 
 * @author - Cristiane Cobo - QAT Brazil
 */
public enum GroupOrderByEnum implements IStringEnum
{
	/** The Group Name. */
	ID_COLUMN("grouping_id"),

	/** The Group Name. */
	NAME_COLUMN("name"),

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
	private GroupOrderByEnum(String columNameInd)
	{
		columName = columNameInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
	{
		return columName;
	}

}
