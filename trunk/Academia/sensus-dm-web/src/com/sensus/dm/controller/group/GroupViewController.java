package com.sensus.dm.controller.group;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.group.model.response.GroupResponse;
import com.sensus.dm.common.group.model.response.InquiryGroupResponse;
import com.sensus.dm.controller.filter.BaseFilterController;

/**
 * The Class GroupViewController.
 */
@Controller
@RequestMapping("/group")
public class GroupViewController extends BaseFilterController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GroupViewController.class);

	/** The Constant GROUP_ID. */
	private static final String ID = "id";

	/** The Constant FETCH_LIST. */
	public static final String FETCH_LIST = "";

	/** The Constant FETCH_UPDATE. */
	public static final String FETCH_UPDATE = "/update";

	/** The Constant VIEW_GROUP_MAIN. */
	private static final String VIEW_GROUP_MAIN = "/group/group_main";

	/** The Constant VIEW_GROUP_CREATE. */
	private static final String VIEW_GROUP_CREATE = "/group/group_create";

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String SORT_EXPRESSION_NAME = "NAME";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "GroupViewController";

	/** The Constant GROUP. */
	private static final String GROUP = "group";

	/**
	 * Fetch list.
	 * 
	 * @param request the request
	 * @return the model (inquiry group response) and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_GROUP_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		InquiryGroupRequest inquiryGroupRequest = new InquiryGroupRequest();
		inquiryGroupRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_NAME, Direction.Ascending));

		// ADD user context to request
		addUserContextToRequest(inquiryGroupRequest);

		String pageSize = getUserSettings().getPageSize();

		if (!ValidationUtil.isNullOrEmpty(pageSize))
		{
			inquiryGroupRequest.setPageSize(Integer.valueOf(pageSize));
			inquiryGroupRequest.setPreQueryCount(true);
		}

		// Fetch Table
		InquiryGroupResponse inquiryGroupResponse = getGroupBCF().fetchAllGroups(inquiryGroupRequest);

		// Fetch Filter
		HashMap<String, Object> response = new HashMap<String, Object>();
		createFilterGroupType(response, request);
		ServiceTypeEnum serviceTypeEnum = ServiceTypeEnum.valueOf(getServiceType().toUpperCase());

		if (ServiceTypeEnum.ELECTRIC.equals(serviceTypeEnum))
		{
			createFilterGroupDeviceType(response, request);
		}

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(inquiryGroupResponse));
			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(response));

		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
			modelAndView.addObject(FILTERS, null);
		}

		return modelAndView;
	}

	/**
	 * Fetch update.
	 * 
	 * @param groupId the group id
	 * @param request the request
	 * @return the model (inquiry group response) and view
	 */
	@RequestMapping(value = FETCH_UPDATE, method = RequestMethod.GET)
	public ModelAndView loadUpdate(@RequestParam(ID) int id, HttpServletRequest request)
	{
		GroupResponse groupResponse = new GroupResponse();
		InquiryGroupRequest inquiryGroupRequest = new InquiryGroupRequest();

		// ADD user context to request
		addUserContextToRequest(inquiryGroupRequest);

		inquiryGroupRequest.addGroup(new Group(id));

		groupResponse = getGroupBCF().fetchGroupById(inquiryGroupRequest);
		ModelAndView modelAndView = new ModelAndView(VIEW_GROUP_CREATE);
		try
		{
			modelAndView.addObject(GROUP, getMapper().writeValueAsString(groupResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(GROUP, null);
		}

		return modelAndView;

	}

}
