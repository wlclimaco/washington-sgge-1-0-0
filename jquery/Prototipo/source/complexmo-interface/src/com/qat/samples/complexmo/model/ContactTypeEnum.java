package com.qat.samples.complexmo.model;

import com.qat.framework.model.IIntegerEnum;

public enum ContactTypeEnum implements IIntegerEnum
{
	UNKNOWN(0), PHONE_HOME(1), EMAIL_HOME(2), POSTAL_HOME(3), PHONE_WORK(4), EMAIL_WORK(5), POSTAL_WORK(6);

	/** The code. */
	private Integer code;

	/**
	 * Instantiates a new contact purpose enum.
	 * 
	 * @param value the value
	 */
	private ContactTypeEnum(int value)
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
	public static ContactTypeEnum enumForValue(Integer value)
	{
		for (ContactTypeEnum e : values())
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
		ContactTypeEnum[] enums = ContactTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder sb = new StringBuilder();
		for (ContactTypeEnum i : enums)
		{
			sb.append(comma + i.getValue());
			comma = ", ";
		}
		return sb.toString();
	}
}
