package com.sensus.lc.schedule.bcl;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Interface IScheduleBCL.
 */
public interface IScheduleBCL
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
	 * @return the internal response
	 */
	InternalResponse insertSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Initiate delete light from schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateDeleteLightFromSchedule(
			ScheduleRequest scheduleRequest);

	/**
	 * Delete light from schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse deleteLightFromSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Initiate delete schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse initiateDeleteSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Delete schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse deleteSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Initiate update schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 * @throws DatatypeConfigurationException the datatype configuration exception
	 */
	InternalResponse initiateUpdateSchedule(ScheduleRequest scheduleRequest)
			throws DatatypeConfigurationException;

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
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<Schedule> fetchScheduleById(ScheduleRequest scheduleRequest);

	/**
	 * Apply light to schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse applyLightToSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Initiate apply light to schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateApplyLightToSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Can insert.
	 * 
	 * @param schedule the schedule
	 * @param list the list
	 * @param tenant the tenant
	 */
	void fetchCanInsert(Schedule schedule, List<MessageInfo> list, Tenant tenant);

	/**
	 * Can update.
	 * 
	 * @param schedule the schedule
	 * @param list the list
	 * @param tenant the tenant
	 */
	void fetchCanUpdate(Schedule schedule, List<MessageInfo> list, Tenant tenant);

	/**
	 * Apply unknown event schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse applyUnknownEventSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Apply unknown offset schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse applyUnknownOffsetSchedule(ScheduleRequest scheduleRequest);
}
