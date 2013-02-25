package com.sensus.mlc.wui.schedule.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.scheduler.model.Schedule;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.util.ModeEnum;

/**
 * The Class ScheduleBCFMockImpl.
 */
public class ScheduleBCFMockImpl extends BaseMockImpl implements IScheduleBCF
{

	/** The schedule name. */
	private String scheduleName = "Schedule ";

	/** The schedule id. */
	private Integer scheduleId = 1;

	/** The light id. */
	private Integer lightId = 1;

	/**
	 * Creates the schedule list.
	 * 
	 * @param listSize the list size
	 * @return the list
	 */
	private List<Schedule> createScheduleList(Integer listSize)
	{
		// Create User List
		List<Schedule> scheduleList = new ArrayList<Schedule>();

		// Create Users to set at list
		for (int i = 0; i < listSize; i++)
		{
			Schedule schedule = new Schedule();

			schedule.setModelAction(PersistanceActionEnum.NONE);
			schedule.setCreateDate(new Date());
			schedule.setId(i + this.scheduleId);
			schedule.setName(this.scheduleName + i);
			schedule.setDescription("Description" + i);
			schedule.setCenterLatitude(35.867950984340 + i);
			schedule.setCenterLongitude(35.867950984340 + i);
			schedule.setLight(new Light(this.lightId));

			if ((i % 2) == 0)
			{
				schedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
			}
			else
			{
				schedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
			}

			scheduleList.add(schedule);
		}

		return scheduleList;
	}

	/**
	 * Gets the schedule response default.
	 * 
	 * @return the schedule response default
	 */
	private ScheduleResponse getScheduleResponseDefault()
	{
		ScheduleResponse response = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setSchedules(createScheduleList(1));
			return response;
		}

		return (ScheduleResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcf.IScheduleBCF#fetchAllSchedules(com.sensus.mlc.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InquiryScheduleResponse fetchAllSchedules(InquiryScheduleRequest inquiryScheduleRequest)
	{

		InquiryScheduleResponse response = new InquiryScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setSchedules(createScheduleList(inquiryScheduleRequest.getPageSize()));
			return response;
		}

		return (InquiryScheduleResponse)testOtherDefaultModes(response);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#insertSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest)
	{
		this.scheduleName = scheduleRequest.getSchedule().getName();
		return getScheduleResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#initiateDeleteSmartpointFromSchedule(com.sensus.mlc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public ScheduleResponse initiateDeleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		this.lightId = scheduleRequest.getSelectionPaginationIds().get(0);
		return getScheduleResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcf.IScheduleBCF#deleteSmartpointFromSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse deleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#initiateDeleteSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse initiateDeleteSchedule(ScheduleRequest scheduleRequest)
	{
		this.scheduleId = scheduleRequest.getSelectionPaginationIds().get(0);
		return getScheduleResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#deleteSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#initiateUpdateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse initiateUpdateSchedule(ScheduleRequest scheduleRequest)
	{
		return getScheduleResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#updateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		this.scheduleId = scheduleRequest.getSchedule().getId();
		return getScheduleResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#fetchScheduleById(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest)
	{
		this.scheduleId = scheduleRequest.getSchedule().getId();
		return getScheduleResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcf.IScheduleBCF#applySmartPointToSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse applySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#initiateApplySmartPointToSchedule(com.sensus.mlc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public ScheduleResponse initiateApplySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		this.scheduleId = scheduleRequest.getSchedule().getId();
		this.lightId = scheduleRequest.getSelectionPaginationIds().get(0);
		return getScheduleResponseDefault();
	}
}
