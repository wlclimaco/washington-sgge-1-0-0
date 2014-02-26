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

	/** The list type enum id. */
	private Integer listTypeEnumId;

	/**
	 * Instantiates a new list type enum.
	 * 
	 * @param listTypeEnumIdParam the list type enum id param
	 */
	private CurtirTypeEnum(Integer listTypeEnumIdParam)
	{
		listTypeEnumId = listTypeEnumIdParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return listTypeEnumId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the list type enum
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
