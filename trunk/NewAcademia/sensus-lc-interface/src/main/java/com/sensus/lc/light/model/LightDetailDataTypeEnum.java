package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LightDetailDataTypeEnum. it is used to in READ_LIGHT_STATUS messages to request different levels of details.
 * STATUS = AppCode 11 + AppCode90
 * CONFIGURATION = AppCode85 + AppCode94 + AppCode71(optional)
 * 
 * @author - Alex Barros - QAT Omaha
 */
public enum LightDetailDataTypeEnum implements IIntegerEnum
{

	/** LIGHT. */
	STATUS(1),
	/** MOTION. */
	CONFIGURATION(2);

	/** The day of week. */
	private Integer lightDetailDataType;

	/**
	 * Instantiates a new smartTypeEnum ind enum.
	 * 
	 * @param lightDetailDataTypeValue the light detail data type value
	 */
	private LightDetailDataTypeEnum(Integer lightDetailDataTypeValue)
	{
		lightDetailDataType = lightDetailDataTypeValue;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return lightDetailDataType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * 
	 * @return SmartPointTypeEnum
	 */
	public static LightDetailDataTypeEnum enumForValue(Integer value)
	{
		for (LightDetailDataTypeEnum e : values())
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
		LightDetailDataTypeEnum[] enums = LightDetailDataTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LightDetailDataTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
