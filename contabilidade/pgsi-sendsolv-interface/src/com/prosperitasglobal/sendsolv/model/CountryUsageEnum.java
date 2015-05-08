package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * Enumeration specifying the values defined for a usage of a country in the SendSolv system.
 */
public enum CountryUsageEnum implements IIntegerEnum, II18nEnum
{
	/** Citizenship usage. */
	CITIZENSHIP(1, "com.prosperitasglobal.sendsolv.model.countryusageenum.citizenship"),

	/** Residence usage. */
	RESIDENCE(2, "com.prosperitasglobal.sendsolv.model.countryusageenum.residence"),

	/** Birth usage. */
	BIRTH(3, "com.prosperitasglobal.sendsolv.model.countryusageenum.birth");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value The value of the payer type enumeration.
	 * @param labelKeyParam The label key parameter.
	 */
	private CountryUsageEnum(Integer value, String labelKeyParam)
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
	 * @param value The value of the country usage enumeration.
	 * @return The country usage enumeration.
	 */
	public static CountryUsageEnum enumForValue(Integer value)
	{
		for (CountryUsageEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values for the country usage's.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (CountryUsageEnum i : CountryUsageEnum.class.getEnumConstants())
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