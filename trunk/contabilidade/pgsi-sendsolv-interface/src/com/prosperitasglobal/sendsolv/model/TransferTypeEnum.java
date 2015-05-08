package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum TransferTypeEnum.
 */
public enum TransferTypeEnum implements IIntegerEnum, II18nEnum
{
	/** The recurring. */
	RECURRING(1, "com.prosperitasglobal.sendsolv.model.transfertypeEnum.recurring"),

	/** The one time. */
	ONE_TIME(2, "com.prosperitasglobal.sendsolv.model.transfertypeEnum.onetime");

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
	private TransferTypeEnum(Integer value, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the transfer type enum
	 */
	public static TransferTypeEnum enumForValue(Integer value)
	{
		for (TransferTypeEnum e : values())
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

		for (TransferTypeEnum i : TransferTypeEnum.class.getEnumConstants())
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

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return code;
	}
}
