package com.sensus.lc.light.model.criteria;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * The Class SearchTerm.
 */
@SuppressWarnings("serial")
public class SearchTerm implements Serializable
{
	/** Attributes. */
	private String value;
	private TermSearchType searchType = TermSearchType.LIKE;/* Default value */

	/**
	 * The Enum TermSearchType.
	 */
	public enum TermSearchType
	{
		EQUAL,
		LIKE;
	}

	/**
	 * Instantiates a new search term.
	 * 
	 * @param value the value
	 */
	public SearchTerm(String value)
	{
		setValue(value);
	}

	/**
	 * Instantiates a new search term.
	 * 
	 * @param value the value
	 */
	public SearchTerm(BigInteger value)
	{
		if (value == null)
		{
			return;
		}
		setValue(String.valueOf(value));
	}

	/**
	 * Instantiates a new search term.
	 * 
	 * @param value the value
	 * @param searchType the search type
	 */
	public SearchTerm(String value, TermSearchType searchType)
	{
		setValue(value);
		setSearchType(searchType);
	}

	/**
	 * Instantiates a new search term.
	 * 
	 * @param value the value
	 * @param searchType the search type
	 */
	public SearchTerm(BigInteger value, TermSearchType searchType)
	{
		if (value == null)
		{
			return;
		}
		setValue(String.valueOf(value));
		setSearchType(searchType);
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value the new value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Gets the search type.
	 * 
	 * @return the search type
	 */
	public TermSearchType getSearchType()
	{
		return searchType;
	}

	/**
	 * Sets the search type.
	 * 
	 * @param searchType the new search type
	 */
	public void setSearchType(TermSearchType searchType)
	{
		this.searchType = searchType;
	}

	/**
	 * Checks if is term search type equal.
	 * 
	 * @return true, if is term search type equal
	 */
	public boolean isTermSearchTypeEqual()
	{
		return getSearchType() == TermSearchType.EQUAL;
	}

	/**
	 * Checks if is term search type like.
	 * 
	 * @return true, if is term search type like
	 */
	public boolean isTermSearchTypeLike()
	{
		return getSearchType() == TermSearchType.LIKE;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SearchText [getValue()=" + getValue() + ", getSearchType()=" + getSearchType() + ", toString()="
				+ super.toString() + "]";
	}
}
