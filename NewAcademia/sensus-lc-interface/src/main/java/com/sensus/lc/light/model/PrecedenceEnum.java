package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ColorPrecedenceEnum.
 */
public enum PrecedenceEnum implements IIntegerEnum
{

	/** ALARM. */
	ALARM(0),

	/** WARNING. */
	WARNING(1),

	/** Maintenance Mode. */
	MAINTENANCE(2),

	/** DEACTIVATED. */
	DEACTIVATED(3),

	/** ACTIVE. */
	ACTIVE(4),

	/** UNKNOWN. */
	UNKNOWN(5);

	private Integer precedenceValue;

	private PrecedenceEnum(Integer value)
	{
		precedenceValue = value;
	}

	/**
	 * @return the precedenceValue
	 */
	public Integer getPrecedenceValue()
	{
		return precedenceValue;
	}

	/**
	 * @param precedenceValue the precedenceValue to set
	 */
	public void setPrecedenceValue(Integer precedenceValue)
	{
		this.precedenceValue = precedenceValue;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return precedenceValue;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the precedence enum
	 */
	public static PrecedenceEnum enumForValue(Integer value)
	{
		for (PrecedenceEnum type : values())
		{
			if (type.getValue().equals(value))
			{
				return type;
			}
		}
		return null;
	}

}
