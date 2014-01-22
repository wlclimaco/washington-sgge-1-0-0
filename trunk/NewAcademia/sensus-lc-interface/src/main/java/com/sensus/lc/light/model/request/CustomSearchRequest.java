package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.InquiryRequest;
import com.sensus.lc.light.model.CustomSearch;

/**
 * The Class CustomSearchRequest.
 *
 */
public class CustomSearchRequest extends InquiryRequest
{

	/** The custom search. */
	private CustomSearch customSearch;

	/**
	 * Gets the custom search.
	 *
	 * @return the custom search
	 */
	public CustomSearch getCustomSearch()
	{
		return customSearch;
	}

	/**
	 * Sets the custom search.
	 *
	 * @param customSearch the new custom search
	 */
	public void setCustomSearch(CustomSearch customSearch)
	{
		this.customSearch = customSearch;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CustomSearchRequest [getCustomSearch()=" + getCustomSearch() + ", toString()=" + super.toString() + "]";
	}
}