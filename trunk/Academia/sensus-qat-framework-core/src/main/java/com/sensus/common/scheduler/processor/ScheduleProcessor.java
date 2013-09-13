package com.sensus.common.scheduler.processor;

import com.sensus.common.scheduler.model.Schedule;

/**
 * The Class ScheduleProcessor.
 * 
 * @author QAT Brazil
 */
public class ScheduleProcessor
{

	/** The schedule. */
	private Schedule schedule;

	/**
	 * Instantiates a new schedule processor.
	 */
	public ScheduleProcessor()
	{
	}

	/**
	 * Execute.
	 */
	public void execute()
	{

	}

	/**
	 * Gets the schedule.
	 * 
	 * @return the schedule
	 */
	public Schedule getSchedule()
	{
		return schedule;
	}

	/**
	 * Sets the schedule.
	 * 
	 * @param schedule the new schedule
	 */
	public void setSchedule(Schedule schedule)
	{
		this.schedule = schedule;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ScheduleProcessor [getSchedule()=" + getSchedule() + "]";
	}

}
