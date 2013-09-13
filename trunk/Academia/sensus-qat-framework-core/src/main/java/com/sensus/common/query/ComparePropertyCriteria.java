package com.sensus.common.query;

import java.util.List;

/**
 * Represents a simple comparison criteria where a given property is compared using an operator to a given value.
 */
public class ComparePropertyCriteria extends Criteria
{
	/** The property. */
	private String leftProperty;

	/** The operator. */
	private Operator operator = Operator.EQUALS;

	/** The value. */
	private String rightProperty;

	// [start] Constructors

	/**
	 * Instantiates a new compare criteria.
	 * 
	 * @param leftProperty the property
	 * @param operator the operator
	 * @param rightProperty the value
	 */
	public ComparePropertyCriteria(String leftProperty, Operator operator, String rightProperty)
	{
		this.leftProperty = leftProperty;
		this.rightProperty = rightProperty;
		this.operator = operator;
	}

	// [end] Constructors

	// [start] Properties

	/**
	 * Gets the left property.
	 * 
	 * @return the property
	 */
	public String getLeftProperty()
	{
		return this.leftProperty;
	}

	/**
	 * Gets the right property.
	 * 
	 * @return the right property
	 */
	public String getRightProperty()
	{
		return this.rightProperty;
	}

	// [end] Properties

	public void validate(List<String> msgList)
	{
		if (this.leftProperty == null || this.leftProperty.length() == 0)
		{
			msgList.add("Within the ComparePropertyCriteria instance a "
					+ "left-property name is required and cannot be empty.");
		}

		if (this.rightProperty == null || this.rightProperty.length() == 0)
		{
			msgList.add("Within the ComparePropertyCriteria instance the "
					+ "right-property name is required and cannot be empty.");
		}
	}

	/**
	 * Renders this criteria instance in the form of a sql-like phrase.
	 * 
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return " " + this.leftProperty + " " + this.operator + " " + this.rightProperty;
	}

}
