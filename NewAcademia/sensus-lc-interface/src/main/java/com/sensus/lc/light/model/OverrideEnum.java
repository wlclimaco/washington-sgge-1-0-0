package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum OverrideEnum.
 */
public enum OverrideEnum implements IIntegerEnum
{

	/** The none. */
	NONE(0),

	/** The scheduled. */
	SCHEDULED(1),

	/** The permanent. */
	PERMANENT(2),

	/** The per date. */
	PER_DATE(3),

	/** The all. */
	ALL(4);

	/** The override enum. */
	private Integer value;

	/**
	 * Instantiates a new override enum.
	 * 
	 * @param overrideParam the override param
	 */
	private OverrideEnum(Integer overrideParam)
	{
		value = overrideParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return value;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the override enum
	 */
	public static OverrideEnum enumForValue(Integer value)
	{
		for (OverrideEnum e : values())
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
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (OverrideEnum i : OverrideEnum.class.getEnumConstants())
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}
}
