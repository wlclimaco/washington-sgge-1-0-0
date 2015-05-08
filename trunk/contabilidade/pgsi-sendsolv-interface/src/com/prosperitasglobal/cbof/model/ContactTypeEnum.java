package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum ContactTypeEnum.
 */
public enum ContactTypeEnum implements IIntegerEnum, II18nEnum
{

	/** The unknown. */
	UNKNOWN(1, "cbof.base.model.contacttypeenum.unknown"),

	/** The other. */
	OTHER(2, "cbof.base.model.contacttypeenum.other"),

	/** The email personal. */
	EMAIL_PERSONAL(3, "cbof.base.model.contacttypeenum.email_personal"),

	/** The postal home. */
	POSTAL_HOME(4, "cbof.base.model.contacttypeenum.postal_home"),

	/** The phone work. */
	PHONE_WORK(5, "cbof.base.model.contacttypeenum.work"),

	/** The email work. */
	EMAIL_WORK(6, "cbof.base.model.contacttypeenum.email_work"),

	/** The postal work. */
	POSTAL_WORK(7, "cbof.base.model.contacttypeenum.postal_work"),

	/** The mobile. */
	MOBILE(8, "cbof.base.model.contacttypeenum.mobile");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * Instantiates a new contact purpose enum.
	 *
	 * @param value the value
	 * @param labelKeyParam the label key param
	 */
	private ContactTypeEnum(Integer value, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.II18nEnum#getLabelKey()
	 */
	@Override
	public String getLabelKey()
	{
		return labelKey;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return code;
	}

	/**
	 * This static method is used to convert an internal value into the enum. This is useful when a value from an
	 * external source such as a database needs to be converted to an enum.
	 *
	 * @param value the value
	 * @return the contact type enum
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
