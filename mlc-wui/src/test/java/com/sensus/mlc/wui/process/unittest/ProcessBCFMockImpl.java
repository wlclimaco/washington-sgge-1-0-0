package com.sensus.mlc.wui.process.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.response.TenantResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.util.ModeEnum;

public class ProcessBCFMockImpl extends BaseMockImpl implements IProcessBCF
{

	private static final String DESCRIPTION = "Description ";
	private static final String USER = "User";
	private static final String LCACTION = "LCAction ";

	private Process createProcess(Integer id)
	{

		Process process = new Process(id);

		process.setCreateDate(new Date());
		process.setDescription(DESCRIPTION + id);
		process.setEndTime(new Date());
		process.setModelAction(PersistanceActionEnum.NONE);
		process.setCreateUser(USER);
		process.setStartTime(new Date());
		process.setEndTime(new Date());

		if (id % 2 == 0)
		{
			process.setIsMonitoredInstance(true);
			process.setIsProcessComplete(true);
			process.setIsParent(true);
		}
		else
		{
			process.setIsMonitoredInstance(false);
			process.setIsProcessComplete(false);
			process.setIsParent(false);
		}

		LCAction lcAction = new LCAction();
		lcAction.setModelAction(PersistanceActionEnum.NONE);
		lcAction.setDescription(LCACTION + DESCRIPTION);
		lcAction.setActionType(LCActionTypeEnum.INSERT_GROUP);
		lcAction.setActionTypeValue(LCActionTypeEnum.INSERT_GROUP.getValue());

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();

		LCActionParameter lcActionParameter = new LCActionParameter();
		lcActionParameter.setModelAction(PersistanceActionEnum.NONE);
		lcActionParameter.setProperty(PropertyEnum.GROUP_ID);
		lcActionParameter.setPropertyValue(PropertyEnum.GROUP_ID.getValue());

		actionParameters.add(lcActionParameter);

		lcAction.setActionParameters(actionParameters);

		process.setLcAction(lcAction);

		return process;
	}

	private List<Process> createProcessList(Integer listSize)
	{

		// Create Process List
		List<Process> processList = new ArrayList<Process>();

		// Create Users to set at list
		for (int i = 0; i < listSize; i++)
		{
			processList.add(createProcess(i));
		}

		return processList;
	}

	@Override
	public ProcessResponse insertProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setResponseTime(new Date());
			return response;

		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse updateProcess(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse unmonitorProcess(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setProcesses(createProcessList(processRequest.getPageSize()));
			response.setResponseTime(new Date());

			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public InquiryProcessResponse fetchProcesses(InquiryProcessRequest processRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setProcesses(createProcessList(processRequest.getPageSize()));
			response.setResponseTime(new Date());

			return response;
		}

		return (InquiryProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse fetchProcessById(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<Process> processList = new ArrayList<Process>();

			processList.add(createProcess(processRequest.getProcess().getId()));

			response.setProcesses(processList);
			response.setResponseTime(new Date());

			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse fetchProcessByTransactionId(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse retryProcess(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse abortProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setResponseTime(new Date());

			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse fetchProcessByLight(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse fetchRniLinkStatus(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setRniOnline(true);
			response.setResponseTime(new Date());

			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public TenantResponse fetchTenantByRniCode(LightingControlRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse generateSummaryFileCSV(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse fetchCountMonitoredProcesses(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			HashMap<String, Integer> countMonitoredProcess = new HashMap<String, Integer>();

			countMonitoredProcess.put("count_processing", 3);
			countMonitoredProcess.put("count_monitored", 4);

			response.setCountMonitoredProcess(countMonitoredProcess);
			response.setResponseTime(new Date());

			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryProcessResponse generateFileCSV(InquiryProcessRequest inquiryProcessRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
