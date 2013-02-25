package com.sensus.mlc.smartpoint.model;

import java.util.List;

import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.model.MLCSortExpression;

/**
 * The Class CustomSearch.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 */
@SuppressWarnings("serial")
public class CustomSearch extends SearchLight
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

	/** The sort expression. */
	private MLCSortExpression sortExpression;

	/**
	 * Instantiates a new custom search.
	 */
	public CustomSearch()
	{
	}

	/**
	 * Instantiates a new custom search.
	 * 
	 * @param id the id
	 */
	public CustomSearch(Integer idValue)
	{
		setId(idValue);
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
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	@Override
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	@Override
	public Integer getId()
	{
		return id;
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
	 * Gets the sort expression.
	 * 
	 * @return the sort expression
	 */
	public MLCSortExpression getSortExpression()
	{
		return sortExpression;
	}

	/**
	 * Sets the sort expression.
	 * 
	 * @param sortExpression the new sort expression
	 */
	public void setSortExpression(MLCSortExpression sortExpression)
	{
		this.sortExpression = sortExpression;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.model.SearchLight#toString()
	 */
	@Override
	public String toString()
	{
		return "CustomSearch [getId()=" + getId() + "getName()=" + getName() + ", getDescription()=" + getDescription()
				+ ", getListColumn()=" + getListColumn() + ", getListFilters()=" + getListFilters()
				+ ", getListTypeEnum()=" + getListTypeEnum() + ", getSortExpression()=" + getSortExpression()
				+ ", getLight()=" + getLight() + ", getStatusList()=" + getStatusList() + ", getPropertyValues()="
				+ getPropertyValues() + ", getId()=" + getId() + ", getSearchParameters()=" + getSearchParameters()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
