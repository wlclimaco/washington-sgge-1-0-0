package com.sensus.dm.common.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum AlertEnum.
 * 
 * @author QAT Global
 */
public enum AlertEnum implements IIntegerEnum
{

	/** The alarm. */
	ALARM(0);

	/** The alert. */
	private Integer alert;

	/**
	 * Instantiates a new alert enum.
	 * 
	 * @param alertParam the alert param
	 */
	private AlertEnum(Integer alertParam)
	{
		alert = alertParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return alert;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the alert enum
	 */
	public static AlertEnum enumForValue(Integer value)
	{
		for (AlertEnum e : values())
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
		AlertEnum[] enums = AlertEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (AlertEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
