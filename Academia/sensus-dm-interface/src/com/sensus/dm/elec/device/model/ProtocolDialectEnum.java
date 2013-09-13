package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ProtocolDialectEnum.
 * 
 * @author QAT Brazil.
 */
public enum ProtocolDialectEnum implements IIntegerEnum
{

	/** The FLEXNET. */
	FLEXNET(1),

	/** The ZIGBEE. */
	ZIGBEE(2),

	/** The ZWAVE. */
	ZWAVE(3);

	/** The protocol dialect. */
	private Integer protocolDialect;

	/**
	 * Instantiates a new protocol dialect enum.
	 * 
	 * @param protocolDialectInd the protocol dialect ind
	 */
	private ProtocolDialectEnum(Integer protocolDialectInd)
	{
		protocolDialect = protocolDialectInd;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	public Integer getValue()
	{
		return protocolDialect;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the protocol dialect enum
	 */
	public static ProtocolDialectEnum enumForValue(Integer value)
	{
		for (ProtocolDialectEnum e : values())
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
		ProtocolDialectEnum[] enums = ProtocolDialectEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ProtocolDialectEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
