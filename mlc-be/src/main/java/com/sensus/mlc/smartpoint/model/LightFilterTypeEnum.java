package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LightFilterEnum.
 */
public enum LightFilterTypeEnum implements IIntegerEnum
{

	/** The input voltage range. */
	INPUT_VOLTAGE_RANGE(1),

	/** The firmware version. */
	FIRMWARE_VERSION(2),

	/** The lamp type wattage dimmable. */
	LAMP_TYPE_WATTAGE_DIMMABLE(3),

	/** The wattage rating. */
	WATTAGE_RATING(4),

	/** The color temperature. */
	COLOR_TEMPERATURE(5),

	/** The lamp type. */
	LAMP_TYPE(6),

	/** The dimmable. */
	DIMMABLE(7),

	/** The housing. */
	HOUSING(8),

	/** The housing color. */
	HOUSING_COLOR(9);

	/** The light filter type id. */
	private Integer lightFilterTypeId;

	/**
	 * Instantiates a new light filter type enum.
	 * 
	 * @param value the value
	 */
	private LightFilterTypeEnum(Integer value)
	{
		this.lightFilterTypeId = value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return this.lightFilterTypeId;
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
