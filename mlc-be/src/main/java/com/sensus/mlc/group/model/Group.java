package com.sensus.mlc.group.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.smartpoint.model.Light;

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

	/** The lights. */
	private List<Light> lights;

	/** The smart point count. */
	private Integer smartPointCount;

	/** Latitude of the center point. */
	private Double centerLatitude;

	/** Longitude of the center point. */
	private Double centerLongitude;

	/**
	 * Instantiates a new group.
	 */
	public Group()
	{
		setLights(new ArrayList<Light>());
	}

	/**
	 * Instantiates a new group with the given ID.
	 *
	 * @param groupId the group id
	 */
	public Group(Integer groupId)
	{
		setId(groupId);
		setLights(new ArrayList<Light>());
	}

	/**
	 * Instantiates a new group with the given action.
	 *
	 * @param action the action
	 */
	public Group(PersistanceActionEnum action)
	{
		setModelAction(action);
		setLights(new ArrayList<Light>());
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
		setLights(new ArrayList<Light>());
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
		setLights(lightsList);
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
		setLights(lightsList);
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
	 * Gets the first light.
	 *
	 * @return the first light
	 */
	public Light getFirstLight()
	{
		if (getLights().size() > 0)
		{

			return getLights().get(0);
		}
		return new Light();
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
	 * Sets the lights.
	 *
	 * @param lightList the new lights
	 */
	public void setLights(List<Light> lightList)
	{
		lights = lightList;
	}

	/**
	 * Adds the light.
	 *
	 * @param light the light
	 */
	public void addLight(Light light)
	{
		getLights().add(light);
	}

	/**
	 * Sets the smart point count.
	 *
	 * @param smartPointCountParam the new smart point count
	 */
	public void setSmartPointCount(Integer smartPointCountParam)
	{
		smartPointCount = smartPointCountParam;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Group [getId()=" + getId() + ", getName()=" + getName() + ", getDescription()=" + getDescription()
				+ ", getCenterLatitude()=" + getCenterLatitude() + ", getCenterLongitude()=" + getCenterLongitude()
				+ ", getLights()=" + getLights() + ", getSmartPointCount()=" + getSmartPointCount()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
