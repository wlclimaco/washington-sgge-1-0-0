package com.sensus.dm.common.schedule.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;

/**
 * The Interface IScheduleDAC.
 * 
 * @author QAT Brazil.
 */
public interface IScheduleDAC
{
	/**
	 * Fetch all schedule.
	 * 
	 * @param inquiryScheduleRequest the inquiry schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMSchedule> fetchAllSchedule(InquiryScheduleRequest inquiryScheduleRequest);

	/**
	 * Fetch schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMSchedule> fetchSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Fetch schedule by group.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMSchedule> fetchScheduleByGroup(ScheduleRequest scheduleRequest);

	/**
	 * Fetch schedule by device.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMSchedule> fetchScheduleByDevice(ScheduleRequest scheduleRequest);

	/**
	 * Insert schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMSchedule> insertSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Delete schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse deleteSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Update schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse updateSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Insert schedule property.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse insertScheduleProperty(ScheduleRequest scheduleRequest);

	/**
	 * Delete all schedule property.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse deleteAllScheduleProperty(ScheduleRequest scheduleRequest);

	/**
	 * Fetch can delete.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the boolean
	 */
	InternalResultsResponse<Boolean> canScheduleBeDeleted(ScheduleRequest scheduleRequest);

	/**
	 * Fetch can insert.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the boolean
	 */
	InternalResultsResponse<Boolean> canScheduleBeInserted(ScheduleRequest scheduleRequest);

	/**
	 * Fetch can update.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the boolean
	 */
	InternalResultsResponse<Boolean> canScheduleBeUpdated(ScheduleRequest scheduleRequest);

	/**
	 * Fetch schedules to execute.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMSchedule> fetchSchedulesToExecute(ScheduleRequest scheduleRequest);

}
