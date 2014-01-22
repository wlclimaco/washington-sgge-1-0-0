package com.sensus.lc.analytics.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * * The Model Object Analytics Group.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 * 
 */
@SuppressWarnings("serial")
public class AnalyticsGroup extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The columns. */
	private List<AnalyticsGroupColumns> columns = new ArrayList<AnalyticsGroupColumns>();

	/**
	 * Instantiates a new analytics group.
	 */
	public AnalyticsGroup()
	{
	}

	/**
	 * Instantiates a new analytics group.
	 * 
	 * @param idValue the id value
	 * @param nameValue the name value
	 */
	public AnalyticsGroup(Integer idValue, String nameValue)
	{
		setId(idValue);
		setName(nameValue);
	}

	/**
	 * Instantiates a new analytics group.
	 * 
	 * @param groupId the analytics group id
	 */
	public AnalyticsGroup(Integer groupId)
	{
		setId(groupId);
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
	 * @param groupId the new id
	 */
	public void setId(Integer groupId)
	{
		id = groupId;
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
	 * Sets the name.
	 * 
	 * @param groupName the new name
	 */
	public void setName(String groupName)
	{
		name = groupName;
	}

	/**
	 * Gets the columns.
	 * 
	 * @return the columns
	 */
	public List<AnalyticsGroupColumns> getColumns()
	{
		return columns;
	}

	/**
	 * Adds the column.
	 * 
	 * @param column the column
	 */
	public void addColumn(AnalyticsGroupColumns column)
	{
		getColumns().add(column);
	}

	/**
	 * Sets the columns.
	 * 
	 * @param columns the new columns
	 */
	public void setColumns(List<AnalyticsGroupColumns> columns)
	{
		this.columns = columns;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "AnalyticsGroup [getId()=" + getId() + ", getName()=" + getName() + ", getColumns()=" + getColumns()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}