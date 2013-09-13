package com.sensus.dm.common.process.bcl.impl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.process.bcl.IProcessSummaryBCL;
import com.sensus.dm.common.process.dac.IProcessSummaryDAC;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.DemandResponseSetupAction;

/**
 * The Class ProcessBCLImpl.
 * 
 * @author QAT Global
 * 
 */
public class ProcessSummaryBCLImpl implements IProcessSummaryBCL
{

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The process summary dac. */
	private IProcessSummaryDAC processSummaryDAC;

	/**
	 * Gets the process summary dac.
	 * 
	 * @return the process summary dac
	 */
	public IProcessSummaryDAC getProcessSummaryDAC()
	{
		return processSummaryDAC;
	}

	/**
	 * Sets the process summary dac.
	 * 
	 * @param processSummaryDAC the new process summary dac
	 */
	public void setProcessSummaryDAC(IProcessSummaryDAC processSummaryDAC)
	{
		this.processSummaryDAC = processSummaryDAC;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessSummaryBCL#fetchDemandResponseProgramParticipation(com.sensus.dm.common
	 * .process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandResponseProgramParticipation(ProcessRequest processRequest)
	{
		return getProcessSummaryDAC().fetchDemandResponseProgramParticipation(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessSummaryBCL#fetchAllDemandResponseSetup(com.sensus.dm.common.process.
	 * model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchAllDemandResponseSetup(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> internalResponse =
				getProcessSummaryDAC().fetchAllDemandResponseSetup(processRequest);

		if (internalResponse.isInError() || !internalResponse.hasResults())
		{
			return internalResponse;
		}

		DMConvertUtil.convertPropertyToDemandResponseSetup(new DemandResponseSetupAction(internalResponse
				.getFirstResult().getAction()),
				internalResponse.getFirstResult().getProperties());

		return internalResponse;
	}

	/**
	 * Fetch demand response summary.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandResponseSummary(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response =
				getProcessSummaryDAC().fetchDemandResponseSummary(processRequest);
		if (response.isInError() || !response.hasResults())
		{
			return response;
		}

		DMConvertUtil.convertPropertyToDemandResponse(new DemandResponseEventAction(response.getFirstResult()
				.getAction()),
				response.getFirstResult().getProperties());

		return response;
	}

	/**
	 * Fetch process items by connect disconnect.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByConnectDisconnect(ProcessRequest processRequest)
	{
		return getProcessSummaryDAC().fetchProcessItemsByConnectDisconnect(processRequest);
	}

	/**
	 * Fetch process send han text message.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessSendHanTextMessage(
			ProcessRequest processRequest)
	{
		return getProcessSummaryDAC().fetchProcessSendHanTextMessage(processRequest);
	}

	/**
	 * Fetch processes demand response status.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessesDemandResponseStatus(
			ProcessRequest processRequest)
	{
		return getProcessSummaryDAC().fetchProcessesDemandResponseStatus(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessSummaryBCL#fetchLastTamperDetectTimeout(com.sensus.dm.common.process
	 * .model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchLastTamperDetectTimeout(ProcessRequest processRequest)
	{
		return getProcessSummaryDAC().fetchLastTamperDetectTimeout(processRequest);
	}

}
