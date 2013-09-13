package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum HanLifecycleStateEnum.
 * 
 * @author QAT Global.
 */
public enum HanLifecycleStateEnum implements IIntegerEnum
{

	/** The unknown. */
	UNKNOWN(0),

	/** The joined. */
	JOINED(1),

	/** The unjoined. */
	UNJOINED(2),

	/** The pending join. */
	PENDING_JOIN(3);

	/** The han lifecycle state. */
	private Integer hanLifecycleState;

	/**
	 * Instantiates a new han lifecycle state enum.
	 * 
	 * @param hanLifecycleStateParam the han lifecycle state
	 */
	private HanLifecycleStateEnum(Integer hanLifecycleStateParam)
	{
		hanLifecycleState = hanLifecycleStateParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return hanLifecycleState;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the han lifecycle state enum
	 */
	public static HanLifecycleStateEnum enumForValue(Integer value)
	{
		for (HanLifecycleStateEnum e : values())
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
		HanLifecycleStateEnum[] enums = HanLifecycleStateEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (HanLifecycleStateEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
