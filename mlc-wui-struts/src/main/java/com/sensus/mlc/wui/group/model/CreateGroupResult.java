package com.sensus.mlc.wui.group.model;

import com.sensus.mlc.wui.base.model.JsonResult;

/**
 * Json result for create group action.
 * 
 * @author Anke Doerfel-parker
 * 
 */
@SuppressWarnings("serial")
public class CreateGroupResult extends JsonResult
{
	/**
	 * The id of the newly created group.
	 */
	private Integer groupId;

	/**
	 * Set the group id.
	 * 
	 * @param groupIdIn the group id
	 */
	public void setGroupId(Integer groupIdIn)
	{
		groupId = groupIdIn;
	}

	/**
	 * Get the group id.
	 * 
	 * @return the group id
	 */
	public Integer getGroupId()
	{
		return groupId;
	}

	/**
	 * Returns string representation of the object.
	 * 
	 * @return string representation of the object
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CreateGroupResult [groupId=" + groupId + ", getGroupId()=" + getGroupId() + "]";
	}
}
