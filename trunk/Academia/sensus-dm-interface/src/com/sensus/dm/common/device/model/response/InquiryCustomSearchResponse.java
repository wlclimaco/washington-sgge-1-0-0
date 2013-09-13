package com.sensus.dm.common.device.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.common.device.model.Column;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.DeviceFilterEnum;

/**
 * The Class InquiryCustomSearchResponse.
 * 
 * @author - QAT Brazil
 */
public class InquiryCustomSearchResponse extends InquiryResponse
{

	/** The CustomSearchs. */
	private List<CustomSearch> customSearches;

	/** The list column. */
	private List<Column> listColumn;

	/** The list filters. */
	private List<DeviceFilterEnum> listFilters;

	/**
	 * Gets the custom searches.
	 * 
	 * @return the custom searches
	 */
	public List<CustomSearch> getCustomSearches()
	{
		if (customSearches == null)
		{
			customSearches = new ArrayList<CustomSearch>();
		}

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

	/**
	 * Gets the Columns.
	 * 
	 * @return the columns
	 */
	public List<Column> getListColumn()
	{
		return listColumn;
	}

	/**
	 * Sets the Columns.
	 * 
	 * @param listColumn the columns
	 */
	public void setListColumn(List<Column> listColumn)
	{
		this.listColumn = listColumn;
	}

	/**
	 * Gets the FilterEnum.
	 * 
	 * @return the FilterEnums
	 */
	public List<DeviceFilterEnum> getListFilters()
	{
		return listFilters;
	}

	/**
	 * Sets the FiltersEnum.
	 * 
	 * @param listFilters the FilterEnum
	 */
	public void setListFilters(List<DeviceFilterEnum> listFilters)
	{
		this.listFilters = listFilters;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
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
		return "InquiryCustomSearchResponse [getCustomSearches()=" + getCustomSearches() + ", getListColumn()="
				+ getListColumn() + ", getListFilters()=" + getListFilters() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()="
				+ getMessageIterator() + ", getMessageList()=" + getMessageList() + ", getMessageInfoList()="
				+ getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
