package com.sensus.dm.elec.device.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class LifecycleState.
 * 
 * @author - QAT Brazil
 * 
 */
@SuppressWarnings("serial")
public class LifecycleState extends SensusModel
{

	/** The code. */
	private String code;

	/** The description. */
	private String description;

	/**
	 * Instantiates a new device type.
	 */
	public LifecycleState()
	{
	}

	/**
	 * Instantiates a new lifecycle state.
	 * 
	 * @param paramCode the param code
	 */
	public LifecycleState(String paramCode)
	{
		setCode(paramCode);
	}

	/**
	 * Instantiates a new lifecycle state.
	 * 
	 * @param paramCode the param code
	 * @param paramDescription the param description
	 */
	public LifecycleState(String paramCode, String paramDescription)
	{
		this(paramCode);
		setDescription(paramDescription);
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code the code to set
	 */
	public void setCode(String code)
	{
		this.code = code;
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
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LifecycleState [getCode()=" + getCode()
				+ ", getDescription()=" + getDescription()
				+ ", getCreateUser()=" + getCreateUser() + "]";
	}

}
