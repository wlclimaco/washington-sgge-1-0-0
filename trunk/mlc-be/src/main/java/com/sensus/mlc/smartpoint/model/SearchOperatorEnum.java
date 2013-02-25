package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum OperatorEnum.
 * 
 * @author - Guilherme Vicentine - QAT
 */
public enum SearchOperatorEnum implements IStringEnum
{

	/** The EQUAL Operator. */
	EQUAL("="),

	/** The GREATER Operator. */
	LIKE("like");

	/** The propertyId. */
	private String propertyId;

	/**
	 * Instantiates a new operator enum.
	 * 
	 * @param propertyIdParam the property id param
	 */
	private SearchOperatorEnum(String propertyIdParam)
	{
		propertyId = propertyIdParam;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue()
	{
		return propertyId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the property enum
	 */
	public static SearchOperatorEnum enumForValue(String value)
	{
		for (SearchOperatorEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

}
