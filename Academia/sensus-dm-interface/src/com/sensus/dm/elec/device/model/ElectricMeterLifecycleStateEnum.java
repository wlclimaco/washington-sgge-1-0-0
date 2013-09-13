package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum ElectricMeterLifecycleStateEnum.
 */
public enum ElectricMeterLifecycleStateEnum implements IStringEnum
{

	/** The installed. */
	INSTALLED("Install"),

	/** The inventory. */
	INVENTORY("Inventory"),

	/** The not assigned. */
	NOT_ASSIGNED("Not_Assigned");

	/** The electric meterlifecycle. */
	private String electricMeterlifecycle;

	/**
	 * Instantiates a new electric meter lifecycle state enum.
	 * 
	 * @param electricMeterlifecycle the electric meterlifecycle
	 */
	private ElectricMeterLifecycleStateEnum(String electricMeterlifecycleParam)
	{
		electricMeterlifecycle = electricMeterlifecycleParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return electricMeterlifecycle;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the electric meter lifecycle state enum
	 */
	public static ElectricMeterLifecycleStateEnum enumForValue(String value)
	{
		for (ElectricMeterLifecycleStateEnum e : values())
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
		ElectricMeterLifecycleStateEnum[] enums = ElectricMeterLifecycleStateEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ElectricMeterLifecycleStateEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
