/*
 *
 */
package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * Criteria for Schedule/Light relationship.
 * 
 * @author Thiago
 */
public class ScheduleCriteria
{

	/**
	 * Attributes.
	 */
	private List<Integer> lightIdList = new ArrayList<Integer>();

	/** The light schedule. */
	private List<Integer> lightSchedule = new ArrayList<Integer>();

	/** The all events. */
	private String allEvents;

	/** The all offsets. */
	private String allOffsets;

	/**
	 * Gets the light id list.
	 * 
	 * @return the lightIdList
	 */
	public List<Integer> getLightIdList()
	{
		return lightIdList;
	}

	/**
	 * Sets the light id list.
	 * 
	 * @param lightIdList the lightIdList to set
	 */
	public void setLightIdList(List<Integer> lightIdList)
	{
		this.lightIdList = lightIdList;
	}

	/**
	 * Checks for selected light.
	 * 
	 * @return true, if successful
	 */
	public boolean hasSelectedLight()
	{
		return !isNullOrEmpty(getLightIdList());
	}

	/**
	 * Checks for selected light.
	 * 
	 * @return true, if successful
	 */
	public boolean hasSelectedSchedule()
	{
		return !isNullOrEmpty(getLightSchedule());
	}

	/**
	 * Checks for filter.
	 * 
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return hasSelectedLight();
	}

	/**
	 * Gets the light schedule.
	 * 
	 * @return the light schedule
	 */
	public List<Integer> getLightSchedule()
	{
		return lightSchedule;
	}

	/**
	 * Sets the light schedule.
	 * 
	 * @param lightSchedule the new light schedule
	 */
	public void setLightSchedule(List<Integer> lightSchedule)
	{
		this.lightSchedule = lightSchedule;
	}

	/**
	 * Gets the all events.
	 * 
	 * @return the all events
	 */
	public String getAllEvents()
	{
		return allEvents;
	}

	/**
	 * Sets the all events.
	 * 
	 * @param allEvents the new all events
	 */
	public void setAllEvents(String allEvents)
	{
		this.allEvents = allEvents;
	}

	/**
	 * Gets the all offsets.
	 * 
	 * @return the all offsets
	 */
	public String getAllOffsets()
	{
		return allOffsets;
	}

	/**
	 * Sets the all offsets.
	 * 
	 * @param allOffsets the new all offsets
	 */
	public void setAllOffsets(String allOffsets)
	{
		this.allOffsets = allOffsets;
	}

	/**
	 * To string.
	 * 
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "ScheduleCriteria [getLightIdList()=" + getLightIdList() + ", hasSelectedLight()=" + hasSelectedLight()
				+ ", hasFilter()=" + hasFilter() + ", getLightSchedule()=" + getLightSchedule() + ", getAllEvents()="
				+ getAllEvents() + ", getAllOffsets()=" + getAllOffsets() + ", toString()=" + super.toString() + "]";
	}

}
