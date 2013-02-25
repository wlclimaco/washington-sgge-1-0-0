package com.sensus.mlc.schedule.dac;

import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createSchedule;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tenant.model.Tenant;

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
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#fetchAllSchedules(com.sensus.mlc.schedule.model.request.
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
	 * com.sensus.mlc.schedule.dac.IScheduleDAC#insertSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
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
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#deleteSmartpointFromSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.dac.IScheduleDAC#updateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
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
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#fetchScheduleById(com.sensus.mlc.schedule.model.Schedule)
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#applySmartPointToSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Schedule> response = getScheduleResponse();
		response = insertScheduleFromSchedule(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#deleteSchedule(com.sensus.mlc.schedule.model.Schedule)
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
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#fetchCanDelete(com.sensus.mlc.schedule.model.Schedule)
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
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#applyUnknownOffsetSchedule(com.sensus.mlc.schedule.model.request.
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
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#applyUnknownEventSchedule(com.sensus.mlc.schedule.model.request.
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
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#fetchCanInsert(com.sensus.mlc.schedule.model.Schedule,
	 * java.util.List, com.sensus.mlc.tenant.model.Tenant)
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
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#fetchCanUpdate(com.sensus.mlc.schedule.model.Schedule,
	 * java.util.List, com.sensus.mlc.tenant.model.Tenant)
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.dac.IScheduleDAC#fetchSmartpointByScheduleToMap(com.sensus.mlc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Light> fetchSmartpointByScheduleToMap(ScheduleRequest scheduleRequest)
	{

		InternalResultsResponse<Light> response = getLightResponse();

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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#fetchSmartpointBySchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchSmartpointBySchedule(ScheduleRequest scheduleRequest)
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
		response.addResult(createSchedule());
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

	/**
	 * Gets the schedule response.
	 * 
	 * @return the schedule response
	 */
	private InternalResultsResponse<Light> getLightResponse()
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		response.addResult(createLight());
		response.getResultsSetInfo().setTotalRowsAvailable(1);
		return response;
	}

}