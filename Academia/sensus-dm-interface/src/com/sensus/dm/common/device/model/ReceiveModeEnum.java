package com.sensus.dm.common.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ReceiveModeEnum.
 *
 * @author QAT Global.
 */
public enum ReceiveModeEnum implements IIntegerEnum
{

	/** Receive mode: 0 5 kbps, Buddy Enabled. */
	BUDDY_ENABLED_5_KBPS_0(0),

	/** Receive mode: 1 5 kbps, Buddy Enabled. */
	BUDDY_ENABLED_5_KBPS_1(1),

	/** Receive mode: 2 5 kbps, Buddy Enabled. */
	BUDDY_ENABLED_5_KBPS_2(2),

	/** Receive mode: 3 5 kbps, Buddy Disabled. */
	BUDDY_DISABLED_5_KBPS_3(3),

	/** Receive mode: 4 10 kbps, Buddy Enabled. */
	BUDDY_ENABLED_10_KBPS_4(4),

	/** Receive mode: 5 10 kbps, Buddy Enabled. */
	BUDDY_ENABLED_10_KBPS_5(5),

	/** Receive mode: 6 10 kbps, Buddy Enabled. */
	BUDDY_ENABLED_10_KBPS_6(6),

	/** Receive mode: 7 10 kbps, Buddy Disabled. */
	BUDDY_DISABLED_10_KBPS_7(7);

	/** The receive mode. */
	private Integer receiveMode;

	/**
	 * Instantiates a new receive mode enum.
	 *
	 * @param receiveModeParam the receive mode param
	 */
	private ReceiveModeEnum(Integer receiveModeParam)
	{
		receiveMode = receiveModeParam;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	public Integer getValue()
	{
		return receiveMode;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the receive mode enum
	 */
	public static ReceiveModeEnum enumForValue(Integer value)
	{
		for (ReceiveModeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}
}
