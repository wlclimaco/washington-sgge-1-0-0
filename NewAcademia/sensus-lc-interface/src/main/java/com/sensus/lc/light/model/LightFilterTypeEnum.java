package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LightFilterEnum.
 */
public enum LightFilterTypeEnum implements IIntegerEnum
{

	/** The lamp type. */
	LAMP_TYPE(1),

	/** The wattage rating. */
	WATTAGE_RATING(2),

	/** The lamp type wattage dimmable. */
	LAMP_TYPE_WATTAGE_DIMMABLE(3),

	/** The housing. */
	HOUSING(4),

	/** The dimmable. */
	DIMMABLE(5),

	/** The housing color. */
	HOUSING_COLOR(6),

	/** The input voltage range. */
	INPUT_VOLTAGE_RANGE(7),

	/** The firmware version. */
	FIRMWARE_VERSION(8),

	/** The color temperature. */
	COLOR_TEMPERATURE(9);

	/** The light filter type id. */
	private Integer lightFilterTypeName;

	/**
	 * Instantiates a new light filter type enum.
	 * 
	 * @param value the value
	 */
	private LightFilterTypeEnum(Integer value)
	{
		this.lightFilterTypeName = value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return this.lightFilterTypeName;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the light filter enum
	 */
	public static LightFilterTypeEnum enumForValue(Integer value)
	{
		for (LightFilterTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}
}
