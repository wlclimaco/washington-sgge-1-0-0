package com.sensus.lc.light.model;

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
	INDUCTION(1, "I"),

	/** The LED. */
	LED(2, "L"),

	/** The OTHER. */
	OTHER(3, "O");

	/** The analytic type id. */
	private Integer lightTypeId;

	private String lightType;

	/**
	 * Instantiates a new light type enum.
	 *
	 * @param lightTypeIdParam the light type id param
	 * @param lightTypeParam the light type param
	 */
	private LightTypeEnum(Integer lightTypeIdParam, String lightTypeParam)
	{
		lightTypeId = lightTypeIdParam;
		setLightType(lightTypeParam);
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

	public String getStringValue()
	{
		return lightType;
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
	 * @param value
	 * @return
	 */
	public static LightTypeEnum enumForValue(String value)
	{
		for (LightTypeEnum type : values())
		{
			if (type.getStringValue().equals(value))
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

	public String getLightType()
	{
		return lightType;
	}

	public void setLightType(String lightType)
	{
		this.lightType = lightType;
	}
}
