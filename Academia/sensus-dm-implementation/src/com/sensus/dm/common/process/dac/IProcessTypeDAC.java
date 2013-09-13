package com.sensus.dm.common.process.dac;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;

/**
 * The Interface IProcessDAC.
 * 
 * @author QAT Global.
 */
public interface IProcessTypeDAC
{

	/**
	 * Fetch process type by description.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessType> fetchProcessTypeByDescription(
			ProcessRequest processRequest);

	/**
	 * Fetch all process category.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<ProcessCategory> fetchAllProcessCategory(ProcessRequest processRequest);

}
