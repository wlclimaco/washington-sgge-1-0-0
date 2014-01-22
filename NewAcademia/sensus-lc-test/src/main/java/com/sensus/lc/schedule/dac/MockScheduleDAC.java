package com.sensus.lc.schedule.dac;

import static com.sensus.lc.base.TestBaseUtil.createEventSchedule;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class MockScheduleDAC.
 */
public class MockScheduleDAC extends AbstractMockBase implements IScheduleDAC
{
	/** The Constant ARBITRARY_SUNSET_OFFSET_MINUTE_N_15. */
	private static final Integer ARBITRARY_SUNSET_OFFSET_MINUTE_N_15 = -15;

	/** The Constant ARBITRARY_SUNRISE_OFFSET_MINUTE_N_20. */
	private static final Integer ARBITRARY_SUNRISE_OFFSET_MINUTE_N_20 = -20;

	/** The Constant ARBITRARY_OFFSET_SCHEDULE_9999. */
	private static final Integer ARBITRARY_OFFSET_SCHEDULE_9999 = 9999;

	/** The Constant ARBITRARY_LIGHT_ID_1001. */
	private static final Integer ARBITRARY_LIGHT_ID_1001 = 1001;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#fetchAllSchedules(com.sensus.lc.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Schedule> fetchAllSchedules(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.dac.IScheduleDAC#insertSchedule(com.sensus.lc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Schedule> insertSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#deleteLightFromSchedule(com.sensus.lc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteLightFromSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.dac.IScheduleDAC#updateSchedule(com.sensus.lc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#fetchScheduleById(com.sensus.lc.schedule.model.Schedule)
	 */
	@Override
	public InternalResultsResponse<Schedule> fetchScheduleById(Schedule schedule)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			if (schedule.getName().equals("SunriseSunsetNegative"))
			{
				response = getScheduleOffSetSunriseSunsetNegativeResponse();
			}
			else
			{
				response = getScheduleOffSetResponse();
			}
		}
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#applyLightToSchedule(com.sensus.lc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResponse applyLightToSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#deleteSchedule(com.sensus.lc.schedule.model.Schedule)
	 */
	@Override
	public InternalResponse deleteSchedule(Schedule schedule)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#fetchCanDelete(com.sensus.lc.schedule.model.Schedule)
	 */
	@Override
	public Boolean fetchCanDelete(Schedule schedule)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return true;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return false;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#applyUnknownOffsetSchedule(com.sensus.lc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownOffsetSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#applyUnknownEventSchedule(com.sensus.lc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownEventSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#fetchCanInsert(com.sensus.lc.schedule.model.Schedule,
	 * java.util.List, com.sensus.lc.tenant.model.Tenant)
	 */
	@Override
	public Boolean fetchCanInsert(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return true;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return false;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#fetchCanUpdate(com.sensus.lc.schedule.model.Schedule,
	 * java.util.List, com.sensus.lc.tenant.model.Tenant)
	 */
	@Override
	public Boolean fetchCanUpdate(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return true;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return false;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.sensus.lc.schedule.dac.IScheduleDAC#fetchLightBySchedule(com.sensus.lc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightBySchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		if (!scheduleRequest.isMonitored())
		{
			List<Light> listLight = new ArrayList<Light>();
			Light light = new Light();
			light.setId(ARBITRARY_LIGHT_ID_1001);
			listLight.add(light);
			response.getResultsList().addAll(listLight);
		}

		return response;
	}

	/**
	 * Insert schedule from schedule.
	 *
	 * @param response the response
	 * @return the internal response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Schedule> insertScheduleFromSchedule(InternalResultsResponse<Schedule> response)
	{
		if (getAreaEnum() != LCAreaEnum.SCHEDULE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/**
	 * Gets the schedule response.
	 *
	 * @return the schedule response
	 */
	private InternalResultsResponse<Schedule> getScheduleResponse()
	{
		InternalResultsResponse<Schedule> response = new InternalResultsResponse<Schedule>();
		response.addResult(createEventSchedule());
		response.getResultsSetInfo().setTotalRowsAvailable(1);
		return response;
	}

	/**
	 * Gets the schedule off set response.
	 *
	 * @return the schedule off set response
	 */
	private InternalResultsResponse<Schedule> getScheduleOffSetResponse()
	{
		InternalResultsResponse<Schedule> response = new InternalResultsResponse<Schedule>();
		response.addResult(TestBaseUtil.createOffSetSchedule());
		response.getResultsSetInfo().setTotalRowsAvailable(1);
		return response;
	}

	/**
	 * Gets the schedule off set sunrise sunset negative response.
	 *
	 * @return the schedule off set sunrise sunset negative response
	 */
	private InternalResultsResponse<Schedule> getScheduleOffSetSunriseSunsetNegativeResponse()
	{
		OffsetSchedule offSetSchedule = new OffsetSchedule();
		offSetSchedule.setId(ARBITRARY_OFFSET_SCHEDULE_9999);
		offSetSchedule.setName("Sunrise Sunset Negative ");
		offSetSchedule.setDescription("Sunrise Sunset Negative");
		offSetSchedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		offSetSchedule.setSunriseOffsetMinutes(ARBITRARY_SUNRISE_OFFSET_MINUTE_N_20);
		offSetSchedule.setSunsetOffsetMinutes(ARBITRARY_SUNSET_OFFSET_MINUTE_N_15);
		offSetSchedule.setSunriseBefore(true);
		offSetSchedule.setSunsetBefore(true);

		InternalResultsResponse<Schedule> response = new InternalResultsResponse<Schedule>();
		response.addResult(offSetSchedule);

		response.getResultsSetInfo().setTotalRowsAvailable(1);
		return response;
	}
}