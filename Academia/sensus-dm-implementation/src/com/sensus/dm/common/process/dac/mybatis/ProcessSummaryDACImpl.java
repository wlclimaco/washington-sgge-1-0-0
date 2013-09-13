package com.sensus.dm.common.process.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.process.dac.IProcessSummaryDAC;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemHistory;
import com.sensus.dm.common.process.model.request.ProcessRequest;

/**
 * The Class ProcessSummaryDACImpl.
 * 
 * @author QAT Global
 */
public class ProcessSummaryDACImpl extends SqlSessionDaoSupport implements IProcessSummaryDAC
{

	/** The Constant PROCESS_SUMMARY_MAP. */
	private static final String PROCESS_SUMMARY_MAP = "ProcessSummaryMap.";

	/** The Constant FETCH_PROCESS_DEMAND_RESPONSE_SUMMARY. */
	private static final String FETCH_PROCESS_DEMAND_RESPONSE_SUMMARY = PROCESS_SUMMARY_MAP
			+ "fetchProcessDemandResponseSummary";

	/** The Constant FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE. */
	private static final String FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE = PROCESS_SUMMARY_MAP
			+ "fetchProcessSendHanTextMessage";

	/** The Constant FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION. */
	private static final String FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION = PROCESS_SUMMARY_MAP
			+ "fetchDemandResponseProgramParticipation";

	/** The Constant PROCESS_MAP_DEMAND_RESPONSE_SETUP. */
	private static final String PROCESS_MAP_DEMAND_RESPONSE_SETUP = PROCESS_SUMMARY_MAP
			+ "fetchDemandResponseSetup";

	/** The Constant PROCESS_MAP_TAMPER_DETECT_TIMEOUT. */
	private static final String PROCESS_MAP_TAMPER_DETECT_TIMEOUT = PROCESS_SUMMARY_MAP
			+ "fetchTamperDetectTimeout";

	/** The Constant FETCH_PROCESS_ITEMS_BY_CONNECT_DISCONNECT. */
	private static final String FETCH_PROCESS_ITEMS_BY_CONNECT_DISCONNECT = PROCESS_SUMMARY_MAP
			+ "fetchProcessItemsByConnectDisconnect";

	/** The Constant FETCH_PROCESSES_DEMAND_RESPONSE_STATUS. */
	private static final String FETCH_PROCESSES_DEMAND_RESPONSE_STATUS = PROCESS_SUMMARY_MAP
			+ "fetchProcessesDemandResponseStatus";

	/** The Constant FETCH_PROCESS_ITEMS_DEMAND_RESPONSE_SUMMARY. */
	private static final String FETCH_PROCESS_ITEMS_DEMAND_RESPONSE_SUMMARY = PROCESS_SUMMARY_MAP
			+ "fetchProcessItemDemandResponseSummary";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchDemandResponseProgramParticipation(com.sensus.dm.common
	 * .process.model.request.ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandResponseProgramParticipation(ProcessRequest processRequest)
	{
		List<ProcessItemHistory> historyList = SensusMyBatisDacHelper.doQueryForList(
				getSqlSession(), FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION,
				processRequest.getFirstProcess().getFirstProcessItem().getDevice());

		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();
		response.addResult(new DMProcess(new ProcessItem(historyList)));

		DMConvertUtil.checkResult(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchAllDemandResponseSetup(com.sensus.dm.common.process.
	 * model.request.ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchAllDemandResponseSetup(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response =
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), PROCESS_MAP_DEMAND_RESPONSE_SETUP,
						processRequest.getFirstProcess().getFirstProcessItem().getDevice().getRadio().getFlexNetId(),
						new InternalResultsResponse<DMProcess>());

		DMConvertUtil.checkResult(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchProcessItemsByConnectDisconnect(com.sensus.dm.common
	 * .process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByConnectDisconnect(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> response = new InternalResultsResponse<ProcessItem>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_ITEMS_BY_CONNECT_DISCONNECT,
				processRequest.getFirstProcess(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchDemandResponseSummary(com.sensus.dm.common.process.model
	 * .request.ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandResponseSummary(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_DEMAND_RESPONSE_SUMMARY,
				processRequest.getFirstProcess(), response);

		if (!ValidationUtil.isNull(response.getFirstResult()))
		{
			// specific query to import actions once the import can fail and dont create tblhandevice records
			response.getFirstResult().setProcessItems(
					SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_ITEMS_DEMAND_RESPONSE_SUMMARY,
							processRequest.getFirstProcess()));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessSendHanTextMessage(com.sensus.dm.common.process.model.
	 * request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessSendHanTextMessage(
			ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE,
				processRequest.getFirstProcess(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessesDemandResponseStatus(com.sensus.dm.common.process.model
	 * .
	 * request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessesDemandResponseStatus(
			ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESSES_DEMAND_RESPONSE_STATUS,
				processRequest.getFirstProcess(), response);

		DMConvertUtil.checkResult(response);

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchLastTamperDetectTimeout(com.sensus.dm.common.process
	 * .model.request.ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchLastTamperDetectTimeout(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response =
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), PROCESS_MAP_TAMPER_DETECT_TIMEOUT,
						processRequest.getFirstProcess().getFirstProcessItem().getDevice().getRadio().getFlexNetId(),
						new InternalResultsResponse<DMProcess>());

		DMConvertUtil.checkResult(response);

		return response;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

}
