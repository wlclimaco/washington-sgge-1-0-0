package com.sensus.common.scheduler.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;
import com.sensus.common.scheduler.action.Action;

/**
 * The Class Schedule.
 * 
 * @author QAT Brazil
 */
@SuppressWarnings("serial")
public class Schedule extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The last job execution status. */
	private ScheduleStatusEnum statusEnum;

	/** The action. */
	private Action action;

	/** The frequency. */
	private Frequency frequency;

	/** The start time. */
	private Date startTime;

	/** The end date. */
	private Date endDate;

	/**
	 * Instantiates a new schedule.
	 */
	public Schedule()
	{

	}

	/**
	 * Instantiates a new schedule.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param description the description
	 * @param statusEnum the status enum
	 * @param action the action
	 * @param frequency the frequency
	 * @param startTime the start time
	 */
	public Schedule(Integer id, String name, String description, ScheduleStatusEnum statusEnum,
			Action action,
			Frequency frequency, Date startTime)
	{
		setId(id);
		setName(name);
		setDescription(description);
		setStatusEnum(statusEnum);
		setAction(action);
		setFrequency(frequency);
		setStartTime(startTime);
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
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public Action getAction()
	{
		return action;
	}

	/**
	 * Sets the action.
	 * 
	 * @param action the new action
	 */
	public void setAction(Action action)
	{
		this.action = action;
	}

	/**
	 * Gets the frequency.
	 * 
	 * @return the frequency
	 */
	public Frequency getFrequency()
	{
		return frequency;
	}

	/**
	 * Sets the frequency.
	 * 
	 * @param frequency the new frequency
	 */
	public void setFrequency(Frequency frequency)
	{
		this.frequency = frequency;
	}

	/**
	 * Gets the start time.
	 * 
	 * @return the start time
	 */
	public Date getStartTime()
	{
		return startTime;
	}

	/**
	 * Sets the start time.
	 * 
	 * @param startTime the new start time
	 */
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * Gets the status enum.
	 * 
	 * @return the status enum
	 */
	public ScheduleStatusEnum getStatusEnum()
	{
		return statusEnum;
	}

	/**
	 * Sets the status enum.
	 * 
	 * @param statusEnum the new status enum
	 */
	public void setStatusEnum(ScheduleStatusEnum statusEnum)
	{
		this.statusEnum = statusEnum;
	}

	/**
	 * Sets the status enum value.
	 * 
	 * @param statusEnumVal the new status enum value
	 */
	public void setStatusEnumValue(Integer statusEnumVal)
	{
		statusEnum = ScheduleStatusEnum.enumForValue(statusEnumVal);
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Schedule [getId()=" + getId() + ", getName()=" + getName() + ", getDescription()=" + getDescription()
				+ ", getAction()=" + getAction() + ", getFrequency()=" + getFrequency() + ", getStartTime()="
				+ getStartTime() + ", getStatusEnum()=" + getStatusEnum() + ", getEndDate()=" + getEndDate()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
