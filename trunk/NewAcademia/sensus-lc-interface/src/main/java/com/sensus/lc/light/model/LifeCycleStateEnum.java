package com.sensus.lc.light.model;

import com.sensus.cbof.model.IDeviceLifeCycleState;

public enum LifeCycleStateEnum implements IDeviceLifeCycleState
{
	/**
	 * IMPORTANT TO PRESERVE THESE VALUES, BECAUSE IT HAS PRECEDENCE
	 */

	/** Maintenance Mode. */
	MAINTENANCE(3, "smartpoint.status.MAINTENANCE"),

	/** DEACTIVATED. */
	DEACTIVATED(4, "smartpoint.status.DEACTIVATED"),

	/** ACTIVE. */
	ACTIVE(5, "smartpoint.status.ACTIVE"),

	/** UNKNOWN. */
	UNKNOWN(6, "smartpoint.status.UNKNOWN");

	/** The Status Message Type. */
	private Integer value;

	/** The life cycle state key. */
	private String lifeCycleStateKey;

	/**
	 * Instantiates a new life cycle state enum.
	 * 
	 * @param value the value
	 * @param lifeCycleStateKeyParam the life cycle state key param
	 */
	private LifeCycleStateEnum(Integer value, String lifeCycleStateKeyParam)
	{
		this.value = value;
		lifeCycleStateKey = lifeCycleStateKeyParam;
	}

	/**
	 * Instantiates a new life cycle state enum.
	 * 
	 * @param valueParam the value param
	 */
	private LifeCycleStateEnum(Integer valueParam)
	{
		value = valueParam;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return value;
	}

	/**
	 * Gets the life cycle state key.
	 * 
	 * @return the life cycle state key
	 */
	public String getLifeCycleStateKey()
	{
		return lifeCycleStateKey;
	}

	/**
	 * Sets the life cycle state key.
	 * 
	 * @param lifeCycleStateKey the new life cycle state key
	 */
	public void setLifeCycleStateKey(String lifeCycleStateKey)
	{
		this.lifeCycleStateKey = lifeCycleStateKey;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * 
	 * @return statusMessageTypeEnum
	 */
	public static LifeCycleStateEnum enumForValue(Integer value)
	{
		for (LifeCycleStateEnum e : values())
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
		LifeCycleStateEnum[] enums = LifeCycleStateEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LifeCycleStateEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
