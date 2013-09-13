package com.sensus.dm.common.process.bcf;

import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;

/**
 * The Interface IProcessBCF.
 * 
 * @author QAT Global
 */
public interface IProcessTypeBCF
{

	/**
	 * Fetch all process category.
	 * 
	 * @param processRequest the process request
	 * @return the inquiry process response
	 */
	ProcessResponse fetchAllProcessCategory(ProcessRequest processRequest);
}
