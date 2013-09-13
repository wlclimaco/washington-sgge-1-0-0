package com.sensus.dm.common.process.model;

import com.sensus.common.model.SensusModel;

/**
 * * The Model Object Action.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class ProcessType extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The description. */
	private String description;

	/** The action category. */
	private ProcessCategory processCategory;

	/**
	 * Instantiates a new action.
	 */
	public ProcessType()
	{
	}

	/**
	 * Instantiates a new action type.
	 * 
	 * @param descriptionParam the description
	 */
	public ProcessType(String descriptionParam)
	{
		setDescription(descriptionParam);
	}

	/**
	 * Instantiates a new action.
	 * 
	 * @param valueId the id
	 * @param valueDescription the description
	 */
	public ProcessType(Integer valueId, String valueDescription)
	{
		setId(valueId);
		setDescription(valueDescription);
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
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
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

	public ProcessCategory getProcessCategory()
	{
		return processCategory;
	}

	public void setProcessCategory(ProcessCategory processCategory)
	{
		this.processCategory = processCategory;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "ActionType [getDescription()=" + getDescription() + ", getId()=" + getId()
				+ ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}