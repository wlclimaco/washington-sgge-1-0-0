package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum GetDataFromLightEnum.
 */
public enum GetDataFromLightEnum implements IIntegerEnum
{

	/** The all. */
	ALL(1),

	/** The alarm only. */
	ALARM_ONLY(2),

	/** The reading. */
	READING(3);

	/** The Get data from light. */
	private Integer getDataFromLight;

	/**
	 * Instantiates a new gets the data from light enum.
	 *
	 * @param getDataFromLightParam the get data from light param
	 */
	private GetDataFromLightEnum(Integer getDataFromLightParam)
	{
		getDataFromLight = getDataFromLightParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{

		return getDataFromLight;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the gets the data from light enum
	 */
	public static GetDataFromLightEnum enumForValue(Integer value)
	{
		for (GetDataFromLightEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

}
