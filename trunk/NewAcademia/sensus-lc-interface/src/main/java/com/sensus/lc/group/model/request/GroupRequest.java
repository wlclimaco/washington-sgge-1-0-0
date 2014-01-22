package com.sensus.lc.group.model.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.LightSelectionRequest;
import com.sensus.lc.base.util.LCPropertiesExtractorUtil;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class GroupRequest.
 */
public class GroupRequest extends LightSelectionRequest
{

	/** The group. */
	@XmlElement(nillable = true)
	private Group group;

	/** The old name. */
	@XmlElement(nillable = true)
	private String oldName;

	/** The group list. */
	private List<Group> groupList;

	/** The light protected. */
	private Boolean lightProtected;

	/** The tag id. */
	private Integer tagId;

	/** The percentage. */
	private Integer percentage;

	/** The light request. */
	private LightRequest lightRequest;

	/**
	 * Instantiates a new group request.
	 */
	public GroupRequest()
	{
	}

	/**
	 * Instantiates a new group request.
	 * 
	 * @param userContext the user context
	 */
	public GroupRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new group request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public GroupRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Gets the percentage.
	 * 
	 * @return the percentage
	 */
	public Integer getPercentage()
	{
		return percentage;
	}

	/**
	 * Sets the percentage.
	 * 
	 * @param percentage the new percentage
	 */
	public void setPercentage(Integer percentage)
	{
		this.percentage = percentage;
	}

	/**
	 * Gets the tag id.
	 * 
	 * @return the tag id
	 */
	public Integer getTagId()
	{
		return tagId;
	}

	/**
	 * Sets the tag id.
	 * 
	 * @param tagId the new tag id
	 */
	public void setTagId(Integer tagId)
	{
		this.tagId = tagId;
	}

	/**
	 * Sets the group.
	 * 
	 * @param groupObject the new group
	 */
	public void setGroup(Group groupObject)
	{
		group = groupObject;
	}

	/**
	 * Gets the group.
	 * 
	 * @return the group
	 */
	public Group getGroup()
	{
		return group;
	}

	/**
	 * Gets the old name.
	 * 
	 * @return the old name
	 */
	public String getOldName()
	{
		return oldName;
	}

	/**
	 * Sets the old name.
	 * 
	 * @param oldNameStr the new old name
	 */
	public void setOldName(String oldNameStr)
	{
		oldName = oldNameStr;
	}

	/**
	 * Sets the light protected.
	 * 
	 * @param lightProtected the new light protected
	 */
	public void setLightProtected(Boolean lightProtected)
	{
		this.lightProtected = lightProtected;
	}

	/**
	 * Gets the light protected.
	 * 
	 * @return the light protected
	 */
	public Boolean getLightProtected()
	{
		return lightProtected;
	}

	/**
	 * Gets the group list.
	 * 
	 * @return the group list
	 */
	public List<Group> getGroupList()
	{
		return groupList;
	}

	/**
	 * Sets the group list.
	 * 
	 * @param groupList the new group list
	 */
	public void setGroupList(List<Group> groupList)
	{
		this.groupList = groupList;
	}

	/**
	 * Gets the group id list.
	 * 
	 * @return the group id list
	 */
	public String getStringGroupIdList()
	{
		if (getGroupList() == null || getGroupList().isEmpty())
		{
			return null;
		}
		List<Integer> groupIdList = LCPropertiesExtractorUtil.extractGroupId(getGroupList());
		return StringUtils.join(groupIdList, ",");
	}

	/**
	 * Gets the light request.
	 * 
	 * @return the light request
	 */
	public LightRequest getLightRequest()
	{
		return lightRequest;
	}

	/**
	 * Sets the light request.
	 * 
	 * @param lightRequest the new light request
	 */
	public void setLightRequest(LightRequest lightRequest)
	{
		this.lightRequest = lightRequest;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "GroupRequest [getPercentage()=" + getPercentage() + ", getTagId()=" + getTagId() + ", getGroup()="
				+ getGroup() + ", getOldName()=" + getOldName() + ", getLightProtected()=" + getLightProtected()
				+ ", getGroupList()=" + getGroupList() + ", getStringGroupIdList()=" + getStringGroupIdList()
				+ ", toString()=" + super.toString() + "]";
	}
}
