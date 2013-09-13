/*
 *
 */
package com.sensus.dm.common.schedule.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.schedule.model.DMSchedule;

/**
 * The Class ScheduleResponse.
 * 
 * @author Gustavo Aragao - QAT Brazil
 */
public class ScheduleResponse extends Response
{

	/** The schedules. */
	private List<DMSchedule> schedules;

	/**
	 * Gets the schedules.
	 * 
	 * @return the schedules
	 */
	public List<DMSchedule> getSchedules()
	{
		return schedules;
	}

	/**
	 * Sets the schedules.
	 * 
	 * @param schedules the new schedules
	 */
	public void setSchedules(List<DMSchedule> schedules)
	{
		this.schedules = schedules;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ScheduleResponse [getSchedules()=" + getSchedules() + "]";
	}

}
