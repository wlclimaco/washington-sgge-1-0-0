/*
 *
 */
package com.sensus.dm.common.process.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.dm.common.process.dac.IProcessTypeDAC;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;

/**
 * The Class ProcessTypeDACImpl.
 * 
 * @author QAT Global
 */
public class ProcessTypeDACImpl extends SqlSessionDaoSupport implements IProcessTypeDAC
{
	/** The Constant PROCESS_MAP. */
	private static final String PROCESS_MAP = "ProcessTypeMap.";

	/** The Constant FETCH_PROCESS_TYPE_BY_DESCRIPTION. */
	private static final String FETCH_ALL_TYPE_BY_DESCRIPTION = PROCESS_MAP + "fetchAllTypeByDescription";

	/** The Constant FETCH_ALL_PROCESS_CATEGORY. */
	private static final String FETCH_ALL_CATEGORY = PROCESS_MAP + "fetchAllCategory";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessTypeDAC#fetchProcessTypeByDescription(com.sensus.dm.common.process.model
	 * .request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessType> fetchProcessTypeByDescription(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessType> response = new InternalResultsResponse<ProcessType>();

		response.addResult(
				(ProcessType)SensusMyBatisDacHelper.doQueryForObject(
						getSqlSession(), FETCH_ALL_TYPE_BY_DESCRIPTION,
						processRequest.getFirstProcess().getProcessType()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessTypeDAC#fetchAllProcessCategory(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ProcessCategory> fetchAllProcessCategory(ProcessRequest processRequest)
	{
		return SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_CATEGORY, processRequest,
				new InternalResultsResponse<ProcessCategory>());
	}

}
