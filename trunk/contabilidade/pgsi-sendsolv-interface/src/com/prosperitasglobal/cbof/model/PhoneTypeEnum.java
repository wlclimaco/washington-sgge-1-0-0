package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum PhoneTypeEnum lists the different types of {@link Phone} allowed on SendSolv
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:03 AM
 */
public enum PhoneTypeEnum implements IIntegerEnum, II18nEnum
{

	/** The home. */
	HOME(1, "com.prosperitasglobal.sendsolv.model.phonetypeenum.home"),

	/** The cellular. */
	CELLULAR(2, "com.prosperitasglobal.sendsolv.model.cellular"),

	/** The work. */
	WORK(3, "com.prosperitasglobal.sendsolv.model.phonetypeenum.work"),

	/** The fax. */
	FAX(4, "com.prosperitasglobal.sendsolv.model.phonetypeenum.fax"),

	/** The unknown. */
	UNKNOWN(5, "com.prosperitasglobal.sendsolv.model.phonetypeenum.unknown");

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
	private PhoneTypeEnum(Integer value, String labelKeyParam)
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
	public static PhoneTypeEnum enumForValue(Integer value)
	{
		for (PhoneTypeEnum e : values())
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

		for (PhoneTypeEnum i : PhoneTypeEnum.class.getEnumConstants())
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