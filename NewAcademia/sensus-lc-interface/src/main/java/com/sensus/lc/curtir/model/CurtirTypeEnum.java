package com.sensus.lc.curtir.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum EcoModeFilterEnum.
 */
public enum CurtirTypeEnum implements IIntegerEnum
{

	/** The economy. */
	ACADEMIA(1),

	/** The value. */
	GRUPOMUSCULAR(2),

	/** The security. */
	EXERCICIO(3);

	/** The data type. */
	private Integer dataType;

	/**
	 * Instantiates a new data type enum.
	 * 
	 * @param dataTypeInd the data type ind
	 */
	private CurtirTypeEnum(Integer dataTypeInd)
	{
		dataType = dataTypeInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{

		return dataType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the data type enum
	 */
	public static CurtirTypeEnum enumForValue(Integer value)
	{
		for (CurtirTypeEnum e : values())
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
		CurtirTypeEnum[] enums = CurtirTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (CurtirTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
