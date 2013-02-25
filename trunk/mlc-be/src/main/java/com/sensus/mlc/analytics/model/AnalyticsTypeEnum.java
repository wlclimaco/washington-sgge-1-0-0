package com.sensus.mlc.analytics.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum AnalyticsTypeEnum.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 * 
 */
public enum AnalyticsTypeEnum implements IStringEnum
{

	/** The LIGHTING_ALARM. */
	LIGHTING_ALARM("1"),

	/** The LIGHTING_WARNING. */
	LIGHTING_WARNING("2"),

	/** The LIGHTING_INSTALLED. */
	LIGHTING_INSTALLED("3"),

	/** The ECOMODE_CONSUMPTION. */
	ECOMODE_CONSUMPTION("4"),

	LIGHTING_ECOMODE("5"),

	/** The ECOMODE_CARBON_CREDITS. */
	ECOMODE_CARBON_CREDITS("6");

	/** The analytic type id. */
	private String analyticTypeId;

	/**
	 * Instantiates a new analytics type enum.
	 * 
	 * @param id the id
	 */
	private AnalyticsTypeEnum(String id)
	{
		analyticTypeId = id;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
	{
		return analyticTypeId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the analytics type enum
	 */
	public static AnalyticsTypeEnum enumForValue(String value)
	{
		for (AnalyticsTypeEnum type : values())
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
		AnalyticsTypeEnum[] enums = AnalyticsTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (AnalyticsTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
