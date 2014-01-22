package com.sensus.lc.user.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum UserOrderByEnum.
 */
public enum UserOrderByEnum implements IStringEnum
{

	/** The USE r_ id. */
	USER_ID("user_id"),

	/** The USE r_ name. */
	USER_NAME("user_name"),

	/** The ROLE. */
	ROLE("role"),

	/** The EMAIL. */
	EMAIL("email"),

	/** The TOTA l_ lights. */
	TOTAL_LIGHTS("total_lights"),

	/** The DAT e_ modify. */
	DATE_MODIFY("date_modify");

	/** The colum name. */
	private String columName;

	/**
	 * Instantiates a new user order by enum.
	 * 
	 * @param columNameInd the colum name ind
	 */
	private UserOrderByEnum(String columNameInd)
	{
		columName = columNameInd;
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
	 * @return the user order by enum
	 */
	public static UserOrderByEnum enumForValue(String value)
	{
		for (UserOrderByEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

}
