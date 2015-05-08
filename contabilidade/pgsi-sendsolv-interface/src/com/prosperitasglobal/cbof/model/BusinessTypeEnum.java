package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum BusinessTypeEnum determines which different business entities can be used in SendSolv
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:07 AM
 */
public enum BusinessTypeEnum implements II18nEnum, IIntegerEnum
{

	/** The organization. */
	ORGANIZATION(1, "com.prosperitasglobal.sendsolv.model.businesstype.organization"),

	/** The location. */
	LOCATION(2, "com.prosperitasglobal.sendsolv.model.businesstype.location"),

	/** The member. */
	MEMBER(3, "com.prosperitasglobal.sendsolv.model.businesstype.member"),

	/** The liaison. */
	LIAISON(4, "com.prosperitasglobal.sendsolv.model.businesstype.liaison"),

	/** The recipient. */
	RECIPIENT(5, "com.prosperitasglobal.sendsolv.model.businesstype.recipient"),

	/** The unknown. */
	UNKNOWN(6, "com.prosperitasglobal.sendsolv.model.businesstype.unknown"),

	/** The money transfer. */
	MONEY_TRANSFER(7, "com.prosperitasglobal.sendsolv.model.businesstype.moneytransfer"),

	/** The money transfer batch. */
	MONEY_TRANSFER_BATCH(8, "com.prosperitasglobal.sendsolv.model.businesstype.moneytransferbatch");

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
	private BusinessTypeEnum(Integer value, String labelKeyParam)
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
	public static BusinessTypeEnum enumForValue(Integer value)
	{
		for (BusinessTypeEnum e : values())
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

		for (BusinessTypeEnum i : BusinessTypeEnum.class.getEnumConstants())
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