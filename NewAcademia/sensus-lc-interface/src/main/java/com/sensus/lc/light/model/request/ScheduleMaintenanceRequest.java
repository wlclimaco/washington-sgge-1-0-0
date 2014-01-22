package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.light.model.LightSchedule;

/**
 * Used for updating last operational data information about a given Light.
 */
public class ScheduleMaintenanceRequest extends MaintenanceRequest
{

	/** The light id. */
	private Integer lightId;

	/** The schedule. */
	private LightSchedule schedule;

	/**
	 * Gets the light id.
	 *
	 * @return the light id
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * Sets the light id.
	 *
	 * @param lightId the new light id
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * Gets the schedule.
	 *
	 * @return the schedule
	 */
	public LightSchedule getSchedule()
	{
		return schedule;
	}

	/**
	 * Sets the schedule.
	 *
	 * @param schedule the schedule to set
	 */
	public void setSchedule(LightSchedule schedule)
	{
		this.schedule = schedule;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ScheduleMaintenanceRequest [getLightId()=" + getLightId() + ", getSchedule()=" + getSchedule()
				+ ", toString()=" + super.toString() + "]";
	}


}
