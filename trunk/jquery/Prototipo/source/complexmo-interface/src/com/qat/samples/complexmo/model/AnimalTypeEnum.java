package com.qat.samples.complexmo.model;

import com.qat.framework.model.IIntegerEnum;

public enum AnimalTypeEnum implements IIntegerEnum
{
	UNKNOWN(0), MAMMAL(1), REPTILE(2), BIRDS(3), INSECTS(4), MARINE(5);

	/** The code. */
	private Integer code;

	/**
	 * Instantiates a new contact purpose enum.
	 * 
	 * @param value the value
	 */
	private AnimalTypeEnum(int value)
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
	public static AnimalTypeEnum enumForValue(Integer value)
	{
		for (AnimalTypeEnum e : values())
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
		AnimalTypeEnum[] enums = AnimalTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder sb = new StringBuilder();
		for (AnimalTypeEnum i : enums)
		{
			sb.append(comma + i.getValue());
			comma = ", ";
		}
		return sb.toString();
	}
}
