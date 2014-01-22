package com.sensus.lc.base.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ListTypeEnum.
 */
public enum ListTypeEnum implements IIntegerEnum
{

	/** The default. */
	DEFAULT(0),

	/** The SMARTPOINTLIST. */
	SMARTPOINTLIST(1);

	/** The list type enum id. */
	private Integer listTypeEnumId;

	/**
	 * Instantiates a new list type enum.
	 * 
	 * @param listTypeEnumIdParam the list type enum id param
	 */
	private ListTypeEnum(Integer listTypeEnumIdParam)
	{
		listTypeEnumId = listTypeEnumIdParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return listTypeEnumId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the list type enum
	 */
	public static ListTypeEnum enumForValue(Integer value)
	{
		for (ListTypeEnum e : values())
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
		ListTypeEnum[] enums = ListTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ListTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
