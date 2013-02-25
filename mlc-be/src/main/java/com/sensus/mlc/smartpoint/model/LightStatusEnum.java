package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LightStatusEnum (status table).
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public enum LightStatusEnum implements IIntegerEnum
{
	/** Override. */
	OVERRIDE(-1),

	/** Maintenance Mode. */
	MAINTENANCE(0),

	/** ALARM. */
	ALARM(1),

	/** WARNING. */
	WARNING(2),

	/** ACTIVE. */
	ACTIVE(3),

	/** DEACTIVATED. */
	DEACTIVATED(4),

	/** UNKNOWN. */
	UNKNOWN(5);

	/** The Status Message Type. */
	private Integer statusMessageType;

	/**
	 * Instantiates a new statusMessageTypeEnum ind enum.
	 * 
	 * @param statusMessageTypeInd the status message type ind
	 */
	private LightStatusEnum(Integer statusMessageTypeInd)
	{
		statusMessageType = statusMessageTypeInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return statusMessageType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * 
	 * @return statusMessageTypeEnum
	 */
	public static LightStatusEnum enumForValue(Integer value)
	{
		for (LightStatusEnum e : values())
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
		LightStatusEnum[] enums = LightStatusEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LightStatusEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
