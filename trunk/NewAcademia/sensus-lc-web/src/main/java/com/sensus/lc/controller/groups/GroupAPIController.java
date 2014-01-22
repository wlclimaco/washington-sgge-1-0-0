package com.sensus.lc.controller.groups;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.group.bcf.IGroupBCF;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.group.model.response.GroupResponse;
import com.sensus.lc.group.model.response.InquiryGroupResponse;

/**
 * The Class GroupAPIController.
 */
/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/api/group")
public class GroupAPIController extends BaseController
{

	/** The Constant ACTION. */
	private static final String ACTION = "action";

	/** The Constant DELETE. */
	private static final String DELETE_GROUP = "/delete";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant FETCH_BY_ID. */
	private static final String FETCH_BY_ID = "fetchById";

	/** The Constant FETCH_COUNT. */
	private static final String FETCH_COUNT = "/fetchcount";

	/** The Constant FETCH. */
	private static final String FETCH_GROUPS = "/fetch";

	/** The Constant INSERT_LIGHTS. */
	private static final String INSERT_LIGHTS = "/insertlights";

	/** The Constant INSERT_LIGHTS. */
	private static final String DELETE_LIGHTS = "/deletelights";

	/** The Constant GROUP_ID. */
	private static final String GROUP_ID = "groupId";

	/** The Constant INSERT. */
	private static final String INSERT_GROUP = "/insert";

	/** The Constant IS_ALL_ROWS. */
	private static final String IS_ALL_ROWS = "isAllRows";

	/** The Constant SELECTION_PAGINATION_IDS. */
	private static final String SELECTION_PAGINATION_IDS = "selectionPaginationIds";

	/** The Constant UPDATE. */
	private static final String UPDATE_GROUP = "/update";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GroupAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "GroupAPIController";

	/** The group bcf. */
	private IGroupBCF groupBCF;

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
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Fetch all.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @param request the request
	 * @return the inquiry group response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public InquiryGroupResponse fetchAll(
			@RequestBody InquiryPaginationRequest inquiryPaginationRequest,
			HttpServletRequest request)
	{

		InquiryGroupResponse inquiryGroupResponse = new InquiryGroupResponse();

		try
		{
			// ADD user context to request
			setUserContext(inquiryPaginationRequest, request);

			inquiryGroupResponse = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryGroupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryGroupResponse;
	}

	/**
	 * Insert lights.
	 * 
	 * @param groupRequest the group request
	 * @param request the request
	 * @return the group response
	 */
	@RequestMapping(value = INSERT_LIGHTS, method = RequestMethod.POST)
	@ResponseBody
	public GroupResponse insertLights(
			@RequestBody GroupRequest groupRequest,
			HttpServletRequest request)
	{

		GroupResponse groupResponse = new GroupResponse();

		try
		{
			// ADD user context to request
			setUserContext(groupRequest, request);

			groupResponse = getGroupBCF().insertLightToGroup(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupResponse;
	}

	/**
	 * Delete lights.
	 * 
	 * @param groupRequest the group request
	 * @param request the request
	 * @return the group response
	 */
	@RequestMapping(value = DELETE_LIGHTS, method = RequestMethod.POST)
	@ResponseBody
	public GroupResponse deleteLights(
			@RequestBody GroupRequest groupRequest,
			HttpServletRequest request)
	{

		GroupResponse groupResponse = new GroupResponse();

		try
		{
			// ADD user context to request
			setUserContext(groupRequest, request);

			groupResponse = getGroupBCF().deleteLightFromGroup(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupResponse;
	}

	/**
	 * Fetch.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the group response
	 */
	@RequestMapping(value = FETCH_GROUPS, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{

		GroupResponse groupResponse = new GroupResponse();
		try
		{

			GroupRequest groupRequest = new GroupRequest();

			// ADD user context to request
			setUserContext(groupRequest, request);

			// Chooses the proper BCF from Action String
			switch (String.valueOf(jsonRequest.get(ACTION)))
			{
				case FETCH_BY_ID:
					groupRequest.setGroup(new Group(Integer.valueOf(jsonRequest.get(GROUP_ID).toString())));
					groupResponse = getGroupBCF()
							.fetchGroupById(groupRequest);
					break;
				default:
					break;
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupResponse;
	}

	/**
	 * Update.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = UPDATE_GROUP, method = RequestMethod.POST)
	@ResponseBody
	public Response update(
			@RequestBody GroupRequest groupRequest,
			HttpServletRequest request)
	{
		GroupResponse groupResponse = new GroupResponse();
		try
		{
			// ADD user context to request
			setUserContext(groupRequest, request);

			groupResponse = getGroupBCF().updateGroup(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return groupResponse;

	}

	/**
	 * Delete.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = DELETE_GROUP, method = RequestMethod.POST)
	@ResponseBody
	public Response delete(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		GroupResponse groupResponse = new GroupResponse();
		try
		{

			GroupRequest groupRequest = new GroupRequest();

			// ADD user context to request
			setUserContext(groupRequest, request);

			// Verify if exists ids selected
			if (!ValidationUtil.isNull(jsonRequest.get(SELECTION_PAGINATION_IDS)))
			{
				groupRequest.setSelectionPaginationIds((List<Integer>)jsonRequest.get(SELECTION_PAGINATION_IDS));

				// Verify if the checkbox "Select All" was marked
				if (!ValidationUtil.isNull(jsonRequest.get(IS_ALL_ROWS)))
				{
					groupRequest.setPaginationAllSelected(Boolean.valueOf(jsonRequest.get(IS_ALL_ROWS).toString()));
				}

				groupResponse = getGroupBCF().deleteGroup(groupRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupResponse;

	}

	/**
	 * Insert.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_GROUP, method = RequestMethod.POST)
	@ResponseBody
	public Response insert(
			@RequestBody GroupRequest groupRequest,
			HttpServletRequest request)
	{
		GroupResponse groupResponse = new GroupResponse();

		try
		{
			// ADD user context to request
			setUserContext(groupRequest, request);

			groupResponse = getGroupBCF().insertGroup(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupResponse;

	}

	/**
	 * Fetch count lights from groups.
	 * 
	 * @param groupRequest the group request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_COUNT, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchCountLightsFromGroups(
			@RequestBody GroupRequest groupRequest,
			HttpServletRequest request)
	{

		GroupResponse groupResponse = new GroupResponse();

		try
		{

			setUserContext(groupRequest, request);

			groupResponse = getGroupBCF().fetchCountLightsFromGroups(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupResponse;

	}

}
