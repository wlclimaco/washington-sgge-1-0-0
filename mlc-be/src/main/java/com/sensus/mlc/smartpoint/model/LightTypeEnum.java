package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum AnalyticsTypeEnum.
 *
 * @author - Guilherme Vicentine - QAT Brazil
 *
 */
public enum LightTypeEnum implements IIntegerEnum
{

	/** The INDUCTON. */
	INDUCTION(1),

	/** The LED. */
	LED(2),

	/** The OTHER. */
	OTHER(3);

	/** The analytic type id. */
	private Integer lightTypeId;

	/**
	 * Instantiates a new analytics type enum.
	 *
	 * @param id the id
	 */
	private LightTypeEnum(Integer id)
	{
		lightTypeId = id;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return lightTypeId;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the analytics type enum
	 */
	public static LightTypeEnum enumForValue(Integer value)
	{
		for (LightTypeEnum type : values())
		{
			if (type.getValue().equals(value))
			{
				return type;
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
		LightTypeEnum[] enums = LightTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LightTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
