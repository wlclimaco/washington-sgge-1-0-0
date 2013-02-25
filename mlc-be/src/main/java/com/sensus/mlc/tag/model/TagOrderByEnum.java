package com.sensus.mlc.tag.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum TagOrderByEnum.
 * 
 * @author - Igor Mendes - QAT Brazil
 */
public enum TagOrderByEnum implements IStringEnum
{

	/** The I d_ column. */
	ID_COLUMN("tag_id"),

	/** The NAM e_ column. */
	NAME_COLUMN("name"),

	/** The SMARTPOINT s_ column. */
	SMARTPOINTS_COLUMN("smartpoint_count"),

	/** The DAT e_ adde d_ column. */
	DATE_ADDED_COLUMN("create_date");

	/** The colum name. */
	private String columName;

	/**
	 * Instantiates a new tag order by enum.
	 * 
	 * @param columNameInd the colum name ind
	 */
	private TagOrderByEnum(String columNameInd)
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
