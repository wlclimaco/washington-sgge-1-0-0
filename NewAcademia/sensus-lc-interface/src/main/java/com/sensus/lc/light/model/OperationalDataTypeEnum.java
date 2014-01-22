package com.sensus.lc.light.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum OperationalDataTypeEnum.
 * 
 * @author - Alex Barros - QAT
 */
public enum OperationalDataTypeEnum implements IIntegerEnum
{

	/** The CUMULATIVECONSUMPTION. */
	CUMULATIVECONSUMPTION(1),

	/** The CURRENT. */
	CURRENT(2),

	/** The VOLTAGE. */
	VOLTAGE(3);

	/** The data type. */
	private Integer operationalDataType;

	/**
	 * Instantiates a new data type enum.
	 * 
	 * @param operationalDataTypeInd the operational data type ind
	 */
	private OperationalDataTypeEnum(Integer operationalDataTypeInd)
	{
		operationalDataType = operationalDataTypeInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{

		return operationalDataType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the data type enum
	 */
	public static OperationalDataTypeEnum enumForValue(Integer value)
	{
		for (OperationalDataTypeEnum e : values())
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
		OperationalDataTypeEnum[] enums = OperationalDataTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (OperationalDataTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
