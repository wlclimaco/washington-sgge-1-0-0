package com.sensus.dm.common.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum MeterStateEnum.
 * 
 * @author - QAT Brazil
 */
public enum DeviceStateEnum implements IIntegerEnum
{

	/** The OFF. */
	OFF(0),

	/** The ON. */
	ON(1),

	/** Maintenance Mode. */
	MAINTENANCE(3),

	/** Deactivated (Idle) Mode. */
	DEACTIVATED(4),

	/** Don't know the Meter Status. */
	UNKNOWN(5);

	/** The meter state. */
	private Integer meterState;

	/**
	 * Instantiates a new meter state.
	 * 
	 * @param meterStatusEnumInd the meter state enum ind
	 */
	private DeviceStateEnum(Integer meterStateEnumInd)
	{
		meterState = meterStateEnumInd;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return meterState;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the meter status enum
	 */
	public static DeviceStateEnum enumForValue(Integer value)
	{
		for (DeviceStateEnum e : values())
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
		DeviceStateEnum[] enums = DeviceStateEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DeviceStateEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
