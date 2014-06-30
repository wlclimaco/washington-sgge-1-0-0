package com.qat.samples.sysmgmt.cliente.model;

import com.qat.framework.model.IIntegerEnum;
import com.qat.samples.sysmgmt.util.TableTypeEnum;

/**
 * The Enum SexIndEnum for DrugPrice.
 */

public enum ClienteTypeEnum implements IIntegerEnum
{

	FISICA(1),
	JURIDICA(2);
	/** The code. */
	private Integer code;

	/**
	 * Instantiates a new contact purpose enum.
	 * 
	 * @param value the value
	 */
	private ClienteTypeEnum(int value)
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
	public static ClienteTypeEnum enumForValue(Integer value)
	{
		for (ClienteTypeEnum e : values())
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
