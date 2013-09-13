package com.sensus.common.query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents a simple comparison criteria where a given property is compared using an operator to a given value.
 */
public class CompareValueCriteria extends Criteria
{

	/** The property. */
	private String property;

	/** The operator. */
	private Operator operator = Operator.EQUALS;

	/** The value. */
	private String value;

	/** The wildcard. */
	private boolean wildcard = false;

	/** The sounds like. */
	private boolean soundsLike = false;

	// [start] Constructors

	/**
	 * Instantiates a new compare criteria.
	 * 
	 * @param property the property
	 * @param operator the operator
	 * @param value the value
	 */
	public CompareValueCriteria(String property, Operator operator, boolean value)
	{
		this.property = property;
		this.value = String.valueOf(value);
		this.operator = operator;
	}

	/**
	 * Instantiates a new compare criteria.
	 * 
	 * @param property the property
	 * @param operator the operator
	 * @param operand the operand
	 */
	public CompareValueCriteria(String property, Operator operator, Date operand)
	{
		this(property, operator, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(operand));
	}

	/**
	 * Instantiates a new compare criteria.
	 * 
	 * @param property the property
	 * @param matchType the match type
	 * @param value the value
	 */
	public CompareValueCriteria(String property, Operator matchType, float value)
	{
		this.property = property;
		this.value = String.valueOf(value);
	}

	/**
	 * Instantiates a new compare criteria.
	 * 
	 * @param property the property
	 * @param operator the operator
	 * @param value the value
	 */
	public CompareValueCriteria(String property, Operator operator, int value)
	{
		this.property = property;
		this.value = String.valueOf(value);
		this.operator = operator;
	}

	/**
	 * Instantiates a new compare criteria.
	 * 
	 * @param property the property
	 * @param operator the operator
	 * @param value the value
	 */
	public CompareValueCriteria(String property, Operator operator, String value)
	{
		this.property = property;
		this.value = this.quote(value);
		this.operator = operator;
	}

	// [end] Constructors

	// [start] Properties

	/**
	 * Gets the property.
	 * 
	 * @return the property
	 */
	public String getProperty()
	{
		return this.property;
	}

	/**
	 * Returns an indication as to whether or not this comparison criteria is a "wild-card" type comparison or not.
	 * 
	 * @return true, if it is a wild-card type comparison
	 */
	public boolean isWildcard()
	{
		return this.wildcard;
	}

	/**
	 * Sets the wildcard indicator for this instance.
	 * 
	 * @param wildcard the new wildcard
	 */
	public void setWildcard(boolean wildcard)
	{
		this.wildcard = wildcard;
	}

	/**
	 * Returns an indication as to whether or not this comparison criteria is a "sounds-like" type comparison or not.
	 * 
	 * @return true, if is sounds like
	 */
	public boolean isSoundsLike()
	{
		return this.soundsLike;
	}

	/**
	 * Sets the sounds-like indicator for this instance.
	 * 
	 * @param soundsLike the new sounds like
	 */
	public void setSoundsLike(boolean soundsLike)
	{
		this.soundsLike = soundsLike;
	}

	// [end] Properties

	public void validate(List<String> msgList)
	{
		if (this.property == null || this.property.length() == 0)
		{
			msgList.add("Within the CompareCriteria instance a property name is required and cannot be empty.");
		}

		if (this.value == null || this.value.length() == 0)
		{
			msgList.add("Within the CompareCriteria instance the property value is required and cannot be empty.");
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
		return " " + this.property + " " + this.operator + " " + this.value;
	}

}
