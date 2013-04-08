package com.sensus.mlc.gestao.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class InquiryTagRequest.
 * 
 * @author - Alex Barros - QAT
 */
public class TagRequest extends LightSelectionRequest
{

	/** The tag. */
	private Tag tag;

	/** The include smart points to group. */
	private Boolean includeSmartPointsToGroup;

	/** The tags. */
	private List<Tag> tags = new ArrayList<Tag>();

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
	 * Gets the include smart points to group.
	 * 
	 * @return the include smart points to group
	 */
	public Boolean getIncludeSmartPointsToGroup()
	{
		return includeSmartPointsToGroup;
	}

	/**
	 * Sets the include smart points to group.
	 * 
	 * @param includeSmartPointsToGroup the new include smart points to group
	 */
	public void setIncludeSmartPointsToGroup(Boolean includeSmartPointsToGroup)
	{
		this.includeSmartPointsToGroup = includeSmartPointsToGroup;
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
	 * @param tag the tag
	 */
	public void addToTags(Tag tagValue)
	{
		getTags().add(tagValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "TagRequest [getTag()=" + getTag() + ", getIncludeSmartPointsToGroup()=" + "getTags() = " + getTags()
				+ getIncludeSmartPointsToGroup() + ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}
