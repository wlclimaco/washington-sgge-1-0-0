package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum CustomSearchOrderByEnum.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 */
public enum CustomSearchOrderByEnum implements IStringEnum
{
	/** The Custom Search ID. */
	CUSTOM_SEARCH_ID("cs.custom_search_id"),

	/** The Custom Search name. */
	CUSTOM_SEARCH_NAME("cs.custom_search_name"),

	/** The Custom Search description. */
	CUSTOM_SEARCH_DESCRIPTION("cs.custom_search_description"),

	/** The Custom Search create date. */
	CUSTOM_SEARCH_DATE_ADDED("cs.create_date");

	/** The colum name. */
	private String columName;

	/**
	 * Instantiates a new order by enum.
	 * 
	 * @param columNameInd the colum name ind
	 */
	private CustomSearchOrderByEnum(String columNameInd)
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
