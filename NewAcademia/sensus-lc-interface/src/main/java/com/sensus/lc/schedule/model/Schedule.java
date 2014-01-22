package com.sensus.lc.schedule.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.light.model.PrecedenceEnum;

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
	private List<Integer> lights = new ArrayList<Integer>();

	/** The name. */
	private String name;

	/** The lights. */
	private Integer lightCount;

	/** The schedule type. */
	private ScheduleTypeEnum scheduleType;

	/** Latitude of the center point. */
	private Double centerLatitude;

	/** Longitude of the center point. */
	private Double centerLongitude;

	/** The precedence enum. */
	private PrecedenceEnum precedence;

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
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
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
	 * @return the lights
	 */
	public List<Integer> getLights()
	{
		return lights;
	}

	/**
	 * @param lights the lights to set
	 */
	public void setLights(List<Integer> lights)
	{
		this.lights = lights;
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
	 * Gets the light count.
	 * 
	 * @return the light count
	 */
	public Integer getLightCount()
	{
		return lightCount;
	}

	/**
	 * Sets the light count.
	 * 
	 * @param lightCount the new light count
	 */
	public void setLightCount(Integer lightCount)
	{
		this.lightCount = lightCount;
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

	/**
	 * Gets the precedence.
	 * 
	 * @return the precedence
	 */
	public PrecedenceEnum getPrecedence()
	{
		return precedence;
	}

	/**
	 * Sets the precedence.
	 * 
	 * @param precedence the new precedence
	 */
	public void setPrecedence(PrecedenceEnum precedence)
	{
		this.precedence = precedence;
	}

	/**
	 * Sets the precedence value.
	 * 
	 * @param PrecedenceValue the new precedence value
	 */
	public void setPrecedenceValue(Integer precedenceValue)
	{
		precedence = PrecedenceEnum.enumForValue(precedenceValue);
	}

	/**
	 * Gets the precedence value.
	 * 
	 * @return the precedence value
	 */
	public Integer getPrecedenceValue()
	{
		if (precedence == null)
		{
			return null;
		}
		return precedence.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Schedule [getDescription()=" + getDescription() + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getLights()=" + getLights() + ", getLightCount()=" + getLightCount() + ", getScheduleTypeEnum()="
				+ getScheduleTypeEnum() + ", getScheduleTypeValue()=" + getScheduleTypeValue()
				+ ", getCenterLatitude()=" + getCenterLatitude() + ", getCenterLongitude()=" + getCenterLongitude()
				+ ", getPrecedence()=" + getPrecedence() + ", getPrecedenceValue()=" + getPrecedenceValue()
				+ ", toString()=" + super.toString() + "]";
	}
}
