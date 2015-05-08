package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

// TODO: Auto-generated Javadoc
/**
 * The Enum LiaisonTypeEnum distinguishes between the different types of {@link Liaison} that can be referenced in
 * SendSolv.
 *
 * @author abarros
 * @version 1.0
 * @created 05-Sep-2014 11:18:15 AM
 */
public enum LiaisonTypeEnum implements IIntegerEnum, II18nEnum
{

	/** The accounting. */
	ACCOUNTING(1, "com.prosperitasglobal.sendsolv.model.liaisontypeenum.accounting"),

	/** The payroll. */
	PAYROLL(2, "com.prosperitasglobal.sendsolv.model.liaisontypeenum.payroll"),

	/** The human resources. */
	HUMAN_RESOURCES(3, "com.prosperitasglobal.sendsolv.model.liaisontypeenum.humanresource"),

	/** The executive. */
	EXECUTIVE(4, "com.prosperitasglobal.sendsolv.model.liaisontypeenum.executive"),

	/** The other. */
	OTHER(5, "com.prosperitasglobal.sendsolv.model.liaisontypeenum.other");

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
	private LiaisonTypeEnum(Integer value, String labelKeyParam)
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
	public static LiaisonTypeEnum enumForValue(Integer value)
	{
		for (LiaisonTypeEnum e : values())
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

		for (LiaisonTypeEnum i : LiaisonTypeEnum.class.getEnumConstants())
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