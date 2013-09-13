package com.sensus.dm.common.schedule.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Interface IScheduleBCL.
 * 
 * @author QAT Global
 */
public interface IScheduleBCL
{

	/**
	 * Insert schedule on demand.
	 * 
	 * @param inquiryActionRequest the inquiry action request
	 * @return the internal response
	 */
	InternalResponse insertScheduleOnDemand(InquiryActionRequest inquiryActionRequest);

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
	 * Fetch schedules to be executed.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMSchedule> fetchSchedulesToExecute(ScheduleRequest request);

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
	 * Update schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse updateSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Delete schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse deleteSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryScheduleRequest the inquiry schedule request
	 * @return the internal response
	 */
	InternalResponse generateFileCSV(InquiryScheduleRequest inquiryScheduleRequest);

	/**
	 * Generate file csv schedule device.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVScheduleDevice(ScheduleRequest scheduleRequest);

	/**
	 * Update schedule status.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	InternalResponse updateScheduleStatus(ScheduleRequest scheduleRequest);

}
