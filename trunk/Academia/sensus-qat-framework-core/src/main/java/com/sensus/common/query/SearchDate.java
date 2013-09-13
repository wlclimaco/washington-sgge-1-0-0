package com.sensus.common.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Class that encapsulates a search date value and the conditions in how it should be leveraged to conduct a search.
 * 
 * This object is meant to be placed on criteria objects used for inquiry based requests.
 * 
 */
public class SearchDate implements Serializable
{

	/** The search value. */
	private Date searchValue;

	/** The search type. */
	private DateSearchType searchType;

	/**
	 * An enum that controls the supported search types.
	 */
	public enum DateSearchType
	{
		/** Indicating an exact match should be performed. */
		EXACT_MATCH,

		/** Indicating a greater-than comparison should be performed. */
		GREATER_THAN,

		/** Indicating a less-than comparison should be performed. */
		LESS_THAN;
	}

	/**
	 * Default Constructor.
	 */
	public SearchDate()
	{
		super();
	}

	/**
	 * Constructor that allows the ability to instantiate and populate in one call.
	 * 
	 * @param newSearchValue the new search value
	 * @param newSearchType the new search type
	 */
	public SearchDate(Date newSearchValue, DateSearchType newSearchType)
	{
		this();
		setSearchValue(newSearchValue);
		setSearchType(newSearchType);
	}

	/**
	 * Setter.
	 * 
	 * @param newValue The Value to Set.
	 */
	public void setSearchValue(Date newValue)
	{
		searchValue = newValue;
	}

	/**
	 * Getter.
	 * 
	 * @return The value to use in the search condition.
	 */
	public Date getSearchValue()
	{
		return searchValue;
	}

	/**
	 * Setter.
	 * 
	 * @param newValue The Value to Set.
	 */
	public void setSearchType(DateSearchType newValue)
	{
		searchType = newValue;
	}

	/**
	 * Getter - Returns the type of search that is being requested.
	 * 
	 * @return SearchType that was selected.
	 */
	public DateSearchType getSearchType()
	{
		return searchType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DynamicSearchString [getSearchValue()=" + getSearchValue() + ", getSearchType()=" + getSearchType()
				+ "]";
	}

}
