package com.sensus.lc.process.model;

import java.util.Date;
import java.util.List;

import com.sensus.lc.light.model.LightTextSearch;

/**
 * The Class ProcessFilter.
 */
public class ProcessFilter
{

	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;

	/** The action category list. */
	private List<LCActionCategoryEnum> actionCategoryList;

	/** The light text search. */
	private LightTextSearch lightTextSearch;

	/** The user list . */
	private List<Integer> userIds;

	/**
	 * Gets the action category list.
	 * 
	 * @return the action category list
	 */
	public List<LCActionCategoryEnum> getActionCategoryList()
	{
		return actionCategoryList;
	}

	/**
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Sets the action category list.
	 * 
	 * @param actionCategoryList the new action category list
	 */
	public void setActionCategoryList(List<LCActionCategoryEnum> actionCategoryList)
	{
		this.actionCategoryList = actionCategoryList;
	}

	/**
	 * Sets the light text search.
	 * 
	 * @param lightTextSearch the new light text search
	 */
	public void setLightTextSearch(LightTextSearch lightTextSearch)
	{
		this.lightTextSearch = lightTextSearch;
	}

	/**
	 * Gets the light text search.
	 * 
	 * @return the light text search
	 */
	public LightTextSearch getLightTextSearch()
	{
		return lightTextSearch;
	}

	/**
	 * Sets the user ids.
	 * 
	 * @param userIds the new user ids
	 */
	public void setUserIds(List<Integer> userIds)
	{
		this.userIds = userIds;
	}

	/**
	 * Gets the user ids.
	 * 
	 * @return the user ids
	 */
	public List<Integer> getUserIds()
	{
		return userIds;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessFilter [getActionCategoryList()=" + getActionCategoryList() + ", getStartDate()="
				+ getStartDate() + ", getEndDate()=" + getEndDate() + ", getLightTextSearch()=" + getLightTextSearch()
				+ ", getUserIds()=" + getUserIds() + "]";
	}

}
