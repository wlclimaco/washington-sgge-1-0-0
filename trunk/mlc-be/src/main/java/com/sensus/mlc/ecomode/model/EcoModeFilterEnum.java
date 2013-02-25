package com.sensus.mlc.ecomode.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum EcoModeFilterEnum.
 */
public enum EcoModeFilterEnum implements IStringEnum
{

	/** The economy. */
	ECONOMY(50, 100),

	/** The value. */
	VALUE(15, 50),

	/** The security. */
	SECURITY(0, 15);

	/** The initial value. */
	private Integer initialValue;

	/** The end value. */
	private Integer endValue;

	/**
	 * Instantiates a new eco mode filter enum.
	 * 
	 * @param initialValueParam the initial value param
	 * @param endValueParam the end value param
	 */
	private EcoModeFilterEnum(Integer initialValueParam, Integer endValueParam)
	{
		initialValue = initialValueParam;
		endValue = endValueParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return joinValues();
	}

	/**
	 * Gets the initial value.
	 * 
	 * @return the initial value
	 */
	public Integer getInitialValue()
	{
		return initialValue;
	}

	/**
	 * Gets the end value.
	 * 
	 * @return the end value
	 */
	public Integer getEndValue()
	{
		return endValue;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the eco mode filter enum
	 */
	public static EcoModeFilterEnum enumForValue(String value)
	{
		if (value == null)
		{
			return null;
		}

		for (EcoModeFilterEnum type : values())
		{
			if (type.joinValues().equals(value))
			{
				return type;
			}
		}
		return null;
	}

	/**
	 * Join values.
	 * 
	 * @return the string
	 */
	private String joinValues()
	{
		return initialValue + "_" + endValue;
	}

}
