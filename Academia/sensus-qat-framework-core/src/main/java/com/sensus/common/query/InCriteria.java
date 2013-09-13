package com.sensus.common.query;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Represents query criteria in the form of a "IN" clause where a list of values is indicated and in turn included in
 * the query comparison.
 */
public class InCriteria extends Criteria
{

	/** The property. */
	private String property;

	/** The value. */
	private String value;

	/**
	 * Create a new instance for the given property with the given collection of values.
	 * 
	 * @param property the property
	 * @param values the values
	 */
	public InCriteria(String property, Collection values)
	{
		this.property = property;
		StringBuffer v = new StringBuffer();
		Iterator i = values.iterator();
		boolean hasNext = i.hasNext();
		while (hasNext)
		{
			Object curr = i.next();
			hasNext = i.hasNext();
			if (curr instanceof Number)
			{
				v.append(curr);
			}
			else
			{
				v.append(this.quote(curr.toString()));
			}
			if (hasNext)
			{
				v.append(',');
			}
		}
		this.value = v.toString();
	}

	/**
	 * Create a new instance for the given property with the list of String values.
	 * 
	 * @param property the property
	 * @param values the values
	 */
	public InCriteria(String property, String... values)
	{
		this.property = property;
		StringBuffer v = new StringBuffer();
		for (int i = 0; i < values.length; i++)
		{
			v.append(this.quote(values[i]));
			if (i < values.length - 1)
			{
				v.append(',');
			}
		}
		this.value = v.toString();
	}

	/**
	 * Create a new instance for the given property with the list of int values.
	 * 
	 * @param property the property
	 * @param values the values
	 */
	public InCriteria(String property, int... values)
	{
		this.property = property;
		StringBuffer v = new StringBuffer();
		for (int i = 0; i < values.length; i++)
		{
			v.append(values[i]);
			if (i < values.length - 1)
			{
				v.append(',');
			}
		}
		this.value = v.toString();
	}

	/**
	 * Create a new instance for the given property with the list of float values.
	 * 
	 * @param property the property
	 * @param values the values
	 */
	public InCriteria(String property, float[] values)
	{
		this.property = property;
		StringBuffer v = new StringBuffer();
		for (int i = 0; i < values.length; i++)
		{
			v.append(values[i]);
			if (i < values.length - 1)
			{
				v.append(',');
			}
		}
		this.value = v.toString();
	}

	/**
	 * Gets the property for which this criteria applies.
	 * 
	 * @return the property
	 */
	public String getProperty()
	{
		return this.property;
	}

	@Override
	public void validate(List<String> msgList)
	{
		if (this.property == null || this.property.length() == 0)
		{
			msgList.add("Within the InCriteria instance a property name is required and cannot be empty.");
		}

		if (this.value == null || this.value.length() == 0)
		{
			msgList.add("Within the InCriteria instance a collection of values is required and cannot be empty.");
		}
		return;
	}

	/**
	 * Renders this criteria object in the form of a sql-like phrase.
	 * 
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" " + this.property);
		sb.append(" IN (");
		sb.append(this.value);
		sb.append(")");

		return sb.toString();
	}

}
