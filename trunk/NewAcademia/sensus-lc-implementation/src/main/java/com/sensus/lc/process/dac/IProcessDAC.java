package com.sensus.lc.process.dac;

import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessRequest;

/**
 * The Interface IProcessDAO.
 */
public interface IProcessDAC
{
	/**
	 * Insert process.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> insertProcess(ProcessRequest processRequest);

	/**
	 * Update process.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse updateProcess(ProcessRequest processRequest);

	/**
	 * Unmonitor process.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse unmonitorProcess(ProcessRequest processRequest);

	/**
	 * Fetch monitored processes.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchMonitoredProcesses(ProcessRequest processRequest);

	/**
	 * Fetch process by id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchProcessById(ProcessRequest processRequest);

	/**
	 * Fetch process by rni id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchProcessByRniId(ProcessRequest processRequest);

	/**
	 * Fetch process by file name.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchProcessByFileName(ProcessRequest processRequest);

	/**
	 * Fetch process by light.
	 * 
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchProcessByLight(LightRequest lightRequest);

	/**
	 * Fetch all process.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchAllProcess(InquiryProcessRequest processRequest);

	/**
	 * Fetch unsubmitted processes.
	 * 
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchUnsubmittedProcesses();

	/**
	 * Update csv downloaded.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse updateCSVDownloaded(ProcessRequest processRequest);

	/**
	 * Fetch count monitored processes.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(ProcessRequest processRequest);

	/**
	 * Fetch summary by process id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchSummaryByProcessId(ProcessRequest processRequest);
}
