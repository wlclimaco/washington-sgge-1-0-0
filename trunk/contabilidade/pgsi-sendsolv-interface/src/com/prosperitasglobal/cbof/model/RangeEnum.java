package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.II18nEnum;

/**
 * The Enum RangeEnum.
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:06 AM
 */
public enum RangeEnum implements II18nEnum
{

	/** The number of employees. */
	NUMBER_OF_EMPLOYEES(1, "sendsolv.base.model.rangetypeenum.number_of_employees"),

	/** The number of migrant workers. */
	NUMBER_OF_MIGRANT_WORKERS(2, "sendsolv.base.model.rangetypeenum.number_of_migrant_workers"),

	/** The unknown. */
	UNKNOWN(3, "sendsolv.base.model.rangetypeenum.unknown");

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
	private RangeEnum(Integer value, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
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
	public static RangeEnum enumForValue(Integer value)
	{
		for (RangeEnum e : values())
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