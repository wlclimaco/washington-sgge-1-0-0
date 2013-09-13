package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum RemoteConnectStatusEnum.
 * 
 * @author QAT Global.
 */
public enum RemoteConnectStatusEnum implements IIntegerEnum
{

	/** Unknown Connection Status. */
	UNKNOWN(0),

	/** Endpoint Connected to RNI. */
	CONNECT(1),

	/** Endpoint Disconnected from RNI. */
	DISCONNECT(2),

	/** The relay on the meter is open but the customer has the ability to connect it. */
	ARMED(3);

	/** The remote connect status. */
	private Integer remoteConnectStatus;

	/**
	 * Instantiates a new remote connect status enum.
	 * 
	 * @param remoteConnectStatusParam the remote connect status
	 */
	private RemoteConnectStatusEnum(Integer remoteConnectStatusParam)
	{
		remoteConnectStatus = remoteConnectStatusParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return remoteConnectStatus;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the remote connect status enum
	 */
	public static RemoteConnectStatusEnum enumForValue(Integer value)
	{
		for (RemoteConnectStatusEnum e : values())
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
		RemoteConnectStatusEnum[] enums = RemoteConnectStatusEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (RemoteConnectStatusEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
