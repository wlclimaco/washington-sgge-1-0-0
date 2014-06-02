package com.qat.samples.sysmgmt.documento.model;

import com.qat.framework.model.IStringEnum;

/**
 * The Enum SexIndEnum for DrugPrice.
 */
public enum DocumentoTypeEnum implements IStringEnum
{

	/** The Male. */
	CPF("C"),
	/** The Female. */
	RG("R"),
	/** The Both. */
	CNPJ("J"),

	INCMUN("I");

	/** The sex. */
	private String sex;

	/**
	 * Instantiates a new sex ind enum.
	 * 
	 * @param sexInd the sex ind
	 */
	DocumentoTypeEnum(String sexInd)
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
	public static DocumentoTypeEnum enumForValue(String value)
	{
		for (DocumentoTypeEnum e : values())
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
		DocumentoTypeEnum[] enums = DocumentoTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DocumentoTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
