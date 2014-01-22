package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum NotificationTypeEnum.
 * 
 * @author - Gustavo Peres - QAT Brazil
 */
public enum NotificationTypeEnum implements IIntegerEnum
{
	/** The SETUP. */
	SETUP(0),

	/** The ALARM. */
	ALARM(1),

	/** The UNSOLICITED_STATUS. */
	UNSOLICITED_STATUS(2),

	/** The FORCED_STATUS. */
	FORCED_STATUS(3),

	/** The BINDING. */
	BINDING(4),

	/** The SET_LIGHT_INTENSITY. */
	SET_LIGHT_INTENSITY(5),

	/** The AD d_ ligh t_ t o_ schedule. */
	ADD_LIGHT_TO_SCHEDULE(6),

	/** The DE l_ ligh t_ fro m_ schedule. */
	DEL_LIGHT_FROM_SCHEDULE(7),

	/** The UP d_ ligh t_ property. */
	UPD_LIGHT_PROPERTY(8),

	/** The CLEA r_ alarm. */
	CLEAR_ALARM(9),

	/** The CHANNE l_ setu p_ audit. */
	CHANNEL_SETUP_AUDIT(10),

	/** The edit status. */
	EDIT_STATUS(11),

	/** The SET_LIGHT_BLINK. */
	SET_LIGHT_BLINK(12);

	/** The notification type. */
	private Integer notificationType;

	/**
	 * Instantiates a new notification type enum.
	 * 
	 * @param notificationTypeParam the notification type param
	 */
	private NotificationTypeEnum(Integer notificationTypeParam)
	{
		notificationType = notificationTypeParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return notificationType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the notification type enum
	 */
	public static NotificationTypeEnum enumForValue(Integer value)
	{
		for (NotificationTypeEnum e : values())
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
		NotificationTypeEnum[] enums = NotificationTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (NotificationTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
