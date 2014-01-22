package com.sensus.lc.light.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.base.model.ListTypeEnum;
/**
 * The Class CustomSearch.
 *
 */
@SuppressWarnings("serial")
public class CustomSearch extends SensusModel
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
	private List<Filter> listFilters;

	/** The list type enum. */
	private ListTypeEnum listTypeEnum;

	/** The search parameters. */
	private List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();

	/** The property id. */
	private Integer propertyId;

	/** The property value. */
	private String propertyValue;

	/**
	 * Instantiates a new custom search.
	 */
	public CustomSearch()
	{
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
	public List<Filter> getListFilters()
	{
		return listFilters;
	}

	/**
	 * Sets the list filters.
	 *
	 * @param listFilters the new list filters
	 */
	public void setListFilters(List<Filter> listFilters)
	{
		this.listFilters = listFilters;
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * Gets the property id.
	 *
	 * @return the property id
	 */
	public Integer getPropertyId()
	{
		return propertyId;
	}

	/**
	 * Sets the property id.
	 *
	 * @param propertyId the new property id
	 */
	public void setPropertyId(Integer propertyId)
	{
		this.propertyId = propertyId;
	}

	/**
	 * Gets the property value.
	 *
	 * @return the property value
	 */
	public String getPropertyValue()
	{
		return propertyValue;
	}

	/**
	 * Sets the property value.
	 *
	 * @param propertyValue the new property value
	 */
	public void setPropertyValue(String propertyValue)
	{
		this.propertyValue = propertyValue;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "CustomSearch [getName()=" + getName() + ", getDescription()=" + getDescription() + ", getListColumn()="
				+ getListColumn() + ", getListFilters()=" + getListFilters() + ", getListTypeEnum()="
				+ getListTypeEnum() + ", getId()=" + getId() + ", getSearchParameters()=" + getSearchParameters()
				+ ", getPropertyId()=" + getPropertyId() + ", getPropertyValue()=" + getPropertyValue()
				+ ", toString()=" + super.toString() + "]";
	}
}
