package com.sensus.mlc.wui.process.unittest.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.DataTypeEnum;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.response.TenantResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

/**
 * The Class LongRunningProcessBCFMockImpl.
 */
public class ProcessBCFMockImpl extends BaseMockImpl implements IProcessBCF
{
	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = "C:\\QATEclipseWorkSpace\\mlc-wui\\WebContent\\File.csv";

	/** The COUNT. */
	private static Integer COUNT = 1;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#insertProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse insertProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		// Invalid inputs cover Failure scenario
		if (MODE_FAILURE.equals(getMode()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR,
					MessageSeverity.Error, MessageLevel.FieldValidation));

			return response;
		}

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));

			return response;
		}

		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#updateProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse updateProcess(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#unmonitorProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse unmonitorProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		// Invalid inputs cover Failure scenario
		if (MODE_FAILURE.equals(getMode()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR,
					MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));
			response.setOperationSuccess(true);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/** The Constant SMARTPOINT_COUNT. */
	public static final Integer LONG_RUNNING_PROCESS_COUNT = 20;

	/** The Constant PROCESS_COUNT. */
	public static final Integer PROCESS_COUNT = 20;

	/*
	 * (non-Javadoc)
	 * @seecom.sensus.mlc.longrunningprocess.bcf.ILongRunningProcessBCF#
	 * fetchLongRunningProcessById(com.sensus.mlc.
	 * longrunningprocess.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessById(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (MODE_FAILURE.equals(getMode()))
		{
			if (ValidationUtil.isNull(processRequest.getProcess().getId()))
			{
				response.setOperationSuccess(false);
				response.addMessage(new Message(MESSAGE_ERROR,
						MessageSeverity.Error, MessageLevel.FieldValidation));
				return response;
			}
		}
		if (MODE_SUCCESS.equals(getMode()))
		{

			// Will create 100 Process itens
			List<ProcessItem> processItemList = createProcessItens(100);

			Process process = new Process();

			process.setId(1);
			process.setCreateDate(new Date());

			LCAction lcAction = new LCAction();
			lcAction.setActionType(LCActionTypeEnum.ADD_SMP_TO_GRP);
			lcAction.setDescription("Add to Group");

			process.setLcAction(lcAction);
			process.setIsProcessComplete(true);
			process.setStartTime(new Date());
			process.setEndTime(new Date());
			process.setProcessItems(processItemList);
			process.setDescription("Teste FlexNet ID Description");
			process.setCreateUser("Adam Admin");
			process.setIsSubmitted(true);
			process.setIsParent(true);
			process.setIsSubmitted(null);

			Process children = new Process();

			children.setId(100);
			children.setParentProcess(process);
			children.setCreateDate(new Date(3000));
			children.setProcessItems(processItemList);
			children.setLcAction(lcAction);
			children.setStartTime(new Date());
			children.setEndTime(new Date());
			children.setProcessItems(processItemList);
			children.setDescription("Description Test");
			children.setCreateUser("Adam Admin");
			children.setIsProcessComplete(true);
			children.setIsSubmitted(true);
			children.setIsParent(true);
			children.setIsSubmitted(null);

			Process subchildren = new Process();

			subchildren.setId(101);
			subchildren.setParentProcess(children);
			subchildren.setCreateDate(new Date(3000));
			subchildren.setProcessItems(processItemList);
			subchildren.setLcAction(lcAction);
			subchildren.setStartTime(new Date());
			subchildren.setEndTime(new Date());
			subchildren.setProcessItems(processItemList);
			subchildren.setDescription("Description Test");
			subchildren.setCreateUser("Adam Admin");
			subchildren.setIsProcessComplete(true);
			subchildren.setIsSubmitted(true);

			response.setProcesses(new ArrayList<Process>());
			response.getProcesses().add(subchildren);
			response.getProcesses().add(process);

			ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
			resultsSetInfo.setTotalRowsAvailable(PROCESS_COUNT);
			response.setOperationSuccess(true);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/**
	 * Creates the process itens.
	 * 
	 * @param numberOfElements the number of elements
	 * @return the list
	 */
	private List<ProcessItem> createProcessItens(Integer numberOfElements)
	{
		List<ProcessItem> processItemList = new ArrayList<ProcessItem>();

		for (int i = 0; i < numberOfElements; i++)
		{

			ProcessItem processResult = new ProcessItem();

			processResult.setProcessStatusReasonEnum(ProcessStatusReasonEnum.ABORTED);
			processResult.setLight(getLight());
			processResult.setProcessItemStatusEnum(ProcessItemStatusEnum.ABORTED);
			processResult.setProcessItemStatusEnumValue(1);

			processItemList.add(processResult);

			processResult = new ProcessItem();

			processResult.setProcessStatusReasonEnum(ProcessStatusReasonEnum.RNI_SYNC_FAILURE);
			processResult.setLight(getLight());
			processResult.setProcessItemStatusEnum(ProcessItemStatusEnum.RNISYNCFAILURE);
			processResult.setProcessItemStatusEnumValue(1);

			processItemList.add(processResult);

			processResult = new ProcessItem();

			processResult.setProcessStatusReasonEnum(ProcessStatusReasonEnum.RNI_ASYNC_FAILURE);
			processResult.setLight(getLight());
			processResult.setProcessItemStatusEnum(ProcessItemStatusEnum.RNIASYNCFAILURE);
			processResult.setProcessItemStatusEnumValue(1);

			processItemList.add(processResult);

			processResult = new ProcessItem();

			processResult.setProcessStatusReasonEnum(ProcessStatusReasonEnum.LIGHT_DEACTIVATED);
			processResult.setLight(getLight());
			processResult.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
			processResult.setProcessItemStatusEnumValue(1);

			processItemList.add(processResult);

			processResult = new ProcessItem();

			processResult.setProcessStatusReasonEnum(ProcessStatusReasonEnum.LIGHT_IN_MAINTENANCE);
			processResult.setLight(getLight());
			processResult.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
			processResult.setProcessItemStatusEnumValue(1);

			processItemList.add(processResult);

		}

		return processItemList;
	}

	/**
	 * Gets the light parameter list.
	 * 
	 * @return the light parameter list
	 */
	private List<LightParameter> getLightParameterList()
	{
		LightParameter lightParameter = new LightParameter();

		lightParameter.setValue("12345 Burnside East - " + COUNT);
		lightParameter.setPropertyEnum(PropertyEnum.STREET_NAME);
		lightParameter.setDataTypeEnum(DataTypeEnum.STRING);

		List<LightParameter> parameter = new ArrayList<LightParameter>();

		parameter.add(lightParameter);

		return parameter;
	}

	/**
	 * Gets the light.
	 * 
	 * @return the light
	 */
	private Light getLight()
	{
		Light light = new Light();
		light.setId(COUNT);
		light.setParameters(getLightParameterList());
		light.setRniId(2000 + COUNT++);

		return light;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.longrunningprocess.bcf.ILongRunningProcessBCF#
	 * fetchMonitoredLongRunningProcesses(com.sensus.mlc
	 * .longrunningprocess.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{

			List<ProcessItem> processItemList;

			response.setProcesses(new ArrayList<Process>());

			Integer pageSize;

			if (processRequest.getPageSize() == 0)
			{
				pageSize = 20;
			}
			else
			{
				pageSize = processRequest.getPageSize();
			}

			for (int i = processRequest.getStartRow(); i < (processRequest
					.getStartRow() + pageSize); i++)
			{
				processItemList = new ArrayList<ProcessItem>();
				ProcessItem processResult = new ProcessItem();

				processResult
						.setProcessStatusReasonEnum(ProcessStatusReasonEnum.ABORTED);
				Light light = new Light();
				light.setId(i);
				light.setRniId(23456 + i);
				processResult.setLight(light);
				processResult
						.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
				processResult.setProcessItemStatusEnumValue(i);

				processItemList.add(processResult);

				Process process = new Process();
				process.setId(i);
				Calendar date = Calendar.getInstance();
				if (i < 6)
				{
					process.setCreateDate(new Date());
				}
				else
				{
					date.setTime(new Date());
					date.add(Calendar.DAY_OF_MONTH, -1);
					process.setCreateDate(date.getTime());
				}

				LCAction lcAction = new LCAction();
				lcAction.setActionType(LCActionTypeEnum.ADD_SMP_TO_GRP);
				lcAction.setDescription("Add to Group");
				process.setLcAction(lcAction);
				process.setIsProcessComplete(true);
				process.setStartTime(new Date());
				process.setEndTime(new Date());
				process.setProcessItems(processItemList);
				process.setDescription("Teste FlexNet ID Description");
				process.setCreateUser("Adam Admin");
				process.setIsSubmitted(true);

				// Add parent
				if (i == 1)
				{
					process.setIsParent(true);
					process.setIsSubmitted(null);
					Process children = new Process();
					children.setId(100);
					children.setParentProcess(process);
					children.setCreateDate(new Date(3000));
					children.setProcessItems(processItemList);
					children.setLcAction(lcAction);
					children.setStartTime(new Date());
					children.setEndTime(new Date());
					children.setProcessItems(processItemList);
					children.setDescription("Description Test");
					children.setCreateUser("Adam Admin");
					children.setIsProcessComplete(true);
					children.setIsSubmitted(true);

					children.setIsParent(true);
					children.setIsSubmitted(null);
					Process subchildren = new Process();
					subchildren.setId(101);
					subchildren.setParentProcess(children);
					subchildren.setCreateDate(new Date(3000));
					subchildren.setProcessItems(processItemList);
					subchildren.setLcAction(lcAction);
					subchildren.setStartTime(new Date());
					subchildren.setEndTime(new Date());
					subchildren.setProcessItems(processItemList);
					subchildren.setDescription("Description Test");
					subchildren.setCreateUser("Adam Admin");
					subchildren.setIsProcessComplete(true);
					subchildren.setIsSubmitted(true);
					response.getProcesses().add(subchildren);
				}
				// Add export download
				else if (i == 8)
				{
					lcAction = new LCAction();
					lcAction.setActionType(LCActionTypeEnum.GENERATE_CSV_FILE);
					lcAction.setDescription("Export CSV");
					process.setLcAction(lcAction);
					process.setIsProcessComplete(true);
					process.setStartTime(new Date());
					process.setEndTime(new Date());
					process.setProcessItems(processItemList);
					process.setDescription("File Downloaded");
					process.setCreateUser("Adam Admin");
					process.setIsSubmitted(true);
					response.getProcesses().add(process);
				}
				// Add export ready to download
				else if (i == 9)
				{
					lcAction = new LCAction();
					lcAction.setActionType(LCActionTypeEnum.GENERATE_CSV_FILE);
					lcAction.setDescription("Export CSV");
					process.setLcAction(lcAction);
					process.setIsProcessComplete(true);
					process.setStartTime(new Date());
					process.setEndTime(new Date());
					process.setProcessItems(processItemList);
					process.setDescription("c://fileName.csv");
					process.setCreateUser("Adam Admin");
					process.setIsSubmitted(true);
					response.getProcesses().add(process);
				}
				// Add exporting csv file
				else if (i == 10)
				{
					lcAction = new LCAction();
					lcAction.setActionType(LCActionTypeEnum.GENERATE_CSV_FILE);
					lcAction.setDescription("Export CSV");
					process.setLcAction(lcAction);
					process.setIsProcessComplete(false);
					process.setStartTime(new Date());
					process.setEndTime(new Date());
					process.setProcessItems(processItemList);
					process.setCreateUser("Adam Admin");
					process.setIsSubmitted(true);
					response.getProcesses().add(process);
				}
				else
				{
					response.getProcesses().add(process);
				}
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(PROCESS_COUNT);
			}

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));

			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR,
					MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN,
					MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.longrunningprocess.bcf.ILongRunningProcessBCF#
	 * fetchLongRunningProcessByTransactionId(com.sensus
	 * .mlc.longrunningprocess.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessByTransactionId(
			ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.longrunningprocess.bcf.ILongRunningProcessBCF#abortLRP
	 * (com.sensus.mlc.longrunningprocess.model .request.ProcessRequest)
	 */
	@Override
	public ProcessResponse abortProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		// Invalid inputs cover Failure scenario
		if (MODE_FAILURE.equals(getMode())
				&& ValidationUtil.isNullOrZero(processRequest.getProcess().getId()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR,
					MessageSeverity.Error, MessageLevel.FieldValidation));

			return response;
		}

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));

			return response;
		}

		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.longrunningprocess.bcf.ILongRunningProcessBCF#retryLRP
	 * (com.sensus.mlc.longrunningprocess.model .request.ProcessRequest)
	 */
	@Override
	public ProcessResponse retryProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		// Invalid inputs cover Failure scenario
		if (ValidationUtil.isNullOrZero(processRequest.getProcess().getId()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR,
					MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchProcesses(com.sensus.mlc.process.model.request.InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcesses(
			InquiryProcessRequest processRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{

			response.setProcesses(new ArrayList<Process>());

			Integer pageSize;

			if (processRequest.getPageSize() == 0)
			{
				pageSize = 20;
			}
			else
			{
				pageSize = processRequest.getPageSize();
			}

			for (int i = processRequest.getStartRow(); i < (processRequest
					.getStartRow() + pageSize); i++)
			{
				ProcessItem processResult = new ProcessItem();

				processResult.setProcessStatusReasonEnum(ProcessStatusReasonEnum.ABORTED);
				Light light = new Light();
				light.setId(i);
				light.setRniId(23456 + i);
				processResult.setLight(light);
				processResult
						.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
				processResult.setProcessItemStatusEnumValue(i);

				List<ProcessItem> processItemList = new ArrayList<ProcessItem>();

				processItemList.add(processResult);

				Process process = new Process();
				process.setId(i);

				Calendar date = Calendar.getInstance();

				if (i < 6)
				{
					// TODAY
					process.setCreateDate(new Date());
				}
				else
				{
					// YESTERDAY
					date.setTime(new Date());
					date.add(Calendar.DAY_OF_MONTH, -1);
					process.setCreateDate(date.getTime());
				}

				LCAction lcAction = new LCAction();
				lcAction.setActionType(LCActionTypeEnum.ADD_SMP_TO_GRP);
				lcAction.setDescription("Add to Group");

				process.setLcAction(lcAction);
				process.setIsProcessComplete(true);
				process.setStartTime(new Date());
				process.setEndTime(new Date());
				process.setProcessItems(processItemList);
				process.setDescription("Teste FlexNet ID Description");
				process.setCreateUser("Adam Admin");
				process.setIsSubmitted(true);

				// Add parent
				if (i == 1)
				{
					process.setIsParent(true);
					process.setIsSubmitted(null);

					Process children = new Process();
					children.setId(100);
					children.setParentProcess(process);
					children.setCreateDate(new Date(3000));
					children.setProcessItems(processItemList);
					children.setLcAction(lcAction);
					children.setStartTime(new Date());
					children.setEndTime(new Date());
					children.setProcessItems(processItemList);
					children.setDescription("Description Test");
					children.setCreateUser("Adam Admin");
					children.setIsProcessComplete(false);
					children.setIsSubmitted(true);
					children.setIsParent(true);
					children.setIsSubmitted(null);

					Process subchildren = new Process();
					subchildren.setId(101);
					subchildren.setParentProcess(children);
					subchildren.setCreateDate(new Date(3000));
					subchildren.setProcessItems(processItemList);
					subchildren.setLcAction(lcAction);
					subchildren.setStartTime(new Date());
					subchildren.setEndTime(new Date());
					subchildren.setProcessItems(processItemList);
					subchildren.setDescription("Description Test");
					subchildren.setCreateUser("Adam Admin");
					subchildren.setIsProcessComplete(false);
					subchildren.setIsSubmitted(true);

					response.getProcesses().add(subchildren);
				}
				// Add export download
				else if (i == 8)
				{
					lcAction = new LCAction();
					lcAction.setActionType(LCActionTypeEnum.GENERATE_CSV_FILE);
					lcAction.setDescription("Export CSV");
					process.setLcAction(lcAction);
					process.setIsProcessComplete(true);
					process.setStartTime(new Date());
					process.setEndTime(new Date());
					process.setProcessItems(processItemList);
					process.setDescription("File Downloaded");
					process.setCreateUser("Adam Admin");
					process.setIsSubmitted(true);
					response.getProcesses().add(process);
				}
				// Add export ready to download
				else if (i == 9)
				{
					lcAction = new LCAction();
					lcAction.setActionType(LCActionTypeEnum.GENERATE_CSV_FILE);
					lcAction.setDescription("Export CSV");
					process.setLcAction(lcAction);
					process.setIsProcessComplete(true);
					process.setStartTime(new Date());
					process.setEndTime(new Date());
					process.setProcessItems(processItemList);
					process.setDescription("c://fileName.csv");
					process.setCreateUser("Adam Admin");
					process.setIsSubmitted(true);
					response.getProcesses().add(process);
				}
				// Add exporting csv file
				else if (i == 10)
				{
					lcAction = new LCAction();
					lcAction.setActionType(LCActionTypeEnum.GENERATE_CSV_FILE);
					lcAction.setDescription("Export CSV");
					process.setLcAction(lcAction);
					process.setIsProcessComplete(false);
					process.setStartTime(new Date());
					process.setEndTime(new Date());
					process.setProcessItems(processItemList);
					process.setCreateUser("Adam Admin");
					process.setIsSubmitted(true);
					response.getProcesses().add(process);
				}
				else
				{
					response.getProcesses().add(process);
				}
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(PROCESS_COUNT);
				response.setResultsSetInfo(resultsSetInfo);

			}

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));

			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR,
					MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN,
					MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchProcessByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public ProcessResponse fetchProcessByLight(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();

		// Invalid inputs cover Failure scenario
		if (ValidationUtil.isNullOrZero(lightRequest.getFirstLight().getId()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR,
					MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			Calendar c = Calendar.getInstance();

			response.setProcesses(new ArrayList<Process>());
			Process process = new Process();
			process.setId(1);
			LCAction action = new LCAction();
			action.setDescription("Create Group");
			process.setLcAction(action);
			process.setCreateUser("Admin");
			process.setDescription("Create Group 1");
			process.setEndTime(new Date());
			process.setEstimatedSecondsToComplete(10);
			process.setIsProcessComplete(true);
			process.setStartTime(new Date());
			process.setCreateDate(c.getTime());

			response.getProcesses().add(process);

			c.add(Calendar.DATE, -1);
			process = new Process();
			process.setId(1);
			action = new LCAction();
			action.setDescription("Create Group");
			process.setLcAction(action);
			process.setCreateUser("Admin");
			process.setDescription("Create Group 20");
			process.setEndTime(new Date());
			process.setEstimatedSecondsToComplete(20);
			process.setIsProcessComplete(true);
			process.setStartTime(new Date());
			process.setCreateDate(c.getTime());

			response.getProcesses().add(process);

			c.add(Calendar.DATE, -1);
			process = new Process();
			process.setId(1);
			action = new LCAction();
			action.setDescription("Create Group");
			process.setLcAction(action);
			process.setCreateUser("Admin");
			process.setDescription("Create Group 30");
			process.setEndTime(new Date());
			process.setEstimatedSecondsToComplete(30);
			process.setIsProcessComplete(true);
			process.setStartTime(new Date());
			process.setCreateDate(c.getTime());

			response.getProcesses().add(process);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));
			return response;

		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchTenantByRniCode(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public TenantResponse fetchTenantByRniCode(LightingControlRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchRniLinkStatus(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchRniLinkStatus(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));
			return response;

		}
		throw new RuntimeException("Kaboom");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#updateCSVDownloaded(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		// Invalid inputs cover Failure scenario
		if (ValidationUtil.isNullOrEmpty(processRequest.getProcessList())
				|| ValidationUtil.isNullOrZero(processRequest.getProcessList()
						.get(0).getId()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR,
					MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		else if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));

			Process process = new Process();
			process.setId(01);

			List<Process> processList = new ArrayList<Process>();
			processList.add(process);

			response.setProcesses(processList);

			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#generateSummaryFileCSV(com.sensus.mlc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse generateSummaryFileCSV(ProcessRequest processRequest)
	{
		/** Create a response */
		ProcessResponse processResponse = new ProcessResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			processResponse.setFileName(FILE_NAME);
			processResponse.setOperationSuccess(true);
			processResponse.addMessage(new Message(MESSAGE_INFO,
					MessageSeverity.Info, MessageLevel.Other));

			return processResponse;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			processResponse.addMessage(new Message(MESSAGE_ERROR,
					MessageSeverity.Error, MessageLevel.Other));
			processResponse.addMessage(new Message(MESSAGE_WARN,
					MessageSeverity.Warning, MessageLevel.Other));
			processResponse.setOperationSuccess(false);

			return processResponse;
		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#fetchCountMonitoredProcesses(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchCountMonitoredProcesses(
			ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			HashMap<String, Integer> mapCount = new HashMap<String, Integer>();
			mapCount.put("count_monitored", 1);
			mapCount.put("count_processing", 1);
			response.setOperationSuccess(true);
			response.setCountMonitoredProcess(mapCount);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info,
					MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
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