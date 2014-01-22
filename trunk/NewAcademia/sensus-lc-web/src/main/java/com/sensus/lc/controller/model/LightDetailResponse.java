package com.sensus.lc.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.tag.model.Tag;

/**
 * The Class LightResponse.
 * 
 */
public class LightDetailResponse extends Response
{

	/** The light. */
	private Light light;

	/** The groups. */
	private List<Group> groups = new ArrayList<Group>();

	/** The tags. */
	private List<Tag> tags = new ArrayList<Tag>();

	/**
	 * Instantiates a new light composite response.
	 */
	public LightDetailResponse()
	{
	}

	/**
	 * Gets the light.
	 * 
	 * @return the light
	 */
	public Light getLight()
	{
		return light;
	}

	/**
	 * Sets the light.
	 * 
	 * @param light the new light
	 */
	public void setLight(Light light)
	{
		this.light = light;
	}

	/**
	 * Gets the groups.
	 * 
	 * @return the groups
	 */
	public List<Group> getGroups()
	{
		return groups;
	}

	/**
	 * Sets the groups.
	 * 
	 * @param groups the new groups
	 */
	public void setGroups(List<Group> groups)
	{
		this.groups = groups;
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public List<Tag> getTags()
	{
		return tags;
	}

	/**
	 * Sets the tags.
	 * 
	 * @param tags the new tags
	 */
	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightDetailResponse [getLight()=" + getLight() + ", getGroups()=" + getGroups() + ", getTags()="
				+ getTags() + "]";
	}

}