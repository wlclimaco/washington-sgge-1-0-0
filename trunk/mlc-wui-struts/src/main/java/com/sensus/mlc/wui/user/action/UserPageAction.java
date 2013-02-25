package com.sensus.mlc.wui.user.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.Response;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.UserRequest;
import com.sensus.mlc.user.model.response.UserResponse;
import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.model.IdValuePair;

/**
 * Action rendering User-related pages. At this point, this page has no extra logic over what LayoutBase already
 * provides.
 * 
 * @author Vinicius Scalon Ferreira
 * 
 */
@SuppressWarnings("serial")
public class UserPageAction extends LayoutBase
{

	/** CONSTANTS **/

	/** The Constant DELETE_USER. */
	private static final String DELETE_USER = "user.actions.deleteUser";

	/** The user bcf. */
	private IUserBCF userBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The response. */
	private Response response;

	/** The user request. */
	private UserRequest userRequest;

	/** The user id. */
	private String userId;

	/** The user. */
	private User user;

	/**
	 * Gets the action list.
	 * 
	 * @return the action list
	 */
	public Map<String, List<IdValuePair>> getActionList()
	{
		Map<String, List<IdValuePair>> map = new LinkedHashMap<String, List<IdValuePair>>();

		// Delete User
		List<IdValuePair> deleteUserList = new ArrayList<IdValuePair>();
		deleteUserList.add(new IdValuePair("deleteUser", getText(DELETE_USER)));

		map.put(getText(DELETE_USER), deleteUserList);
		return map;
	}

	/**
	 * Fill update user.
	 * 
	 * @return the string
	 */
	public String fillUpdateUser()
	{
		UserRequest request = new UserRequest();
		request.setUserContext(getUserContext());

		UserResponse response = getUserBCF().fetchUserById(request);

		setUser(response.getUser());

		return SUCCESS;
	}

	/**
	 * Gets the group list.
	 * 
	 * @return the group list
	 */
	public List<Group> getGroupList()
	{
		List<Group> list = new ArrayList<Group>();
		try
		{
			InquiryPaginationRequest request = new InquiryPaginationRequest(getUserContext());

			request.setPageSize(0);

			InquiryGroupResponse response = getGroupBCF().fetchAllGroups(request);

			list.addAll(response.getGroups());
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("GROUP_LIST_ERROR", e);
			}
		}

		return list;
	}

	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
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
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user)
	{
		this.user = user;
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

}
