package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum MeterEncryptionStatus.
 * 
 * @author - QAT Brazil
 */
public enum MeterEncryptionStatus implements IStringEnum
{

	/** Encrypted. */
	ENCRYPTED("Enabled"),

	/** NotEncrypted. */
	NOTENCRYPTED("Disabled"),

	/** Unknown Encryption. */
	UNKNOWN("Unknown");

	/** The Encryption Status Message Type. */
	private String statusMessageType;

	/**
	 * Constructor.
	 * 
	 * @param statusMessageTypeInd
	 */
	private MeterEncryptionStatus(String statusMessageTypeInd)
	{
		statusMessageType = statusMessageTypeInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
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
	public static MeterEncryptionStatus enumForValue(String value)
	{
		for (MeterEncryptionStatus e : values())
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
		MeterEncryptionStatus[] enums = MeterEncryptionStatus.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (MeterEncryptionStatus i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
