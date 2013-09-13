package com.sensus.dm.common.action.model;

import com.sensus.common.model.IStringEnum;

/**
 * Enum for Action Columns.
 * 
 * @author QAT Brazil.
 * 
 */
public enum ActionColumnEnum implements IStringEnum
{

	/** The ACTION_ID. */
	ACTION_ID("at.action_type_id"),

	/** The ACTION_NAME. */
	ACTION_NAME("at.description"),

	/** The ACTION_TYPE. */
	ACTION_TYPE("ac.name"),

	/** The CITY_NAME. */
	MODIFIED_BY("at.modified_user"),

	/** The DATE_MODIFIED. */
	DATE_MODIFIED("at.modified_date");

	/** The column value. */
	private String columnValue;

	/**
	 * Instantiates a new property enum.
	 * 
	 * @param propertyIdParam the property id param
	 */
	private ActionColumnEnum(String paramColumnValue)
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
	 * @return the property enum
	 */
	public static ActionColumnEnum enumForValue(String value)
	{
		for (ActionColumnEnum e : values())
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
		ActionColumnEnum[] enums = ActionColumnEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ActionColumnEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
