package com.sensus.lc.controller.schedule.unittest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.schedule.bcf.IScheduleBCF;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.lc.schedule.model.response.ScheduleResponse;

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

	@Override
	public ScheduleResponse applyLightToSchedule(
			ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (ScheduleResponse)testOtherDefaultModes(response);
	}

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
			schedule.setId(i);
			schedule.setName(scheduleName);
			schedule.setDescription("Description");
			schedule.setCenterLatitude(35.867950984340 + i);
			schedule.setCenterLongitude(35.867950984340 + i);
			schedule.setLights(Arrays.asList(i));

			if (i % 2 == 0)
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

	@Override
	public ScheduleResponse deleteLightFromSchedule(
			ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (ScheduleResponse)testOtherDefaultModes(response);
	}

	@Override
	public ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (ScheduleResponse)testOtherDefaultModes(response);
	}

	@Override
	public InquiryScheduleResponse fetchAllSchedules(
			InquiryScheduleRequest inquiryScheduleRequest)
	{

		InquiryScheduleResponse response = new InquiryScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (ValidationUtil.isNullOrZero(inquiryScheduleRequest.getPageSize()))
			{
				inquiryScheduleRequest.setPageSize(25);
			}
			response.setSchedules(createScheduleList(inquiryScheduleRequest.getPageSize()));
			return response;
		}

		return (InquiryScheduleResponse)testOtherDefaultModes(response);

	}

	@Override
	public ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest)
	{
		scheduleId = scheduleRequest.getSchedule().getId();
		return getScheduleResponseDefault();
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

	@Override
	public ScheduleResponse initiateApplyLightToSchedule(
			ScheduleRequest scheduleRequest)
	{
		scheduleId = scheduleRequest.getSchedule().getId();
		lightId = scheduleRequest.getSelectionPaginationIds().get(0);
		return getScheduleResponseDefault();
	}

	@Override
	public ScheduleResponse initiateDeleteLightFromSchedule(
			ScheduleRequest scheduleRequest)
	{
		lightId = scheduleRequest.getSelectionPaginationIds().get(0);
		return getScheduleResponseDefault();
	}

	@Override
	public ScheduleResponse initiateDeleteSchedule(
			ScheduleRequest scheduleRequest)
	{
		scheduleId = scheduleRequest.getSelectionPaginationIds().get(0);
		return getScheduleResponseDefault();
	}

	@Override
	public ScheduleResponse initiateUpdateSchedule(
			ScheduleRequest scheduleRequest)
	{
		scheduleId = scheduleRequest.getSchedule().getId();
		return getScheduleResponseDefault();
	}

	@Override
	public ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest)
	{
		scheduleName = scheduleRequest.getSchedule().getName();
		return getScheduleResponseDefault();
	}

	@Override
	public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		scheduleId = scheduleRequest.getSchedule().getId();
		return getScheduleResponseDefault();
	}

}
