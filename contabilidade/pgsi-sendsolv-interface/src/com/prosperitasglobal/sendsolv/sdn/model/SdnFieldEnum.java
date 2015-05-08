package com.prosperitasglobal.sendsolv.sdn.model;

import com.qat.framework.model.IStringEnum;

public enum SdnFieldEnum implements IStringEnum
{
	FIRST_NAME("F"),

	LAST_NAME("L"),

	FIRST_LAST_NAME("B"),

	ID("I"),

	YEAR_OF_BIRTH("Y"),

	ADDRESS_COUNTRY("A"),

	CITIZENSHIP_COUNTRY("Z"),

	NATIONALITY_COUNTRY("T"),

	CITY("C");

	/** The sex. */
	private String sdnField;

	/**
	 * Instantiates a new SdnField enum.
	 *
	 * @param sdnField the sdnField
	 */
	SdnFieldEnum(String sdnField)
	{
		this.sdnField = sdnField;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue()
	{
		return sdnField;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 *
	 * @return sdnField the sdnField
	 */
	public static SdnFieldEnum enumForValue(String value)
	{
		for (SdnFieldEnum e : values())
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
		SdnFieldEnum[] enums = SdnFieldEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (SdnFieldEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
