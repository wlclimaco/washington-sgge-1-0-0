package com.sensus.common.query;

import java.util.List;

/**
 * Used to represent a range of values to include in the "where" clause. Note the values are considered "inclusive" in
 * the range check.
 * 
 */
public class BetweenCriteria extends Criteria
{
	private String property;

	private Range range = null;

	/**
	 * Instantiates a new instance for the given property using the given String values.
	 * 
	 * @param property The name of the property
	 * @param startValue The start value
	 * @param endValue The end value
	 */
	public BetweenCriteria(String property, String startValue, String endValue)
	{
		this.property = property;
		this.range = new Range(super.quote(startValue), super.quote(endValue));
	}

	/**
	 * Instantiates a new instance for the given property using the given integer values.
	 * 
	 * @param property The name of the property
	 * @param startValue The start value
	 * @param endValue The end value
	 */
	public BetweenCriteria(String property, int startValue, int endValue)
	{
		this.property = property;
		this.range = new Range(String.valueOf(startValue), String.valueOf(endValue));
	}

	@Override
	public void validate(List<String> msgList)
	{
		if (this.property == null || this.property.length() == 0)
		{
			msgList.add("Within the BetweenCriteria instance a property name is required and cannot be empty.");
		}

		if (this.range == null)
		{
			msgList.add("Within the BetweenCriteria instance a range instance is required and cannot be empty.");
		}
		return;
	}

	/**
	 * Renders a SQL-like representation of this criteria.
	 * 
	 * @return the string
	 */
	public String toString()
	{
		return " " + this.property + " BETWEEN " + this.range.getStart() + " AND " + this.range.getEnd();
	}
}
