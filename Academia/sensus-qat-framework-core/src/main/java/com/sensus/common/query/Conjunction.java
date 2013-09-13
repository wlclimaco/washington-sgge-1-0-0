package com.sensus.common.query;

/**
 * Used to indicate how a given Criteria instance should be "connected" with the next Criteria instance.
 */
public enum Conjunction
{

	/** The AND. */
	AND("AND"),

	/** The OR. */
	OR("OR");

	/** The value. */
	private String value;

	/**
	 * Instantiates a new operator.
	 * 
	 * @param newValue the new value
	 */
	private Conjunction(String newValue)
	{
		this.value = newValue;
	}

	/**
	 * Render the Conjuction in the form of a String.
	 * 
	 * @return the string
	 * @see java.lang.Enum#toString()
	 */
	public String toString()
	{
		return " " + this.value;
	}
}
