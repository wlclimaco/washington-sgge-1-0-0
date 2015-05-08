package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * Enumeration specifying the values defined for a payer type in the SendSolv system.
 */
public enum PayerTypeEnum implements IIntegerEnum, II18nEnum
{
	/** Retail payer. */
	RETAIL(1, "com.prosperitasglobal.sendsolv.model.payertypeenum.retail"),

	/** Financial payer. */
	FINANCIAL(2, "com.prosperitasglobal.sendsolv.model.payertypeenum.financial");

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
	private PayerTypeEnum(Integer value, String labelKeyParam)
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
	 * @param value The value of the payer type enumeration.
	 * @return The payer type enumeration.
	 */
	public static PayerTypeEnum enumForValue(Integer value)
	{
		for (PayerTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values for the payer type's.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (PayerTypeEnum i : PayerTypeEnum.class.getEnumConstants())
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
