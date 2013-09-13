package com.sensus.common.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Class that encapsulates a search date range and the conditions in how it should be leveraged to conduct a search.
 * 
 * This object is meant to be placed on criteria objects used for inquiry based requests.
 * 
 */
public class SearchDateRange implements Serializable
{

	/** The search value. */
	private Date fromSearchValue;

	/** The to search value. */
	private Date toSearchValue;

	/** The search type. */
	private DateRangeSearchType searchType;

	/**
	 * An enumeration that controls the supported search types.
	 */
	public enum DateRangeSearchType
	{
		/** Indicates the search should be for a date between these values. */
		BETWEEN,

		/** Indicates the search should be for a date between or greater than these values. */
		BETWEEN_OR_GREATER_THAN,

		/** Indicates the search should be for a date between or less than these values. */
		BETWEEN_OR_LESS_THAN,

		/** Indicate the search should be for dates that overlap. */
		OVERLAP,

		/** Indicates the search should be for a date that is outside these date values. */
		NOT_BETWEEN;
	}

	/**
	 * Default Constructor.
	 */
	public SearchDateRange()
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
	public SearchDateRange(Date newFromSearchValue, Date newToSearchValue, DateRangeSearchType newSearchType)
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
	public void setSearchType(DateRangeSearchType newValue)
	{
		searchType = newValue;
	}

	/**
	 * Getter - Returns the type of search that is being requested.
	 * 
	 * @return SearchType that was selected.
	 */
	public DateRangeSearchType getSearchType()
	{
		return searchType;
	}

	/**
	 * Sets the from search value.
	 * 
	 * @param fromSearchValue the new from search value
	 */
	public void setFromSearchValue(Date fromSearchValue)
	{
		this.fromSearchValue = fromSearchValue;
	}

	/**
	 * Gets the from search value.
	 * 
	 * @return the from search value
	 */
	public Date getFromSearchValue()
	{
		return fromSearchValue;
	}

	/**
	 * Sets the to search value.
	 * 
	 * @param toSearchValue the new to search value
	 */
	public void setToSearchValue(Date toSearchValue)
	{
		this.toSearchValue = toSearchValue;
	}

	/**
	 * Gets the to search value.
	 * 
	 * @return the to search value
	 */
	public Date getToSearchValue()
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
