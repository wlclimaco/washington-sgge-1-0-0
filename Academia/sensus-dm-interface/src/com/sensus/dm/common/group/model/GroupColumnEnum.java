package com.sensus.dm.common.group.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum GroupColumnEnum.
 * 
 * @author QAT Global.
 */
public enum GroupColumnEnum implements IStringEnum
{
	/** The DESCRIPTION. */
	DESCRIPTION("DESCRIPTION"),

	/** The name. */
	NAME("NAME"),

	/** The modified date. */
	MODIFIED_DATE("MODIFIED_DATE"),

	/** The groupset id. */
	GROUPSET_ID("GROUPSET_ID"),

	/** The groupset type id. */
	GROUPSET_TYPE_ID("GROUPSET_TYPE_ID"),

	/** The groupset type. */
	GROUPSET_TYPE("GROUPSET_TYPE"),

	/** The endpoint count. */
	ENDPOINT_COUNT("ENDPOINT_COUNT"),

	/** The device type. */
	DEVICE_TYPE("DEVICE_TYPE");

	/** The property Id. */
	private String columnValue;

	/**
	 * Instantiates a new group column enum.
	 * 
	 * @param paramColumnValue the param column value
	 */
	private GroupColumnEnum(String paramColumnValue)
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
	 * @return the group column enum
	 */
	public static GroupColumnEnum enumForValue(String value)
	{
		for (GroupColumnEnum e : values())
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
		GroupColumnEnum[] enums = GroupColumnEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (GroupColumnEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
