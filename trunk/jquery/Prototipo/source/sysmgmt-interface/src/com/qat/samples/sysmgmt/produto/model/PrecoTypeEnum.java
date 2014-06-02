package com.qat.samples.sysmgmt.produto.model;

import com.qat.framework.model.IStringEnum;

/**
 * The Enum SexIndEnum for DrugPrice.
 */
public enum PrecoTypeEnum implements IStringEnum
{

	/** The Male. */
	MARCA("1"),
	/** The Female. */
	MENU("2"),
	SUBMENU("3"),
	TRIMENU("4"),
	UNIMED("5");
	/** The Both. */

	/** The sex. */
	private String sex;

	/**
	 * Instantiates a new sex ind enum.
	 * 
	 * @param sexInd the sex ind
	 */
	PrecoTypeEnum(String sexInd)
	{
		sex = sexInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
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
	public static PrecoTypeEnum enumForValue(String value)
	{
		for (PrecoTypeEnum e : values())
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
		PrecoTypeEnum[] enums = PrecoTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (PrecoTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
