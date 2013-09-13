package com.sensus.common.query;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;

/**
 * Class that encapsulates a search string and the conditions in how it should
 * be leveraged to conduct a search.
 * 
 * This object is meant to be placed on criteria objects used for inquiry
 * based requests.
 * 
 */
public class SearchString implements Serializable
{
	// [start] Private fields
	private String searchValue;
	private SearchType searchType;
	private boolean caseSensitive;
	private String workingValue;

	// [end] Private fields

	/**
	 * An enum that controls the supported search types for a DynamicSearchString.
	 * Valid values are:
	 * <p>
	 * <ul>
	 * <li>STARTS_WITH</li>
	 * <li>ENDS_WITH</li>
	 * <li>CONTAINS</li>
	 * <li>EXACT_MATCH</li>
	 * </ul>
	 */
	public enum SearchType
	{
		STARTS_WITH,
		ENDS_WITH,
		CONTAINS,
		EXACT_MATCH,
		GREATER_THAN,
		LESS_THAN;
	}

	// [start] Constructors

	/**
	 * Default Constructor.
	 */
	public SearchString()
	{
		super();
	}

	/**
	 * Constructor that allows the ability to instantiate and populate in one call.
	 * 
	 * @param newSearchValue the new search value
	 * @param newSearchType the new search type
	 */
	public SearchString(String newSearchValue, SearchType newSearchType)
	{
		this(newSearchValue, newSearchType, false);
	}

	/**
	 * Constructor that allows the ability to instantiate and populate in one call.
	 * 
	 * @param newSearchValue the new search value
	 * @param newSearchType the new search type
	 * @param newCaseSensitive the new case sensitive
	 */
	public SearchString(String newSearchValue, SearchType newSearchType, boolean newCaseSensitive)
	{
		this();
		setSearchValue(newSearchValue);
		setSearchType(newSearchType);
		setCaseSensitive(newCaseSensitive);
	}

	// [end] Constructors

	// [start] Getter/Setters

	/**
	 * Setter.
	 * 
	 * @param newValue The Value to Set.
	 */
	public void setSearchValue(String newValue)
	{
		searchValue = newValue;
	}

	/**
	 * Getter.
	 * 
	 * @return The value to use in the search condition.
	 */
	public String getSearchValue()
	{
		return searchValue;
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
	 * Setter.
	 * 
	 * @param newValue The Value to Set.
	 */
	public void setCaseSensitive(Boolean newValue)
	{
		caseSensitive = newValue;
	}

	/**
	 * Getter - Determines if the searchValue should be used in a case sensitive fashion.
	 * 
	 * @return the boolean
	 */
	public Boolean isCaseSensitive()
	{
		return caseSensitive;
	}

	/**
	 * Setter.
	 * 
	 * @param newValue The Value to Set.
	 */
	public void setWorkingValue(String newValue)
	{
		workingValue = newValue;
	}

	/**
	 * Getter - The working value is a value that can be updated without impact
	 * to the original search value specified. This working value can be updated
	 * to account for the search type or case sensitivity settings.
	 * 
	 * @return The workingValue, if null the searchValue will be set as the
	 *         working value and returned.
	 */
	@XmlTransient
	public String getWorkingValue()
	{
		if (StringUtils.isBlank(workingValue))
		{
			setWorkingValue(getSearchValue());
		}
		return workingValue;
	}

	// [end] Getter/Setters

	@Override
	public String toString()
	{
		return "DynamicSearchString [getSearchValue()=" + getSearchValue() + ", getSearchType()=" + getSearchType()
				+ ", isCaseSensative()=" + isCaseSensitive() + ", getWorkingValue()=" + getWorkingValue() + "]";
	}

}
