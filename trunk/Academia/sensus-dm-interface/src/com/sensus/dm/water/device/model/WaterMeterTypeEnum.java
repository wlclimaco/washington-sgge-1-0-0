package com.sensus.dm.water.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum WaterMeterTypeEnum.
 * 
 * @author QAT Global.
 */
public enum WaterMeterTypeEnum implements IIntegerEnum
{

	/** The FLEXNE t_1 wa y_ water. */
	FLEXNET_1WAY_WATER(6, "Flexnet Water"),

	/** The iperl. */
	IPERL(11, "iPERL"),

	/** The North american 2 way water. */
	NORTH_AMERICAN_2WAY_WATER(47, "North American 2-Way Water"),

	/** The Na2w water shutoff. */
	NA2W_WATER_SHUTOFF(64, "NA2W Water Shutoff");

	/** The water meter type. */
	private Integer waterMeterType;

	/** The water meter type description. */
	private String waterMeterTypeDescription;

	/**
	 * Instantiates a new water meter type enum.
	 * 
	 * @param waterMeterTypeParam the water meter type param
	 * @param waterMeterTypeDescriptionParam the water meter type description param
	 */
	private WaterMeterTypeEnum(Integer waterMeterTypeParam, String waterMeterTypeDescriptionParam)
	{
		waterMeterType = waterMeterTypeParam;
		waterMeterTypeDescription = waterMeterTypeDescriptionParam;
	}

	/**
	 * Gets the water meter type description.
	 * 
	 * @return the water meter type description
	 */
	public String getWaterMeterTypeDescription()
	{
		return waterMeterTypeDescription;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return waterMeterType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the water meter type enum
	 */
	public static WaterMeterTypeEnum enumForValue(Integer value)
	{
		for (WaterMeterTypeEnum e : values())
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
		WaterMeterTypeEnum[] enums = WaterMeterTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (WaterMeterTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
