package com.sensus.lc.process.dac;

import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessRequest;

/**
 * The Class MockProcessDAC.
 */
public class MockProcessDAC extends AbstractMockBase implements IProcessDAC
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.dac.IProcessDAC#fetchProcessById(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessById(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getResultsResponseProcessBySituation();

		if (ProcessStatusReasonEnum.ABORTED.equals(processRequest.getProcessStatusReasonEnum()))
		{
			response.getFirstResult().setRniCorrelationId("2");
		}

		if (getTestControl() == "ALREADY_RETRY")
		{
			response.getFirstResult().setHasChild(Boolean.TRUE);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.dac.IProcessDAC#fetchMonitoredProcesses(com.sensus.lc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.dac.IProcessDAC#fetchUnsubmittedProcesses()
	 */
	@Override
	public InternalResultsResponse<Process> fetchUnsubmittedProcesses()
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		response.addResult(new Process());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.dac.IProcessDAC#insertProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> insertProcess(ProcessRequest processRequest)
	{

		return getResultsResponseProcessBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.dac.IProcessDAC#unmonitorProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse unmonitorProcess(ProcessRequest processRequest)
	{
		return getResponseProcessBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.dac.IProcessDAC#updateProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcess(ProcessRequest processRequest)
	{
		return getResponseProcessBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.dac.IProcessDAC#fetchProcessByRniId(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByRniId(ProcessRequest processRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.dac.IProcessDAC#fetchProcessByLight(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByLight(LightRequest lightRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.dac.IProcessDAC#fetchProcessByFileName(com.sensus.lc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByFileName(ProcessRequest processRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.dac.IProcessDAC#updateCSVDownloaded(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.dac.IProcessDAC#fetchCountMonitoredProcesses(com.sensus.lc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(
			ProcessRequest processRequest)
	{
		return getResultsResponseHashMapBySituation();
	}

	/**
	 * Gets the results response process by situation.
	 * 
	 * @return the results response process by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Process> getResultsResponseProcessBySituation()
	{
		InternalResultsResponse<Process> internalResultsResponse = new InternalResultsResponse<Process>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getProcessResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the results response hash map by situation.
	 * 
	 * @return the results response hash map by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<HashMap<String, Integer>> getResultsResponseHashMapBySituation()
	{
		InternalResultsResponse<HashMap<String, Integer>> internalResultsResponse =
				new InternalResultsResponse<HashMap<String, Integer>>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getHashMapResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the response process by situation.
	 * 
	 * @return the response process by situation
	 */
	private InternalResponse getResponseProcessBySituation()
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getProcessResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/**
	 * Gets the process response.
	 * 
	 * @return the process response
	 */
	private InternalResultsResponse<Process> getProcessResponse()
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		response.addResult(TestBaseUtil.createProcess(TestBaseUtil.createLight(), null));
		response.getFirstResult().setTenant(TestBaseUtil.createTenant());
		return response;
	}

	/**
	 * Gets the hash map response.
	 * 
	 * @return the hash map response
	 */
	private InternalResultsResponse<HashMap<String, Integer>> getHashMapResponse()
	{
		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();

		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("String1", 1);
		paramMap.put("String2", 2);

		response.addResult(paramMap);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.dac.IProcessDAC#fetchAllProcess(com.sensus.lc.process.model.request.InquiryProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchAllProcess(InquiryProcessRequest processRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	@Override
	public InternalResultsResponse<Process> fetchSummaryByProcessId(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getResultsResponseProcessBySituation();

		if (ProcessStatusReasonEnum.ABORTED.equals(processRequest.getProcessStatusReasonEnum()))
		{
			response.getFirstResult().setRniCorrelationId("2");
		}

		return response;
	}
}