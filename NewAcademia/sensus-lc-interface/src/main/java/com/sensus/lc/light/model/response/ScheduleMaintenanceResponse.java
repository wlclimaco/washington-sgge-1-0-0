package com.sensus.lc.light.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.LightSchedule;

/**
 * The Class ScheduleMaintenanceResponse.
 */
public class ScheduleMaintenanceResponse extends InquiryResponse
{

	/** The light schedule list. */
	private List<LightSchedule> lightScheduleList;

	/**
	 * Gets the light schedule list.
	 *
	 * @return the light schedule list
	 */
	public List<LightSchedule> getLightScheduleList()
	{
		return lightScheduleList;
	}

	/**
	 * Sets the light schedule list.
	 *
	 * @param lightScheduleList the new light schedule list
	 */
	public void setLightScheduleList(List<LightSchedule> lightScheduleList)
	{
		this.lightScheduleList = lightScheduleList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ScheduleMaintenanceResponse [getLightScheduleList()=" + getLightScheduleList() + ", toString()="
				+ super.toString() + "]";
	}
}
