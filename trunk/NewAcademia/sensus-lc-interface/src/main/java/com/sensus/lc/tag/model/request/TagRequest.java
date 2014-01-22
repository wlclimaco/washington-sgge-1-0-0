package com.sensus.lc.tag.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.LightSelectionRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class InquiryTagRequest.
 * 
 * @author - Alex Barros - QAT
 */
public class TagRequest extends LightSelectionRequest
{

	/** The tag. */
	private Tag tag;

	/** The include lights to group. */
	private Boolean includeLightsToGroup;

	/** The tags. */
	private List<Tag> tags = new ArrayList<Tag>();

	/** The light request. */
	private LightRequest lightRequest;

	/**
	 * Instantiates a new tag request.
	 */
	public TagRequest()
	{
	}

	/**
	 * Instantiates a new tag request.
	 * 
	 * @param userContext the user context
	 */
	public TagRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new tag request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public TagRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Sets the tag.
	 * 
	 * @param tag the new tag
	 */
	public void setTag(Tag tag)
	{
		this.tag = tag;
	}

	/**
	 * Gets the tag.
	 * 
	 * @return the tag
	 */
	public Tag getTag()
	{
		return tag;
	}

	/**
	 * Gets the include lights to group.
	 * 
	 * @return the include lights to group
	 */
	public Boolean getIncludeLightsToGroup()
	{
		return includeLightsToGroup;
	}

	/**
	 * Sets the include lights to group.
	 * 
	 * @param includeLightsToGroup the new include lights to group
	 */
	public void setIncludeLightsToGroup(Boolean includeLightsToGroup)
	{
		this.includeLightsToGroup = includeLightsToGroup;
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

	/**
	 * Adds the to tags.
	 * 
	 * @param tagValue the tag value
	 */
	public void addToTags(Tag tagValue)
	{
		getTags().add(tagValue);
	}

	/**
	 * @return the lightRequest
	 */
	public LightRequest getLightRequest()
	{
		return lightRequest;
	}

	/**
	 * @param lightRequest the lightRequest to set
	 */
	public void setLightRequest(LightRequest lightRequest)
	{
		this.lightRequest = lightRequest;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "TagRequest [getTag()=" + getTag() + ", getIncludeLightsToGroup()=" + getIncludeLightsToGroup()
				+ ", getTags()=" + getTags() + ", toString()=" + super.toString() + "]";
	}

}
