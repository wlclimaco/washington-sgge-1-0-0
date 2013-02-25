package com.sensus.mlc.wui.group.action;

import com.sensus.common.model.Response;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.wui.base.action.LayoutBase;

/**
 * The Class GroupCreatePageAction.
 * 
 * @author Cristiane Cobo
 */
@SuppressWarnings("serial")
public class GroupCreatePageAction extends LayoutBase
{

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The group name. */
	private String groupName;

	/** The group description. */
	private String groupDescription;

	/** The group id. */
	private Integer groupId;

	/** The response. */
	private Response response;

	/** The group request. */
	private GroupRequest groupRequest;

	/**
	 * Creates the group page.
	 * 
	 * @return the string
	 */
	public String createGroupPage()
	{
		return SUCCESS;
	}

	/**
	 * Insert group.
	 * 
	 * @return the string
	 */
	public String insertGroup()
	{
		try
		{

			GroupResponse response = getGroupBCF().insertGroup(getGroupRequest());

			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error creating groups", e);
			}
			return ERROR;
		}

		return SUCCESS;

	}

	/**
	 * Update group.
	 * 
	 * @return the string
	 */
	public String updateGroup()
	{
		try
		{

			GroupResponse GroupResponse = getGroupBCF().updateGroup(getGroupRequest());

			setResponse(GroupResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error updating groups", e);
			}
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * @return the groupRequest
	 */
	public GroupRequest getGroupRequest()
	{
		return groupRequest;
	}

	/**
	 * @param groupRequest the groupRequest to set
	 */
	public void setGroupRequest(GroupRequest groupRequest)
	{
		this.groupRequest = groupRequest;
	}

	/**
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName()
	{
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	/**
	 * @return the groupDescription
	 */
	public String getGroupDescription()
	{
		return groupDescription;
	}

	/**
	 * @param groupDescription the groupDescription to set
	 */
	public void setGroupDescription(String groupDescription)
	{
		this.groupDescription = groupDescription;
	}

	/**
	 * @return the groupId
	 */
	public Integer getGroupId()
	{
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}

}
