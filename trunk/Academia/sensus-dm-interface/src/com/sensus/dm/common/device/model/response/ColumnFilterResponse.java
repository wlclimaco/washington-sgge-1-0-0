package com.sensus.dm.common.device.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.device.model.Column;
import com.sensus.dm.common.device.model.DeviceFilterEnum;

/**
 * The Class ColumnFilterResponse.
 * 
 * @author QAT Global.
 */
public class ColumnFilterResponse extends Response
{
	/** The list column. */
	private List<Column> listColumn;

	/** The additional columns. */
	private List<Column> additionalColumns;

	/** The filters. */
	private List<DeviceFilterEnum> filters;

	/** The additional filters. */
	private List<DeviceFilterEnum> additionalFilters;

	/**
	 * Gets the list column.
	 * 
	 * @return the list column
	 */
	public List<Column> getListColumn()
	{
		return listColumn;
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
		return additionalColumns;
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
	public List<DeviceFilterEnum> getFilters()
	{
		return filters;
	}

	/**
	 * Sets the filters.
	 * 
	 * @param filters the new filters
	 */
	public void setFilters(List<DeviceFilterEnum> filters)
	{
		this.filters = filters;
	}

	/**
	 * Gets the additional filters.
	 * 
	 * @return the additional filters
	 */
	public List<DeviceFilterEnum> getAdditionalFilters()
	{
		return additionalFilters;
	}

	/**
	 * Sets the additional filters.
	 * 
	 * @param additionalFilters the new additional filters
	 */
	public void setAdditionalFilters(List<DeviceFilterEnum> additionalFilters)
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
		return "ColumnFilterResponse [getListColumn()=" + getListColumn() + ", getAdditionalColumns()="
				+ getAdditionalColumns() + ", getFilters()=" + getFilters() + ", getAdditionalFilters()="
				+ getAdditionalFilters() + "]";
	}
}
