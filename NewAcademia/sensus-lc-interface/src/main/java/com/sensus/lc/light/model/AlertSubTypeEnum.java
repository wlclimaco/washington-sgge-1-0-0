package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum AlertSubTypeEnum.
 * 
 * @author - Gustavo Peres - QAT Brazil
 */
public enum AlertSubTypeEnum implements IIntegerEnum
{

	/** The lamp failure. */
	LAMP_FAILURE(1, AlertTypeEnum.ALARM, "smartpoint.status.LAMP_FAILURE"),

	/** The power failure. */
	POWER_FAILURE(2, AlertTypeEnum.ALARM, "smartpoint.status.POWER_FAILURE"),

	/** The board failure. */
	BOARD_FAILURE(3, AlertTypeEnum.ALARM, "smartpoint.status.BOARD_FAILURE"),

	/** The metrology error. */
	METROLOGY_ERROR(4, AlertTypeEnum.ALARM, "smartpoint.status.METROLOGY_ERROR"),

	/** The metrology com failure. */
	METROLOGY_COM_FAILURE(5, AlertTypeEnum.ALARM, "smartpoint.status.METROLOGY_COM_FAILURE"),

	/** The power surge detected. */
	POWER_SURGE_DETECTED(6, AlertTypeEnum.WARNING, "smartpoint.status.POWER_SURGE_DETECTED"),

	/** The brownout detected. */
	BROWN_OUT_DETECTED(7, AlertTypeEnum.WARNING, "smartpoint.status.BROWN_OUT_DETECTED"),

	/** The communication fail. */
	COMMUNICATION_FAIL(8, AlertTypeEnum.WARNING, "smartpoint.status.COMMUNICATION_FAIL"),

	/** The high current. */
	HIGH_CURRENT(9, AlertTypeEnum.WARNING, "smartpoint.status.HIGH_CURRENT"),

	/** The low current. */
	LOW_CURRENT(10, AlertTypeEnum.WARNING, "smartpoint.status.LOW_CURRENT"),

	/** The reverse energy. */
	REVERSE_ENERGY(11, AlertTypeEnum.WARNING, "smartpoint.status.REVERSE_ENERGY"),

	/** The metrology reset. */
	METROLOGY_RESET(12, AlertTypeEnum.WARNING, "smartpoint.status.METROLOGY_RESET");

	/** The alert type. */
	private AlertTypeEnum alertType;

	/** The alert sub type. */
	private Integer alertSubType;

	/** The alert subt type key. */
	private String alertSubtTypeKey;

	/**
	 * Instantiates a new alert sub type enum.
	 * 
	 * @param alertSubTypeParam the alert sub type param
	 * @param alertTypeParam the alert type param
	 * @param alertSubtTypeKeyParam the alert subt type key param
	 */
	private AlertSubTypeEnum(Integer alertSubTypeParam, AlertTypeEnum alertTypeParam, String alertSubtTypeKeyParam)
	{
		alertSubType = alertSubTypeParam;
		alertType = alertTypeParam;
		alertSubtTypeKey = alertSubtTypeKeyParam;
	}

	/**
	 * Instantiates a new alert sub type enum.
	 * 
	 * @param alertSubTypeParam the alert sub type param
	 */
	private AlertSubTypeEnum(Integer alertSubTypeParam, AlertTypeEnum alertTypeParam)
	{
		alertSubType = alertSubTypeParam;
		alertType = alertTypeParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return alertSubType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the alert sub type enum
	 */
	public static AlertSubTypeEnum enumForValue(Integer value)
	{
		for (AlertSubTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the alert type.
	 * 
	 * @return the alert type
	 */
	public AlertTypeEnum getAlertType()
	{
		return alertType;
	}

	/**
	 * Sets the alert type.
	 * 
	 * @param alertType the new alert type
	 */
	public void setAlertType(AlertTypeEnum alertType)
	{
		this.alertType = alertType;
	}

	/**
	 * Gets the alert sub type.
	 * 
	 * @return the alert sub type
	 */
	public Integer getAlertSubType()
	{
		return alertSubType;
	}

	/**
	 * Sets the alert sub type.
	 * 
	 * @param alertSubType the new alert sub type
	 */
	public void setAlertSubType(Integer alertSubType)
	{
		this.alertSubType = alertSubType;
	}

	/**
	 * Gets the alert subt type key.
	 * 
	 * @return the alert subt type key
	 */
	public String getAlertSubtTypeKey()
	{
		return alertSubtTypeKey;
	}

	/**
	 * Sets the alert subt type key.
	 * 
	 * @param alertSubtTypeKey the new alert subt type key
	 */
	public void setAlertSubtTypeKey(String alertSubtTypeKey)
	{
		this.alertSubtTypeKey = alertSubtTypeKey;
	}

	/**
	 * Gets the valid values.
	 * 
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		AlertSubTypeEnum[] enums = AlertSubTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (AlertSubTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
