package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * Enumeration specifying the values defined for an enrollment type in the SendSolv system.
 */
public enum EnrollmentTypeEnum implements IIntegerEnum, II18nEnum
{
	/** By Phone. */
	PHONE(1, "com.prosperitasglobal.sendsolv.model.enrollmenttypeenum.phone"),

	/** In Person. */
	IN_PERSON(2, "com.prosperitasglobal.sendsolv.model.enrollmenttypeenum.inperson");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value The value of the enrollment type enumeration.
	 * @param labelKeyParam The label key parameter.
	 */
	private EnrollmentTypeEnum(Integer value, String labelKeyParam)
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
	 * @param value The value of the enrollment type enumeration.
	 * @return The enrollment type enumeration.
	 */
	public static EnrollmentTypeEnum enumForValue(Integer value)
	{
		for (EnrollmentTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values for the enrollment type.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (EnrollmentTypeEnum i : EnrollmentTypeEnum.class.getEnumConstants())
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