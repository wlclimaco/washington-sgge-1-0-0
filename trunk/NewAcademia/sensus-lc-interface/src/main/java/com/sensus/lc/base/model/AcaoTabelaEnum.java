package com.sensus.lc.base.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum EcoModeFilterEnum.
 */
public enum AcaoTabelaEnum implements IIntegerEnum
{

	/** The economy. */
	INSERT_ACADEMIA(1),

	/** The value. */
	INSERT_GRUPO_MUSCULAR(2),

	/** The security. */
	INSERT_EXERCICIO(3);

	/** The list type enum id. */
	private Integer listTypeEnumId;

	/**
	 * Instantiates a new list type enum.
	 * 
	 * @param listTypeEnumIdParam the list type enum id param
	 */
	private AcaoTabelaEnum(Integer listTypeEnumIdParam)
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
	public static AcaoTabelaEnum enumForValue(Integer value)
	{
		for (AcaoTabelaEnum e : values())
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
		AcaoTabelaEnum[] enums = AcaoTabelaEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (AcaoTabelaEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
