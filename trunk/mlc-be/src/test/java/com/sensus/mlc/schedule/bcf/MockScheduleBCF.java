package com.sensus.mlc.schedule.bcf;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;

public class MockScheduleBCF extends AbstractMockBase implements IScheduleBCF
{
	@Override
	public ScheduleResponse applySmartPointToSchedule(ScheduleRequest request)
	{
		return getScheduleResponseBySituation();
	}

	@Override
	public ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		response.setOperationSuccess(true);
		return response;
	}

	@Override
	public ScheduleResponse deleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(Boolean.TRUE);
		}

		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Boolean.FALSE);
		}
		return response;
	}

	@Override
	public InquiryScheduleResponse fetchAllSchedules(InquiryScheduleRequest inquiryScheduleRequest)
	{
		return new InquiryScheduleResponse();
	}

	@Override
	public ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest)
	{
		return new ScheduleResponse();
	}

	@Override
	public ScheduleResponse initiateApplySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		return new ScheduleResponse();
	}

	@Override
	public ScheduleResponse initiateDeleteSchedule(ScheduleRequest scheduleRequest)
	{
		return new ScheduleResponse();
	}

	@Override
	public ScheduleResponse initiateDeleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		return new ScheduleResponse();
	}

	@Override
	public ScheduleResponse initiateUpdateSchedule(ScheduleRequest scheduleRequest)
	{
		return new ScheduleResponse();
	}

	@Override
	public ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest)
	{
		return new ScheduleResponse();
	}

	@Override
	public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		return new ScheduleResponse();
	}

	private ScheduleResponse getScheduleResponseBySituation()
	{
		ScheduleResponse response = new ScheduleResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(Boolean.TRUE);
		}

		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Boolean.FALSE);
		}
		return response;
	}
}