package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

// TODO: Auto-generated Javadoc
/**
 * Enumeration specifying the values defined for a payment type in the SendSolv system.
 */
public enum PaymentTypeEnum implements IIntegerEnum, II18nEnum
{
	/** Cash payment. */
	CASH(1, "com.prosperitasglobal.sendsolv.model.paymenttypeenum.cash"),

	/** A deposit in the account. */
	IN_ACCOUNT(2, "com.prosperitasglobal.sendsolv.model.paymenttypeenum.inaccount"),

	/** Cash to agent payment. */
	CASH_TO_AGENT(3, "com.prosperitasglobal.sendsolv.model.paymenttypeenum.cashtoagent"),

	/** A home delivery payment. */
	HOME_DELIVERY(4, "com.prosperitasglobal.sendsolv.model.paymenttypeenum.homedelivery"),

	/** A create account. */
	CREATE_ACCOUNT(5, "com.prosperitasglobal.sendsolv.model.paymenttypeenum.createaccount"),

	/** A create account. */
	RECHARGE_ACCOUNT(6, "com.prosperitasglobal.sendsolv.model.paymenttypeenum.rechargeaccount");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value The value of the payment type enumeration.
	 * @param labelKeyParam The label key parameter.
	 */
	private PaymentTypeEnum(Integer value, String labelKeyParam)
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
	 * @param value The value of the payment type enumeration.
	 * @return The payment type enumeration.
	 */
	public static PaymentTypeEnum enumForValue(Integer value)
	{
		for (PaymentTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Sets the enum for value.
	 *
	 * @param value the enum for value
	 * @return the payment type enum
	 */
	public static PaymentTypeEnum setEnumForValue(Integer value)
	{
		return enumForValue(value);
	}

	/**
	 * Gets the valid values for the payment type's.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (PaymentTypeEnum i : PaymentTypeEnum.class.getEnumConstants())
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
