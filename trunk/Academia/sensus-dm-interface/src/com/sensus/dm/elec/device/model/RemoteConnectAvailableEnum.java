package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum RemoteConnectAvailableEnum.
 * 
 * @author QAT Global.
 */
public enum RemoteConnectAvailableEnum implements IIntegerEnum
{

	/** The unknown. */
	UNKNOWN(0),

	/** The remote disconnect. */
	REMOTE_DISCONNECT(1),

	/** The not remote disconnect. */
	NOT_REMOTE_DISCONNECT(2);

	/** The remote connect available. */
	private Integer remoteConnectAvailable;

	/**
	 * Instantiates a new remote connect available enum.
	 * 
	 * @param remoteConnectAvailableParam the remote connect available
	 */
	private RemoteConnectAvailableEnum(Integer remoteConnectAvailableParam)
	{
		remoteConnectAvailable = remoteConnectAvailableParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return remoteConnectAvailable;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the remote connect available enum
	 */
	public static RemoteConnectAvailableEnum enumForValue(Integer value)
	{
		for (RemoteConnectAvailableEnum e : values())
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
		RemoteConnectAvailableEnum[] enums = RemoteConnectAvailableEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (RemoteConnectAvailableEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
