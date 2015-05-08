package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum PersonTypeEnum lists the different types of {@link Person} that can exist in SendSolv
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 9:59:58 AM
 */
public enum PersonTypeEnum implements IIntegerEnum, II18nEnum
{

	/** The liaison accounting. */
	LIAISON(1, "com.prosperitasglobal.sendsolv.model.persontypeenum.liaison"),

	/** The member. */
	MEMBER(2, "com.prosperitasglobal.sendsolv.model.persontypeenum.member"),

	/** The recipient. */
	RECIPIENT(3, "com.prosperitasglobal.sendsolv.model.persontypeenum.recipient"),

	/** The other. */
	OTHER(7, "com.prosperitasglobal.sendsolv.model.persontypeenum.other");

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
	private PersonTypeEnum(Integer value, String labelKeyParam)
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
	public static PersonTypeEnum enumForValue(Integer value)
	{
		for (PersonTypeEnum e : values())
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

		for (PersonTypeEnum i : PersonTypeEnum.class.getEnumConstants())
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