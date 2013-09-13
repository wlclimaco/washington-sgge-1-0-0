package com.sensus.common.scheduler.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum Frequency.
 * 
 * @author QAT Brazil
 */
public enum FrequencyTypeEnum implements IStringEnum
{

	/** Daily. */
	DAILY("DAILY"),

	/** Never. */
	NEVER("NEVER"),

	/** Every Week Day. */
	EVERY_WEEKDAY("EVERY_WEEKDAY"),

	/** Every Monday Wednesday Friday. */
	EVERY_MON_WED_FRI("EVERY_MON_WED_FRI"),

	/** Every Tuesday Thursday. */
	EVERY_TUE_THURS("EVERY_TUE_THURS"),

	/** Weekly. */
	WEEKLY("WEEKLY"),

	/** Monthly. */
	MONTHLY("MONTHLY"),

	/** Yearly. */
	YEARLY("YEARLY"),

	/** Custom. */
	CUSTOM("CUSTOM");

	/** The property Id. */
	private String frequency;

	/**
	 * Instantiates a new property enum.
	 * 
	 * @param propertyIdParam the property id param
	 */
	private FrequencyTypeEnum(String frequencyIdParam)
	{
		frequency = frequencyIdParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return frequency;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the property enum
	 */
	public static FrequencyTypeEnum enumForValue(String value)
	{
		for (FrequencyTypeEnum e : values())
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
		FrequencyTypeEnum[] enums = FrequencyTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (FrequencyTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
