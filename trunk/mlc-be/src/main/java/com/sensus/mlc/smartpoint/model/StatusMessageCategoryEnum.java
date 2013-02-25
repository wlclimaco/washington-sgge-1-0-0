package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum StatusMessageCategoryEnum - the different messages RNI can send.
 * 
 * @author - Alex Barros - QAT Omaha
 */
public enum StatusMessageCategoryEnum implements IIntegerEnum
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

	/** The Status Message Type. */
	private Integer statusMessageType;

	/**
	 * Instantiates a new statusMessageTypeEnum ind enum.
	 * 
	 * @param statusMessageTypeInd the status message type ind
	 */
	private StatusMessageCategoryEnum(Integer statusMessageTypeInd)
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
	public static StatusMessageCategoryEnum enumForValue(Integer value)
	{
		for (StatusMessageCategoryEnum e : values())
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
		StatusMessageCategoryEnum[] enums = StatusMessageCategoryEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (StatusMessageCategoryEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
