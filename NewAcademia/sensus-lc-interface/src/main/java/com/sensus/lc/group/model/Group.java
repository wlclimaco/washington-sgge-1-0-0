package com.sensus.lc.group.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.PrecedenceEnum;

/**
 * * The Model Object Group.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
@SuppressWarnings("serial")
public class Group extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The light count. */
	private Integer lightCount;

	/** Latitude of the center point. */
	private Double latitude;

	/** Longitude of the center point. */
	private Double longitude;

	/** The precedence. */
	private PrecedenceEnum precedence;

	/**
	 * Instantiates a new group.
	 */
	public Group()
	{
	}

	/**
	 * Instantiates a new group with the given ID.
	 * 
	 * @param groupId the group id
	 */
	public Group(Integer groupId)
	{
		setId(groupId);
	}

	/**
	 * Instantiates a new group with the given action.
	 * 
	 * @param action the action
	 */
	public Group(PersistanceActionEnum action)
	{
		setModelAction(action);
	}

	/**
	 * Instantiates a new group with the given ID and the given action.
	 * 
	 * @param groupId the group id
	 * @param action the action
	 */
	public Group(Integer groupId, PersistanceActionEnum action)
	{
		setId(groupId);
		setModelAction(action);
	}

	/**
	 * Instantiates a new group with the given ID and the given action.
	 * 
	 * @param groupId the group id
	 * @param action the action
	 * @param lightsList the lights list
	 */
	public Group(Integer groupId, PersistanceActionEnum action, List<Light> lightsList)
	{
		setId(groupId);
		setModelAction(action);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param groupId the group id
	 * @param nameString the name string
	 * @param descriptionString the description string
	 * @param lightsList the lights list
	 */
	public Group(Integer groupId, String nameString, String descriptionString,
			List<Light> lightsList)
	{
		setId(groupId);
		setName(nameString);
		setDescription(descriptionString);
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
	 * @param groupId the new id
	 */
	public void setId(Integer groupId)
	{
		id = groupId;
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
	 * @param groupName the new name
	 */
	public void setName(String groupName)
	{
		name = groupName;
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
	 * @param descriptionGroup the new description
	 */
	public void setDescription(String descriptionGroup)
	{
		description = descriptionGroup;
	}

	/**
	 * Gets the center latitude.
	 * 
	 * @return the center latitude
	 */
	public Double getLatitude()
	{
		return latitude;
	}

	/**
	 * Sets the center latitude.
	 * 
	 * @param centerLatitude the new center latitude
	 */
	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * Gets the center longitude.
	 * 
	 * @return the center longitude
	 */
	public Double getLongitude()
	{
		return longitude;
	}

	/**
	 * Sets the center longitude.
	 * 
	 * @param centerLongitude the new center longitude
	 */
	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
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
	 * @param precedenceValue the new precedence value
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

	@Override
	public String toString()
	{
		return "Group [getId()=" + getId() + ", getName()=" + getName() + ", getDescription()=" + getDescription()
				+ ", getLatitude()=" + getLatitude() + ", getLongitude()=" + getLongitude() + ", getLightCount()="
				+ getLightCount() + ", getPrecedence()=" + getPrecedence() + ", getPrecedenceValue()="
				+ getPrecedenceValue() + ", toString()=" + super.toString() + "]";
	}

}
