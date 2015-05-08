package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.II18nEnum;

/**
 * The Enum CodeValueEnum.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 2:16:32 PM
 */
public enum CodeValueEnum implements II18nEnum
{

	/** The naics. */
	NAICS(1, "com.prosperitasglobal.sendsolv.model.codevaluetypeenum.naics"),

	/** The sic. */
	SIC(2, "com.prosperitasglobal.sendsolv.model.codevaluetypeenum.sic"),

	/** The unknown. */
	PREFIX(3, "com.prosperitasglobal.sendsolv.model.codevaluetypeenum.prefix"),

	/** The suffix. */
	SUFFIX(4, "com.prosperitasglobal.sendsolv.model.codevaluetypeenum.suffix"),

	/** The unknown. */
	UNKNOWN(5, "com.prosperitasglobal.sendsolv.model.codevaluetypeenum.unknown");

	/** The code. */
	private Integer code;
	/**
	 * The label key.
	 */
	private String labelKey;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.II18nEnum#getLabelKey()
	 */
	@Override
	public String getLabelKey()
	{
		return labelKey;
	}

	/**
	 * The Constructor.
	 *
	 * @param value the value
	 * @param labelKeyParam labelKeyParam
	 */
	private CodeValueEnum(Integer value, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
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
	public static CodeValueEnum enumForValue(Integer value)
	{
		for (CodeValueEnum e : values())
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

		for (RangeEnum i : RangeEnum.class.getEnumConstants())
		{
			enumValue.append(comma).append(i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}

}