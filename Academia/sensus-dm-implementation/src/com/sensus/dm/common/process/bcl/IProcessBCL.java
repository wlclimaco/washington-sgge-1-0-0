package com.sensus.dm.common.process.bcl;

import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;

/**
 * The Interface IProcessBCL.
 * 
 * @author QAT Global
 */
public interface IProcessBCL
{
	/**
	 * Insert process.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> insertProcess(ProcessRequest processRequest);

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
	 * Fetch monitored process.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchMonitoredProcess(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Fetch process by id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcessById(ProcessRequest processRequest);

	/**
	 * Fetch today processes.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchTodayProcesses(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Fetch count monitored processes.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(ProcessRequest processRequest);

	/**
	 * Update process status.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse updateProcessStatus(ProcessRequest processRequest);

	/**
	 * Check link status.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Boolean> checkLinkStatus(ProcessRequest processRequest);

	/**
	 * Insert process items.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> insertProcessItems(ProcessRequest processRequest);

	/**
	 * Update process items.
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
	 * Fetch communication summary.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchCommunicationSummary(ProcessRequest processRequest);

	/**
	 * Fetch import han device summary.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchImportHanDeviceSummary(ProcessRequest processRequest);

	/**
	 * Fetch demand read ping summary.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchDemandReadPingSummary(ProcessRequest processRequest);

	/**
	 * Fetch process items by schedule.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResultsResponse<DMProcess> fetchProcessItemsBySchedule(ProcessRequest processRequest);

	/**
	 * Fetch started processes.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchStartedProcesses(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Fetch process items by process id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcessItemsByProcessId(ProcessRequest processRequest);

	/**
	 * Fetch processes item in processing.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcessesWithProcessItemInProcessing(ProcessRequest request);

	/**
	 * Fetch process by RNI event id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcessByRniEventId(ProcessRequest processRequest);

	/**
	 * Fetch all process items.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> fetchAllProcessItems(ProcessRequest processRequest);

	/**
	 * Update process items to expire.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse updateProcessItemsToExpire(ProcessRequest processRequest);

	/**
	 * Fetch relays.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> fetchRelays(ProcessRequest processRequest);

	/**
	 * Fetch relays by process id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> fetchRelaysByProcessId(ProcessRequest processRequest);

	/**
	 * Insert process property.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse insertProcessProperty(ProcessRequest request);
}
