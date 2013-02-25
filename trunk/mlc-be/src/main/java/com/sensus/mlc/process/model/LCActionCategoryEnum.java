package com.sensus.mlc.process.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LCActionCategoryEnum.
 */
public enum LCActionCategoryEnum implements IIntegerEnum
{

	/** The control lights. */
	CONTROL_LIGHTS(1),

	/** The manage lights. */
	MANAGE_LIGHTS(2),

	/** The manage groups. */
	MANAGE_GROUPS(3),

	/** The manage tags. */
	MANAGE_TAGS(4),

	/** The manage schedules. */
	MANAGE_SCHEDULES(5),

	/** The unknown. */
	UNKNOWN(6);

	/** The lc action category. */
	private Integer lcActionCategory;

	/**
	 * Instantiates a new lC action category enum.
	 * 
	 * @param lcActionCategoryInd the lc action category ind
	 */
	private LCActionCategoryEnum(Integer lcActionCategoryInd)
	{
		lcActionCategory = lcActionCategoryInd;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return lcActionCategory;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the lC action category enum
	 */
	public static LCActionCategoryEnum enumForValue(Integer value)
	{
		for (LCActionCategoryEnum e : values())
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
		LCActionCategoryEnum[] enums = LCActionCategoryEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LCActionCategoryEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
