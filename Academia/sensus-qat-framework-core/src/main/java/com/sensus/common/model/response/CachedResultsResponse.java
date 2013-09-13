package com.sensus.common.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Used as the response object for cache operations. <br/>
 * Does not include any status or messages. Meant to aide if later add cache when originally have coded using InternalResultsResponse<br/>
 * See the {@link InternalResponse InternalResponse} class for more information on internal operations and responses.<br/>
 * This Serializable class includes a type safe collection for results.
 */
public class CachedResultsResponse<T> implements Serializable
{

	private static final long serialVersionUID = 1L;

	/** The results list. */
	private List<T> resultsList = new ArrayList<T>();

	private ResultsSetInfo resultsSetInfo = null;

	/**
	 * Gets the ResultsSetInfo for this internal response. This will never be null and when you need to access the
	 * contents or set the content you would go through this "getter" method.
	 * 
	 * @return the results set info
	 */
	public ResultsSetInfo getResultsSetInfo()
	{
		if (this.resultsSetInfo == null)
		{
			this.resultsSetInfo = new ResultsSetInfo();
		}

		return this.resultsSetInfo;
	}

	/**
	 * Gets the results list.
	 * 
	 * @return the results list
	 */
	public List<T> getResultsList()
	{
		return this.resultsList;
	}

	/**
	 * Adds a single results object to the list.
	 * 
	 * @param value the value
	 */
	public void addResult(T value)
	{
		this.getResultsList().add(value);
	}

	/**
	 * Gets the first results item from the results list. If the list is empty a null value will be returned.
	 * 
	 * @return the first result
	 */
	public T getFirstResult()
	{
		if (this.resultsList.size() > 0)
		{
			return this.resultsList.get(0);
		}

		return null;

	}

	/**
	 * Checks for results.
	 * 
	 * @return true, if successful
	 */
	public boolean hasResults()
	{
		return this.getResultsList().size() > 0;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append(super.toString());

		sb.append("\n\tCachedResultsResponse::resultsList.count=[" + this.resultsList.size() + "] ");
		if (this.resultsSetInfo != null)
		{
			sb.append("\n\t\t" + this.resultsSetInfo.toString());
		}
		else
		{
			sb.append("results set info is  null.");
		}

		return sb.toString();
	}
}
