package com.sensus.mlc.wui.user.action;

import java.util.List;

import com.sensus.common.model.Response;
import com.sensus.common.model.UserContext;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.request.UserRequest;
import com.sensus.mlc.user.model.response.UserResponse;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * Action handling Group-related Ajax callbacks.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class UserAjaxAction extends ActionBase
{
	/** The user bcf. */
	private IUserBCF userBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The response. */
	private Response response;

	/** The user request. */
	private UserRequest userRequest;

	/** The user context. */
	private UserContext fillUserContext;

	/** The group request. */
	private GroupRequest groupRequest;

	/** The lights. */
	private List<Light> lights;

	/**
	 * Fill user context.
	 * 
	 * @return the string
	 */
	public String fillUserContext()
	{

		setFillUserContext(getUserContext());

		return SUCCESS;
	}

	/**
	 * Fill user page.
	 * 
	 * @return the string
	 */
	public String fillUserPage()
	{
		UserResponse resp = getUserBCF().fetchUserById(getUserRequest());
		setResponse(resp);

		return SUCCESS;
	}

	/**
	 * Fetch lights by group.
	 * 
	 * @return the string
	 */
	public String fetchLightsByGroupList()
	{
		GroupResponse groupResp = getGroupBCF().fetchGroupsByIdList(getGroupRequest());
		setResponse(groupResp);

		return SUCCESS;
	}

	public String countLightsbyGroup()
	{
		InquiryLightResponse response = getGroupBCF().fetchCountLightsFromGroups(getGroupRequest());
		setResponse(response);

		return SUCCESS;
	}

	/**
	 * Delete user.
	 * 
	 * @return the string
	 */
	public String deleteUser()
	{
		UserResponse userResp = getUserBCF().deleteUser(getUserRequest());
		setResponse(userResp);

		return SUCCESS;
	}

	/**
	 * Creates the user.
	 * 
	 * @return the string
	 */
	public String createUser()
	{
		UserResponse userResp = getUserBCF().insertUser(getUserRequest());
		setResponse(userResp);

		return SUCCESS;
	}

	public String updateUser()
	{
		UserResponse userResp = getUserBCF().updateUser(getUserRequest());
		setResponse(userResp);

		return SUCCESS;
	}

	public String fetchLightByUserToMap()
	{
		InquiryLightResponse userResp = getGroupBCF().fetchSmartpointByGroupToMap(getGroupRequest());
		setResponse(userResp);

		return SUCCESS;

	}

	/**
	 * @return the userBCF
	 */
	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	/**
	 * @param userBCF the userBCF to set
	 */
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
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
	 * @return the userRequest
	 */
	public UserRequest getUserRequest()
	{
		return userRequest;
	}

	/**
	 * @param userRequest the userRequest to set
	 */
	public void setUserRequest(UserRequest userRequest)
	{
		this.userRequest = userRequest;
	}

	/**
	 * @return the fillUserContext
	 */
	public UserContext getFillUserContext()
	{
		return fillUserContext;
	}

	/**
	 * @param fillUserContext the fillUserContext to set
	 */
	public void setFillUserContext(UserContext fillUserContext)
	{
		this.fillUserContext = fillUserContext;
	}

	/**
	 * @return the groupBCF
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * @param groupBCF the groupBCF to set
	 */
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
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
	 * @return the lights
	 */
	public List<Light> getLights()
	{
		return lights;
	}

	/**
	 * @param lights the lights to set
	 */
	public void setLights(List<Light> lights)
	{
		this.lights = lights;
	}

}
