package com.sensus.mlc.wui.groups;

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
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

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
	private static final String DELETE = "/delete";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant FETCH_BY_ID. */
	private static final String FETCH_BY_ID = "fetchById";

	private static final String FETCH_COUNT = "/fetchcount";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant INSERT_LIGHTS. */
	private static final String INSERT_LIGHTS = "/insertlights";

	/** The Constant INSERT_LIGHTS. */
	private static final String DELETE_LIGHTS = "/deletelights";

	/** The Constant GROUP_ID. */
	private static final String GROUP_ID = "groupId";

	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = "groupName";

	/** The Constant GROUP_NAME_OLD. */
	private static final String GROUP_NAME_OLD = "groupNameOld";

	/** The Constant GROUP_DESCRIPTION. */
	private static final String GROUP_DESCRIPTION = "groupDescription";

	/** The Constant INSERT. */
	private static final String INSERT = "/insert";

	/** The Constant IS_ALL_ROWS. */
	private static final String IS_ALL_ROWS = "isAllRows";

	/** The Constant SELECTION_PAGINATION_IDS. */
	private static final String SELECTION_PAGINATION_IDS = "selectionPaginationIds";

	/** The Constant UPDATE. */
	private static final String UPDATE = "/update";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

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

			groupResponse = getGroupBCF().insertSmartpointToGroup(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupResponse;
	}

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

			groupResponse = getGroupBCF().deleteSmartPointFromGroup(groupRequest);

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
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
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
			switch (jsonRequest.get(ACTION).toString())
			{
				case FETCH_BY_ID:
					groupRequest.setGroup(new Group(Integer.valueOf(jsonRequest.get(GROUP_ID).toString())));
					groupResponse = getGroupBCF()
							.fetchGroupById(groupRequest);
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
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public Response update(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		GroupResponse groupResponse = new GroupResponse();
		try
		{
			GroupRequest groupRequest = new GroupRequest();

			// ADD user context to request
			setUserContext(groupRequest, request);

			Group group = new Group();

			// Validation of fields of group
			if (!ValidationUtil.isNull(jsonRequest.get(GROUP_ID).toString()))
			{
				group.setId(Integer.valueOf(jsonRequest.get(GROUP_ID).toString()));
			}
			if (!ValidationUtil.isNull(jsonRequest.get(GROUP_NAME).toString()))
			{
				group.setName(jsonRequest.get(GROUP_NAME).toString());
			}
			if (!ValidationUtil.isNull(jsonRequest.get(GROUP_DESCRIPTION).toString()))
			{
				group.setDescription(jsonRequest.get(GROUP_DESCRIPTION).toString());
			}

			groupRequest.setGroup(group);

			// Add in groupRequest the old name of group
			if (!ValidationUtil.isNull(jsonRequest.get(GROUP_NAME_OLD).toString()))
			{
				groupRequest.setOldName(jsonRequest.get(GROUP_NAME_OLD).toString());
			}
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
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
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
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response insert(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{

		GroupResponse groupResponse = new GroupResponse();

		try
		{
			GroupRequest groupRequest = new GroupRequest();

			// ADD user context to request
			setUserContext(groupRequest, request);

			Group group = new Group();

			// Validation of fields of group
			if (!ValidationUtil.isNull(jsonRequest.get(GROUP_NAME).toString()))
			{
				group.setName(jsonRequest.get(GROUP_NAME).toString());
			}
			if (!ValidationUtil.isNull(jsonRequest.get(GROUP_DESCRIPTION).toString()))
			{
				group.setDescription(jsonRequest.get(GROUP_DESCRIPTION).toString());
			}

			groupRequest.setGroup(group);
			groupResponse = getGroupBCF().insertGroup(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupResponse;

	}

	@RequestMapping(value = FETCH_COUNT, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchCountLightsFromGroups(
			@RequestBody GroupRequest groupRequest,
			HttpServletRequest request)
	{

		InquiryLightResponse inquiryLightResponse = new InquiryLightResponse();

		try
		{

			setUserContext(groupRequest, request);

			inquiryLightResponse = getGroupBCF().fetchCountLightsFromGroups(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryLightResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryLightResponse;

	}

}
