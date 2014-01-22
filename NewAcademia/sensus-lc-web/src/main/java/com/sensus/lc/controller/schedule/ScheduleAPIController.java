package com.sensus.lc.controller.schedule;

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
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.controller.light.LightAPIController;
import com.sensus.lc.schedule.bcf.IScheduleBCF;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.lc.schedule.model.response.ScheduleResponse;

/**
 * The Class DashBoardSearchAjaxAction is called when table is rendering.
 *
 * @author Weslley Silva
 */

/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/api/schedule")
public class ScheduleAPIController extends BaseController
{
	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	/** The Constant MAP_FETCH_ALL. */
	private static final String MAP_FETCH_ALL = "/fetchAll";

	/** The Constant MAP_DELETE. */
	private static final String MAP_DELETE = "/delete";

	/** The Constant MAP_INSERT. */
	private static final String MAP_INSERT = "/insert";

	/** The Constant MAP_INSERT_LIGHTS. */
	private static final String MAP_INSERT_LIGHTS = "/insertlights";

	/** The Constant MAP_UPDATE. */
	private static final String MAP_UPDATE = "/update";

	/** The Constant MAP_DELETE_LIGHTS. */
	private static final String MAP_DELETE_LIGHTS = "/deletelights";

	/** The analytics bcf. */
	private IScheduleBCF scheduleBCF;

	/** The Constant SELECTION_PAGINATION_IDS. */
	private static final String SELECTION_PAGINATION_IDS = "selectionPaginationIds";

	/** The Constant PAGINATION_ALL_SELECTED. */
	private static final String PAGINATION_ALL_SELECTED = "paginationAllSelected";

	/** The Constant SCHEDULE_TYPE_ENUM. */
	private static final String SCHEDULE_TYPE_ENUM = "scheduleTypeEnum";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ScheduleAPIController";

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
	 * Fetch all.
	 * 
	 * @param inquiryScheduleRequest the inquiry schedule request
	 * @param request the request
	 * @return the inquiry schedule response
	 */
	@RequestMapping(value = MAP_FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public InquiryScheduleResponse fetchAll(@RequestBody InquiryScheduleRequest inquiryScheduleRequest,
			HttpServletRequest request)
	{

		InquiryScheduleResponse inquiryScheduleResponse = new InquiryScheduleResponse();

		try
		{
			setUserContext(inquiryScheduleRequest, request);
			inquiryScheduleResponse = getScheduleBCF().fetchAllSchedules(inquiryScheduleRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryScheduleResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return inquiryScheduleResponse;

	}

	/**
	 * Fetch.
	 * 
	 * @param scheduleRequest the schedule request
	 * @param request the request
	 * @return the schedule response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public ScheduleResponse fetch(@RequestBody ScheduleRequest scheduleRequest, HttpServletRequest request)
	{

		ScheduleResponse scheduleResponse = new ScheduleResponse();

		try
		{
			setUserContext(scheduleRequest, request);
			scheduleResponse = getScheduleBCF().fetchScheduleById(scheduleRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, scheduleResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return scheduleResponse;

	}

	/**
	 * Insert.
	 * 
	 * @param scheduleRequest the schedule request
	 * @param request the request
	 * @return the schedule response
	 */
	@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST)
	@ResponseBody
	public ScheduleResponse insert(@RequestBody ScheduleRequest scheduleRequest, HttpServletRequest request)
	{

		ScheduleResponse scheduleResponse = new ScheduleResponse();

		try
		{
			setUserContext(scheduleRequest, request);
			scheduleResponse = getScheduleBCF().insertSchedule(scheduleRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, scheduleResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return scheduleResponse;

	}

	/**
	 * Update.
	 * 
	 * @param scheduleRequest the schedule request
	 * @param request the request
	 * @return the schedule response
	 */
	@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public ScheduleResponse update(@RequestBody ScheduleRequest scheduleRequest, HttpServletRequest request)
	{

		ScheduleResponse scheduleResponse = new ScheduleResponse();

		try
		{
			setUserContext(scheduleRequest, request);
			scheduleResponse = getScheduleBCF().updateSchedule(scheduleRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, scheduleResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return scheduleResponse;

	}

	/**
	 * Delete.
	 * 
	 * @param mapRequest the map request
	 * @param request the request
	 * @return the response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response delete(@RequestBody Map<String, Object> mapRequest, HttpServletRequest request)
	{

		ScheduleResponse scheduleResponse = new ScheduleResponse();
		try
		{
			ScheduleRequest scheduleRequest = new ScheduleRequest();

			List<Integer> ids = (List<Integer>)mapRequest.get(SELECTION_PAGINATION_IDS);

			if (!ValidationUtil.isNull(mapRequest.get(PAGINATION_ALL_SELECTED)))
			{
				scheduleRequest.setPaginationAllSelected((Boolean)mapRequest.get(PAGINATION_ALL_SELECTED));
			}

			if (!ValidationUtil.isNullOrEmpty(ids))
			{
				scheduleRequest.setSelectionPaginationIds(ids);

				if (ScheduleTypeEnum.EVENT.toString().equals(mapRequest.get(SCHEDULE_TYPE_ENUM)))
				{
					scheduleRequest.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
				}
				else
				{
					scheduleRequest.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
				}

				setUserContext(scheduleRequest, request);

				scheduleResponse = getScheduleBCF().initiateDeleteSchedule(scheduleRequest);
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, scheduleResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return scheduleResponse;

	}

	/**
	 * Initiate apply smart point to schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_INSERT_LIGHTS, method = RequestMethod.POST)
	@ResponseBody
	public Response initiateApplySmartPointToSchedule(@RequestBody ScheduleRequest scheduleRequest,
			HttpServletRequest request)
	{

		ScheduleResponse scheduleResponse = new ScheduleResponse();
		try
		{
			setUserContext(scheduleRequest, request);
			scheduleResponse = getScheduleBCF().initiateApplyLightToSchedule(scheduleRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, scheduleResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return scheduleResponse;

	}

	/**
	 * Initiate delete smartpoint from schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_DELETE_LIGHTS, method = RequestMethod.POST)
	@ResponseBody
	public Response initiateDeleteSmartpointFromSchedule(@RequestBody ScheduleRequest scheduleRequest,
			HttpServletRequest request)
	{

		ScheduleResponse scheduleResponse = new ScheduleResponse();
		try
		{
			setUserContext(scheduleRequest, request);
			scheduleResponse = getScheduleBCF().initiateDeleteLightFromSchedule(scheduleRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, scheduleResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return scheduleResponse;

	}

}
