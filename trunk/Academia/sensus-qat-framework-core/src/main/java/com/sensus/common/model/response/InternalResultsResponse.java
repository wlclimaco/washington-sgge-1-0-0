package com.sensus.common.model.response;

import java.util.ArrayList;
import java.util.List;


/**
 * Used as the internal response object for internal operations that require some type of results-set or object to be
 * returned to the consumer. <br/>
 * See the {@link InternalResponse InternalResponse} class for more information on internal operations and responses.<br/>
 * This sub-class of the base {@link InternalResponse} type includes a type safe collection for results.
 */
public class InternalResultsResponse<T> extends InternalResponse
{

	/** The results list. */
	private List<T> resultsList = new ArrayList<T>();

	private ResultsSetInfo resultsSetInfo = null;

	/**
	 * Instantiates a new internal results response.
	 */
	public InternalResultsResponse()
	{
	}

	/**
	 * Instantiates a new internal results response.
	 *
	 * @param t the t
	 */
	public InternalResultsResponse(T t)
	{
		addResult(t);
	}

	/**
	 * Instantiates a new internal results response.
	 *
	 * @param ts the ts
	 */
	public InternalResultsResponse(List<T> ts)
	{
		addResults(ts);
	}

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
	 * Adds the multi-object to the list.
	 *
	 * @param values the values
	 */
	public void addResults(List<T> values)
	{
		this.getResultsList().addAll(values);
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
		return "InternalResultsResponse [getResultsSetInfo()=" + getResultsSetInfo() + ", getResultsList()=" + getResultsList() + ", getFirstResult()=" + getFirstResult()
				+ ", hasResults()=" + hasResults() + "]";
	}

}
