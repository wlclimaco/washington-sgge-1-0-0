package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum HanDeviceTypeEnum.
 */
public enum HanDeviceTypeEnum implements IIntegerEnum
{
	/** The THERMOSTAT. */
	THERMOSTAT(0),

	/** The IHD. */
	IHD(2);

	/** The han device type. */
	private Integer hanDeviceType;

	/**
	 * Instantiates a new hAN device type enum.
	 * 
	 * @param hanDeviceTypeInd the han device type ind
	 */
	private HanDeviceTypeEnum(Integer hanDeviceTypeInd)
	{
		hanDeviceType = hanDeviceTypeInd;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return hanDeviceType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the hAN device type enum
	 */
	public static HanDeviceTypeEnum enumForValue(Integer value)
	{
		for (HanDeviceTypeEnum e : values())
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
		HanDeviceTypeEnum[] enums = HanDeviceTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (HanDeviceTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
