package com.sensus.lc.controller.process.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessCSVRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.InquiryProcessResponse;
import com.sensus.lc.process.model.response.ProcessResponse;

public class ProcessBCFMockImpl extends BaseMockImpl implements IProcessBCF
{

	private static final String DESCRIPTION = "Description ";
	private static final String USER = "User";
	private static final String LCACTION = "LCAction ";
	private static final String FILE_NAME = "/opt/flexnet-slc/csvtem...23_12_42_06_757_BRT.csv";

	@Override
	public ProcessResponse abortProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

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
	public InquiryProcessResponse fetchAllProcess(InquiryProcessRequest processRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setProcesses(createProcessList(processRequest.getPageSize()));
			return response;
		}

		return (InquiryProcessResponse)testOtherDefaultModes(response);
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
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setProcesses(createProcessList(processRequest.getPageSize()));
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
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
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse fetchProcessByLight(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse fetchProcessByTransactionId(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse fetchRniLinkStatus(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setRniOnline(true);
			response.setOperationSuccess(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public CSVResponse generateFileCSV(ProcessCSVRequest request)
	{
		CSVResponse response = new CSVResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setFileName(FILE_NAME);
			return response;
		}

		return (CSVResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse generateSummaryFileCSV(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setProcesses(createProcessList(1));
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest lightSelectionRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setProcesses(createProcessList(1));
			response.setOperationSuccess(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse insertProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse retryProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse unmonitorProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse updateProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse fetchSummaryByProcessId(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<Process> processList = new ArrayList<Process>();

			processList.add(createProcess(processRequest.getProcess().getId()));

			response.setProcesses(processList);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

}
