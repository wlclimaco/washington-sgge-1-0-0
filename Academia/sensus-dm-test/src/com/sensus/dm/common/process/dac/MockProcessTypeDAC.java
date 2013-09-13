package com.sensus.dm.common.process.dac;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockProcessDAC.
 * 
 * @author QAT Global.
 */
public class MockProcessTypeDAC extends AbstractMockBase implements IProcessTypeDAC
{

	/** The Constant ONE. */
	private static final int ONE = 1;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessTypeByDescription(com.sensus.dm.common.process.model
	 * .request.
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ProcessType> fetchProcessTypeByDescription(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		ProcessType pt = new ProcessType();
		pt.setId(ONE);

		return new InternalResultsResponse<ProcessType>(pt);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchAllProcessCategory(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessCategory> fetchAllProcessCategory(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<ProcessCategory>(TestBaseUtil.createProcessCategory());
	}

}