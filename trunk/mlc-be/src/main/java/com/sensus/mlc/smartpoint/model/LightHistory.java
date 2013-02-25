package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class LightHistory.
 */
@SuppressWarnings("serial")
public class LightHistory extends SensusModel
{
	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The process id. */
	private Integer processId;

	/** The status message id. */
	private Integer statusMessageId;

	/** The status. */
	private boolean statusComplete;

	/** The smartpoint count. */
	private Integer smartpointCount;

	/**
	 * Instantiates a new light history.
	 */
	public LightHistory()
	{
	}

	/**
	 * Instantiates a new light history.
	 * 
	 * @param name the name
	 * @param description the description
	 * @param processId the process id
	 */
	public LightHistory(String nameValue, String descriptionValue, Integer processIdValue)
	{
		name = nameValue;
		description = descriptionValue;
		processId = processIdValue;
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
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
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
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Checks if is status.
	 * 
	 * @return true, if is status
	 */
	public boolean isStatusComplete()
	{
		return statusComplete;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status the new status
	 */
	public void setStatusComplete(boolean status)
	{
		statusComplete = status;
	}

	/**
	 * @return the statusMessageId
	 */
	public Integer getStatusMessageId()
	{
		return statusMessageId;
	}

	/**
	 * @param statusMessageId the statusMessageId to set
	 */
	public void setStatusMessageId(Integer statusMessageId)
	{
		this.statusMessageId = statusMessageId;
	}

	/**
	 * @return the smartpointCount
	 */
	public Integer getSmartpointCount()
	{
		return smartpointCount;
	}

	/**
	 * @param smartpointCount the smartpointCount to set
	 */
	public void setSmartpointCount(Integer smartpointCount)
	{
		this.smartpointCount = smartpointCount;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightHistory [getName()=" + getName() + ", getDescription()=" + getDescription() + ", getProcessId()="
				+ getProcessId() + ", isStatusComplete()=" + isStatusComplete() + ", getStatusMessageId()="
				+ getStatusMessageId() + "]";
	}

}
