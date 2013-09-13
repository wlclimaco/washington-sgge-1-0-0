package com.sensus.dm.common.action.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum DMActionResultEnum.
 * 
 * @author QAT Global
 */
public enum DMActionResultEnum implements IStringEnum
{

	/** The INITIALIZED. */
	INITIALIZED("Initialized"),

	/** The FAILURE. */
	FAILURE("Failure"),

	/** The TIMED_OUT. */
	NC_TIMED_OUT("NC Timed out"),

	/** The NOT_INITIALIZED. */
	NOT_INITIALIZED("Not Initialized");

	/** The demand reset result. */
	private String demandResetResult;

	/**
	 * Instantiates a new demand reset result enum.
	 * 
	 * @param result the meter life cycle status ind
	 */
	private DMActionResultEnum(String result)
	{
		demandResetResult = result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return demandResetResult;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the demand reset result enum
	 */
	public static DMActionResultEnum enumForValue(String value)
	{
		for (DMActionResultEnum e : values())
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
		DMActionResultEnum[] enums = DMActionResultEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DMActionResultEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
