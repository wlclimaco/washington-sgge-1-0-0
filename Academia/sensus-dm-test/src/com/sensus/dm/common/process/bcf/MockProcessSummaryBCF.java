package com.sensus.dm.common.process.bcf;

import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockProcessSummaryBCF.
 * 
 * @author QAT Global.
 * 
 */
public class MockProcessSummaryBCF extends AbstractMockBase implements IProcessSummaryBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessSummaryBCF#fetchDemandResponseSummary(com.sensus.dm.common.process.model
	 * .request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchDemandResponseSummary(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessSummaryBCF#fetchDemandResponseProgramParticipation(com.sensus.dm.common
	 * .process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchDemandResponseProgramParticipation(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessSummaryBCF#fetchAllDemandResponseSetup(com.sensus.dm.common.process.
	 * model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchAllDemandResponseSetup(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessSummaryBCF#fetchProcessSendHanTextMessage(com.sensus.dm.common.process
	 * .model.request.ProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcessSendHanTextMessage(ProcessRequest processRequest)
	{
		return new InquiryProcessResponse();
	}

}
