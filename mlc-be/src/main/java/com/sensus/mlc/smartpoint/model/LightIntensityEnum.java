package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LightIntensityTypeEnum.
 * 
 * @author - Alex Barros - QAT
 */
public enum LightIntensityEnum implements IIntegerEnum
{
	/** The LEVEL_0 = OFF. */
	LEVEL_0(0, 0),

	/** The LEVEL_1. */
	LEVEL_1(1, 100),

	/** The LEVEL_2. */
	LEVEL_2(2, 100),

	/** The LEVEL_3. */
	LEVEL_3(3, 100),

	/** The LEVEL_4. */
	LEVEL_4(4, 100),

	/** The LEVEL_5 = ON. */
	LEVEL_5(5, 100),

	/** The LEVEL_6. */
	LEVEL_6(6, 100),

	/** The LEVEL_7. */
	LEVEL_7(7, 0);

	/** The light intensity. */
	private Integer lightIntensity;

	/** The percentage. */
	private Integer percentage;

	/**
	 * Instantiates a new light intensity type enum.
	 * 
	 * @param intensityParam the intensity param
	 * @param percentageParam the percentage param
	 */
	private LightIntensityEnum(Integer intensityParam, Integer percentageParam)
	{
		lightIntensity = intensityParam;
		percentage = percentageParam;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return lightIntensity;
	}

	/**
	 * Gets the percentage.
	 * 
	 * @return the percentage
	 */
	public Integer getPercentage()
	{
		return percentage;
	}

	/**
	 * Sets the percentage.
	 * 
	 * @param percentageParam the new percentage
	 */
	public void setPercentage(Integer percentageParam)
	{
		percentage = percentageParam;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return SmartPointTypeEnum
	 */
	public static LightIntensityEnum enumForValue(Integer value)
	{
		for (LightIntensityEnum e : values())
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
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (LightIntensityEnum i : LightIntensityEnum.class.getEnumConstants())
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}
}
