package com.sensus.dm.common.process.bcf;

import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockProcessBCF.
 * 
 * @author QAT Global
 */
public class MockProcessBCF extends AbstractMockBase implements IProcessBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#updateProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse updateProcess(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		return new InquiryProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessById(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcessById(ProcessRequest processRequest)
	{
		return new InquiryProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchMonitoredProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchMonitoredProcess(InquiryProcessRequest inquiryProcessRequest)
	{
		return new InquiryProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchCountMonitoredProcesses(com.sensus.dm.common.process.model.
	 * request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchCountMonitoredProcesses(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchTodayProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@Override
	public InquiryProcessResponse fetchTodayProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		return new InquiryProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#checkLinkStatus(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse checkLinkStatus(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchCommunicationSummary(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse fetchCommunicationSummary(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchImportHanDeviceSummary(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchImportHanDeviceSummary(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchDemandReadPingSummary(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchDemandReadPingSummary(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessItemsBySchedule(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessItemsBySchedule(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessItemsByProcessId(com.sensus.dm.common.process.model.
	 * request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessItemsByProcessId(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchAllProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchAllProcessItems(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#updateProcessItemsToExpire(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse updateProcessItemsToExpire(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchRelays(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse fetchRelays(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchRelaysByProcessId(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchRelaysByProcessId(ProcessRequest processRequest)
	{
		return new ProcessResponse();
	}

}
