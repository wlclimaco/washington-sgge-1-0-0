package com.sensus.mlc.schedule.model.request;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ScheduleRequest.
 */
/**
 * @author QATEmployee
 * 
 */
public class ScheduleRequest extends LightSelectionRequest
{

	/** The schedule. */
	@XmlElement(nillable = true)
	private Schedule schedule;

	/** The schedule type enum. */
	private ScheduleTypeEnum scheduleTypeEnum;

	/** The light id list. */
	private List<Integer> lightIdList = new ArrayList<Integer>();

	/**
	 * Instantiates a new schedule request.
	 */
	public ScheduleRequest()
	{
	}

	/**
	 * Instantiates a new schedule request.
	 * 
	 * @param userContext the user context
	 */
	public ScheduleRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new schedule request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public ScheduleRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
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

	/**
	 * Gets the schedule type enum.
	 * 
	 * @return the schedule type enum
	 */
	public ScheduleTypeEnum getScheduleTypeEnum()
	{
		return scheduleTypeEnum;
	}

	/**
	 * Sets the schedule type enum.
	 * 
	 * @param scheduleTypeEnum the new schedule type enum
	 */
	public void setScheduleTypeEnum(ScheduleTypeEnum scheduleTypeEnum)
	{
		this.scheduleTypeEnum = scheduleTypeEnum;
	}

	/**
	 * Sets the schedule type enum value.
	 * 
	 * @param scheduleTypeEnumValue the new schedule type enum value
	 */
	public void setScheduleTypeEnumValue(Integer scheduleTypeEnumValue)
	{
		scheduleTypeEnum = ScheduleTypeEnum.enumForValue(scheduleTypeEnumValue);
	}

	/**
	 * Sets the offset schedule.
	 * 
	 * @param offsetScheduleObject the new offset schedule
	 */
	public void setOffsetSchedule(OffsetSchedule offsetScheduleObject)
	{
		schedule = offsetScheduleObject;
	}

	/**
	 * Sets the event schedule.
	 * 
	 * @param eventSchedule the new event schedule
	 */
	public void setEventSchedule(EventSchedule eventSchedule)
	{
		schedule = eventSchedule;
	}

	/**
	 * Gets the light id list.
	 * 
	 * @return the light id list
	 */
	public List<Integer> getLightIdList()
	{
		return lightIdList;
	}

	/**
	 * Sets the light id list.
	 * 
	 * @param lightIdList the new light id list
	 */
	public void setLightIdList(List<Integer> lightIdList)
	{
		this.lightIdList = lightIdList;
	}

	/**
	 * Adds the light id list.
	 * 
	 * @param lightId the light id
	 */
	public void addLightIdList(Integer lightId)
	{
		getLightIdList().add(lightId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "ScheduleRequest [getSchedule()=" + getSchedule() + ", getBottomLeftLat()=" + getBottomLeftLat()
				+ ", getBottomLeftLon()=" + getBottomLeftLon() + ", getTopRightLat()=" + getTopRightLat()
				+ ", getTopRightLon()=" + getTopRightLon() + ", getMaxSmartpointCount()=" + getMaxSmartpointCount()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()=" + getSearchLight()
				+ ", getPaginationAllSelected()=" + getPaginationAllSelected() + ", getSelectionPaginationIds()="
				+ getSelectionPaginationIds() + ", getTenant()=" + getTenant() + ", getUserContext()="
				+ getUserContext() + "getLightIdList" + getLightIdList() + "]";
	}

}
