package com.qat.samples.sysmgmt.endereco.model;

import com.qat.framework.model.IStringEnum;

/**
 * The Enum SexIndEnum for DrugPrice.
 */
public enum EnderecoEnum implements IStringEnum
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
	EnderecoEnum(String sexInd)
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
	public static EnderecoEnum enumForValue(String value)
	{
		for (EnderecoEnum e : values())
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
		EnderecoEnum[] enums = EnderecoEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (EnderecoEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
