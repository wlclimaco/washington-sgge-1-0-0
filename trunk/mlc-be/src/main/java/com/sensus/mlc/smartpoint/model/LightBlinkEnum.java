package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

public enum LightBlinkEnum implements IIntegerEnum
{
	/** The Blink 0 = Not blinking. */
	NONE(0),

	/** The Blink 1 = Blinking fast. */
	FAST(1),

	/** The Blink 2 = Blinking slow. */
	SLOW(2);

	/** The light intensity. */
	private Integer lightBlink;

	/**
	 * Instantiates a new light Blink type enum.
	 * 
	 * @param blinkParam the blink param
	 * 
	 */
	private LightBlinkEnum(Integer blinkParam)
	{
		lightBlink = blinkParam;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return lightBlink;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return SmartPointTypeEnum
	 */
	public static LightBlinkEnum enumForValue(Integer value)
	{
		for (LightBlinkEnum e : values())
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

		for (LightBlinkEnum i : LightBlinkEnum.class.getEnumConstants())
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}
}