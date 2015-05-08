package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum PoliticallyExposedPersonEnum.
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:04 AM
 */
public enum PoliticallyExposedPersonEnum implements IIntegerEnum, II18nEnum
{

	/** The yes. */
	YES(1, "com.prosperitasglobal.sendsolv.model.politicallyexposedpersonenum.yes"),

	/** The no. */
	NO(2, "com.prosperitasglobal.sendsolv.model.politicallyexposedpersonenum.no"),

	/** The unknown. */
	UNKNOWN(3, "com.prosperitasglobal.sendsolv.model.politicallyexposedpersonenum.unknown");

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
	private PoliticallyExposedPersonEnum(Integer value, String labelKeyParam)
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
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the process status enum
	 */
	public static PoliticallyExposedPersonEnum enumForValue(Integer value)
	{
		for (PoliticallyExposedPersonEnum e : values())
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

		for (PoliticallyExposedPersonEnum i : PoliticallyExposedPersonEnum.class.getEnumConstants())
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