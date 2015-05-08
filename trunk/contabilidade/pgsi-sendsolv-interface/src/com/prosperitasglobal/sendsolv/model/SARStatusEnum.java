package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum SARStatusEnum.
 *
 * @version 1.0
 * @created 11-Feb-2015 10:00:07 AM
 */
public enum SARStatusEnum implements IIntegerEnum, II18nEnum
{

	/** The pgsi_review is the initial status when someone creates the incident. */
	PGSI_REVIEW(1, "com.prosperitasglobal.sendsolv.model.sarstatusenum.pgsireview"),

	/** The filing status is set when the compliance officer decides to file a report. */
	FILING(2, "com.prosperitasglobal.sendsolv.model.sarstatusenum.filing"),

	/** The filed status is set when the compliance officer has filed the report with the feds. */
	FILED(3, "com.prosperitasglobal.sendsolv.model.sarstatusenum.filed"),

	/** The closed status is set when the compliance officer decides not to file a report. */
	CLOSED(4, "com.prosperitasglobal.sendsolv.model.sarstatusenum.closed");

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
	private SARStatusEnum(Integer value, String labelKeyParam)
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
	 * @return the SAR status enum
	 */
	public static SARStatusEnum enumForValue(Integer value)
	{
		for (SARStatusEnum e : values())
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

		for (SARStatusEnum i : SARStatusEnum.class.getEnumConstants())
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