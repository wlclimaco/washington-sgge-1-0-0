package com.sensus.mlc.schedule.bcf;

import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;

/**
 * The Interface IScheduleBCF.
 */
public interface IScheduleBCF
{
	/**
	 * Fetch all schedules.
	 * 
	 * @param inquiryScheduleRequest the inquiry schedule request
	 * @return the inquiry schedule response
	 */
	InquiryScheduleResponse fetchAllSchedules(InquiryScheduleRequest inquiryScheduleRequest);

	/**
	 * Insert schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Initiate delete smartpoint from schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse initiateDeleteSmartpointFromSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Delete smartpoint from schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse deleteSmartpointFromSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Initiate delete schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse initiateDeleteSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Delete schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Initiate update schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse initiateUpdateSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Update schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Fetch schedule by id.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest);

	/**
	 * Apply smart point to schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse applySmartPointToSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Initiate apply smart point to schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse initiateApplySmartPointToSchedule(ScheduleRequest scheduleRequest);
}
