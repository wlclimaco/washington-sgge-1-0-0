package com.sensus.common.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is used to represent a "query". The query is not necessarily specific to SQL, instead it tries to be a
 * generic construct representing a query and the various types of information that needs to be represented in a query.
 * There are several "criteria" items that are included in a query including:
 * <ol>
 * <li>{@link CompareValueCriteria}
 * <li>{@link BetweenCriteria}
 * <li>{@link InCriteria}
 * <li>{@link GroupCriteria}
 * </ol>
 * In addition you can specify an {@link Order} for the query results which indicates based on a given property the sort
 * order in which the results should be sorted. <br/>
 * Also there is the ability to specify a the Distinct property on a given query which indicates duplicates should be
 * removed from the results set.
 */
public class Query
{

	/** The is distinct. */
	private boolean isDistinct = false;

	/** The criteria list. */
	private List<Criteria> criteriaList;

	/** The order. */
	private List<Order> order;

	// [start] Constructor

	/**
	 * Default Constructor. Use the other methods to populate the query.
	 */
	public Query()
	{
		this.criteriaList = new ArrayList<Criteria>();
		this.order = new ArrayList<Order>();
	}

	// [end] Constructor

	// [start] Properties

	/**
	 * Checks if the Distinct property is set.
	 *
	 * @return true, if is distinct
	 */
	public boolean isDistinct()
	{
		return this.isDistinct;
	}

	/**
	 * Sets the distinct property.
	 *
	 * @param distinct the new distinct
	 */
	public void setDistinct(boolean distinct)
	{
		this.isDistinct = distinct;
	}

	/**
	 * Adds a criteria instance.
	 *
	 * @param criteria the criteria
	 */
	public void addCriteria(Criteria criteria)
	{
		this.criteriaList.add(criteria);
	}

	/**
	 * Removes a criteria instance.
	 *
	 * @param criteria the criteria
	 */
	public void removeCriteria(Criteria criteria)
	{
		this.criteriaList.remove(criteria);
	}

	/**
	 * Adds an Order instance.
	 *
	 * @param order the order
	 */
	public void addOrder(Order order)
	{
		this.order.add(order);
	}

	/**
	 * Removes an Order instance.
	 *
	 * @param order the order
	 */
	public void removeOrder(Order order)
	{
		this.order.remove(order);
	}

	/**
	 * Get a List of Order instances.
	 *
	 * @return the list
	 */
	public List<Order> listOrder()
	{
		return Collections.unmodifiableList(this.order);
	}

	// [end] Properties

	/**
	 * Validate the query and all it's "parts".
	 *
	 * @return A string containing error messages or null if everything validates clean.
	 */
	public String validate()
	{
		List<String> msgList = new ArrayList<String>();

		if (this.criteriaList == null || this.criteriaList.size() == 0)
		{
			msgList.add("Within the Query instance at least one Criteria is required.");
		}
		else
		{
			for (Criteria c : this.criteriaList)
			{
				c.validate(msgList);
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String msg : msgList)
		{
			sb.append(msg);
		}

		return sb.length() > 0 ? sb.toString() : null;
	}

	/**
	 * Render the query.
	 *
	 * @return the string
	 */
	public String toString()
	{
		boolean first = true;
		StringBuilder sb = new StringBuilder();

		if (this.validate() != null)
		{
			System.out.println("There was an error in the validation so the "
					+ "rendering of the query may not work or be correct.");
		}

		for (Criteria c : this.criteriaList)
		{
			if (first == false)
			{
				sb.append(c.getConjuction().toString());
			}

			first = false;
			sb.append(c.toString());
		}

		return sb.toString();
	}
}
