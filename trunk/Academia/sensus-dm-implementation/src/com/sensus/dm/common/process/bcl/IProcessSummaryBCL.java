package com.sensus.dm.common.process.bcl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.ProcessRequest;

/**
 * The Interface IProcessBCL.
 * 
 * @author QAT Global
 */
public interface IProcessSummaryBCL
{

	/**
	 * Fetch all demand response setup.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchAllDemandResponseSetup(ProcessRequest processRequest);

	/**
	 * Fetch demand response program participation.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchDemandResponseProgramParticipation(ProcessRequest processRequest);

	/**
	 * Fetch demand response summary.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchDemandResponseSummary(ProcessRequest processRequest);

	/**
	 * Fetch process send han text message.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcessSendHanTextMessage(ProcessRequest processRequest);

	/**
	 * Fetch process items by connect disconnect.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessItem> fetchProcessItemsByConnectDisconnect(ProcessRequest processRequest);

	/**
	 * Fetch processes demand response status.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchProcessesDemandResponseStatus(ProcessRequest processRequest);

	/**
	 * Fetch last tamper detect timeout.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMProcess> fetchLastTamperDetectTimeout(ProcessRequest processRequest);

}
