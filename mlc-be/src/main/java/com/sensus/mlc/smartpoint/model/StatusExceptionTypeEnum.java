package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum StatusExceptionTypeEnum.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public enum StatusExceptionTypeEnum implements IIntegerEnum
{

	/** The LAMP_FAILURE. */
	LAMP_FAILURE(1, LightStatusEnum.ALARM),

	/** The POWER_FAILURE. */
	POWER_FAILURE(2, LightStatusEnum.ALARM),

	/** The BOARD FAILURE. */
	BOARD_FAILURE(3, LightStatusEnum.ALARM),

	/** The METROLOGY ERROR. */
	METROLOGY_ERROR(4, LightStatusEnum.ALARM),

	/** The METROLOGY COM FAILURE. */
	METROLOGY_COM_FAILURE(5, LightStatusEnum.ALARM),

	/** The POWER_SURGE_DETECTED. */
	POWER_SURGE_DETECTED(6, LightStatusEnum.WARNING),

	/** The BROWN_OUT_DETECTED. */
	BROWN_OUT_DETECTED(7, LightStatusEnum.WARNING),

	/** The COMMUNICATION_FAIL. */
	COMMUNICATION_FAIL(8, LightStatusEnum.WARNING),

	/** The HIGH CURRENT. */
	HIGH_CURRENT(9, LightStatusEnum.WARNING),

	/** The LOW CURRENT. */
	LOW_CURRENT(10, LightStatusEnum.WARNING),

	/** The REVERSE ENERGY. */
	REVERSE_ENERGY(11, LightStatusEnum.WARNING),

	/** The METROLOGY RESET. */
	METROLOGY_RESET(12, LightStatusEnum.WARNING);

	/** The status message type. */
	private Integer statusMessageType;

	/** The light status enum. */
	private LightStatusEnum lightStatusEnum;

	/**
	 * Instantiates a new status exception type enum.
	 * 
	 * @param statusMessageTypeInd the status message type ind
	 */
	private StatusExceptionTypeEnum(Integer statusMessageTypeInd, LightStatusEnum lightStatus)
	{
		statusMessageType = statusMessageTypeInd;
		lightStatusEnum = lightStatus;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return statusMessageType;
	}

	/**
	 * Gets the value string.
	 * 
	 * @return the value string
	 */
	public String getValueString()
	{
		return String.valueOf(getValue());
	}

	/**
	 * Gets the light status enum.
	 * 
	 * @return the light status enum
	 */
	public LightStatusEnum getLightStatusEnum()
	{
		return lightStatusEnum;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the status exception type enum
	 */
	public static StatusExceptionTypeEnum enumForValue(Integer value)
	{
		for (StatusExceptionTypeEnum e : values())
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
		StatusExceptionTypeEnum[] enums = StatusExceptionTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (StatusExceptionTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
