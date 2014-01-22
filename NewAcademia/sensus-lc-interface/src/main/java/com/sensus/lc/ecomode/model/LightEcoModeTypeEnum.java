package com.sensus.lc.ecomode.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LightTypeEnum.
 */
public enum LightEcoModeTypeEnum implements IIntegerEnum
{

	/** The hps. */
	HPS(1),

	/** The lps. */
	LPS(2),

	/** The mh. */
	MH(3),

	/** The mv. */
	MV(4);

	/** The light type id. */
	private Integer lightTypeId;

	/**
	 * Instantiates a new light type enum.
	 * 
	 * @param lightTypeIdValue the light type id value
	 */
	private LightEcoModeTypeEnum(Integer lightTypeIdValue)
	{
		lightTypeId = lightTypeIdValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return lightTypeId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the light type enum
	 */
	public static LightEcoModeTypeEnum enumForValue(Integer value)
	{
		for (LightEcoModeTypeEnum type : values())
		{
			if (type.getValue().equals(value))
			{
				return type;
			}
		}
		return null;
	}
}
