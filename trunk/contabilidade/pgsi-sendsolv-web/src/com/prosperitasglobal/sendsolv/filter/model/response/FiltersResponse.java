package com.prosperitasglobal.sendsolv.filter.model.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qat.framework.model.response.Response;

/**
 * The Class FiltersResponse.
 */
public class FiltersResponse extends Response
{

	/** The filters. */
	private Map<String, List<?>> filters = new HashMap<>();

	/** The filters list. */
	private List<String> filtersList = new ArrayList<String>();

	/**
	 * Gets the filters.
	 *
	 * @return the filters
	 */
	public Map<String, List<?>> getFilters()
	{
		return filters;
	}

	/**
	 * Sets the filters.
	 *
	 * @param filters the filters
	 */
	public void setFilters(Map<String, List<?>> filters)
	{
		this.filters = filters;
	}

	/**
	 * Adds the filter.
	 *
	 * @param name the name
	 * @param data the data
	 */
	public void addFilter(String name, List<?> data)
	{
		filters.put(name, data);
	}

	/**
	 * Gets the filters list.
	 *
	 * @return the filtersList
	 */
	public List<String> getFiltersList()
	{
		return filtersList;
	}

	/**
	 * Sets the filters list.
	 *
	 * @param filtersList the filtersList to set
	 */
	public void setFiltersList(List<String> filtersList)
	{
		this.filtersList = filtersList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FiltersResponse [getFilters()=" + getFilters() + ", getFiltersList()=" + getFiltersList() + "]";
	}

}
