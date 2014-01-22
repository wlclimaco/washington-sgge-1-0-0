package com.sensus.lc.controller.schedule;

import java.io.IOException;

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
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.schedule.bcf.IScheduleBCF;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.lc.schedule.model.response.ScheduleResponse;

/**
 * The Class ScheduleViewController.
 */
/**
 * @author QAT-Global
 * 
 */
@Controller
public class ScheduleViewController extends BaseViewController
{

	/** The Constant FETCH_LIST. */
	public static final String FETCH_LIST = "";

	/** The Constant FETCH_UPDATE. */
	public static final String FETCH_UPDATE = "systemintelligence/schedule/insertEvent";

	/** The Constant VIEW_SCHEDULE_MAIN. */
	private static final String VIEW_SCHEDULE_MAIN = "/schedule/schedule_main";

	/** The Constant VIEW_SCHEDULE_OFFSET. */
	private static final String VIEW_SCHEDULE_OFFSET = "/schedule/schedule_create_offset";

	/** The Constant VIEW_SCHEDULE_EVENT. */
	private static final String VIEW_SCHEDULE_EVENT = "/schedule/schedule_create_event";

	/** The Constant INQUIRY_ACTION_TABLE. */
	private static final String INQUIRY_ACTION_TABLE = "table";

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String SORT_EXPRESSION_NAME = "NAME";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ScheduleViewController";

	/** The Constant SCHEDULE_ID. */
	private static final String SCHEDULE_ID = "id";

	/** The analytics bcf. */
	private IScheduleBCF scheduleBCF;

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the schedule bcf
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
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
	 * @return the model (inquiry schedule response) and view
	 */

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_SCHEDULE_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		InquiryScheduleRequest inquiryScheduleRequest = new InquiryScheduleRequest(getUserContext(request));
		// Check for page size
		Integer pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrZero(pageSize))
		{
			inquiryScheduleRequest.setPageSize(pageSize);
		}

		inquiryScheduleRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_NAME, Direction.Ascending));
		inquiryScheduleRequest.setAction(INQUIRY_ACTION_TABLE);

		InquiryScheduleResponse inquiryScheduleResponse = getScheduleBCF().fetchAllSchedules(inquiryScheduleRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(inquiryScheduleResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}
		return modelAndView;
	}

	/**
	 * Fetch.
	 * 
	 * @param scheduleRequest the schedule request
	 * @param request the request
	 * @return the schedule response
	 */
	@RequestMapping(value = "/systemintelligence/schedule/insertEvent", method = RequestMethod.GET)
	public ModelAndView loadListEvent(@RequestParam(value = SCHEDULE_ID, required = true) Integer scheduleId,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_SCHEDULE_EVENT);

		ScheduleResponse scheduleResponse = new ScheduleResponse();
		ScheduleRequest scheduleRequest = new ScheduleRequest();

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		Schedule schedule = new Schedule();
		schedule.setId(scheduleId);

		scheduleRequest.setSchedule(schedule);
		scheduleRequest.setUserContext(getUserContext(request));
		scheduleResponse = getScheduleBCF().fetchScheduleById(scheduleRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(scheduleResponse));
		}
		catch (Exception e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}
		return modelAndView;

	}

	/**
	 * Fetch.
	 * 
	 * @param scheduleRequest the schedule request
	 * @param request the request
	 * @return the schedule response
	 */
	@RequestMapping(value = "/systemintelligence/schedule/insertOffset", method = RequestMethod.GET)
	public ModelAndView loadListOffset(@RequestParam(value = SCHEDULE_ID, required = true) Integer scheduleId,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_SCHEDULE_OFFSET);

		ScheduleResponse scheduleResponse = new ScheduleResponse();
		ScheduleRequest scheduleRequest = new ScheduleRequest();

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		Schedule schedule = new Schedule();
		schedule.setId(scheduleId);

		scheduleRequest.setSchedule(schedule);
		scheduleRequest.setUserContext(getUserContext(request));
		scheduleResponse = getScheduleBCF().fetchScheduleById(scheduleRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(scheduleResponse));
		}
		catch (Exception e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}
		return modelAndView;

	}
}
