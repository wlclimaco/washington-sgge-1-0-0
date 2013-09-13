package com.sensus.common.query;

import java.io.Serializable;

/**
 * Class that encapsulates a search number range and the conditions in how it should be leveraged to conduct a search.
 * Basically the value is stored in a Long instance and made available through the getters.
 * 
 * This object is meant to be placed on criteria objects used for inquiry based requests.
 * 
 */
public class SearchNumberRange implements Serializable
{

	/** The search value. */
	private Long fromSearchValue;

	/** The to search value. */
	private Long toSearchValue;

	/** The search type. */
	private SearchType searchType;

	/**
	 * An enumeration that controls the supported search types.
	 */
	public enum SearchType
	{
		/** Indicates the search should be for a number between these values, inclusive. */
		BETWEEN,

		/** Indicates the search should be for a number between or greater than these values. */
		BETWEEN_OR_GREATER_THAN,

		/** Indicates the search should be for a number between or less than these values. */
		BETWEEN_OR_LESS_THAN,

		/** Indicates the search should be for a number that is outside these date values. */
		NOT_BETWEEN;
	}

	/**
	 * Default Constructor.
	 */
	public SearchNumberRange()
	{
		super();
	}

	/**
	 * Constructor that allows the ability to instantiate and populate in one call.
	 * 
	 * @param newFromSearchValue the new from search value
	 * @param newToSearchValue the new to search value
	 * @param newSearchType the new search type
	 */
	public SearchNumberRange(Long newFromSearchValue, Long newToSearchValue, SearchType newSearchType)
	{
		this();
		setFromSearchValue(newFromSearchValue);
		setToSearchValue(newToSearchValue);
		setSearchType(newSearchType);
	}

	/**
	 * Setter.
	 * 
	 * @param newValue The Value to Set.
	 */
	public void setSearchType(SearchType newValue)
	{
		searchType = newValue;
	}

	/**
	 * Getter - Returns the type of search that is being requested.
	 * 
	 * @return SearchType that was selected.
	 */
	public SearchType getSearchType()
	{
		return searchType;
	}

	/**
	 * Sets the from search value.
	 * 
	 * @param fromSearchValue the new from search value
	 */
	public void setFromSearchValue(Long fromSearchValue)
	{
		this.fromSearchValue = fromSearchValue;
	}

	/**
	 * Gets the from search value.
	 * 
	 * @return the from search value
	 */
	public Long getFromSearchValue()
	{
		return fromSearchValue;
	}

	/**
	 * Sets the to search value.
	 * 
	 * @param toSearchValue the new to search value
	 */
	public void setToSearchValue(Long toSearchValue)
	{
		this.toSearchValue = toSearchValue;
	}

	/**
	 * Gets the to search value.
	 * 
	 * @return the to search value
	 */
	public Long getToSearchValue()
	{
		return toSearchValue;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DynamicSearchDateRange [getSearchType()=" + getSearchType() + ", getFromSearchValue()="
				+ getFromSearchValue() + ", getToSearchValue()=" + getToSearchValue() + "]";
	}

}
