package com.sensus.mlc.smartpoint.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.Filter;

/**
 * The Class ColumnFilterRequest.
 */
public class ColumnFilterRequest extends LightingControlRequest
{

	/** The custom search id. */
	private Integer customSearchId;

	/** The list column. */
	private List<Column> listColumn;

	/** The additional columns. */
	private List<Column> additionalColumns;

	/** The filters. */
	private List<Filter> filters;

	/** The additional filters. */
	private List<Filter> additionalFilters;

	/** The list type enum. */
	private ListTypeEnum listTypeEnum;

	/** The list url. */
	private String listUrl;

	/**
	 * Instantiates a new column filter request.
	 */
	public ColumnFilterRequest()
	{

	}

	/**
	 * Instantiates a new column filter request.
	 * 
	 * @param userContext the user context
	 */
	public ColumnFilterRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the custom search id.
	 * 
	 * @return the custom search id
	 */
	public Integer getCustomSearchId()
	{
		return customSearchId;
	}

	/**
	 * Sets the custom search id.
	 * 
	 * @param customSearchId the new custom search id
	 */
	public void setCustomSearchId(Integer customSearchId)
	{
		this.customSearchId = customSearchId;
	}

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
	public List<Filter> getFilters()
	{
		return filters;
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
		return additionalFilters;
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

	/**
	 * Gets the list type enum.
	 * 
	 * @return the list type enum
	 */
	public ListTypeEnum getListTypeEnum()
	{
		return listTypeEnum;
	}

	/**
	 * Sets the list type enum.
	 * 
	 * @param listTypeEnum the new list type enum
	 */
	public void setListTypeEnum(ListTypeEnum listTypeEnum)
	{
		this.listTypeEnum = listTypeEnum;
	}

	@Override
	public String toString()
	{
		return "ColumnFilterRequest [getCustomSearchId()=" + getCustomSearchId() + ", getListColumn()="
				+ getListColumn() + ", getAdditionalColumns()=" + getAdditionalColumns() + ", getFilters()="
				+ getFilters() + ", getAdditionalFilters()=" + getAdditionalFilters() + ", getListTypeEnum()="
				+ getListTypeEnum() + "]";
	}

	/**
	 * @return the listUrl
	 */
	public String getListUrl()
	{
		return listUrl;
	}

	/**
	 * @param listUrl the listUrl to set
	 */
	public void setListUrl(String listUrl)
	{
		this.listUrl = listUrl;
	}

}