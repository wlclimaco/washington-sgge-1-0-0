package com.sensus.dm.common.base.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum BaseSortEnum.
 * 
 * @author QAT Global.
 */
public enum BaseSortEnum implements IStringEnum
{
	/** The DESCRIPTION. */
	DESCRIPTION("description"),

	/** The id. */
	ID("ID"),

	/** The LATITUDE. */
	LATITUDE("LATITUDE"),

	/** The LONGITUDE. */
	LONGITUDE("LONGITUDE"),

	/** The modified user. */
	MODIFIED_USER("MODIFIED_USER"),

	/** The modified date. */
	MODIFIED_DATE("modified_date"),

	/** The name. */
	NAME("name"),

	/** The end time. */
	END_TIME("END_TIME"),

	/** The start time. */
	START_TIME("START_TIME"),

	/** The status. */
	STATUS("STATUS");

	/** The property Id. */
	private String columnValue;

	/**
	 * Instantiates a new base column enum.
	 * 
	 * @param paramColumnValue the param column value
	 */
	private BaseSortEnum(String paramColumnValue)
	{
		columnValue = paramColumnValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return columnValue;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the base column enum
	 */
	public static BaseSortEnum enumForValue(String value)
	{
		for (BaseSortEnum e : values())
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
		BaseSortEnum[] enums = BaseSortEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (BaseSortEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
