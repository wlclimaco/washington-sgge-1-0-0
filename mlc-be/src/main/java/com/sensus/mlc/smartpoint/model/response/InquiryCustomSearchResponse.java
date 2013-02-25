package com.sensus.mlc.smartpoint.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.smartpoint.model.CustomSearch;

/**
 * The Class InquiryCustomSearchResponse.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 */
public class InquiryCustomSearchResponse extends InquiryResponse
{

	/** The CustomSearchs. */
	private List<CustomSearch> customSearches;

	/**
	 * Gets the custom searches.
	 * 
	 * @return the custom searches
	 */
	public List<CustomSearch> getCustomSearches()
	{
		if (this.customSearches == null)
		{
			this.customSearches = new ArrayList<CustomSearch>();
		}

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
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setCustomSearches(new ArrayList<CustomSearch>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryCustomSearchResponse [getCustomSearches()=" + getCustomSearches() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}
