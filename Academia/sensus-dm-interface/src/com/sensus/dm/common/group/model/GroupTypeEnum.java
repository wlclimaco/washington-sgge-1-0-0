package com.sensus.dm.common.group.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum GroupTypeEnum.
 * 
 * @author - QAT Brazil.
 */
public enum GroupTypeEnum implements IIntegerEnum
{

	/** The BILLING. */
	BILLING(4),

	/** The OPERATIONS. */
	OPERATIONS(6),

	/** The ALL_OTHERS. */
	ALL_OTHERS(7);

	/** The group type. */
	private Integer groupType;

	/**
	 * Instantiates a new group type enum.
	 * 
	 * @param groupTypeInd the group type ind
	 */
	private GroupTypeEnum(Integer groupTypeInd)
	{
		groupType = groupTypeInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Integer getValue()
	{

		return groupType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the group type enum
	 */
	public static GroupTypeEnum enumForValue(Integer value)
	{
		for (GroupTypeEnum e : values())
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
		GroupTypeEnum[] enums = GroupTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (GroupTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
