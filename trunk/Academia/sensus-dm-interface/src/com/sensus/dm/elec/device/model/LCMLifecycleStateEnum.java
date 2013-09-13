package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LCMLifecycleStateEnum.
 * 
 * @author QAT Global.
 */
public enum LCMLifecycleStateEnum implements IIntegerEnum
{

	/** The unknown. */
	UNKNOWN(0),

	/** The joined. */
	JOINED(1),

	/** The unjoined. */
	UNJOINED(2),

	/** The pending join. */
	PENDING_JOIN(3),

	/** The installed. */
	INSTALLED(4);

	/** The state. */
	private Integer state;

	/**
	 * Instantiates a new lCM life cycle state enum.
	 * 
	 * @param stateParam the state
	 */
	private LCMLifecycleStateEnum(Integer stateParam)
	{
		state = stateParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return state;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the lCM life cycle state enum
	 */
	public static LCMLifecycleStateEnum enumForValue(Integer value)
	{
		for (LCMLifecycleStateEnum e : values())
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
		LCMLifecycleStateEnum[] enums = LCMLifecycleStateEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LCMLifecycleStateEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
