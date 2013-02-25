package com.sensus.mlc.schedule.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;

/**
 * The Class ScheduleResponse.
 *
 * @author Gustavo Aragao - QAT Brazil
 */
public class ScheduleResponse extends Response
{

	/** The schedules. */
	private List<? extends Schedule> schedules;

	/** The process. */
	private List<Process> processes = new ArrayList<Process>();

	/**
	 * Sets the schedules.
	 *
	 * @param scheduleObjects the new schedules
	 */
	public void setSchedules(List<Schedule> scheduleObjects)
	{
		schedules = scheduleObjects;
	}

	/**
	 * Gets the schedules.
	 *
	 * @return the schedules
	 */
	@SuppressWarnings("unchecked")
	public List<Schedule> getSchedules()
	{
		return (List<Schedule>)schedules;
	}

	/**
	 * Gets the offset schedules.
	 *
	 * @return the offset schedules
	 */
	@SuppressWarnings("unchecked")
	public List<OffsetSchedule> getOffsetSchedules()
	{
		return (List<OffsetSchedule>)schedules;
	}

	/**
	 * Gets the event schedules.
	 *
	 * @return the event schedules
	 */
	@SuppressWarnings("unchecked")
	public List<EventSchedule> getEventSchedules()
	{
		return (List<EventSchedule>)schedules;
	}

	/**
	 * Gets the first process.
	 *
	 * @return the first process
	 */
	public Process getFirstProcess()
	{
		if (getProcesses().isEmpty())
		{
			return null;
		}
		return getProcesses().get(0);
	}

	/**
	 * Gets the processes.
	 *
	 * @return the processes
	 */
	public List<Process> getProcesses()
	{
		return processes;
	}

	/**
	 * Sets the processes.
	 *
	 * @param processes the new processes
	 */
	public void setProcesses(List<Process> processes)
	{
		this.processes = processes;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ScheduleResponse [getSchedules()=" + getSchedules() + ", getOffsetSchedules()="
				+ getOffsetSchedules()
				+ ", getEventSchedules()=" + getEventSchedules() + ", getFirstProcess()=" + getFirstProcess()
				+ ", getProcesses()=" + getProcesses() + "]";
	}
}
