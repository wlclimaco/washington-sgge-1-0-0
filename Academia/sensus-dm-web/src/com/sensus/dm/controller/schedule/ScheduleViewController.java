package com.sensus.dm.controller.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
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
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.group.model.response.InquiryGroupResponse;
import com.sensus.dm.common.schedule.bcf.IScheduleBCF;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.InquiryScheduleResponse;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.response.InquiryTagResponse;
import com.sensus.dm.controller.filter.BaseFilterController;
import com.sensus.dm.controller.util.DMConvertUtil;

/**
 * The Class ScheduleViewController.
 */
@Controller
public class ScheduleViewController extends BaseFilterController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleViewController.class);

	/** The Constant ACTION. */
	private static final String ACTION = "action";

	/** The Constant EDIT_TYPE. */
	private static final String EDIT_TYPE = "editType";

	/** The Constant GROUP_ID. */
	private static final String ID = "id";

	/** The Constant FETCH_LIST. */
	private static final String FETCH_LIST = "/tab/schedule";

	/** The Constant FETCH_UPDATE. */
	private static final String FETCH_EDIT = "/schedule/edit";

	/** The Constant FETCH_CREATE. */
	private static final String FETCH_CREATE = "/schedule/create";

	/** The Constant VIEW_GROUP_MAIN. */
	private static final String VIEW_SCHEDULE_MAIN = "/schedule/schedule_main";

	/** The Constant VIEW_GROUP_CREATE. */
	private static final String VIEW_SCHEDULE_CREATE = "/schedule/schedule_create_main";

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String SORT_EXPRESSION_SCHEDULE = "action_date";

	/** The Constant SORT_EXPRESSION_GROUP. */
	private static final String SORT_EXPRESSION_GROUP = "NAME";

	/** The Constant SORT_EXPRESSION_TAG. */
	private static final String SORT_EXPRESSION_TAG = "name";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ScheduleViewController";

	/** The Constant GROUP. */
	private static final String SCHEDULE = "schedule";

	/** The Constant GROUP. */
	private static final String GROUP = "group";

	/** The Constant TAG. */
	private static final String TAG = "tag";

	/** The group bcf. */
	private IScheduleBCF scheduleBCF;

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param scheduleBCF the new schedule bcf
	 */
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Fetch list.
	 * 
	 * @param request the request
	 * @return the model (inquiry group response) and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_SCHEDULE_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		InquiryScheduleRequest inquiryScheduleRequest = new InquiryScheduleRequest();
		inquiryScheduleRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_SCHEDULE, Direction.Ascending));

		BaseSearch baseSearch = new BaseSearch();
		baseSearch.setStartDate(DMConvertUtil.getStartOfDay(new Date(), getUserSettings().getTimeZoneMinutes()));
		baseSearch.setEndDate(DMConvertUtil.getEndOfDay(new Date(), getUserSettings().getTimeZoneMinutes()));

		inquiryScheduleRequest.setBaseSearch(baseSearch);

		// ADD user context to request
		addUserContextToRequest(inquiryScheduleRequest);

		String pageSize = getUserSettings().getPageSize();

		if (!ValidationUtil.isNullOrEmpty(pageSize))
		{
			inquiryScheduleRequest.setPageSize(Integer.valueOf(pageSize));
			inquiryScheduleRequest.setPreQueryCount(true);
		}

		inquiryScheduleRequest.setScheduleStatusEnums(new ArrayList<ScheduleStatusEnum>());
		inquiryScheduleRequest.getScheduleStatusEnums().add(ScheduleStatusEnum.ENABLED);

		// Fetch Table
		InquiryScheduleResponse inquiryGroupResponse = getScheduleBCF().fetchAllSchedule(inquiryScheduleRequest);

		// Fetch Filter
		HashMap<String, Object> response = new HashMap<String, Object>();
		createFilterScheduleActionCategories(response, request);
		createFilterStatusScheduled(response, request);
		createFilterRepeats(response, request);
		createFilterUser(response);

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
	 * @param request the request
	 * @return the model (inquiry group response) and view
	 */
	@RequestMapping(value = FETCH_CREATE, method = RequestMethod.GET)
	public ModelAndView loadCreate(HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_SCHEDULE_CREATE);

		try
		{
			modelAndView.addObject(GROUP, getMapper().writeValueAsString(loadGroups()));
			modelAndView.addObject(TAG, getMapper().writeValueAsString(loadTags()));

		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(GROUP, null);
			modelAndView.addObject(TAG, null);
		}

		return modelAndView;

	}

	/**
	 * Fetch update.
	 * 
	 * @param id the id
	 * @param request the request
	 * @return the model (inquiry group response) and view
	 */
	@RequestMapping(value = FETCH_EDIT, method = RequestMethod.GET)
	public ModelAndView loadUpdate(@RequestParam(ID) int id, @RequestParam(EDIT_TYPE) String type,
			HttpServletRequest request)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();
		ScheduleRequest scheduleRequest = new ScheduleRequest();

		// ADD user context to request
		addUserContextToRequest(scheduleRequest);

		if (ACTION.equals(type))
		{
			DMAction action = new DMAction(id);

			DMSchedule schedule = new DMSchedule();
			schedule.setDmAction(action);

			scheduleRequest.setSchedule(schedule);

			// Fetch Schedule by Action ID
			scheduleResponse = getScheduleBCF().fetchScheduleByAction(scheduleRequest);
		}
		else
		{
			DMSchedule schedule = new DMSchedule(id);
			scheduleRequest.setSchedule(schedule);

			// Fetch Schedule by Schedule ID
			scheduleResponse = getScheduleBCF().fetchScheduleById(scheduleRequest);
		}

		ModelAndView modelAndView = new ModelAndView(VIEW_SCHEDULE_CREATE);

		try
		{
			modelAndView.addObject(SCHEDULE, getMapper().writeValueAsString(scheduleResponse));
			modelAndView.addObject(GROUP, getMapper().writeValueAsString(loadGroups()));
			modelAndView.addObject(TAG, getMapper().writeValueAsString(loadTags()));

		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(SCHEDULE, null);
			modelAndView.addObject(GROUP, null);
			modelAndView.addObject(TAG, null);
		}

		return modelAndView;

	}

	/**
	 * Load groups.
	 * 
	 * @return the inquiry group response
	 */
	private InquiryGroupResponse loadGroups()
	{
		// Fetch Group
		InquiryGroupRequest inquiryGroupRequest = new InquiryGroupRequest();
		inquiryGroupRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_GROUP, Direction.Ascending));

		// ADD user context to request
		addUserContextToRequest(inquiryGroupRequest);

		inquiryGroupRequest.setPreQueryCount(true);

		// set the base search with containDevices flag set to true,
		// so only groups and tags which contain devices will be returned
		inquiryGroupRequest.setDeviceSearch(new DeviceSearch(true));

		// Fetch Table
		return getGroupBCF().fetchAllGroups(inquiryGroupRequest);
	}

	/**
	 * Load tags.
	 * 
	 * @return the inquiry tag response
	 */
	private InquiryTagResponse loadTags()
	{
		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest();
		inquiryTagRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_TAG, Direction.Ascending));

		// ADD user context to request
		addUserContextToRequest(inquiryTagRequest);

		inquiryTagRequest.setPreQueryCount(true);

		// set the base search with containDevices flag set to true,
		// so only groups and tags which contain devices will be returned
		inquiryTagRequest.setBaseSearch(new BaseSearch(true));

		return getTagBCF().fetchAllTags(inquiryTagRequest);
	}

}
