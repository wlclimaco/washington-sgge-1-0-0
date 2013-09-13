package com.sensus.common.query;

/**
 * The operator to be used for the comparison of this CompareCriteria instance.
 */
public enum Operator
{
	/** The EQUALS. */
	EQUALS("="),
	/** The GREATER. */
	GREATER(">"),
	/** The GREATER_THAN_EQUAL. */
	GREATER_THAN_EQUAL(">="),
	/** The LESS. */
	LESS("<"),
	/** The LESS_THAN_EQUAL. */
	LESS_THAN_EQUAL("<="),
	/** The LIKE. */
	LIKE("LIKE"),
	/** The NOTEQUAL. */
	NOTEQUAL("<>");

	/** The value. */
	private String value;

	/**
	 * Instantiates a new operator.
	 * 
	 * @param newValue the new value
	 */
	private Operator(String newValue)
	{
		this.value = newValue;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString()
	{
		return this.value;
	}
}
