package com.sensus.dm.water.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum WaterMeterInterfaceTypeEnum.
 * 
 * @author QAT Global.
 */
public enum WaterMeterInterfaceTypeEnum implements IIntegerEnum
{

	/** The no meter attached. */
	NO_METER_ATTACHED(1),

	/** The PULS e_1_ switc h_ pulse. */
	PULSE_1_SWITCH_PULSE(2),

	/** The remote mount gas meter. */
	REMOTE_MOUNT_GAS_METER(3),

	/** The PULS e_2_ switc h_ pulse. */
	PULSE_2_SWITCH_PULSE(4),

	/** The direct mount gas meter. */
	DIRECT_MOUNT_GAS_METER(5),

	/** The SENSU s_3_ wire. */
	SENSUS_3_WIRE(6),

	/** The sensus touch read. */
	SENSUS_TOUCH_READ(7),

	/** The NEPTUN e_6_ digi t_ mode. */
	NEPTUNE_6_DIGIT_MODE(8);

	/** The water meter interface type. */
	private Integer waterMeterInterfaceType;

	/**
	 * Instantiates a new water meter interface type enum.
	 * 
	 * @param waterMeterInterfaceTypeParam the water meter interface type
	 */
	private WaterMeterInterfaceTypeEnum(Integer waterMeterInterfaceTypeParam)
	{
		waterMeterInterfaceType = waterMeterInterfaceTypeParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return waterMeterInterfaceType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the water meter interface type enum
	 */
	public static WaterMeterInterfaceTypeEnum enumForValue(Integer value)
	{
		for (WaterMeterInterfaceTypeEnum e : values())
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
		WaterMeterInterfaceTypeEnum[] enums = WaterMeterInterfaceTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (WaterMeterInterfaceTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
