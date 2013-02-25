package com.sensus.mlc.process.dac;

import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class MockProcessDAC.
 */
public class MockProcessDAC extends AbstractMockBase implements IProcessDAC
{

	/** The Constant PECO. */
	private static final String PECO = "PECO";

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchProcessById(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessById(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getResultsResponseProcessBySituation();

		if (ProcessStatusReasonEnum.ABORTED.equals(processRequest.getProcessStatusReasonEnum()))
		{
			response.getFirstResult().setRniCorrelationId("2");
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchMonitoredProcesses(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchUnsubmittedProcesses()
	 */
	@Override
	public InternalResultsResponse<Process> fetchUnsubmittedProcesses()
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		response.addResult(new Process());
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#insertProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> insertProcess(ProcessRequest processRequest)
	{

		return getResultsResponseProcessBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#unmonitorProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse unmonitorProcess(ProcessRequest processRequest)
	{
		return getResponseProcessBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#updateProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcess(ProcessRequest processRequest)
	{
		return getResponseProcessBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchProcessByRniId(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByRniId(ProcessRequest processRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchProcessByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByLight(LightRequest lightRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchProcesses(com.sensus.mlc.process.model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcesses(InquiryProcessRequest processRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchTenantById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantById(Integer id)
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		response.getResultsList().add(new Tenant(1, PECO, "tenantDescription", PECO));
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchTenantByRniCode(java.lang.String)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByRniCode(String rniCode)
	{
		return getResultsResponseTenantBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchProcessByFileName(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByFileName(ProcessRequest processRequest)
	{
		return getResultsResponseProcessBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchAllTenant()
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchAllTenant()
	{
		return getResultsResponseTenantBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#updateCSVDownloaded(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		return new InternalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchCountMonitoredProcesses(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(
			ProcessRequest processRequest)
	{
		return getResultsResponseHashMapBySituation();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#generateFileCSV(com.sensus.mlc.process.model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSV(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = generateFileCSVFromProcess(inquiryProcessRequest, response);
		return response;
	}

	/**
	 * Generate file csv from process.
	 *
	 * @param inquiryProcessRequest the inquiry process request
	 * @param response the response
	 * @return the internal response
	 */
	private InternalResponse generateFileCSVFromProcess(
			InquiryProcessRequest inquiryProcessRequest,
			InternalResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.PROCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			inquiryProcessRequest.setFileName(TestBaseUtil.FILE_NAME);
			return getInternalResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return response;
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
	 * Gets the results response tenant by situation.
	 *
	 * @return the results response tenant by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tenant> getResultsResponseTenantBySituation()
	{
		InternalResultsResponse<Tenant> internalResultsResponse = new InternalResultsResponse<Tenant>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getTenantResponse();
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
	 * Gets the tenant response.
	 *
	 * @return the tenant response
	 */
	private InternalResultsResponse<Tenant> getTenantResponse()
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		response.addResult(TestBaseUtil.createTenant());
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
}