package com.sensus.mlc.wui.schedule.unittest.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.DaysOfWeekEnum;
import com.sensus.mlc.schedule.model.Event;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class ScheduleBCFMockImpl extends BaseMockImpl implements IScheduleBCF
{

	public static final Integer SCHEDULE_COUNT = 20;
	public static final Integer SCHEDULE_FILTER_COUNT = 9;
	public static final Integer EVENT_COUNT = 10;
	public static final Integer OFFSET_COUNT = 10;
	public static final String SCHEDULE_NAME = "Schedule %d";
	public static final String SMARTPOINT_NAME = "SmartPoint %d";
	public static final String EVENT = "Event ";
	public static final String OFFSET = "OffSet ";
	private static final Integer LATITUDE = 16;
	private static final Integer LONGITUDE = 17;
	/** The PAG e_ size. */
	private Integer pageSize = 20;

	@Override
	public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		// Invalid inputs cover Failure scenario
		if ((scheduleRequest.getSchedule() == null) || (scheduleRequest.getSchedule().getId() == null)
				|| (scheduleRequest.getSchedule().getModelAction() != PersistanceActionEnum.UPDATE))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setSchedules(new ArrayList<Schedule>());
			response.getSchedules().add(scheduleRequest.getSchedule());
			response.getSchedules().get(0).setId(1);
			response.getSchedules().get(0).setModelAction(PersistanceActionEnum.UPDATE);
			return response;
		}

		throw new RuntimeException("Kaboom");
	}

	@Override
	public InquiryScheduleResponse fetchAllSchedules(InquiryScheduleRequest scheduleRequest)
	{
		InquiryScheduleResponse response = new InquiryScheduleResponse();

		if (scheduleRequest.getPageSize() > 0)
		{
			setPageSize(scheduleRequest.getPageSize());
		}
		else
		{
			setPageSize(20);
		}
		Integer startRow = scheduleRequest.getStartRow();
		Integer type = 1;

		if (MODE_SUCCESS.equals(getMode()))
		{
			String name = SCHEDULE_NAME;
			if ((scheduleRequest.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT))
			{
				name = EVENT + SCHEDULE_NAME;
				setPageSize(getPageSize() / 2);
				type = 2;
			}
			else if ((scheduleRequest.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET))
			{
				name = OFFSET + SCHEDULE_NAME;
				setPageSize(getPageSize() / 2);
				type = 1;
			}
			response.setSchedules(new ArrayList<Schedule>());
			for (int i = startRow; i < (startRow + getPageSize()); i++)
			{
				Schedule schedule = new EventSchedule();
				schedule.setId(i);
				schedule.setName(String.format(name, i));
				schedule.setDescription(String.format(name, i));
				schedule.setLights(new ArrayList<Light>());
				schedule.setCreateDate(new Date());
				schedule.setScheduleTypeValue(type);
				if ((scheduleRequest.getScheduleTypeEnum() == null))
				{
					if (type == 1)
					{
						type = 2;
					}
					else if (type == 2)
					{
						type = 1;
					}
				}
				for (int ii = 1; ii <= i; ii++)
				{

					Light sp = new Light();
					sp.setId(i);
					sp.setRniId(i);
					schedule.getLights().add(sp);
				}
				schedule.setSmartPointCount(schedule.getLights().size());
				response.getSchedules().add(schedule);
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(SCHEDULE_COUNT);
				response.setResultsSetInfo(resultsSetInfo);
			}
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		// Invalid inputs cover Failure scenario
		if ((scheduleRequest.getSchedule() == null) || (scheduleRequest.getSchedule().getName() == null)
				|| (scheduleRequest.getSchedule().getModelAction() != PersistanceActionEnum.INSERT))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setSchedules(new ArrayList<Schedule>());
			response.getSchedules().add(scheduleRequest.getSchedule());
			response.getSchedules().get(0).setId(1);
			response.getSchedules().get(0).setModelAction(PersistanceActionEnum.INSERT);
			return response;
		}

		throw new RuntimeException("Kaboom");
	}

	@Override
	public ScheduleResponse initiateDeleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		// Invalid inputs cover Failure scenario
		if (MODE_FAILURE.equals(getMode()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));

			return response;
		}

		if (MODE_SUCCESS.equals(getMode()))
		{
			Process process = new Process();
			process.setId(35);

			response.setProcesses(Arrays.asList(process));

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}

		throw new RuntimeException("Kaboom");

	}

	@Override
	public ScheduleResponse deleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		// Invalid inputs cover Failure scenario
		if ((scheduleRequest.getSchedule() == null) || (scheduleRequest.getSchedule().getLights() == null)
				|| (scheduleRequest.getSchedule().getLights().size() < 1)
				|| (scheduleRequest.getSchedule().getModelAction() != PersistanceActionEnum.UPDATE))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setSchedules(new ArrayList<Schedule>());
			response.getSchedules().add(scheduleRequest.getSchedule());
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ScheduleResponse initiateDeleteSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		// Invalid inputs cover Failure scenario
		if (scheduleRequest.getSelectionPaginationIds() == null)
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");

	}

	@Override
	public ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		// Invalid inputs cover Failure scenario
		if (scheduleRequest.getSchedule().getId() == null)
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ScheduleResponse initiateUpdateSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		// Invalid inputs cover Failure scenario
		if ((scheduleRequest.getSchedule() == null) || (scheduleRequest.getSchedule().getId() == null)
				|| (scheduleRequest.getSchedule().getModelAction() != PersistanceActionEnum.UPDATE))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setSchedules(new ArrayList<Schedule>());
			response.getSchedules().add(scheduleRequest.getSchedule());
			response.getSchedules().get(0).setId(1);
			response.getSchedules().get(0).setModelAction(PersistanceActionEnum.UPDATE);
			return response;
		}

		throw new RuntimeException("Kaboom");
	}

	@Override
	public ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		// Invalid inputs cover Failure scenario
		if ((scheduleRequest.getSchedule().getId() == null))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setSchedules(new ArrayList<Schedule>());

			if (scheduleRequest.getSchedule().getId() == 0)
			{
				// Add Offset Schedule
				OffsetSchedule offsetSchedule = new OffsetSchedule();
				offsetSchedule.setId(0);
				offsetSchedule.setName(SCHEDULE_NAME);
				offsetSchedule.setDescription("Description");
				offsetSchedule.setSunriseOffsetMinutes(5);
				offsetSchedule.setSunsetOffsetMinutes(10);
				offsetSchedule.setSunriseBefore(true);
				offsetSchedule.setSunsetBefore(false);
				response.getSchedules().add(offsetSchedule);
			}
			else if (scheduleRequest.getSchedule().getId() == 1)
			{

				// Add Events
				List<DaysOfWeekEnum> days = new ArrayList<DaysOfWeekEnum>();
				days.add(DaysOfWeekEnum.MONDAY);
				days.add(DaysOfWeekEnum.FRIDAY);
				List<DaysOfWeekEnum> days2 = new ArrayList<DaysOfWeekEnum>();
				days2.add(DaysOfWeekEnum.SATURDAY);
				days2.add(DaysOfWeekEnum.WEDNESDAY);

				List<Event> events = new ArrayList<Event>();
				Event event = new Event();
				event.setDays(days);
				event.setTime(new Date());
				event.setIntensity(0);
				events.add(event);
				Event event2 = new Event();
				event2.setDays(days2);
				event2.setTime(new Date());
				event2.setIntensity(6);
				events.add(event2);

				// Add Event Schedule
				EventSchedule eventSchedule = new EventSchedule();
				eventSchedule.setId(1);
				eventSchedule.setName(SCHEDULE_NAME);
				eventSchedule.setDescription("Description");
				eventSchedule.setEvents(events);
				response.getSchedules().add(eventSchedule);
			}

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ScheduleResponse applySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	@Override
	public ScheduleResponse initiateApplySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		// Invalid inputs cover Failure scenario
		if ((scheduleRequest.getSchedule() == null) || (scheduleRequest.getSchedule().getId() == null))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setSchedules(new ArrayList<Schedule>());
			response.getSchedules().add(scheduleRequest.getSchedule());
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public InquiryLightResponse fetchSmartpointByScheduleToMap(ScheduleRequest scheduleRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();
		if ((scheduleRequest.getSchedule() == null) || (scheduleRequest.getSchedule().getId() == null))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setLights(new ArrayList<Light>());
			for (int i = 1; i <= 3; i++)
			{
				Light sp = new Light();
				sp.setId(i);
				sp.setRniId(i);
				sp.setRniId(i);
				new ArrayList<Light>();
				List<LightParameter> lightParameters = new ArrayList<LightParameter>();
				lightParameters.add(new LightParameter(LATITUDE, "41.25816656"));
				lightParameters.add(new LightParameter(LONGITUDE, "41.25816656"));
				sp.setParameters(lightParameters);
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setLightStatusEnum(LightStatusEnum.ALARM);
				sp.setCurrentStatusMessage(statusMessage);
				response.getLights().add(sp);
			}
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

}
