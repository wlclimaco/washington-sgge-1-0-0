package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum AlertTypeEnum.
 * 
 * @author - Gustavo Peres - QAT Brazil
 */
public enum AlertTypeEnum implements IIntegerEnum
{
	/** The none. */
	NONE(0, PrecedenceEnum.UNKNOWN),

	/** The alarm. */
	ALARM(1, PrecedenceEnum.ALARM),

	/** The waring. */
	WARNING(2, PrecedenceEnum.WARNING);

	/** Attributes. */
	private Integer alertType;
	private PrecedenceEnum precedence;

	/**
	 * Instantiates a new alert type enum.
	 * 
	 * @param alertTypeParam the alert type param
	 * @param precedenceParam the precedence param
	 */
	private AlertTypeEnum(Integer alertTypeParam, PrecedenceEnum precedenceParam)
	{
		alertType = alertTypeParam;
		precedence = precedenceParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return alertType;
	}

	/**
	 * Gets the precedence value.
	 * 
	 * @return the precedence value
	 */
	public Integer getPrecedenceValue()
	{
		if (precedence == null)
		{
			return null;
		}
		return precedence.getValue();
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the alert type enum
	 */
	public static AlertTypeEnum enumForValue(Integer value)
	{
		for (AlertTypeEnum e : values())
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
		AlertTypeEnum[] enums = AlertTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (AlertTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
