package com.sensus.dm.common.process.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * The Class ProcessResult.
 * 
 * @author QAT Brazil.
 */
@SuppressWarnings("serial")
public class ProcessItemHistory extends SensusModel
{
	/** Id. */
	private Integer id;

	/** The description. */
	private String description;

	/** Enum for history status. */
	private ProcessItemHistoryStatusEnum processItemHistoryStatusEnum;

	/** The status time. */
	private Date statusTime;

	/**
	 * Instantiates a new process result.
	 */
	public ProcessItemHistory()
	{
	}

	/**
	 * Instantiates a new process item history.
	 * 
	 * @param processItemHistoryStatusEnumParam the process item history status enum
	 * @param statusTimeParam the status time
	 */
	public ProcessItemHistory(ProcessItemHistoryStatusEnum processItemHistoryStatusEnumParam, Date statusTimeParam)
	{
		setProcessItemHistoryStatusEnum(processItemHistoryStatusEnumParam);
		setStartTime(statusTimeParam);
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * Gets the process item history status enum.
	 * 
	 * @return the process item history status enum
	 */
	public ProcessItemHistoryStatusEnum getProcessItemHistoryStatusEnum()
	{
		return processItemHistoryStatusEnum;
	}

	/**
	 * Sets the process item history status enum.
	 * 
	 * @param processItemHistoryStatusEnum the new process item history status enum
	 */
	public void setProcessItemHistoryStatusEnum(ProcessItemHistoryStatusEnum processItemHistoryStatusEnum)
	{
		this.processItemHistoryStatusEnum = processItemHistoryStatusEnum;
	}

	/**
	 * Gets the Status time.
	 * 
	 * @return the Status time
	 */
	public Date getStatusTime()
	{
		return statusTime;
	}

	/**
	 * Sets the Status time.
	 * 
	 * @param statusTimeParam the new start time
	 */
	public void setStartTime(Date statusTimeParam)
	{
		statusTime = statusTimeParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessItemHistory [getId()=" + getId() + ", getDescription()=" + getDescription()
				+ ", getProcessItemHistoryStatusEnum()=" + getProcessItemHistoryStatusEnum() + ", getStatusTime()="
				+ getStatusTime() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
