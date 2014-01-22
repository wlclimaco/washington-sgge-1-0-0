package com.sensus.lc.schedule.bcf;

import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.lc.schedule.model.response.ScheduleResponse;

public class MockScheduleBCF extends AbstractMockBase implements IScheduleBCF
{
	@Override
	public ScheduleResponse applyLightToSchedule(ScheduleRequest request)
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
	public ScheduleResponse deleteLightFromSchedule(ScheduleRequest scheduleRequest)
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
	public ScheduleResponse initiateApplyLightToSchedule(ScheduleRequest scheduleRequest)
	{
		return new ScheduleResponse();
	}

	@Override
	public ScheduleResponse initiateDeleteSchedule(ScheduleRequest scheduleRequest)
	{
		return new ScheduleResponse();
	}

	@Override
	public ScheduleResponse initiateDeleteLightFromSchedule(ScheduleRequest scheduleRequest)
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