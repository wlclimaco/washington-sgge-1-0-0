package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum GenderEnum.
 *
 * @author abarros
 * @version 1.0
 * @created 05-Sep-2014 11:19:03 AM
 */
public enum GenderEnum implements IIntegerEnum, II18nEnum
{

	/** The male. */
	MALE(1, "com.prosperitasglobal.sendsolv.model.sexenum.male"),

	/** The female. */
	FEMALE(2, "com.prosperitasglobal.sendsolv.model.sexenum.female"),

	/** The no answer. */
	NO_ANSWER(3, "com.prosperitasglobal.sendsolv.model.sexenum.noanswer");

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
	private GenderEnum(Integer value, String labelKeyParam)
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
	 * @return the gender enum
	 */
	public static GenderEnum enumForValue(Integer value)
	{
		for (GenderEnum e : values())
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

		for (GenderEnum i : GenderEnum.class.getEnumConstants())
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