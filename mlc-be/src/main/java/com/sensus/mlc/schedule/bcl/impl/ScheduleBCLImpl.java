package com.sensus.mlc.schedule.bcl.impl;

import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.mlcserver.client.MlcServerClient;
import com.sensus.mlc.mlcserver.type.ObjectFactory;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.bcl.IScheduleBCL;
import com.sensus.mlc.schedule.dac.IScheduleDAC;
import com.sensus.mlc.schedule.model.DaysOfWeekEnum;
import com.sensus.mlc.schedule.model.Event;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ScheduleBCLImpl.
 */
public class ScheduleBCLImpl implements IScheduleBCL
{

	/** The Constant MANY_EVENTS. */
	private static final String MANY_EVENTS = "sensus.mlc.schedulevalidator.manyevents";

	/** The Constant RNI_EXCEPTION. */
	private static final String RNI_EXCEPTION = "sensus.mlc.schedulebcfimpl.rniexception";

	/** The Constant MLC_EXCEPTION. */
	private static final String MLC_EXCEPTION = "sensus.mlc.schedulebcfimpl.mlcexception";

	/** The Constant PROCESS_RUNNING. */
	private static final String PROCESS_RUNNING = "sensus.mlc.schedulebcfimpl.processrunning";

	/** The Constant MLC_SCHEDULE_DELETED. */
	private static final String MLC_SCHEDULE_DELETED = "sensus.mlc.schedulebcfimpl.mlcscheduledeleted";

	/** The Constant ALL_ROWS_PAGE_SIZE. */
	private static final int ALL_ROWS_PAGE_SIZE = 0;

	/** The Constant SMART_POINT_ACCESSOR_BCL_BEAN. */
	private static final String SMART_POINT_ACCESSOR_BCL_BEAN = "smartPointAccessorBCLTarget";

	/** The Constant TOO_MANY_EVENTS_IN_THE_SAME_DAY_BCL_VALIDATION. */
	private static final String TOO_MANY_EVENTS_IN_THE_SAME_DAY_BCL_VALIDATION =
			"sensus.mlc.schedulebclimpl.toomanyevents";

	/** The Constant FETCH_OFFSET_FAILED. */
	private static final String FETCH_OFFSET_FAILED = "sensus.mlc.schedulebcfimpl.fetch.offset.failed";

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The mlc gateway ws. */
	private MlcServerClient mlcGatewayWs; // injected by Spring through setter

	/** The factory. */
	private ObjectFactory factory; // injected by Spring through setter

	/** The schedule dac. */
	private IScheduleDAC scheduleDAC; // injected by Spring through setter

	/** The smart point updater bcl. */
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through Application Context

	/**
	 * Gets the lc help.
	 *
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the lc help.
	 *
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Gets the smart point accessor bcl.
	 *
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		if (smartPointAccessorBCL == null)
		{
			smartPointAccessorBCL =
					(ISmartPointAccessorBCL)SensusAppContext.getApplicationContext().getBean(
							SMART_POINT_ACCESSOR_BCL_BEAN);
		}
		return smartPointAccessorBCL;
	}

	/**
	 * Sets the smart point accessor bcl.
	 *
	 * @param smartPointAccessorBCL the new smart point accessor bcl
	 */
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL)
	{
		this.smartPointAccessorBCL = smartPointAccessorBCL;
	}

	/**
	 * Gets the smart point updater bcl.
	 *
	 * @return the smart point updater bcl
	 */
	public ISmartPointUpdaterBCL getSmartPointUpdaterBCL()
	{
		return smartPointUpdaterBCL;
	}

	/**
	 * Sets the smart point updater bcl.
	 *
	 * @param smartPointUpdaterBCL the new smart point updater bcl
	 */
	public void setSmartPointUpdaterBCL(ISmartPointUpdaterBCL smartPointUpdaterBCL)
	{
		this.smartPointUpdaterBCL = smartPointUpdaterBCL;
	}

	/**
	 * Gets the mlc gateway ws.
	 *
	 * @return the mlc gateway ws
	 */
	public MlcServerClient getMlcGatewayWs()
	{
		return mlcGatewayWs;
	}

	/**
	 * Sets the mlc gateway ws.
	 *
	 * @param mlcGatewayWs the new mlc gateway ws
	 */
	public void setMlcGatewayWs(MlcServerClient mlcGatewayWs)
	{
		this.mlcGatewayWs = mlcGatewayWs;
	}

	/**
	 * Gets the factory.
	 *
	 * @return the factory
	 */
	public ObjectFactory getFactory()
	{
		return factory;
	}

	/**
	 * Sets the factory.
	 *
	 * @param factory the new factory
	 */
	public void setFactory(ObjectFactory factory)
	{
		this.factory = factory;
	}

	/**
	 * Spring Sets the schedule dac.
	 *
	 * @param iScheduleDAC the new schedule dac
	 */
	public void setScheduleDAC(IScheduleDAC iScheduleDAC)
	{
		scheduleDAC = iScheduleDAC;
	}

	/**
	 * Gets the schedule dac.
	 *
	 * @return the schedule dac
	 */
	public IScheduleDAC getScheduleDAC()
	{
		return scheduleDAC;
	}

	/**
	 * Gets the process bcl.
	 *
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 *
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#fetchAllSchedules(com.sensus.mlc.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Schedule> fetchAllSchedules(InquiryScheduleRequest inquiryScheduleRequest)
	{
		return getScheduleDAC().fetchAllSchedules(inquiryScheduleRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#insertSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResponse insertSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = new InternalResponse();
		Schedule schedule = scheduleRequest.getSchedule();
		if ((schedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
				&& (validateEventFrequency(schedule).size() > 0))
		{
			response.setStatus(Status.ValidationError);
			response.addMessage(TOO_MANY_EVENTS_IN_THE_SAME_DAY_BCL_VALIDATION, MessageSeverity.Info,
					MessageLevel.None, new Object[] {response
					.getStatus().toString()});
			return response;
		}

		if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			validateOffsetSchedule((OffsetSchedule)schedule);
		}

		response = getScheduleDAC().insertSchedule(scheduleRequest);

		// create a Process for the created Schedule
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();

		LCAction action = new LCAction(LCActionTypeEnum.INSERT_SCHEDULE);
		actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_ID, schedule.getId().toString()));
		actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_NAME, schedule.getName()));
		action.setActionParameters(actionParameters);

		Process process = LCHelp.generateProcess(false, action, NumberUtils.INTEGER_ZERO);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());

		ProcessRequest processRequest = LCHelp.createProcessRequest(scheduleRequest, process);
		InternalResultsResponse<Process> processResponse = getProcessBCL().insertProcess(processRequest);
		response.addMessages(processResponse.getMessageInfoList());

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
		}

		return response;
	}

	/**
	 * Change the value of Sunrise and/or Sunset offset to negative if Sunrise and/or Sunset before is true.
	 *
	 * @param offsetSchedule the Offset Schedule
	 */
	private void validateOffsetSchedule(OffsetSchedule offsetSchedule)
	{

		if (!ValidationUtil.isNull(offsetSchedule.getSunriseOffsetMinutes()) && offsetSchedule.getSunriseBefore())
		{
			offsetSchedule.setSunriseOffsetMinutes(offsetSchedule.getSunriseOffsetMinutes() * -1);
		}
		if (!ValidationUtil.isNull(offsetSchedule.getSunsetOffsetMinutes()) && offsetSchedule.getSunsetBefore())
		{
			offsetSchedule.setSunsetOffsetMinutes(offsetSchedule.getSunsetOffsetMinutes() * -1);
		}

	}

	/**
	 * Verify if there are more than 4 events for a given day.
	 *
	 * @param schedule the schedule
	 * @return the list
	 */
	private List<Message> validateEventFrequency(Schedule schedule)
	{
		List<Message> messages = new ArrayList<Message>();

		final int maxNumberOfEvents = 4;

		HashMap<DaysOfWeekEnum, Integer> eventsPerDay = new HashMap<DaysOfWeekEnum, Integer>();
		for (DaysOfWeekEnum dayOfWeek : DaysOfWeekEnum.values())
		{
			eventsPerDay.put(dayOfWeek, 0);
		}

		// For Each Event...
		for (Event eventData : ((EventSchedule)schedule).getEvents())
		{
			// For Each DayOfWeek...
			for (DaysOfWeekEnum dayOfWeek : eventData.getDays())
			{
				eventsPerDay.put(dayOfWeek, eventsPerDay.get(dayOfWeek) + 1);
			}
		}

		Iterator<DaysOfWeekEnum> iterator = eventsPerDay.keySet().iterator();
		while (iterator.hasNext())
		{
			DaysOfWeekEnum current = iterator.next();
			if (eventsPerDay.get(current) > maxNumberOfEvents)
			{
				messages.add(new Message(MANY_EVENTS, MessageSeverity.Warning, MessageLevel.Other, new Object[] {
						current.name(), maxNumberOfEvents}));
			}
		}
		return messages;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#initiateDeleteSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse initiateDeleteSchedule(ScheduleRequest scheduleRequest)
	{
		// Instantiate a response
		InternalResponse response = new InternalResponse();

		List<Schedule> scheduleList = getSchedules(scheduleRequest);

		for (Schedule schedule : scheduleList)
		{
			String scheduleName = schedule.getName();
			String scheduleId = String.valueOf(schedule.getId());

			List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
			actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_ID, scheduleId));
			actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_NAME, scheduleName));

			LCAction action = new LCAction(LCActionTypeEnum.DEL_SCHEDULE);
			action.setActionParameters(actionParameters);

			scheduleRequest.setSchedule(schedule);

			ProcessRequest processRequest = createProcessRequest(scheduleRequest, ProcessItemStatusEnum.PENDING);

			List<Light> lights = getScheduleDAC().fetchSmartpointBySchedule(scheduleRequest).getResultsList();
			if (ValidationUtil.isNullOrEmpty(lights))
			{
				InternalResponse scheduleResponse = new InternalResponse();
				scheduleResponse = deleteSchedule(scheduleRequest);
				if (scheduleResponse.isInError())
				{
					return scheduleResponse;
				}
				continue;
			}

			ArrayList<Integer> lightIds = new ArrayList<Integer>();
			for (Light light : lights)
			{
				lightIds.add(light.getId());
			}

			processRequest.getSelectionPaginationIds().clear();
			processRequest.setSelectionPaginationIds(lightIds);

			InternalResultsResponse<Process> processResponse =
					getProcessBCL().submitProcess(processRequest, action);

			if (processResponse.isInError())
			{
				response.setStatus(processResponse.getStatus());
				response.addMessages(processResponse.getMessageInfoList());
				return response;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#deleteSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Schedule> responseSchedule =
				getScheduleDAC().fetchScheduleById(scheduleRequest.getSchedule());

		if (responseSchedule.isInError())
		{
			return responseSchedule;
		}

		Schedule schedule = responseSchedule.getFirstResult();
		String scheduleName = schedule.getName();

		// verify if there are Process processing for that schedule
		InternalResponse response = new InternalResponse();
		if (!getScheduleDAC().fetchCanDelete(schedule))
		{
			// There are Processes running, return with an error
			response.setStatus(InternalResponse.Status.ValidationError);
			response.addObjectErrorMessage(PROCESS_RUNNING, new Object[] {scheduleName});
			return response;
		}

		// Get Schedule Name
		responseSchedule = getScheduleDAC().fetchScheduleById(schedule);

		// Delete schedule in MLC
		InternalResponse responseDeleteSchedule = getScheduleDAC().deleteSchedule(schedule);
		if (responseDeleteSchedule.isInError())
		{
			return responseDeleteSchedule;
		}

		// SUCCESS
		response.addMessage(MLC_SCHEDULE_DELETED, MessageSeverity.Info, MessageLevel.Object,
				new Object[] {scheduleName});

		// create a Process for the created Schedule
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_ID, String.valueOf(schedule.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_NAME, scheduleName));

		LCAction action = new LCAction(LCActionTypeEnum.DEL_SCHEDULE);
		action.setActionParameters(actionParameters);

		Process process = LCHelp.generateProcess(false, action, NumberUtils.INTEGER_ZERO);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());

		ProcessRequest processRequest = createProcessRequest(scheduleRequest, process);
		InternalResultsResponse<Process> processResponse = getProcessBCL().insertProcess(processRequest);
		response.addMessages(processResponse.getMessageInfoList());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#initiateUpdateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse initiateUpdateSchedule(ScheduleRequest scheduleRequest)
			throws DatatypeConfigurationException
	{
		// If there are no events or offset changed or there are no smartpoints, update schedule on RNI and MLC
		if (noEventsOffsetChanged(scheduleRequest))
		{
			InternalResponse responseUpdateSchedule = updateSchedule(scheduleRequest);
			return responseUpdateSchedule;
		}

		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		InternalResponse responseUpdateSchedule = updateSchedule(scheduleRequest);

		Schedule schedule = scheduleRequest.getSchedule();
		String scheduleName = schedule.getName();
		if (responseUpdateSchedule.isInError())
		{
			// RNI Failure
			response.setStatus(InternalResponse.Status.ExternalError);
			response.addObjectErrorMessage(RNI_EXCEPTION, new Object[] {scheduleName});
			return response;
		}

		// getting all lights from this schedule
		InquiryLightRequest inquiryLightRequest = LCHelp.createInquiryLightRequest(scheduleRequest);
		SearchLight searchLight = inquiryLightRequest.getSearch();
		String scheduleId = String.valueOf(schedule.getId());
		searchLight.addSearchParameter(new SearchParameter(PropertyEnum.OFFSET_SCHEDULE, scheduleId));
		List<Light> lightList = getSmartPointAccessorBCL().fetchAllLights(inquiryLightRequest).getResultsList();
		schedule.setLights(lightList);

		// Get the type of schedule
		ScheduleRequest scheduleRequestApplyUnknown = new ScheduleRequest(scheduleRequest.getUserContext());
		scheduleRequestApplyUnknown.setSchedule(schedule);

		LCAction action = new LCAction();
		if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
		{
			action = new LCAction(LCActionTypeEnum.ADD_SMP_TO_SCHEDULE_EVENT);
			applyUnknownEventSchedule(scheduleRequestApplyUnknown);
		}
		else if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			action = new LCAction(LCActionTypeEnum.ADD_SMP_TO_SCHEDULE_OFFSET);
			applyUnknownOffsetSchedule(scheduleRequestApplyUnknown);
		}

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_ID, String.valueOf(schedule.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_NAME, scheduleName));
		action.setActionParameters(actionParameters);

		// If create a Process, it will be always monitored. There is no question to monitor or dismiss at
		// the front end
		scheduleRequest.setIsMonitored(true);

		ProcessRequest processRequest = createProcessRequest(scheduleRequest, ProcessItemStatusEnum.PENDING);
		InternalResultsResponse<Process> processResponse = getProcessBCL().submitProcess(processRequest, action);

		if (processResponse.isInError())
		{
			response.addObjectErrorMessage(MLC_EXCEPTION, new Object[] {
					scheduleName,
					String.valueOf(processResponse.getMessageInfoList().get(0))});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#updateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleRequest scheduleRequest)
	{

		if (scheduleRequest.getSchedule().getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			validateOffsetSchedule((OffsetSchedule)scheduleRequest.getSchedule());
		}

		InternalResponse response = getScheduleDAC().updateSchedule(scheduleRequest);

		if (!response.isInError())
		{
			// create a Process for the update schedule
			List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
			LCActionParameter actionParameterScheduleId =
					new LCActionParameter(PropertyEnum.SCHEDULE_ID, scheduleRequest.getSchedule().getId().toString());
			LCActionParameter actionParameterScheduleName =
					new LCActionParameter(PropertyEnum.SCHEDULE_NAME, scheduleRequest.getSchedule().getName());
			LCAction action = new LCAction(LCActionTypeEnum.UPDATE_SCHEDULE);

			actionParameters.add(actionParameterScheduleId);
			actionParameters.add(actionParameterScheduleName);
			action.setActionParameters(actionParameters);
			ProcessRequest processRequest = new ProcessRequest(scheduleRequest.getUserContext());
			List<Light> lightList = null;
			Process process = lcHelp.generateProcess(null, action, lightList);
			process.setIsProcessComplete(true);
			process.setIsMonitoredInstance(false);
			process.setEndTime(LCDateUtil.getNewDateUTC());
			processRequest.setProcess(process);
			processRequest.setTenant(scheduleRequest.getTenant());
			InternalResultsResponse<Process> processResponse = getProcessBCL().insertProcess(processRequest);
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#fetchScheduleById(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Schedule> fetchScheduleById(ScheduleRequest scheduleRequest)
	{

		InternalResultsResponse<Schedule> response =
				getScheduleDAC().fetchScheduleById(scheduleRequest.getSchedule());

		Schedule schedule = response.getFirstResult();
		if ((!ValidationUtil.isNull(schedule))
				&& (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET))
		{
			OffsetSchedule offsetSchedule = (OffsetSchedule)schedule;
			Integer turnLightsOffMinutes = offsetSchedule.getSunriseOffsetMinutes();

			if (!ValidationUtil.isNull(turnLightsOffMinutes))
			{
				if (turnLightsOffMinutes < 0)
				{
					offsetSchedule.setSunriseOffsetMinutes(turnLightsOffMinutes * -1);
					offsetSchedule.setSunriseBefore(true);
				}
				else
				{
					offsetSchedule.setSunriseBefore(false);
				}
			}
			Integer turnLightsOnMinutes = offsetSchedule.getSunsetOffsetMinutes();
			if (!ValidationUtil.isNull(turnLightsOnMinutes))
			{
				if (turnLightsOnMinutes < 0)
				{
					offsetSchedule.setSunsetOffsetMinutes(turnLightsOnMinutes * -1);
					offsetSchedule.setSunsetBefore(true);
				}
				else
				{
					offsetSchedule.setSunsetBefore(false);
				}
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#initiateApplySmartPointToSchedule(com.sensus.mlc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateApplySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		Schedule schedule = scheduleRequest.getSchedule();
		LCAction action = new LCAction(LCActionTypeEnum.ADD_SMP_TO_SCHEDULE_OFFSET);

		if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
		{
			action = new LCAction(LCActionTypeEnum.ADD_SMP_TO_SCHEDULE_EVENT);
		}

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_ID, schedule.getId().toString()));
		actionParameters.add(new LCActionParameter(PropertyEnum.SCHEDULE_NAME, schedule.getName()));
		action.setActionParameters(actionParameters);

		ProcessRequest processRequest = createProcessRequest(scheduleRequest, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#applySmartPointToSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getScheduleDAC().applySmartPointToSchedule(scheduleRequest);

		if (!response.isInError())
		{
			// If it is an offset schedule, also update offset properties of the smartpoints
			if (ScheduleTypeEnum.OFFSET.equals(scheduleRequest.getSchedule().getScheduleTypeEnum()))
			{
				response = updateSmartPointOffset(scheduleRequest);
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#deleteSmartpointFromSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		return getScheduleDAC().deleteSmartpointFromSchedule(scheduleRequest);
	}

	/**
	 * Update smart point offset.
	 *
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	private InternalResponse updateSmartPointOffset(ScheduleRequest scheduleRequest)
	{
		// get schedule information
		OffsetSchedule schedule = (OffsetSchedule)fetchScheduleById(scheduleRequest).getFirstResult();

		InternalResponse response = new InternalResponse();
		if (!ValidationUtil.isNull(schedule))
		{
			// Create request using data from scheduleRequest;
			LightStatusNotificationRequest request =
					new LightStatusNotificationRequest(scheduleRequest.getUserContext());
			request.setTenant(scheduleRequest.getTenant());

			LightRequest lightRequest = new LightRequest();
			GuaranteeLightExistenceRequest guaranteeRequest = new GuaranteeLightExistenceRequest();
			InternalResultsResponse<Light> internalResultResponse = new InternalResultsResponse<Light>();

			for (Light light : scheduleRequest.getSchedule().getLights())
			{
				lightRequest.addLight(light);

				//Verify if the light has been inserted on Light Schedule table.
				internalResultResponse = getSmartPointAccessorBCL().fetchLightScheduleById(lightRequest);

				light.getLightSchedule().setSunriseOffset(schedule.getSunriseOffsetMinutes());
				light.getLightSchedule().setSunsetOffset(schedule.getSunsetOffsetMinutes());

				//If null in light schedule insert
				if(!internalResultResponse.hasResults())
				{
					guaranteeRequest.addLight(light);
					getSmartPointUpdaterBCL().insertLightSchedule(guaranteeRequest);
					continue;
				}
				//If not null in light schedule update
				response = getSmartPointUpdaterBCL().updateLightSchedule(lightRequest);
			}

		}
		else
		{
			response.addMessage(FETCH_OFFSET_FAILED, MessageSeverity.Error, MessageLevel.Object);
			response.setStatus(InternalResponse.Status.SystemError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#initiateDeleteSmartpointFromSchedule(com.sensus.mlc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateDeleteSmartpointFromSchedule(
			ScheduleRequest scheduleRequest)
	{
		LCAction action = new LCAction(LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_OFFSET);
		if (isEventSchedule(scheduleRequest))
		{
			action = new LCAction(LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_EVENT);
		}

		ProcessRequest processRequest = createProcessRequest(scheduleRequest, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#canInsert(com.sensus.mlc.schedule.model.Schedule, java.util.List,
	 * com.sensus.mlc.base.model.Tenant)
	 */
	@Override
	public void fetchCanInsert(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{
		getScheduleDAC().fetchCanInsert(schedule, list, tenant);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#canUpdate(com.sensus.mlc.schedule.model.Schedule, java.util.List,
	 * com.sensus.mlc.base.model.Tenant)
	 */
	@Override
	public void fetchCanUpdate(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{
		getScheduleDAC().fetchCanUpdate(schedule, list, tenant);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#applyUnknownEventSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownEventSchedule(ScheduleRequest scheduleRequest)
	{
		return getScheduleDAC().applyUnknownEventSchedule(scheduleRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#applyUnknownOffsetSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownOffsetSchedule(ScheduleRequest scheduleRequest)
	{
		return getScheduleDAC().applyUnknownOffsetSchedule(scheduleRequest);
	}

	/**
	 * Checks if is event schedule.
	 *
	 * @param scheduleRequest the schedule request
	 * @return true, if is event schedule
	 */
	private boolean isEventSchedule(ScheduleRequest scheduleRequest)
	{
		if (!ValidationUtil.isNull(scheduleRequest.getScheduleTypeEnum())
				&& (scheduleRequest.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT))
		{
			return true;
		}

		if (!ValidationUtil.isNull(scheduleRequest.getSchedule()) && (scheduleRequest.getSchedule()
				.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT))
		{
			return true;
		}
		return false;
	}

	/**
	 * Gets the schedules.
	 *
	 * @param scheduleRequest the schedule request
	 * @return the schedules
	 */
	private List<Schedule> getSchedules(ScheduleRequest scheduleRequest)
	{
		// build a list of schedules based on the user selection
		InquiryScheduleRequest inquiryScheduleRequest = new InquiryScheduleRequest(scheduleRequest.getUserContext());
		inquiryScheduleRequest.setSelectionPaginationIds(scheduleRequest.getSelectionPaginationIds());
		inquiryScheduleRequest.setPaginationAllSelected(scheduleRequest.getPaginationAllSelected());
		inquiryScheduleRequest.setTenant(scheduleRequest.getTenant());
		inquiryScheduleRequest.setPageSize(ALL_ROWS_PAGE_SIZE);
		List<Schedule> scheduleList = getScheduleDAC().fetchAllSchedules(inquiryScheduleRequest).getResultsList();
		return scheduleList;
	}

	/**
	 * No events offset changed.
	 *
	 * @param scheduleRequest the schedule request
	 * @return true, if successful
	 */
	private boolean noEventsOffsetChanged(ScheduleRequest scheduleRequest)
	{
		Schedule newSchedule = scheduleRequest.getSchedule();
		Schedule oldSchedule = fetchScheduleById(scheduleRequest).getFirstResult();
		if (newSchedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			OffsetSchedule newScheduleOffset = (OffsetSchedule)newSchedule;
			OffsetSchedule oldScheduleOffset = (OffsetSchedule)oldSchedule;

			if (isNoTurnLightsOffMinutesChanged(newScheduleOffset, oldScheduleOffset)
					&& isNoTurnLightsOnMinutesChanged(newScheduleOffset, oldScheduleOffset)
					&& isNoSunriseBeforeChanged(newScheduleOffset, oldScheduleOffset)
					&& isNoSunsetBeforeChanged(newScheduleOffset, oldScheduleOffset))
			{
				return true;
			}

		}
		else if (newSchedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
		{
			EventSchedule newScheduleEvent = (EventSchedule)newSchedule;
			EventSchedule oldScheduleEvent = (EventSchedule)oldSchedule;
			if (newScheduleEvent.getEvents().size() == oldScheduleEvent.getEvents().size())
			{
				for (int i = 0; i < newScheduleEvent.getEvents().size(); i++)
				{
					Event newEvent = newScheduleEvent.getEvents().get(i);
					Event oldEvent = oldScheduleEvent.getEvents().get(i);
					if ((newEvent.getIntensity().equals(oldEvent.getIntensity()))
							&& (newEvent.getTime().getTime() == oldEvent.getTime().getTime())
							&& (newEvent.getDays().size() == oldEvent.getDays().size()))
					{
						for (DaysOfWeekEnum day : newEvent.getDays())
						{
							if (oldEvent.getDays().contains(day))
							{
								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * Checks if is no sunset before changed.
	 *
	 * @param newScheduleOffset the new schedule offset
	 * @param oldScheduleOffset the old schedule offset
	 * @return the boolean
	 */
	private Boolean isNoSunsetBeforeChanged(OffsetSchedule newScheduleOffset, OffsetSchedule oldScheduleOffset)
	{
		if (ValidationUtil.isNull(newScheduleOffset.getSunsetBefore())
				|| ValidationUtil.isNull(oldScheduleOffset.getSunsetBefore()))
		{
			if (ValidationUtil.isNull(newScheduleOffset.getSunsetBefore())
					&& ValidationUtil.isNull(oldScheduleOffset.getSunsetBefore()))
			{
				return true;
			}
		}
		else if (newScheduleOffset.getSunsetBefore().equals(oldScheduleOffset.getSunsetBefore()))
		{
			return true;
		}

		return false;
	}

	/**
	 * Checks if is no sunrise before changed.
	 *
	 * @param newScheduleOffset the new schedule offset
	 * @param oldScheduleOffset the old schedule offset
	 * @return the boolean
	 */
	private Boolean isNoSunriseBeforeChanged(OffsetSchedule newScheduleOffset, OffsetSchedule oldScheduleOffset)
	{
		if (ValidationUtil.isNull(newScheduleOffset.getSunriseBefore())
				|| ValidationUtil.isNull(oldScheduleOffset.getSunriseBefore()))
		{
			if (ValidationUtil.isNull(newScheduleOffset.getSunriseBefore())
					&& ValidationUtil.isNull(oldScheduleOffset.getSunriseBefore()))
			{
				return true;
			}
		}
		else if (newScheduleOffset.getSunriseBefore().equals(oldScheduleOffset.getSunriseBefore()))
		{
			return true;
		}

		return false;
	}

	/**
	 * Checks if is no turn lights on minutes changed.
	 *
	 * @param newScheduleOffset the new schedule offset
	 * @param oldScheduleOffset the old schedule offset
	 * @return the boolean
	 */
	private Boolean isNoTurnLightsOnMinutesChanged(OffsetSchedule newScheduleOffset, OffsetSchedule oldScheduleOffset)
	{
		if (ValidationUtil.isNull(newScheduleOffset.getSunsetOffsetMinutes())
				|| ValidationUtil.isNull(oldScheduleOffset.getSunsetOffsetMinutes()))
		{
			if (ValidationUtil.isNull(newScheduleOffset.getSunsetOffsetMinutes())
					&& ValidationUtil.isNull(oldScheduleOffset.getSunsetOffsetMinutes()))
			{
				return true;
			}
		}
		else if (newScheduleOffset.getSunsetOffsetMinutes().equals(oldScheduleOffset.getSunsetOffsetMinutes()))
		{
			return true;
		}

		return false;
	}

	/**
	 * Checks if is no turn lights off minutes changed.
	 *
	 * @param newScheduleOffset the new schedule offset
	 * @param oldScheduleOffset the old schedule offset
	 * @return the boolean
	 */
	private Boolean isNoTurnLightsOffMinutesChanged(OffsetSchedule newScheduleOffset, OffsetSchedule oldScheduleOffset)
	{
		if (ValidationUtil.isNull(newScheduleOffset.getSunriseOffsetMinutes())
				|| ValidationUtil.isNull(oldScheduleOffset.getSunriseOffsetMinutes()))
		{
			if (ValidationUtil.isNull(newScheduleOffset.getSunriseOffsetMinutes())
					&& ValidationUtil.isNull(oldScheduleOffset.getSunriseOffsetMinutes()))
			{
				return true;
			}
		}
		else if (newScheduleOffset.getSunriseOffsetMinutes().equals(oldScheduleOffset.getSunriseOffsetMinutes())
				&& (newScheduleOffset.getIntensity().intValue() == oldScheduleOffset.getIntensity().intValue()))
		{
			return true;
		}

		return false;
	}

}
