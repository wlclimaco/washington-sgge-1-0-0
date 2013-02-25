package com.sensus.mlc.wui.group.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * Action handling Group-related Ajax callbacks.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class GroupAjaxAction extends ActionBase
{
	/**
	 * The logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(GroupAjaxAction.class);

	/**
	 * The Group BCF object.
	 */
	private IGroupBCF groupBCF;

	/** The group request. */
	private GroupRequest groupRequest;

	/** The response. */
	private Response response;

	/**
	 * Initiate delete group.
	 * 
	 * @return the string
	 */
	public String deleteGroup()
	{
		try
		{

			GroupResponse groupResponse = getGroupBCF().deleteGroup(getGroupRequest());
			setResponse(groupResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error initiating delete group", e);
			}

		}
		return SUCCESS;
	}

	/**
	 * Adds smartpoints to a group.
	 * 
	 * @return always "SUCCESS"
	 */
	public String addSmartpoints()
	{
		try
		{
			GroupRequest GroupReq = getGroupRequest();
			GroupResponse GroupResponse = getGroupBCF().insertSmartpointToGroup(GroupReq);

			setResponse(GroupResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error adding SmartPoints to group", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Removes smartpoints from a group.
	 * 
	 * @return always "SUCCESS"
	 */
	public String removeSmartpoints()
	{
		try
		{
			GroupRequest GroupReq = getGroupRequest();
			GroupResponse GroupResponse = getGroupBCF().deleteSmartPointFromGroup(GroupReq);

			setResponse(GroupResponse);
		}
		catch (final Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error removing SmartPoints from group", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Fetch lights by group to map.
	 * 
	 * @return the string
	 */
	public String fetchLightByGroupToMap()
	{

		try
		{

			InquiryLightResponse response = getGroupBCF().fetchSmartpointByGroupToMap(getGroupRequest());
			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error getting lights by group to map", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Set the Group BCF. Injected by Spring.
	 * 
	 * @param groupBCFIn the Group BCF
	 */
	public void setGroupBCF(IGroupBCF groupBCFIn)
	{
		groupBCF = groupBCFIn;
	}

	/**
	 * Get the Group BCF.
	 * 
	 * @return the Group BCF
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
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
}
