package com.sensus.mlc.smartpoint.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.smartpoint.model.CustomSearch;

/**
 * The Class CustomSearchResponse.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
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
		return this.customSearches;
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
		return "CustomSearchResponse [getCustomSearches()=" + this.getCustomSearches() + ", getMessageIterator()="
				+ this.getMessageIterator() + ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()="
				+ this.getMessageInfoList() + ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}

}