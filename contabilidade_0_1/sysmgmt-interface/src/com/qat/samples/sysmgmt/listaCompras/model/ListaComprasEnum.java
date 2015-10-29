package com.qat.samples.sysmgmt.listaCompras.model;

import com.qat.framework.model.IStringEnum;

/**
 * The Enum SexIndEnum for DrugPrice.
 */
public enum ListaComprasEnum implements IStringEnum
{

	/** The Male. */
	MALE("M"),
	/** The Female. */
	FEMALE("F"),
	/** The Both. */
	BOTH("B");

	/** The sex. */
	private String sex;

	/**
	 * Instantiates a new sex ind enum.
	 * 
	 * @param sexInd the sex ind
	 */
	ListaComprasEnum(String sexInd)
	{
		sex = sexInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue()
	{
		return sex;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * 
	 * @return the sex ind enum
	 */
	public static ListaComprasEnum enumForValue(String value)
	{
		for (ListaComprasEnum e : values())
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
		ListaComprasEnum[] enums = ListaComprasEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ListaComprasEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
