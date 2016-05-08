package com.qat.samples.sysmgmt.site.model;

import com.qat.framework.model.IIntegerEnum;

public enum SiteTypeEnum implements IIntegerEnum
{

	INSERT(1),
	UPDATE(2),
	DELETE(3),
	FETCHALL(4),
	FETCHID(5);

	/** The code. */
	private Integer code;

	/**
	 * Instantiates a new contact purpose enum.
	 * 
	 * @param value the value
	 */
	private SiteTypeEnum(int value)
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
	public static SiteTypeEnum enumForValue(Integer value)
	{
		for (SiteTypeEnum e : values())
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
		SiteTypeEnum[] enums = SiteTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder sb = new StringBuilder();
		for (SiteTypeEnum i : enums)
		{
			sb.append(comma + i.getValue());
			comma = ", ";
		}
		return sb.toString();
	}
}
