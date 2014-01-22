package com.sensus.lc.process.bcl;

import static com.sensus.lc.base.TestBaseUtil.createLight;
import static com.sensus.lc.base.TestBaseUtil.createProcess;

import java.util.HashMap;
import java.util.Random;

import javax.annotation.Resource;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.group.bcl.IGroupBCL;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessCSVRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.ProcessResponse;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class MockProcessBCL.
 */
public class MockProcessBCL extends AbstractMockBase implements IProcessBCL
{
	/** The Constant ARBITRARY_PROCESS_ID_1000. */
	private static final Integer ARBITRARY_PROCESS_ID_1000 = 1000;

	/** The group bcl. */
	private IGroupBCL groupBCL;

	/**
	 * Gets the group bcl.
	 * 
	 * @return the group bcl
	 */
	public IGroupBCL getGroupBCL()
	{
		return groupBCL;
	}

	/**
	 * Sets the group bcl.
	 * 
	 * @param groupBCL the new group bcl
	 */
	@Resource(name = "groupBCLMockTarget")
	public void setGroupBCL(IGroupBCL groupBCL)
	{
		this.groupBCL = groupBCL;
	}

	/**
	 * Instantiates a new mock process bcl.
	 */
	public MockProcessBCL()
	{
	}

	/**
	 * Insert process from process.
	 * 
	 * @param response the response
	 * @return the internal response
	 */
	private InternalResponse insertProcessFromProcess(InternalResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.PROCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getInternalResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/**
	 * Fetch process from process.
	 * 
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Process> fetchProcessFromProcess(InternalResultsResponse<Process> response)
	{
		if (getAreaEnum() != LCAreaEnum.PROCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResultResponseProcess();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#insertProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> insertProcess(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();
		response = fetchProcessFromProcess(response);
		response = insertProcessFromEcoMode(processRequest, response);
		response = insertProcessFromSchedule(processRequest, response);
		response = insertProcessFromLight(processRequest, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#updateProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcess(ProcessRequest processRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertProcessFromProcess(response);
		response = updateProcessFromAnalyticsOrSmartPoint(processRequest, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#unmonitorProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse unmonitorProcess(ProcessRequest processRequest)
	{
		InternalResponse response = getInternalResponseDefault();

		if (getAreaEnum() == LCAreaEnum.ANALYTICS)
		{
			return updateProcessFromAnalyticsOrSmartPoint(processRequest, response);
		}

		response = insertProcessFromProcess(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcl.IProcessBCL#fetchMonitoredProcesses(com.sensus.lc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();
		response = fetchProcessFromProcess(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#fetchProcessById(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessById(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();
		response = fetchProcessFromProcess(response);
		response = fetchProcessByIdFromEcoMode(processRequest, response);
		response = fetchProcessByIdFromSmartpoint(processRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcl.IProcessBCL#fetchProcessByRniId(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByRniId(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();
		response = fetchProcessFromProcess(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#retryProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse retryProcess(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();

		if (processRequest.getProcess().getId().intValue() == ARBITRARY_PROCESS_ID_1000)
		{
			response.setStatus(Status.ExceptionError);
		}
		else
		{
			response = getInternalResponseDefault();
			response = insertProcessFromProcess(response);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#abortProcess(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse abortProcess(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();

		if (processRequest.getProcess().getId().intValue() == ARBITRARY_PROCESS_ID_1000)
		{
			response.setStatus(Status.ExceptionError);
		}
		else
		{
			response = getInternalResponseDefault();
			response = insertProcessFromProcess(response);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Process> fetchAllProcess(InquiryProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();
		response = fetchProcessFromProcess(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcl.IProcessBCL#fetchProcessByLight(com.sensus.lc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByLight(LightRequest lightRequest)
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();
		response = fetchProcessFromProcess(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#checkRniStatus()
	 */
	@Override
	public void checkRniStatus()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#fetchRniLinkStatus(com.sensus.lc.tenant.model.Tenant)
	 */
	@Override
	public InternalResultsResponse<Boolean> fetchRniLinkStatus(Tenant tenant)
	{

		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();
		response.addResult(true);
		response.setStatus(Status.OperationSuccess);

		if (getAreaEnum() != LCAreaEnum.PROCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.getResultsList().clear();
			response.setStatus(Status.ExceptionError);
			response.addResult(false);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcl.IProcessBCL#fetchProcessByFileName(com.sensus.lc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByFileName(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();
		response = fetchProcessFromProcess(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#setGatewayActive(com.sensus.lc.tenant.model.Tenant,
	 * java.lang.Boolean)
	 */
	@Override
	public void setGatewayActive(Tenant tenant, Boolean value)
	{
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcl.IProcessBCL#updateCSVDownloaded(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = insertProcessFromProcess(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcl.IProcessBCL#generateSumaryFileCSV(com.sensus.lc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse generateSumaryFileCSV(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		Light light = createLight();
		Process process = createProcess(light, null);
		response.addProcess(process);

		if (getAreaEnum() != LCAreaEnum.PROCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(true);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(false);
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#fetchCountMonitoredProcesses(com.sensus.lc.process.model.request.
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(
			ProcessRequest processRequest)
	{
		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();

		HashMap<String, Integer> result = new HashMap<>();
		result.put("Process", 1);
		result.put("Process2", 2);

		response.addResult(result);

		if (getAreaEnum() != LCAreaEnum.PROCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);

			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcl.IProcessBCL#insertCSVProcess(com.sensus.lc.process.model.request.InquiryProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest request)
	{
		ProcessResponse response = new ProcessResponse();
		Light light = createLight();
		Process process = createProcess(light, null);
		response.addProcess(process);

		if (getAreaEnum() != LCAreaEnum.PROCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(true);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(false);
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#submitProcess(com.sensus.lc.process.model.request.ProcessRequest,
	 * com.sensus.lc.process.model.LCAction)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Process> submitProcess(ProcessRequest processRequest, LCAction action)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createProcess(TestBaseUtil.createLight(), null));
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Process> fetchSummaryByProcessId(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		InternalResultsResponse<Process> response = getResultResponseProcess();
		response = fetchProcessFromProcess(response);
		response = fetchProcessByIdFromEcoMode(processRequest, response);
		response = fetchProcessByIdFromSmartpoint(processRequest, response);

		return response;
	}

	/**
	 * Gets the process response default.
	 * 
	 * @param processRequest the process request
	 * @return the process response default
	 */
	private InternalResultsResponse<Process> getProcessResponseDefault(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		Process process = processRequest.getProcess();
		process.setProcessItems(TestBaseUtil.createProcessItem(TestBaseUtil.createLight()));
		response.addResult(process);
		response.getResultsSetInfo().setTotalRowsAvailable(1);
		return response;
	}

	/**
	 * Insert process from eco mode.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Process> insertProcessFromEcoMode(ProcessRequest processRequest,
			InternalResultsResponse<Process> response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getProcessResponseDefault(processRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/**
	 * Insert process from schedule.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Process> insertProcessFromSchedule(ProcessRequest processRequest,
			InternalResultsResponse<Process> response)
	{
		if (getAreaEnum() != LCAreaEnum.SCHEDULE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getProcessResponseDefault(processRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/**
	 * Insert process from light.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Process> insertProcessFromLight(ProcessRequest processRequest,
			InternalResultsResponse<Process> response)
	{
		if (getAreaEnum() != LCAreaEnum.LIGHT)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getProcessResponseDefault(processRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/**
	 * Fetch process by id from eco mode.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Process> fetchProcessByIdFromEcoMode(ProcessRequest processRequest,
			InternalResultsResponse<Process> response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response = getProcessResponseDefault(processRequest);
			for (Process process : response.getResultsList())
			{
				process.setId(new Random().nextInt(ARBITRARY_PROCESS_ID_1000));
			}

			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/**
	 * Update process from analytics or smart point.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 * @return the internal response
	 */
	private InternalResponse updateProcessFromAnalyticsOrSmartPoint(
			ProcessRequest processRequest,
			InternalResponse response)
	{

		if (getAreaEnum() != LCAreaEnum.ANALYTICS && getAreaEnum() != LCAreaEnum.SMARTPOINT)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getProcessResponseDefault(processRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/**
	 * Fetch process by id from smartpoint.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Process> fetchProcessByIdFromSmartpoint(ProcessRequest processRequest,
			InternalResultsResponse<Process> response)
	{
		if (getAreaEnum() != LCAreaEnum.SMARTPOINT)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response = getProcessResponseDefault(processRequest);
			for (Process process : response.getResultsList())
			{
				process.setId(new Random().nextInt(ARBITRARY_PROCESS_ID_1000));
			}

			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	@Override
	public CSVInternalResponse generateFileCSV(ProcessCSVRequest request)
	{
		CSVInternalResponse response = new CSVInternalResponse(
				System.getProperty("java.io.tmpdir")
						+ System.getProperty("line.separator")
						+ LCDateUtil.getNewDateUTC().getTime() + ".csv");

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}
}