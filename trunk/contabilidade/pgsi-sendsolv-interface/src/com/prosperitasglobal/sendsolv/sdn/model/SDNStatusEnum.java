package com.prosperitasglobal.sendsolv.sdn.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum SDNStatusEnum.
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:07 AM
 */
public enum SDNStatusEnum implements IIntegerEnum, II18nEnum
{

	/** The positive when a match is found and determined by investigation. */
	POSITIVE(1, "sendsolv.base.model.sdnstatustypeenum.positive"),

	/** The false positive when a match is found but deemed to be not a match. */
	FALSE_POSITIVE(2, "sendsolv.base.model.sdnstatustypeenum.falsepositive"),

	/** The neutral when not match is found. */
	NEUTRAL(3, "sendsolv.base.model.sdnstatustypeenum.neutral"),

	/** The pending PGSi investigation when an initial match is found. */
	PENDING_INVESTIGATION(4, "sendsolv.base.model.sdnstatustypeenum.pendinginvestigation"),

	/** The unknown. */
	UNKNOWN(5, "sendsolv.base.model.sdnstatustypeenum.unknown"),

	/** The pending neutral when an SDN match was found previously but not currently. */
	PENDING_NEUTRAL(6, "sendsolv.base.model.sdnstatustypeenum.pendingneutral"),

	/** The pending investigation by the federal government if PGSi determines it is a match. */
	PENDING_FEDERAL_INVESTIGATION(7, "sendsolv.base.model.sdnstatustypeenum.pendingfederalinvestigation");

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
	private SDNStatusEnum(Integer value, String labelKeyParam)
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
	public static SDNStatusEnum enumForValue(Integer value)
	{
		for (SDNStatusEnum e : values())
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

		for (SDNStatusEnum i : SDNStatusEnum.class.getEnumConstants())
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