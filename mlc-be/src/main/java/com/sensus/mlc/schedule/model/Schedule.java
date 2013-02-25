package com.sensus.mlc.schedule.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class Schedule.
 */
@SuppressWarnings("serial")
public class Schedule extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The description. */
	private String description;

	/** The lights. */
	private List<Light> lights = new ArrayList<Light>();

	/** The name. */
	private String name;

	/** The smart points. */
	private Integer smartPointCount;

	/** The schedule type. */
	private ScheduleTypeEnum scheduleType;

	/** Latitude of the center point. */
	private Double centerLatitude;

	/** Longitude of the center point. */
	private Double centerLongitude;

	/**
	 * Instantiates a new schedule.
	 */
	public Schedule()
	{
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
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Gets the lights.
	 * 
	 * @return the lights
	 */
	public List<Light> getLights()
	{
		return lights;
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
	 * Gets the smart point count.
	 * 
	 * @return the smart point count
	 */
	public Integer getSmartPointCount()
	{
		return smartPointCount;
	}

	/**
	 * Sets the description.
	 * 
	 * @param descriptionObject the new description
	 */
	public void setDescription(String descriptionObject)
	{
		description = descriptionObject;
	}

	/**
	 * Sets the id.
	 * 
	 * @param scheduleId the new id
	 */
	public void setId(Integer scheduleId)
	{
		id = scheduleId;
	}

	/**
	 * Sets the light.
	 * 
	 * @param lightObject the new smart point
	 */
	public void setLight(Light lightObject)
	{
		getLights().add(lightObject);
	}

	/**
	 * Sets the lights.
	 * 
	 * @param lightObjects the new lights
	 */
	public void setLights(List<Light> lightObjects)
	{
		lights = lightObjects;
	}

	/**
	 * Sets the name.
	 * 
	 * @param nameObject the new name
	 */
	public void setName(String nameObject)
	{
		name = nameObject;
	}

	/**
	 * Sets the smart point count.
	 * 
	 * @param smartPointCount the new smart point count
	 */
	public void setSmartPointCount(Integer smartPointCount)
	{
		this.smartPointCount = smartPointCount;
	}

	/**
	 * Gets the schedule type enum.
	 * 
	 * @return the schedule type enum
	 */
	public ScheduleTypeEnum getScheduleTypeEnum()
	{
		return scheduleType;
	}

	/**
	 * Sets the schedule type enum.
	 * 
	 * @param scheduleTypeParam the new schedule type enum
	 */
	public void setScheduleTypeEnum(ScheduleTypeEnum scheduleTypeParam)
	{
		scheduleType = scheduleTypeParam;
	}

	/**
	 * Sets the schedule type value.
	 * 
	 * @param scheduleTypeValue the new schedule type value
	 */
	public void setScheduleTypeValue(Integer scheduleTypeValue)
	{
		scheduleType = ScheduleTypeEnum.enumForValue(scheduleTypeValue);
	}

	/**
	 * Gets the schedule type value.
	 * 
	 * @return the schedule type value
	 */
	public Integer getScheduleTypeValue()
	{
		return scheduleType.getValue();
	}

	/**
	 * Gets the center latitude.
	 * 
	 * @return the center latitude
	 */
	public Double getCenterLatitude()
	{
		return centerLatitude;
	}

	/**
	 * Sets the center latitude.
	 * 
	 * @param centerLatitude the new center latitude
	 */
	public void setCenterLatitude(Double centerLatitude)
	{
		this.centerLatitude = centerLatitude;
	}

	/**
	 * Gets the center longitude.
	 * 
	 * @return the center longitude
	 */
	public Double getCenterLongitude()
	{
		return centerLongitude;
	}

	/**
	 * Sets the center longitude.
	 * 
	 * @param centerLongitude the new center longitude
	 */
	public void setCenterLongitude(Double centerLongitude)
	{
		this.centerLongitude = centerLongitude;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Schedule [getDescription()=" + getDescription() + ", getId()=" + getId() + ", getLights()="
				+ getLights() + ", getName()="
				+ getName() + ", getSmartPointCount()=" + getSmartPointCount() + ", getScheduleTypeEnum()="
				+ getScheduleTypeEnum()
				+ ", getScheduleTypeValue()=" + getScheduleTypeValue() + ", getCenterLatitude()=" + getCenterLatitude()
				+ ", getCenterLongitude()=" + getCenterLongitude() + ", getCreateUser()=" + getCreateUser()
				+ ", getModifyUser()="
				+ getModifyUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyDate()=" + getModifyDate()
				+ ", getModelAction()=" + getModelAction() + "]";
	}
}
