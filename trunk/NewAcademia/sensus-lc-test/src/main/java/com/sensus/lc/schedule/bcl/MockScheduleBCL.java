/*
 *
 */
package com.sensus.lc.schedule.bcl;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class MockScheduleBCL.
 */
public class MockScheduleBCL extends AbstractMockBase implements IScheduleBCL
{

	/** The Constant J_UNIT. */
	private static final String J_UNIT = "JUnit";

	/**
	 * Insert schedule from schedule.
	 * 
	 * @param response the response
	 * @return the internal response
	 */
	private InternalResponse insertScheduleFromSchedule(InternalResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.SCHEDULE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getInternalResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.bcl.IScheduleBCL#insertSchedule(com.sensus.lc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResponse insertSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.bcl.IScheduleBCL#deleteLightFromSchedule(com.sensus.lc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteLightFromSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.bcl.IScheduleBCL#initiateDeleteSchedule(com.sensus.lc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse initiateDeleteSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.bcl.IScheduleBCL#deleteSchedule(com.sensus.lc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.bcl.IScheduleBCL#fetchAllSchedules(com.sensus.lc.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Schedule> fetchAllSchedules(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InternalResultsResponse<Schedule> response = new InternalResultsResponse<Schedule>();

		if (getAreaEnum() != LCAreaEnum.SCHEDULE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.getResultsList().add(new EventSchedule());
			response.getResultsList().add(new EventSchedule());
			response.getResultsList().add(new EventSchedule());
			response.getResultsList().add(new EventSchedule());
			response.getResultsList().add(new EventSchedule());

			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.bcl.IScheduleBCL#initiateDeleteLightFromSchedule(com.sensus.lc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Process> initiateDeleteLightFromSchedule(
			ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);

			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.bcl.IScheduleBCL#initiateUpdateSchedule(com.sensus.lc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse initiateUpdateSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.bcl.IScheduleBCL#updateSchedule(com.sensus.lc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.bcl.IScheduleBCL#fetchScheduleById(com.sensus.lc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Schedule> fetchScheduleById(ScheduleRequest scheduleRequest)
	{
		return getInternalResultResponseBySituationFromSchedule();
	}

	/**
	 * Gets the internal result response by situation from schedule.
	 * 
	 * @return the internal result response by situation from schedule
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Schedule> getInternalResultResponseBySituationFromSchedule()
	{
		InternalResultsResponse<Schedule> response = new InternalResultsResponse<>();
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return response;
		}

		response.setStatus(Status.OperationSuccess);
		if ("EVENT".equals(getTestControl()))
		{
			response.addResult(TestBaseUtil.createEventSchedule());
			return response;
		}
		response.addResult(TestBaseUtil.createOffSetSchedule());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.schedule.bcl.IScheduleBCL#applyLightToSchedule(com.sensus.lc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse applyLightToSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.bcl.IScheduleBCL#initiateApplyLightToSchedule(com.sensus.lc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Process> initiateApplyLightToSchedule(
			ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();

		if (getAreaEnum() != LCAreaEnum.SCHEDULE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);

			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.bcl.IScheduleBCL#fetchCanInsert(com.sensus.lc.schedule.model.Schedule,
	 * java.util.List, com.sensus.lc.tenant.model.Tenant)
	 */
	@Override
	public void fetchCanInsert(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.bcl.IScheduleBCL#fetchCanUpdate(com.sensus.lc.schedule.model.Schedule,
	 * java.util.List, com.sensus.lc.tenant.model.Tenant)
	 */
	@Override
	public void fetchCanUpdate(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.bcl.IScheduleBCL#applyUnknownEventSchedule(com.sensus.lc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownEventSchedule(ScheduleRequest scheduleRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.schedule.bcl.IScheduleBCL#applyUnknownOffsetSchedule(com.sensus.lc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownOffsetSchedule(ScheduleRequest scheduleRequest)
	{
		return new InternalResponse();
	}
}