package com.qat.samples.sysmgmt.util.model;

import com.qat.framework.model.IIntegerEnum;

public enum TableTypeEnum implements IIntegerEnum
{

	SUPERMERCADO(1),
	PRODUTO(2),
	MARCA(3),
	MENU(4),
	SUBMENU(5),
	TRIMENU(6),
	UNIMED(7),
	CLIENTE(8),
	LISTA(9),
	ENDERECO(10),
	DOCUMENTO(11),
	TABPRECO(12),
	CIDADE(13),
	EMBALAGEM(14);
	/** The code. */
	private Integer code;

	/**
	 * Instantiates a new contact purpose enum.
	 * 
	 * @param value the value
	 */
	private TableTypeEnum(int value)
	{
		code = value;
	}

	@Override
	public Integer getValue()
	{
		return code;
	}

	/**
	 * This static method is used to convert an internal value into the enum. This is useful when a value from an
	 * external source such as a database needs to be converted to an enum.
	 * 
	 * @param value
	 * @return
	 */
	public static TableTypeEnum enumForValue(Integer value)
	{
		for (TableTypeEnum e : values())
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
		TableTypeEnum[] enums = TableTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder sb = new StringBuilder();
		for (TableTypeEnum i : enums)
		{
			sb.append(comma + i.getValue());
			comma = ", ";
		}
		return sb.toString();
	}
}
