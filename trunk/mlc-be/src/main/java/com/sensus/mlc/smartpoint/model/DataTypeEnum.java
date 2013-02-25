package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum DataTypeEnum.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public enum DataTypeEnum implements IIntegerEnum
{

	/** The STRING. */
	STRING(1),
	/** The INTEGER. */
	INTEGER(2),
	/** The FLOAT. */
	FLOAT(3),
	/** The DOUBLE. */
	DOUBLE(4),
	/** The DATE. */
	DATE(5),
	/** The BIGDECIMAL. */
	BIGDECIMAL(6),
	/** The DATETIME. */
	DATETIME(7);

	/** The data type. */
	private Integer dataType;

	/**
	 * Instantiates a new data type enum.
	 * 
	 * @param dataTypeInd the data type ind
	 */
	private DataTypeEnum(Integer dataTypeInd)
	{
		dataType = dataTypeInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{

		return dataType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the data type enum
	 */
	public static DataTypeEnum enumForValue(Integer value)
	{
		for (DataTypeEnum e : values())
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
		DataTypeEnum[] enums = DataTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (DataTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
