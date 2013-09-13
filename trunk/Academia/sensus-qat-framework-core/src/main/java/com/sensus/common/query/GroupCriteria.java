package com.sensus.common.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to represent a "group" of criteria instances. This is a logical grouping of several criteria items "grouped"
 * together so they can be used in conjunction with other GroupCriteria instances or regular simple criteria instances.
 */
public class GroupCriteria extends Criteria
{

	/** The criteria list. */
	private List<Criteria> criteriaList = new ArrayList<Criteria>();

	/**
	 * Default constructor.
	 */
	public GroupCriteria()
	{
	}

	/**
	 * Create a new instance using the given Conjunction and list of criteria instances.
	 * 
	 * @param conjuction the conjuction
	 * @param criteriaList the criteria list
	 */
	public GroupCriteria(Conjunction conjuction, List<Criteria> criteriaList)
	{
		super.setConjuction(conjuction);
		this.criteriaList = criteriaList;
	}

	/**
	 * Create a new instance using the given list of criteria instances.
	 * 
	 * @param criteriaList the criteria list
	 */
	public GroupCriteria(List<Criteria> criteriaList)
	{
		this.criteriaList = criteriaList;
	}

	/**
	 * Gets the criteria list.
	 * 
	 * @return the criteria list
	 */
	public List<Criteria> getCriteriaList()
	{
		return this.criteriaList;
	}

	/**
	 * Adds the criteria.
	 * 
	 * @param criteria the criteria
	 */
	public void addCriteria(Criteria criteria)
	{
		this.criteriaList.add(criteria);
	}

	/**
	 * Removes the criteria.
	 * 
	 * @param criteria the criteria
	 */
	public void removeCriteria(Criteria criteria)
	{
		this.criteriaList.remove(criteria);
	}

	/**
	 * Render this criteria in a sql-like format.
	 * 
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		boolean first = true;
		StringBuilder sb = new StringBuilder();

		sb.append(" (");

		for (Criteria c : this.criteriaList)
		{
			if (first == false)
			{
				sb.append(c.getConjuction().toString());
			}

			first = false;
			sb.append(c.toString());
		}

		sb.append(" )");

		return sb.toString();
	}

	@Override
	public void validate(List<String> msgList)
	{
		if (this.criteriaList == null || this.criteriaList.size() == 0)
		{
			msgList.add("Within the GroupCriteria instance at least one Criteria is required.");
		}
		else
		{
			for (Criteria c : this.criteriaList)
			{
				c.validate(msgList);
			}
		}
		return;
	}
}
