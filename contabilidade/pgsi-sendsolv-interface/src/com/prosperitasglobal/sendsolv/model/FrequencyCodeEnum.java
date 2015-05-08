package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * Enumeration specifying the values defined for frequency code in the SendSolv system.
 */
public enum FrequencyCodeEnum implements IIntegerEnum, II18nEnum
{
	/** Weekly. */
	WEEKLY(1, "com.prosperitasglobal.sendsolv.model.frequencycodeenum.weekly"),

	/** Bi-weekly. */
	BI_WEEKLY(2, "com.prosperitasglobal.sendsolv.model.frequencycodeenum.biweekly"),

	/** Semi-monthly. */
	SEMI_MONTHLY(3, "com.prosperitasglobal.sendsolv.model.frequencycodeenum.semimonthly"),

	/** Monthly. */
	MONTHLY(4, "com.prosperitasglobal.sendsolv.model.frequencycodeenum.monthly"),

	/** One day. */
	ONE_DAY(5, "com.prosperitasglobal.sendsolv.model.frequencycodeenum.oneday");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value the value
	 * @param labelKeyParam the label key param
	 */
	private FrequencyCodeEnum(Integer value, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return code;
	}

	/**
	 * Enumeration for value.
	 *
	 * @param value The value of the frequency code enumeration.
	 * @return The frequency code enumeration.
	 */
	public static FrequencyCodeEnum enumForValue(Integer value)
	{
		for (FrequencyCodeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values for the frequency code's.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (FrequencyCodeEnum i : FrequencyCodeEnum.class.getEnumConstants())
		{
			enumValue.append(comma).append(i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.II18nEnum#getLabelKey()
	 */
	@Override
	public String getLabelKey()
	{
		return labelKey;
	}

}