package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum FilingStatus. Carries information about status of documents
 *
 * @author abarros
 * @version 1.0
 * @created 03-Sep-2014 10:00:04 AM
 */
public enum FilingStatusEnum implements II18nEnum, IIntegerEnum
{

	/** The yes. */
	FILED(1, "com.prosperitasglobal.cbof.model.filingstatusenum.filed"),

	/** The no. */
	NOT_FILED(2, "com.prosperitasglobal.cbof.model.filingstatusenum.notfiled"),

	/** The unknown. */
	UNKNOWN(3, "com.prosperitasglobal.cbof.model.filingstatusenum.unknown");

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
	private FilingStatusEnum(Integer value, String labelKeyParam)
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
	public static FilingStatusEnum enumForValue(Integer value)
	{
		for (FilingStatusEnum e : values())
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

		for (FilingStatusEnum i : FilingStatusEnum.class.getEnumConstants())
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