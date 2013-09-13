package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LCMTypeEnum.
 * 
 * @author QAT Global.
 */
public enum LCMTypeEnum implements IIntegerEnum
{

	/** The lcm. */
	LCM(1),

	/** The flexnet lcm. */
	FLEXNET_LCM(2);

	/** The lcm type. */
	private Integer lcmType;

	/**
	 * Instantiates a new lCM type enum.
	 * 
	 * @param lcmTypeParam the lcm type
	 */
	private LCMTypeEnum(Integer lcmTypeParam)
	{
		lcmType = lcmTypeParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return lcmType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the lCM type enum
	 */
	public static LCMTypeEnum enumForValue(Integer value)
	{
		for (LCMTypeEnum e : values())
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
		LCMTypeEnum[] enums = LCMTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LCMTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
