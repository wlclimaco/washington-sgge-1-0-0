package com.sensus.mlc.process.dac;

import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;

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
	 * Fetch processes.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchProcesses(InquiryProcessRequest processRequest);

	/**
	 * Fetch unsubmitted processes.
	 * 
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> fetchUnsubmittedProcesses();

	/**
	 * Fetch tenant by id.
	 * 
	 * @param id the id
	 * @return the tenant
	 */
	InternalResultsResponse<Tenant> fetchTenantById(Integer id);

	/**
	 * Fetch tenant by rni code.
	 * 
	 * @param rniCode the rni code
	 * @return the internal results response
	 */
	InternalResultsResponse<Tenant> fetchTenantByRniCode(String rniCode);

	/**
	 * Fetch all tenant.
	 * 
	 * @return the internal results response
	 */
	InternalResultsResponse<Tenant> fetchAllTenant();

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
	 * Generate file csv.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the string
	 */
	InternalResponse generateFileCSV(InquiryProcessRequest inquiryProcessRequest);
}
