package com.sensus.lc.light.model;

import com.sensus.cbof.model.IDeviceLifeCycleState;

public enum LightStateEnum implements IDeviceLifeCycleState
{
	UNKNOWN(-1),
	ON(1),
	OFF(2),
	DIM(3),
	BLINK(4),
	MAINTENANCE(5);

	private Integer lightStateId;

	private LightStateEnum(Integer id)
	{
		lightStateId = id;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return lightStateId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the light state type enum
	 */
	public static LightStateEnum enumForValue(Integer value)
	{
		for (LightStateEnum type : values())
		{
			if (type.getValue().equals(value))
			{
				return type;
			}
		}

		return null;
	}

}
