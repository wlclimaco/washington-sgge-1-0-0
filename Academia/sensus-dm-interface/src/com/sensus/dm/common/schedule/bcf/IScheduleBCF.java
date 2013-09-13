package com.sensus.dm.common.schedule.bcf;

import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.InquiryScheduleResponse;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Interface IScheduleBCF.
 * 
 * @author QAT Global.
 */
public interface IScheduleBCF
{

	/**
	 * Insert schedule on demand.
	 * 
	 * @param inquiryActionRequest the inquiry action request
	 * @return the schedule response
	 */
	ScheduleResponse insertScheduleOnDemand(InquiryActionRequest inquiryActionRequest);

	/**
	 * Fetch all schedule.
	 * 
	 * @param inquiryScheduleRequest the inquiry schedule request
	 * @return the inquiry schedule response
	 */
	InquiryScheduleResponse fetchAllSchedule(InquiryScheduleRequest inquiryScheduleRequest);

	/**
	 * Fetch schedule by id.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest);

	/**
	 * Fetch schedule by name.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse fetchScheduleByName(ScheduleRequest scheduleRequest);

	/**
	 * Fetch schedule by group.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse fetchScheduleByGroup(ScheduleRequest scheduleRequest);

	/**
	 * Fetch schedule by device.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse fetchScheduleByDevice(ScheduleRequest scheduleRequest);

	/**
	 * Insert schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Update schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Delete schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryScheduleRequest the inquiry schedule request
	 * @return the inquiry schedule response
	 */
	InquiryScheduleResponse generateFileCSV(InquiryScheduleRequest inquiryScheduleRequest);

	/**
	 * Generate file csv schedule device.
	 * 
	 * @param inquiryScheduleRequest the inquiry schedule request
	 * @return the schedule response
	 */
	ScheduleResponse generateFileCSVScheduleDevice(ScheduleRequest inquiryScheduleRequest);

	/**
	 * Update schedule status.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse updateScheduleStatus(ScheduleRequest scheduleRequest);

	/**
	 * Fetch schedule by action.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the schedule response
	 */
	ScheduleResponse fetchScheduleByAction(ScheduleRequest scheduleRequest);
}
