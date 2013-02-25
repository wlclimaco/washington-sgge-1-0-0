package com.sensus.mlc.schedule.bcl;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.tenant.model.Tenant;

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
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#insertSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
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
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#deleteSmartpointFromSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
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
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

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
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#fetchAllSchedules(com.sensus.mlc.schedule.model.request.
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
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#initiateDeleteSmartpointFromSchedule(com.sensus.mlc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Process> initiateDeleteSmartpointFromSchedule(
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
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#initiateUpdateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest
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
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#updateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
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
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#fetchScheduleById(com.sensus.mlc.schedule.model.request.ScheduleRequest)
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
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			response.addResult(TestBaseUtil.createOffSetSchedule());
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
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#applySmartPointToSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertScheduleFromSchedule(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcl.IScheduleBCL#initiateApplySmartPointToSchedule(com.sensus.mlc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Process> initiateApplySmartPointToSchedule(
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
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#fetchCanInsert(com.sensus.mlc.schedule.model.Schedule,
	 * java.util.List, com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public void fetchCanInsert(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#fetchCanUpdate(com.sensus.mlc.schedule.model.Schedule,
	 * java.util.List, com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public void fetchCanUpdate(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#applyUnknownEventSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownEventSchedule(ScheduleRequest scheduleRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcl.IScheduleBCL#applyUnknownOffsetSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownOffsetSchedule(ScheduleRequest scheduleRequest)
	{
		return new InternalResponse();
	}
}