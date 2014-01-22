/*
 *
 */
package com.sensus.lc.process.bcl;

import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessCSVRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.ProcessResponse;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Interface IProcessBCL.
 */
public interface IProcessBCL
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
	 * Fetch all process.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchAllProcess(InquiryProcessRequest processRequest);

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
	 * Fetch monitored processes.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchMonitoredProcesses(ProcessRequest processRequest);

	/**
	 * Retry process.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse retryProcess(ProcessRequest processRequest);

	/**
	 * Abort process.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse abortProcess(ProcessRequest processRequest);

	/**
	 * Fetch process by light.
	 * 
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchProcessByLight(LightRequest lightRequest);

	/**
	 * Submit process.
	 * 
	 * @param processRequest the process request
	 * @param action the action
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> submitProcess(ProcessRequest processRequest, LCAction action);

	/**
	 * Fetch rni link status.
	 * 
	 * @param tenant the tenant
	 * @return the boolean
	 */
	InternalResultsResponse<Boolean> fetchRniLinkStatus(Tenant tenant);

	/**
	 * Check rni status.
	 */
	void checkRniStatus();

	/**
	 * Sets the gateway active.
	 * 
	 * @param tenant the tenant
	 * @param value the new gateway active
	 */
	void setGatewayActive(Tenant tenant, Boolean value);

	/**
	 * Update csv downloaded.
	 * 
	 * @param processRequest the process request
	 * @return the internal response
	 */
	InternalResponse updateCSVDownloaded(ProcessRequest processRequest);

	/**
	 * Generate sumary file csv.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse generateSumaryFileCSV(ProcessRequest processRequest);

	/**
	 * Fetch count monitored processes.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(ProcessRequest processRequest);

	/**
	 * Insert csv process.
	 * 
	 * @param request the request
	 * @return the process response
	 */
	ProcessResponse insertCSVProcess(InquiryPaginationRequest request);

	/**
	 * Generate file csv.
	 * 
	 * @param request the request
	 * @return the cSV internal response
	 */
	CSVInternalResponse generateFileCSV(ProcessCSVRequest request);

	/**
	 * Fetch summary by process id.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchSummaryByProcessId(ProcessRequest processRequest);
}
