package com.sensus.mlc.process.bcf;

import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Interface IProcessBCF.
 */
public interface IProcessBCF
{
	/**
	 * Insert process.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse insertProcess(ProcessRequest processRequest);

	/**
	 * Update process.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse updateProcess(ProcessRequest processRequest);

	/**
	 * Unmonitor process.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse unmonitorProcess(ProcessRequest processRequest);

	/**
	 * Fetch monitored processes.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse fetchMonitoredProcesses(ProcessRequest processRequest);

	/**
	 * Fetch processes.
	 * 
	 * @param processRequest the process request
	 * @return the inquiry process response
	 */
	InquiryProcessResponse fetchProcesses(InquiryProcessRequest processRequest);

	/**
	 * Fetch process by id.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse fetchProcessById(ProcessRequest processRequest);

	/**
	 * Fetch process by transaction id.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse fetchProcessByTransactionId(ProcessRequest processRequest);

	/**
	 * Retry process.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse retryProcess(ProcessRequest processRequest);

	/**
	 * Abort process.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse abortProcess(ProcessRequest processRequest);

	/**
	 * Fetch process by light.
	 * 
	 * @param lightRequest the light request
	 * @return the process response
	 */
	ProcessResponse fetchProcessByLight(LightRequest lightRequest);

	/**
	 * Fetch rni link status.
	 * 
	 * @param processRequest the process request
	 * 
	 * @return the process response
	 */
	ProcessResponse fetchRniLinkStatus(ProcessRequest processRequest);

	/**
	 * Fetch tenant by rni code.
	 * 
	 * @param request the request
	 * @return the tenant response
	 */
	TenantResponse fetchTenantByRniCode(LightingControlRequest request);

	/**
	 * Update csv downloaded.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse updateCSVDownloaded(ProcessRequest processRequest);

	/**
	 * Generate sumary file csv.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse generateSummaryFileCSV(ProcessRequest processRequest);

	/**
	 * Fetch count monitored processes.
	 * 
	 * @param processRequest the process request
	 * @return the process response
	 */
	ProcessResponse fetchCountMonitoredProcesses(ProcessRequest processRequest);

	/**
	 * Insert csv process.
	 * 
	 * @param lightSelectionRequest the light selection request
	 * @return the process response
	 */
	ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the inquiry process response
	 */
	InquiryProcessResponse generateFileCSV(InquiryProcessRequest inquiryProcessRequest);
}
