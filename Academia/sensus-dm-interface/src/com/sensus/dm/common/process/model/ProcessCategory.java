package com.sensus.dm.common.process.model;

import com.sensus.common.model.SensusModel;

/**
 * * The Model Object Action.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class ProcessCategory extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The description. */
	private String name;

	/**
	 * Instantiates a new action.
	 */
	public ProcessCategory()
	{
	}

	/**
	 * Instantiates a new action.
	 * 
	 * @param valueId the id
	 * @param valueName the name
	 * @param valueDescription the description
	 * @param ActionTypeValue the action type enum
	 */
	public ProcessCategory(Integer valueId, String valueName)
	{
		setId(valueId);
		setName(valueName);
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "ActionCategory[getName()=" + getName() + ", getId()=" + getId()
				+ ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}