package com.sensus.lc.schedule.bcf;

import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.lc.schedule.model.response.ScheduleResponse;

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
	 * Initiate delete light from schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse initiateDeleteLightFromSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Delete light from schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse deleteLightFromSchedule(ScheduleRequest scheduleRequest);

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
	 * Apply light to schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse applyLightToSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Initiate apply light to schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse initiateApplyLightToSchedule(ScheduleRequest scheduleRequest);
}
