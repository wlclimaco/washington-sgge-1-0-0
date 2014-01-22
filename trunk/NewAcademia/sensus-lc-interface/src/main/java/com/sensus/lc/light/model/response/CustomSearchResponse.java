package com.sensus.lc.light.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.CustomSearch;

/**
 * The Class CustomSearchResponse.
 */
public class CustomSearchResponse extends InquiryResponse
{

	/** The custom search list. */
	private List<CustomSearch> customSearchList;

	/**
	 * Gets the custom search list.
	 *
	 * @return the custom search list
	 */
	public List<CustomSearch> getCustomSearchList()
	{
		return customSearchList;
	}

	/**
	 * Sets the custom search list.
	 *
	 * @param customSearchList the new custom search list
	 */
	public void setCustomSearchList(List<CustomSearch> customSearchList)
	{
		this.customSearchList = customSearchList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CustomSearchResponse [getCustomSearchList()=" + getCustomSearchList() + ", toString()="
				+ super.toString() + "]";
	}
}
