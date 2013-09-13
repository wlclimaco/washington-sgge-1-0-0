package com.sensus.dm.common.process.dac;

import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;

/**
 * The Interface IProcessDAC.
 * 
 * @author QAT Global.
 */
public interface IProcessDAC
{
	/**
	 * Insert process.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> insertProcess(ProcessRequest processRequest);

	/**
	 * Insert process property.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse insertProcessProperty(ProcessRequest processRequest);

	/**
	 * Update process.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse updateProcess(ProcessRequest processRequest);

	/**
	 * Fetch processes.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcesses(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Fetch process by id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcessById(ProcessRequest processRequest);

	/**
	 * Fetch monitored process.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchMonitoredProcess(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Fetch today processes.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchTodayProcesses(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Fetch processes in processing.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcessesInProcessing(InquiryProcessRequest request);

	/**
	 * Insert process items.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> insertProcessItems(ProcessRequest processRequest);

	/**
	 * Updates process items.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse updateProcessItems(ProcessRequest processRequest);

	/**
	 * Fetch check processing.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse fetchCheckProcessing(ProcessRequest processRequest);

	/**
	 * Fetch count monitored processes.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(ProcessRequest processRequest);

	/**
	 * Summarize process.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse summarizeProcess(ProcessRequest processRequest);

	/**
	 * Fetch process items by device.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> fetchProcessItemsByDevice(ProcessRequest processRequest);

	/**
	 * Fetch scheduled processes.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchScheduledProcesses(InquiryProcessRequest request);

	/**
	 * Fetch started processes.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchStartedProcesses(InquiryProcessRequest request);

	/**
	 * Fetch process items by schedule.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> fetchProcessItemsBySchedule(ProcessRequest processRequest);

	/**
	 * Fetch process items by process id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> fetchProcessItemsByProcessId(ProcessRequest processRequest);

	/**
	 * Fetch process items from process property.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> fetchProcessItemsProcessProperty(ProcessRequest processRequest);

	/**
	 * Fetch process by RNI event id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcessByRniEventId(ProcessRequest processRequest);

	/**
	 * Fetch process property.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Property> fetchProcessProperty(ProcessRequest processRequest);

	/**
	 * Fetch all process items.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> fetchAllProcessItems(ProcessRequest processRequest);
}
