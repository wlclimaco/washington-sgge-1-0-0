package com.sensus.dm.common.schedule.bcf;

import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.InquiryScheduleResponse;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Class MockScheduleBCF.
 */
public class MockScheduleBCF extends AbstractMockBase implements IScheduleBCF
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchAllSchedule(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InquiryScheduleResponse fetchAllSchedule(InquiryScheduleRequest inquiryScheduleRequest)
	{

		return new InquiryScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleById(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByName(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByName(ScheduleRequest scheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByGroup(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByGroup(ScheduleRequest scheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByDevice(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByDevice(ScheduleRequest scheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#insertSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#updateSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#deleteSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#generateFileCSV(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest
	 * )
	 */
	@Override
	public InquiryScheduleResponse generateFileCSV(InquiryScheduleRequest inquiryScheduleRequest)
	{

		return new InquiryScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#generateFileCSVScheduleDevice(com.sensus.dm.common.schedule.model.
	 * request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse generateFileCSVScheduleDevice(ScheduleRequest inquiryScheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#updateScheduleStatus(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse updateScheduleStatus(ScheduleRequest scheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByAction(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByAction(ScheduleRequest scheduleRequest)
	{

		return new ScheduleResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#insertScheduleOnDemand(com.sensus.dm.elec.action.model.request
	 * .InquiryActionRequest)
	 */
	@Override
	public ScheduleResponse insertScheduleOnDemand(InquiryActionRequest inquiryActionRequest)
	{
		return new ScheduleResponse();
	}

}
