package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ElectricMeterLifecycleStatusEnum.
 */
public enum ElectricMeterLifecycleStatusEnum implements IIntegerEnum
{

	/** The active. */
	ACTIVE(1),

	/** The power off. */
	POWER_OFF(2),

	/** The opt out. */
	OPT_OUT(3),

	/** The quarantined. */
	QUARANTINED(4),

	/** The stale. */
	STALE(5);

	/** The status. */
	private Integer status;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return status;
	}

	/**
	 * Instantiates a new electric meter lifecycle status enum.
	 *
	 * @param status the status
	 */
	private ElectricMeterLifecycleStatusEnum(Integer statusParam)
	{
		this.status = statusParam;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the electric meter lifecycle status enum
	 */
	public static ElectricMeterLifecycleStatusEnum enumForValue(Integer value)
	{
		for (ElectricMeterLifecycleStatusEnum e : values())
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
		ElectricMeterLifecycleStatusEnum[] enums = ElectricMeterLifecycleStatusEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ElectricMeterLifecycleStatusEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
