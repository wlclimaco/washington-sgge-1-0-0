package com.sensus.dm.common.device.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.device.model.CustomSearch;

/**
 * The Class CustomSearchResponse.
 *
 * @author - Guilherme Vicentine - QAT Brazil
 */
/**
 * @author QATEmployee
 * 
 */

public class CustomSearchResponse extends Response
{

	/** The custom searches. */
	private List<CustomSearch> customSearches;

	/**
	 * Gets the custom searches.
	 * 
	 * @return the custom searches
	 */
	public List<CustomSearch> getCustomSearches()
	{
		return customSearches;
	}

	/**
	 * Sets the custom searches.
	 * 
	 * @param customSearches the new custom searches
	 */
	public void setCustomSearches(List<CustomSearch> customSearches)
	{
		this.customSearches = customSearches;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CustomSearchResponse [getCustomSearches()=" + getCustomSearches() + "]";
	}

}