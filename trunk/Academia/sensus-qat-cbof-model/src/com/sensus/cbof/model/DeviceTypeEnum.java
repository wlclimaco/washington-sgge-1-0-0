package com.sensus.cbof.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum DeviceTypeEnum.
 * 
 */
public enum DeviceTypeEnum implements IIntegerEnum
{

	/** The electric meter. */
	ELECTRIC_METER(1),

	/** The han device. */
	HAN_DEVICE(2),

	/** The light. */
	LIGHT(3),

	/** The lcm. */
	LCM(4),

	/** The water meter. */
	WATER_METER(5),

	/** The gas meter. */
	GAS_METER(6),

	/** The arqiva device. */
	ARQIVA(7);

	/** The device type. */
	private Integer deviceType;

	/**
	 * Instantiates a new device type enum.
	 * 
	 * @param deviceTypeParam the device type
	 */
	private DeviceTypeEnum(Integer deviceTypeParam)
	{
		deviceType = deviceTypeParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return deviceType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * 
	 * @return TypeEnum
	 */
	public static DeviceTypeEnum enumForValue(Integer value)
	{
		for (DeviceTypeEnum e : values())
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
		DeviceTypeEnum[] enums = DeviceTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DeviceTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
