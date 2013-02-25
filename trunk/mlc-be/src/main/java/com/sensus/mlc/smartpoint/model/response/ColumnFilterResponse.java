package com.sensus.mlc.smartpoint.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.Filter;

public class ColumnFilterResponse extends Response
{
	/** The list column. */
	private List<Column> listColumn;

	/** The additional columns. */
	private List<Column> additionalColumns;

	/** The filters. */
	private List<Filter> filters;

	/** The additional filters. */
	private List<Filter> additionalFilters;

	/**
	 * Gets the list column.
	 * 
	 * @return the list column
	 */
	public List<Column> getListColumn()
	{
		return this.listColumn;
	}

	/**
	 * Sets the list column.
	 * 
	 * @param listColumn the new list column
	 */
	public void setListColumn(List<Column> listColumn)
	{
		this.listColumn = listColumn;
	}

	/**
	 * Gets the additional columns.
	 * 
	 * @return the additional columns
	 */
	public List<Column> getAdditionalColumns()
	{
		return this.additionalColumns;
	}

	/**
	 * Sets the additional columns.
	 * 
	 * @param additionalColumns the new additional columns
	 */
	public void setAdditionalColumns(List<Column> additionalColumns)
	{
		this.additionalColumns = additionalColumns;
	}

	/**
	 * Gets the filters.
	 * 
	 * @return the filters
	 */
	public List<Filter> getFilters()
	{
		return this.filters;
	}

	/**
	 * Sets the filters.
	 * 
	 * @param filters the new filters
	 */
	public void setFilters(List<Filter> filters)
	{
		this.filters = filters;
	}

	/**
	 * Gets the additional filters.
	 * 
	 * @return the additional filters
	 */
	public List<Filter> getAdditionalFilters()
	{
		return this.additionalFilters;
	}

	/**
	 * Sets the additional filters.
	 * 
	 * @param additionalFilters the new additional filters
	 */
	public void setAdditionalFilters(List<Filter> additionalFilters)
	{
		this.additionalFilters = additionalFilters;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ColumnFilterResponse [getListColumn()=" + this.getListColumn() + ", getAdditionalColumns()="
				+ this.getAdditionalColumns() + ", getFilters()=" + this.getFilters() + ", getAdditionalFilters()="
				+ this.getAdditionalFilters() + "]";
	}
}
