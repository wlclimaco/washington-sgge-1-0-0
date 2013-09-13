package com.sensus.dm.common.process.bcf;

import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockProcessTypeBCF.
 * 
 * @author QAT Global
 */
public class MockProcessTypeBCF extends AbstractMockBase implements IProcessTypeBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessTypeBCF#fetchAllProcessCategory(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchAllProcessCategory(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

}
