package com.sensus.lc.process.bcl.impl;

import static com.sensus.lc.base.util.LCDateUtil.convertDateUTCToTimezone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.schedule.model.DaysOfWeekEnum;
import com.sensus.lc.schedule.model.Event;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.mlc.mlcserver.type.BlinkLevel;
import com.sensus.mlc.mlcserver.type.DayOfWeek;
import com.sensus.mlc.mlcserver.type.InitiateApplyScheduleRequest;
import com.sensus.mlc.mlcserver.type.LightLevel;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.ScheduleEvent;
import com.sensus.mlc.mlcserver.type.ScheduleEventData;
import com.sensus.mlc.mlcserver.type.ScheduleOffsetData;
import com.sensus.mlc.mlcserver.type.ScheduleType;
import com.sensus.mlc.mlcserver.type.ScheduledSmartpoint;

/**
 * The Class AddScheduleProcess.
 */
public class ProcessAdditionSmartpointSchedule extends AbstractProcessCommunicationGateway
{

	/**
	 * Instantiates a new process addition smartpoint schedule.
	 */
	ProcessAdditionSmartpointSchedule()
	{
		setSupportedActions(EnumSet.of(LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_EVENT,
				LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_OFFSET));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessCommunicationGateway#sendAction(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public MlcGatewayResponse sendAction(ProcessRequest processRequest)
	{
		UserContext userContext = processRequest.getUserContext();
		Tenant tenant = processRequest.getUserContext().getTenant();
		Process process = processRequest.getProcess();
		setRemoteUserCredentials(tenant);

		List<ScheduledSmartpoint> scheduledSmartpointList = getWsScheduleSmartpointList(userContext, process);

		if (ValidationUtil.isNullOrEmpty(scheduledSmartpointList))
		{
			return null;
		}

		InitiateApplyScheduleRequest request = getFactory().createInitiateApplyScheduleRequest();
		request.setCustomerID(tenant.getRniCode());
		request.setTransactionID(process.getRniCorrelationId());
		request.getScheduledSmartpoint().addAll(scheduledSmartpointList);

		persistLog(processRequest, scheduledSmartpointList);

		// Call Web Service
		return getMlcGatewayWs().setApplySchedule(request);
	}

	/**
	 * Gets the schedule ws new.
	 * 
	 * @param userContext the user context
	 * @param process the process
	 * @return the schedule ws new
	 */
	private List<ScheduledSmartpoint> getWsScheduleSmartpointList(UserContext userContext, Process process)
	{
		Tenant tenant = userContext.<Tenant> getTenant();
		Schedule schedule = new OffsetSchedule();
		schedule.setId(getScheduleIdByProcess(process));
		ScheduleRequest scheduleRequest = new ScheduleRequest(userContext);
		scheduleRequest.setSchedule(schedule);

		InternalResultsResponse<Schedule> response = getScheduleBCL().fetchScheduleById(scheduleRequest);
		if (!response.hasResults())
		{
			return new ArrayList<ScheduledSmartpoint>();
		}

		schedule = response.getFirstResult();
		if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			return getOffsetSmartpointList(userContext, process, schedule);
		}

		return getWsEventSmartpointList(process, tenant, schedule, userContext);
	}

	/**
	 * Gets the offset smartpoint list.
	 * 
	 * @param userContext the user context
	 * @param process the process
	 * @param schedule the schedule
	 * @return the offset smartpoint list
	 */
	private List<ScheduledSmartpoint> getOffsetSmartpointList(UserContext userContext, Process process,
			Schedule schedule)
	{
		LightRequest lightRequest = new LightRequest();
		lightRequest.setUserContext(userContext);
		lightRequest.setActionCriteria(new ActionCriteria());

		List<ScheduledSmartpoint> scheduledSmartpointsList = new ArrayList<ScheduledSmartpoint>();
		for (ProcessItem item : process.getProcessItems())
		{
			if (isFailure(item, process))
			{
				continue;
			}

			com.sensus.mlc.mlcserver.type.Schedule scheduleWsNew = new com.sensus.mlc.mlcserver.type.Schedule();

			scheduleWsNew.setScheduleType(ScheduleType.OFFSET);

			ScheduleOffsetData scheduleOffsetData = new ScheduleOffsetData();
			OffsetSchedule offSetSchedule = (OffsetSchedule)schedule;
			Integer turnLightsOffMinutes = offSetSchedule.getSunriseOffsetMinutes();
			if (!ValidationUtil.isNull(turnLightsOffMinutes) && offSetSchedule.getSunriseBefore())
			{
				turnLightsOffMinutes *= -1;
			}
			scheduleOffsetData.setSunriseOffset(turnLightsOffMinutes);
			Integer turnLightsOnMinutes = offSetSchedule.getSunsetOffsetMinutes();
			if (!ValidationUtil.isNull(turnLightsOnMinutes) && offSetSchedule.getSunsetBefore())
			{
				turnLightsOnMinutes *= -1;
			}
			scheduleOffsetData.setSunsetOffset(turnLightsOnMinutes);

			Light light = item.getLight();
			lightRequest.getActionCriteria().setPercentage(offSetSchedule.getIntensity());
			lightRequest.setLightCriteria(new LightCriteria());
			lightRequest.getLightCriteria().getLightIdList().add(light.getId());
			IntensityEnum intensity = getLightBCL().fetchLightIntensityByLight(lightRequest);
			scheduleOffsetData.setLightLevel(LightLevel.valueOf(intensity.name()));
			scheduleWsNew.setOffsetData(scheduleOffsetData);

			ScheduledSmartpoint scheduledSmartpoint = new ScheduledSmartpoint();
			scheduledSmartpoint.setRniId(light.getRadio().getFlexNetId().intValue());
			scheduledSmartpoint.setCustomerID(userContext.<Tenant> getTenant().getRniCode());
			scheduledSmartpoint.setSchedule(scheduleWsNew);
			scheduledSmartpointsList.add(scheduledSmartpoint);
		}

		return scheduledSmartpointsList;
	}

	/**
	 * Gets the ws event smartpoint list.
	 * 
	 * @param process the process
	 * @param tenant the tenant
	 * @param schedule the schedule
	 * @param userContext the user context
	 * @return the ws event smartpoint list
	 */
	private List<ScheduledSmartpoint> getWsEventSmartpointList(Process process, Tenant tenant, Schedule schedule,
			UserContext userContext)
	{
		List<ScheduledSmartpoint> scheduledSmartpointsList = new ArrayList<ScheduledSmartpoint>();

		for (ProcessItem item : process.getProcessItems())
		{
			if (isFailure(item, process))
			{
				continue;
			}

			com.sensus.mlc.mlcserver.type.Schedule scheduleWsNew = new com.sensus.mlc.mlcserver.type.Schedule();

			scheduleWsNew.setScheduleType(ScheduleType.EVENT);

			EventSchedule eventSchedule = (EventSchedule)schedule;
			ScheduleEventData scheduleEventData = new ScheduleEventData();
			setEventData(item, eventSchedule, scheduleEventData, tenant, userContext);
			scheduleWsNew.setEventData(scheduleEventData);

			ScheduledSmartpoint scheduledSmartpoint = new ScheduledSmartpoint();
			scheduledSmartpoint.setRniId(item.getLight().getRadio().getFlexNetId().intValue());
			scheduledSmartpoint.setCustomerID(tenant.getRniCode());
			scheduledSmartpoint.setSchedule(scheduleWsNew);
			scheduledSmartpointsList.add(scheduledSmartpoint);
		}

		return scheduledSmartpointsList;
	}

	/**
	 * Sets the event data.
	 * 
	 * @param item the item
	 * @param eventSchedule the event schedule
	 * @param scheduleEventData the schedule event data
	 * @param tenant the tenant
	 * @param userContext the user context
	 */
	private void setEventData(
			ProcessItem item,
			EventSchedule eventSchedule,
			ScheduleEventData scheduleEventData,
			Tenant tenant,
			UserContext userContext)
	{
		if (ValidationUtil.isNullOrEmpty(eventSchedule.getEvents()))
		{
			return;
		}

		LightRequest lightRequest = new LightRequest();
		lightRequest.setUserContext(userContext);
		lightRequest.setActionCriteria(new ActionCriteria());

		for (Event eventData : eventSchedule.getEvents())
		{
			ScheduleEvent scheduleEvent = new ScheduleEvent();
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(convertDateUTCToTimezone(eventData.getTime(), tenant.getLightTimeZone()));
			scheduleEvent.setEventHour(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
			scheduleEvent.setEventMinute(gregorianCalendar.get(Calendar.MINUTE));

			lightRequest.getActionCriteria().setPercentage(eventData.getIntensity());
			lightRequest.setLightCriteria(new LightCriteria());
			lightRequest.getLightCriteria().getLightIdList().add(item.getLight().getId());
			IntensityEnum intensity = getLightBCL().fetchLightIntensityByLight(lightRequest);
			scheduleEvent.setLightLevel(LightLevel.valueOf(intensity.toString()));
			for (DaysOfWeekEnum dayOfWeek : eventData.getDays())
			{
				scheduleEvent.getEventDayOfWeek().add(DayOfWeek.valueOf(dayOfWeek.toString()));
			}

			scheduleEvent.setBlink(BlinkLevel.NONE);
			if (!ValidationUtil.isNull(eventData.getLightBlinkEnum()))
			{
				scheduleEvent.setBlink(getBlinkLevel(eventData.getLightBlinkEnum().getValue()));
			}

			scheduleEventData.getScheduleEvent().add(scheduleEvent);
		}
	}

}
