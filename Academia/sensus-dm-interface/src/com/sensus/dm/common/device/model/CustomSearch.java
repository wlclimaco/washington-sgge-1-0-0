package com.sensus.dm.common.device.model;

import java.util.List;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.SortExpression;

/**
 * The Class CustomSearch.
 * 
 * @author - QAT Brazil.
 * 
 */
@SuppressWarnings("serial")
public class CustomSearch extends DeviceSearch
{
	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The list column. */
	private List<Column> listColumn;

	/** The list filters. */
	private List<DeviceFilterEnum> listFilters;

	/** The sort expression. */
	private SortExpression sortExpression;

	/** The search parameters. */
	private List<SearchParameter> searchParameters;

	/** The device type. */
	private DeviceTypeEnum deviceType;

	/**
	 * Instantiates a new custom search.
	 */
	public CustomSearch()
	{

	}

	/**
	 * Instantiates a new custom search.
	 * 
	 * @param idParam the id
	 */
	public CustomSearch(Integer idParam)
	{
		setId(idParam);
	}

	/**
	 * Instantiates a new custom search.
	 * 
	 * @param nameValue the name value
	 * @param descriptionValue the description value
	 */
	public CustomSearch(String nameValue, String descriptionValue)
	{
		setName(nameValue);
		setDescription(descriptionValue);
	}

	/**
	 * Instantiates a new base search.
	 * 
	 * @param idValue the id value
	 * @param searchParametersValue the search parameters value
	 */
	public CustomSearch(Integer idValue, List<SearchParameter> searchParametersValue)
	{
		setId(idValue);
		setSearchParameters(searchParametersValue);
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
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
	 * Gets the list filters.
	 * 
	 * @return the list filters
	 */
	public List<DeviceFilterEnum> getListFilters()
	{
		return listFilters;
	}

	/**
	 * Sets the list filters.
	 * 
	 * @param listFilters the new list filters
	 */
	public void setListFilters(List<DeviceFilterEnum> listFilters)
	{
		this.listFilters = listFilters;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Gets the sortExpression.
	 * 
	 * @return the sortExpression
	 */
	public SortExpression getSortExpression()
	{
		return sortExpression;
	}

	/**
	 * Sets the sort expression.
	 * 
	 * @param sortExpression the new sort expression
	 */
	public void setSortExpression(SortExpression sortExpression)
	{
		this.sortExpression = sortExpression;
	}

	/**
	 * Gets the search parameters.
	 * 
	 * @return the search parameters
	 */
	public List<SearchParameter> getSearchParameters()
	{
		return searchParameters;
	}

	/**
	 * Sets the search parameters.
	 * 
	 * @param searchParameters the new search parameters
	 */
	public void setSearchParameters(List<SearchParameter> searchParameters)
	{
		this.searchParameters = searchParameters;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Gets the device type.
	 * 
	 * @return the device type
	 */
	public DeviceTypeEnum getDeviceType()
	{
		return deviceType;
	}

	/**
	 * Sets the device type.
	 * 
	 * @param deviceType the new device type
	 */
	public void setDeviceType(DeviceTypeEnum deviceType)
	{
		this.deviceType = deviceType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.model.DeviceSearch#toString()
	 */
	@Override
	public String toString()
	{
		return "CustomSearch [getName()=" + getName() + ", getListColumn()=" + getListColumn() + ", getListFilters()="
				+ getListFilters() + ", getDescription()=" + getDescription()
				+ ", getDeviceType()= " + getDeviceType()
				+ ", getSortExpression()=" + getSortExpression() + ", getSearchParameters()="
				+ getSearchParameters() + "]";
	}

}
