package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

public enum BlinkStatusEnum implements IIntegerEnum
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
	private BlinkStatusEnum(Integer blinkParam)
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
	 * @return the blink status enum
	 */
	public static BlinkStatusEnum enumForValue(Integer value)
	{
		for (BlinkStatusEnum e : values())
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

		for (BlinkStatusEnum i : BlinkStatusEnum.class.getEnumConstants())
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}
}