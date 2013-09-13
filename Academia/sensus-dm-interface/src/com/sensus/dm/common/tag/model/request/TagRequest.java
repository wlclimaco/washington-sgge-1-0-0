package com.sensus.dm.common.tag.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class TagRequest.
 * 
 * @author QAT Brazil
 * 
 */
public class TagRequest extends TenantRequest
{
	/** The tags. */
	private List<Tag> tags;

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/**
	 * Instantiates a new tag request.
	 */
	public TagRequest()
	{
	}

	/**
	 * Instantiates a new tag request.
	 * 
	 * @param tag the tag
	 */
	public TagRequest(Tag tag)
	{
		addTag(tag);
	}

	/**
	 * Instantiates a new tag request.
	 * 
	 * @param tag the tag
	 * @param userContext the user context
	 */
	public TagRequest(Tag tag, UserContext userContext)
	{
		this(tag);
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new tag request.
	 * 
	 * @param tag the tag
	 * @param userContext the user context
	 * @param serviceTypeEnum the service type enum
	 */
	public TagRequest(Tag tag, UserContext userContext, ServiceTypeEnum serviceTypeEnum)
	{
		this(tag);
		setUserContext(userContext);
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new tag request.
	 * 
	 * @param tag the tag
	 * @param userContext the user context
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public TagRequest(Tag tag, UserContext userContext, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		this(tag);
		setUserContext(userContext);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
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
	 * Adds the tag.
	 * 
	 * @param tag the tag
	 */
	public void addTag(Tag tag)
	{
		if (getTags() == null)
		{
			setTags(new ArrayList<Tag>());
		}

		getTags().add(tag);
	}

	/**
	 * Gets the first tag.
	 * 
	 * @return the first tag
	 */
	public Tag getFirstTag()
	{
		List<Tag> tagList = getTags();

		if (tagList != null && !tagList.isEmpty())
		{
			return tagList.get(FIRST);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tenant.model.request.TenantRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "TagRequest [getTags()=" + getTags() + ", getFirstTag()=" + getFirstTag() + "]";
	}

}
