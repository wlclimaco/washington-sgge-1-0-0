package com.sensus.mlc.schedule.dac;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Interface IScheduleDAC.
 */
public interface IScheduleDAC
{
	/**
	 * Fetch all schedules.
	 * 
	 * @param inquiryScheduleRequest the inquiry schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<Schedule> fetchAllSchedules(InquiryScheduleRequest inquiryScheduleRequest);

	/**
	 * Insert schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<Schedule> insertSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Delete smartpoint from schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse deleteSmartpointFromSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Delete schedule.
	 * 
	 * @param schedule the schedule
	 * @return the internal response
	 */
	InternalResponse deleteSchedule(Schedule schedule);

	/**
	 * Update schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse updateSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Fetch schedule by id.
	 * 
	 * @param schedule the schedule
	 * @return the internal results response
	 */
	InternalResultsResponse<Schedule> fetchScheduleById(Schedule schedule);

	/**
	 * Apply smart point to schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse applySmartPointToSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Apply unknown offset schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse applyUnknownOffsetSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Apply unknown event schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse applyUnknownEventSchedule(ScheduleRequest scheduleRequest);

	/**
	 * fetch Can delete.
	 * 
	 * @param schedule the schedule
	 * @return the boolean
	 */
	Boolean fetchCanDelete(Schedule schedule);

	/**
	 * fetch Can insert.
	 * 
	 * @param schedule the schedule
	 * @param list the list
	 * @param tenant the tenant
	 * @return the boolean
	 */
	Boolean fetchCanInsert(Schedule schedule, List<MessageInfo> list, Tenant tenant);

	/**
	 * fetch Can update.
	 * 
	 * @param schedule the schedule
	 * @param list the list
	 * @param tenant the tenant
	 * @return the boolean
	 */
	Boolean fetchCanUpdate(Schedule schedule, List<MessageInfo> list, Tenant tenant);

	/**
	 * Fetch smartpoint by schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchSmartpointBySchedule(ScheduleRequest scheduleRequest);
}
