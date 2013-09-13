package com.sensus.dm.controller.schedule;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.Response;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.schedule.bcf.IScheduleBCF;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.ScheduleOrderByEnum;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.InquiryScheduleResponse;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.util.DMConvertUtil;

/**
 * The Class ScheduleAPIController.
 */
@Controller
@RequestMapping("api/schedule")
public class ScheduleAPIController extends BaseController
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleAPIController.class);

	/** The Constant CHECK_SCHEDULE. */
	private static final String CHECK_SCHEDULE = "checksSchedule";

	/** The Constant CHECK_EVENT. */
	private static final String CHECK_EVENT = "checkEvent";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ScheduleAPIController";

	/** The Constant DELETE. */
	private static final String DELETE = "/delete";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchAll";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant OPEN_UPDATE_BY_ACTION. */
	private static final String OPEN_UPDATE_BY_ACTION = "openUpdateByAction";

	/** The Constant OPEN_UPDATE. */
	private static final String OPEN_UPDATE_BY_ID = "openUpdateById";

	/** The Constant SCHEDULE_BY_DEVICE. */
	private static final String SCHEDULE_BY_DEVICE = "scheduleByDevice";

	/** The Constant SCHEDULE_NAME. */
	private static final String SCHEDULE_NAME = "scheduleName";

	/** The Constant STATUS_PAUSED. */
	private static final String STATUS_PAUSED = "statusPaused";

	/** The Constant STATUS_RESUME. */
	private static final String STATUS_RESUME = "statusResume";

	/** The Constant STATUS_FAILED. */
	private static final String STATUS_FAILED = "statusFailed";

	/** The Constant TYPE. */
	private static final String TYPE = "type";

	/** The Constant UPDATE. */
	private static final String UPDATE = "update";

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the scheduleBCF
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the scheduleBCF to set
	 */
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Fetch all.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchAll(@RequestBody InquiryScheduleRequest request)
	{
		InquiryScheduleResponse inquiryScheduleResponse = new InquiryScheduleResponse();

		try
		{
			if (ValidationUtil.isNullOrEmpty(request.getScheduleStatusEnums())
					&& request.getSortExpressions().get(0).getField()
							.equals("action_date"))
			{
				request.getSortExpressions().clear();
				request.getSortExpressions().add(
						new SortExpression(ScheduleOrderByEnum.STATUS.getValue(), Direction.Ascending));
				request.getSortExpressions().add(
						new SortExpression(ScheduleOrderByEnum.ACTION_DATE.getValue(), Direction.Ascending));
			}

			// ADD user context to request
			addUserContextToRequest(request);

			request.setPreQueryCount(true);

			inquiryScheduleResponse = getScheduleBCF().fetchAllSchedule(request);
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
	 * @param request the request
	 * @return the response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody Map<String, Object> request)
	{
		ScheduleResponse response = new ScheduleResponse();
		try
		{
			ScheduleRequest scheduleRequest = new ScheduleRequest();

			// ADD user context to request
			addUserContextToRequest(scheduleRequest);

			// Call fetchScheduleById method for to open schedule dialog on schedule page
			if (OPEN_UPDATE_BY_ID.equals(request.get(TYPE)))
			{
				DMSchedule schedule = new DMSchedule(Integer.parseInt(request.get(ID).toString()));
				scheduleRequest.setSchedule(schedule);

				return getScheduleBCF().fetchScheduleById(scheduleRequest);
			}

			// Call fetchScheduleByDevice method for to load schedule list at device detail page.
			if (SCHEDULE_BY_DEVICE.equals(request.get(TYPE)))
			{
				DMAction action = new DMAction();

				Device device = new Device(new Radio(new BigInteger(request.get(ID).toString())));
				action.addDevice(device);

				DMSchedule epmSchedule = new DMSchedule();
				epmSchedule.setDmAction(action);
				scheduleRequest.setSchedule(epmSchedule);

				return getScheduleBCF().fetchScheduleByDevice(scheduleRequest);
			}

			// Call fetchScheduleByGroup method for to verify if the group is scheduled or no.
			if (CHECK_SCHEDULE.equals(request.get(TYPE)))
			{
				DMAction action = new DMAction();

				action.setGroups(new ArrayList<Group>());

				List<String> ids = (ArrayList<String>)request.get(ID);

				for (String idGroup : ids)
				{
					action.getGroups().add(new Group(Integer.parseInt(idGroup)));
				}

				DMSchedule epmSchedule = new DMSchedule();
				epmSchedule.setDmAction(action);
				scheduleRequest.setSchedule(epmSchedule);

				return getScheduleBCF().fetchScheduleByGroup(scheduleRequest);
			}

			// Call fetchScheduleByName for to check if the schedule name there is already.
			if (CHECK_EVENT.equals(request.get(TYPE)))
			{
				scheduleRequest.setSchedule(new DMSchedule(request.get(SCHEDULE_NAME).toString()));

				return getScheduleBCF().fetchScheduleByName(scheduleRequest);
			}

			// Call fetchScheduleByAction method for to open schedule dialog on other pages
			if (OPEN_UPDATE_BY_ACTION.equals(request.get(TYPE)))
			{
				DMSchedule epmSchedule = new DMSchedule();
				epmSchedule.setDmAction(new DMAction(Integer.parseInt(request.get(ID).toString())));
				scheduleRequest.setSchedule(epmSchedule);

				return getScheduleBCF().fetchScheduleByAction(scheduleRequest);
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Delete.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response delete(@RequestBody Map<String, Integer> request)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			ScheduleRequest scheduleRequest = new ScheduleRequest();

			// ADD user context to request
			addUserContextToRequest(scheduleRequest);

			scheduleRequest.setSchedule(new DMSchedule(request.get(ID)));

			response = getScheduleBCF().deleteSchedule(scheduleRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Update.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public Response update(@RequestBody Map<String, Object> request)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			ScheduleRequest scheduleRequest =
					new ScheduleRequest(new DMSchedule(Integer.parseInt(request.get(ID).toString())));

			// ADD user context to request
			addUserContextToRequest(scheduleRequest);

			response = getScheduleBCF().fetchScheduleById(scheduleRequest);
			if (!response.isOperationSuccess())
			{
				return response;
			}

			DMSchedule schedule = response.getSchedules().get(0);

			if (STATUS_PAUSED.equals(request.get(TYPE)))
			{
				schedule.setStatusEnum(ScheduleStatusEnum.PAUSED);
			}
			else if (STATUS_RESUME.equals(request.get(TYPE)))
			{
				schedule.setStatusEnum(ScheduleStatusEnum.ENABLED);
			}
			else if (STATUS_FAILED.equals(request.get(TYPE)))
			{
				schedule.setStatusEnum(ScheduleStatusEnum.ENABLED);

				// New Next Execution Date
				Date serverUTC = DMConvertUtil.convertDateToDefaultUTC(new Date());

				// New Action Date = New Next Execution Date + (Old Action Date - Old Next Execution Date)
				Date actionDate =
						DMConvertUtil.convertDateToDefaultUTC(
								DMConvertUtil.convertIntegerToDate(
										DMConvertUtil.convertDateToInteger(serverUTC)
												+ (
												DMConvertUtil.convertDateToInteger(DMConvertUtil
														.convertDateToDefaultUTC(schedule
																.getDmAction().getActionTime())) -
												DMConvertUtil.convertDateToInteger(DMConvertUtil
														.convertDateToDefaultUTC(schedule
																.getFrequency().getNextExecution()))
												)));

				schedule.getDmAction().setActionTime(actionDate);
				schedule.getFrequency().setNextExecution(serverUTC);
			}

			scheduleRequest.setSchedule(schedule);

			response = getScheduleBCF().updateScheduleStatus(scheduleRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}
}