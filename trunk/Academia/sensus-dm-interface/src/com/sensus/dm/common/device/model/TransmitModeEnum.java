package com.sensus.dm.common.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum TransmitModeEnum.
 *
 * @author QAT Global.
 */
public enum TransmitModeEnum implements IIntegerEnum
{

	/** Transmit Mode: Normal. */
	NORMAL(0),

	/** Transmit Mode: Full mPass. */
	FULL_MPASS(1),

	/** Transmit Mode: Full Boost. */
	FULL_BOOST(2),

	/** Transmit Mode: Normal, Half Baud Rate. */
	NORMAL_HALF_BAUD_RATE(3),

	/** Transmit Mode: mPass/Normal (1:1). */
	MPASS_NORMAL_1BY1(4),

	/** Transmit Mode: mPass/Normal (1:2). */
	MPASS_NORMAL_1BY2(5),

	/** Transmit Mode: mPass/Normal (1:3). */
	MPASS_NORMAL_1BY3(6),

	/** Transmit Mode: mPass/Normal (1:4). */
	MPASS_NORMAL_1BY4(7),

	/** Transmit Mode: mPass/Normal (1:5). */
	MPASS_NORMAL_1BY5(8),

	/** Transmit Mode: mPass/Normal (1:8). */
	MPASS_NORMAL_1BY8(9),

	/** Transmit Mode: mPass/Normal (1:16). */
	MPASS_NORMAL_1BY16(10),

	/** Transmit Mode: Boost / Normal Mix (1:1). */
	BOOST__NORMAL_MIX_1BY1(11),

	/** Transmit Mode: Boost / Normal Mix (1:2). */
	BOOST__NORMAL_MIX_1BY2(12),

	/** Transmit Mode: Boost / Normal Mix (1:4). */
	BOOST__NORMAL_MIX_1BY4(13),

	/** Transmit Mode: Boost / Normal Mix (1:8). */
	BOOST__NORMAL_MIX_1BY8(14),

	/** Transmit Mode: Boost/Buddy/Normal (1:1:3). */
	BOOST_BUDDY_NORMAL_1BY1BY3(15);

	/** The transmit mode. */
	private Integer transmitMode;

	/**
	 * Instantiates a new transmit mode enum.
	 *
	 * @param transmitModeParam the transmit mode param
	 */
	private TransmitModeEnum(Integer transmitModeParam)
	{
		transmitMode = transmitModeParam;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return transmitMode;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the transmit mode enum
	 */
	public static TransmitModeEnum enumForValue(Integer value)
	{
		for (TransmitModeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}
}
