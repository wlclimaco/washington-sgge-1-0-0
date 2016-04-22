package com.qat.samples.sysmgmt.util.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum StatusEnum.
 * 
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:08 AM
 */
public enum StatusEnum implements IIntegerEnum, II18nEnum
{

	/** The active. */
	ACTIVE(1, "com.prosperitasglobal.sendsolv.model.statusenum.active"),

	/** The inactive. */
	INACTIVE(2, "com.prosperitasglobal.sendsolv.model.statusenum.inactive"),

	/** The setup. */
	APPLY(3, "com.prosperitasglobal.sendsolv.model.statusenum.setup");

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
	private StatusEnum(Integer value, String labelKeyParam)
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
	public static StatusEnum enumForValue(Integer value)
	{
		for (StatusEnum e : values())
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

		for (StatusEnum i : StatusEnum.class.getEnumConstants())
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