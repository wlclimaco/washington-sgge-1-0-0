package com.sensus.mlc.wui.model;

import java.util.Map;

public class FiltersModel
{

	private String filterType;

	private Map<String,Object> filterValue;


	public FiltersModel()
	{


	}


	public FiltersModel(String filterType, Map<String, Object> filterValue)
	{
		this.filterType = filterType;
		this.filterValue = filterValue;
	}


	/**
	 * @return the filterType
	 */
	public String getFilterType()
	{
		return filterType;
	}


	/**
	 * @param filterType the filterType to set
	 */
	public void setFilterType(String filterType)
	{
		this.filterType = filterType;
	}

	/**
	 * @return the filterValue
	 */
	public Map<String,Object> getFilterValue()
	{
		return filterValue;
	}

	/**
	 * @param filterValue the filterValue to set
	 */
	public void setFilterValue(Map<String,Object> filterValue)
	{
		this.filterValue = filterValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Filters [getFilterType()=" + getFilterType() + ", getFilterValue()=" + getFilterValue() + "]";
	}

}
