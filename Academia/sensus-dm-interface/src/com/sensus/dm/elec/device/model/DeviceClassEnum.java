package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum DeviceClassEnum.
 */
public enum DeviceClassEnum implements IIntegerEnum
{
	/** The HVAC compressor. */
	HVAC_COMPRESSOR(1, "HVACCompressor"),

	/** The STRIP heater. */
	STRIP_HEATER(2, "StripHeaters"),

	/** The WATER heater. */
	WATER_HEATER(4, "WaterHeater"),

	/** The POOL pump. */
	POOL_PUMP(8, "PoolPump"),

	/** The SMART appliances. */
	SMART_APPLIANCES(16, "SmartAppliances"),

	/** The IRRIGATION pump. */
	IRRIGATION_PUMP(32, "IrrigationPump"),

	/** The MANAGED commercial. */
	MANAGED_COMMERCIAL(64, "IndustrialLoads"),

	/** The simple misc. */
	SIMPLE_MISC(128, "SimpleMiscLoads"),

	/** The EXTERIOR lighting. */
	EXTERIOR_LIGHTING(256, "ExteriorLighting"),

	/** The INTERIOR lighting. */
	INTERIOR_LIGHTING(512, "InteriorLighting"),

	/** The ELECTRIC vehicle. */
	ELECTRIC_VEHICLE(1024, "ElectricVehicle"),

	/** The GENERATION systems. */
	GENERATION_SYSTEMS(2048, "GenerationSystems"),

	/** The all. */
	ALL(4095, "all");

	/** The device class id. */
	private Integer deviceClassId;

	/** The device class description. */
	private String deviceClassDescription;

	/**
	 * Instantiates a new device class enum.
	 * 
	 * @param deviceClassIdParam the device class id param
	 * @param deviceClassDescriptionParam the device class description param
	 */
	private DeviceClassEnum(Integer deviceClassIdParam, String deviceClassDescriptionParam)
	{
		deviceClassId = deviceClassIdParam;
		deviceClassDescription = deviceClassDescriptionParam;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the device class enum
	 */
	public static DeviceClassEnum enumForValue(Integer value)
	{
		for (DeviceClassEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Enum for description value.
	 * 
	 * @param value the value
	 * @return the device class enum
	 */
	public static DeviceClassEnum enumForDescriptionValue(String value)
	{
		for (DeviceClassEnum e : values())
		{
			if (e.getDeviceClassDescription().equals(value))
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
		DeviceClassEnum[] enums = DeviceClassEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DeviceClassEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return deviceClassId;
	}

	/**
	 * Gets the device class description.
	 * 
	 * @return the device class description
	 */
	public String getDeviceClassDescription()
	{
		return deviceClassDescription;
	}
}
