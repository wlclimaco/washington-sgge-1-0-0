package com.sensus.dm.common.process.bcl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockProcessTypeBCL.
 * 
 * @author QAT Global.
 */
public class MockProcessTypeBCL extends AbstractMockBase implements IProcessTypeBCL
{

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/** The Constant DECODE_FAILED. */
	public static final String DECODE_FAILED = "sensus.dm.common.process.nc.process.decode.failed ";

	/** The Constant DECODE_FAILED_WITHOUT_END_SPACE. */
	public static final String DECODE_FAILED_WITHOUT_END_SPACE = "sensus.dm.common.process.nc.process.decode.failed";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessTypeBCL#fetchProcessTypeByDescription(com.sensus.dm.common.process.model
	 * .request.ProcessRequest)
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
	 * com.sensus.dm.common.process.bcl.IProcessTypeBCL#fetchAllProcessCategory(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ProcessCategory> fetchAllProcessCategory(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessCategory> response = new InternalResultsResponse<ProcessCategory>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		response.addResults(TestBaseUtil.createProcessCategory());
		return response;
	}

}