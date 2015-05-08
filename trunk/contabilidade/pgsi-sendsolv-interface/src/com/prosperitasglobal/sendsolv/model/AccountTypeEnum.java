package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * Enumeration specifying the values defined for an account type in the SendSolv system.
 */
public enum AccountTypeEnum implements IIntegerEnum, II18nEnum
{
	/** Savings. */
	SAVINGS(1, "com.prosperitasglobal.sendsolv.model.accounttypeenum.savings"),

	/** Checking. */
	CHECKING(2, "com.prosperitasglobal.sendsolv.model.accounttypeenum.checking"),

	/** Account number. */
	ACCOUNT_NUMBER(3, "com.prosperitasglobal.sendsolv.model.accounttypeenum.accountnumber"),

	/** Cuenta Clabe. */
	CUENTA_CLABE(4, "com.prosperitasglobal.sendsolv.model.accounttypeenum.cuentaclabe"),

	/** Card number. */
	CARD_NUMBER(5, "com.prosperitasglobal.sendsolv.model.accounttypeenum.cardnumber"),

	/** Interest Checking. */
	INTEREST_CHECKING(6, "com.prosperitasglobal.sendsolv.model.accounttypeenum.interestchecking"),

	/** Money market. */
	MONEY_MARKET(7, "com.prosperitasglobal.sendsolv.model.accounttypeenum.moneymarket"),

	/** Mobile phone number. */
	MOBILE_PHONE_NUMBER(8, "com.prosperitasglobal.sendsolv.model.accounttypeenum.mobilephonenumber");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value The value of the account type enumeration.
	 * @param labelKeyParam The label key parameter.
	 */
	private AccountTypeEnum(Integer value, String labelKeyParam)
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
	 * @param value The value of the account type enumeration.
	 * @return The account type enumeration.
	 */
	public static AccountTypeEnum enumForValue(Integer value)
	{
		for (AccountTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values for the account type.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (AccountTypeEnum i : AccountTypeEnum.class.getEnumConstants())
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