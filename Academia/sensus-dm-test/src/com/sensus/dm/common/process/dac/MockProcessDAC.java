package com.sensus.dm.common.process.dac;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.AddDeviceToGroupAction;
import com.sensus.dm.common.action.model.AddDeviceToScheduleAction;
import com.sensus.dm.common.action.model.AddGroupToScheduleAction;
import com.sensus.dm.common.action.model.CreateScheduleAction;
import com.sensus.dm.common.action.model.CreateTagAction;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.action.model.DeleteDeviceFromGroupAction;
import com.sensus.dm.common.action.model.DeleteDeviceFromScheduleAction;
import com.sensus.dm.common.action.model.DeleteDeviceFromTagAction;
import com.sensus.dm.common.action.model.EditGroupAction;
import com.sensus.dm.common.action.model.EditScheduleAction;
import com.sensus.dm.common.action.model.GenerateCSVFileAction;
import com.sensus.dm.common.action.model.RemoveGroupAction;
import com.sensus.dm.common.action.model.RemoveScheduleAction;
import com.sensus.dm.common.action.model.RemoveTagAction;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.Process;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;

/**
 * The Class MockProcessDAC.
 * 
 * @author QAT Global.
 */
public class MockProcessDAC extends AbstractMockBase implements IProcessDAC
{

	/** The Constant STRING_ONE. */
	private static final String STRING_ONE = "1";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant TWO. */
	private static final int TWO = 2;

	/** The Constant FOUR. */
	private static final int FOUR = 4;

	/** The Constant TEN. */
	private static final int TEN = 10;

	/** The Constant NINETY_NINE. */
	private static final int NINETY_NINE = 99;

	/** The Constant MONITORED_PROCESS. */
	private static final String MONITORED_PROCESS = "MonitoredProcess";

	/** The Constant HAN_DEVICE_FLEXNET_ID. */
	private static final String HAN_DEVICE_FLEXNET_ID = "2153943262073435";

	/** The Constant FILE_DOWNLOADED. */
	private static final String FILE_DOWNLOADED = "File Downloaded";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#insertProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> insertProcess(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> internalResultsResponse = new InternalResultsResponse<DMProcess>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (!ValidationUtil.isNull(processRequest.getFirstProcess()))
		{
			processRequest.getFirstProcess().setId(ONE);
			internalResultsResponse.getResultsList().add(processRequest.getFirstProcess());
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#insertProcessProperty(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse insertProcessProperty(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#updateProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse updateProcess(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> internalResultsResponse = new InternalResultsResponse<DMProcess>();
		if (!ValidationUtil.isNullOrEmpty(inquiryProcessRequest.getSelectionPaginationIds()))
		{
			for (BigInteger id : inquiryProcessRequest.getSelectionPaginationIds())
			{
				DMAction action = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
				action.setId(id.intValue());

				DMProcess process = new DMProcess(id.intValue());
				process.setAction(action);
				process.setTotalSmartpoints(ONE);
				process.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, FOUR));

				internalResultsResponse.getResultsList().add(process);
			}

			return internalResultsResponse;
		}

		if (!ValidationUtil.isNull(inquiryProcessRequest.getProcessId())
				&& inquiryProcessRequest.getProcessId() == NINETY_NINE)
		{
			internalResultsResponse = new InternalResultsResponse<DMProcess>();
			List<Process> processList = createListProcessAndActionName();
			int x = ZERO;

			for (Process item : processList)
			{
				x++;

				DMProcess process = new DMProcess();
				process.setTotalSmartpoints(ONE);

				if (!ValidationUtil.isNull(item.getAction()))
				{
					item.getAction().setId(x);
					process.setAction(item.getAction());

					if (ActionTypeEnum.DELETE_HAN_DEVICE
							.equals(item.getAction().getActionType().getActionTypeEnum()))
					{
						process.setPropertyDevice(HAN_DEVICE_FLEXNET_ID);
						process.setPropertyId(STRING_ONE);
						process.setPropertyValue(PropertyEnum.ON_DEMAND.toString());
					}
				}
				else
				{
					process.setProcessType(item.getProcessType());
					if (GenerateCSVFileAction.ACTION.equals(process.getProcessType().getDescription()))
					{
						process.setDescription(FILE_DOWNLOADED);
					}
				}

				if (x % TWO == ZERO)
				{
					process.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, FOUR));
				}

				internalResultsResponse.getResultsList().add(process);
			}

			return internalResultsResponse;
		}

		// Fetch by Id
		DMAction action = new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));

		DMProcess process =
				new DMProcess(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, FOUR));
		process.setId(ONE);
		process.setTotalSmartpoints(ONE);

		if (!ValidationUtil.isNullOrEmpty(inquiryProcessRequest.getProcesses())
				&& !ValidationUtil.isNull(inquiryProcessRequest.getProcesses().get(ZERO).getId()))
		{
			process.setId(inquiryProcessRequest.getProcesses().get(ZERO).getId());
		}

		process.setAction(action);
		internalResultsResponse.getResultsList().add(process);

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessById(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessById(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		Radio radio = new Radio(new BigInteger(STRING_ONE));

		response.addResult(new DMProcess(new DMAction(new ActionType(ActionTypeEnum.SET_TAMPER_DETECT_TIMER)),
				new ProcessItem(1,
						new Device(radio))));
		response.getFirstResult().setTotalSmartpoints(TWO);

		if (!ValidationUtil.isNull(processRequest.getFirstProcess())
				&& ProcessStatusEnum.ABORTED.equals(processRequest.getFirstProcess()
						.getProcessStatusEnum()))
		{
			response.getFirstResult().setProcessStatusEnum(ProcessStatusEnum.ABORTED);
		}

		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE.getValue(), "1"));
		properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START.getValue(), "1"));
		properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END.getValue(), "1"));
		response.getFirstResult().setProperties(properties);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchMonitoredProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchMonitoredProcess(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> internalResultsResponse = new InternalResultsResponse<DMProcess>();

		if (!ValidationUtil.isNull(inquiryProcessRequest.getFirstProcess().getIsDashboardMonitored())
				&& inquiryProcessRequest.getFirstProcess().getIsDashboardMonitored())
		{
			DMAction action = new DemandResponseEventAction();

			DMProcess process =
					new DMProcess(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, FOUR));
			process.setId(inquiryProcessRequest.getProcessId());
			process.setAction(action);
			process.setIsDashboardMonitored(true);
			process.setTotalSmartpoints(1);
			internalResultsResponse.getResultsList().add(process);
		}
		else if (!ValidationUtil.isNull(inquiryProcessRequest.getFirstProcess().getIsMonitoredInstance())
				&& inquiryProcessRequest.getFirstProcess().getIsMonitoredInstance())
		{
			DMAction action = new DemandResponseEventAction();

			DMProcess process =
					new DMProcess(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, FOUR));
			process.setId(inquiryProcessRequest.getProcessId());
			process.setAction(action);
			process.setIsMonitoredInstance(true);
			process.setTotalSmartpoints(1);
			internalResultsResponse.getResultsList().add(process);
		}
		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchTodayProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchTodayProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		return processResultsResponse(ProcessStatusEnum.SCHEDULED, ProcessItemStatusEnum.NOT_STARTED);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessesInProcessing(com.sensus.dm.common.process.model.request
	 * .
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessesInProcessing(InquiryProcessRequest request)
	{
		return new InternalResultsResponse<DMProcess>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#insertProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ProcessItem> insertProcessItems(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<ProcessItem>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#updateProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcessItems(ProcessRequest processRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchCheckProcessing(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse fetchCheckProcessing(ProcessRequest processRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchCountMonitoredProcesses(com.sensus.dm.common.process.model.
	 * request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(ProcessRequest processRequest)
	{
		HashMap<String, Integer> countMonitoredProcess = new HashMap<String, Integer>();
		countMonitoredProcess.put(MONITORED_PROCESS, TEN);

		return new InternalResultsResponse<HashMap<String, Integer>>(countMonitoredProcess);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.dac.IProcessDAC#summarizeProcess(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse summarizeProcess(ProcessRequest processRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessItemsByDevice(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByDevice(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<ProcessItem>(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED,
				null, FOUR));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchScheduledProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchScheduledProcesses(InquiryProcessRequest request)
	{
		return new InternalResultsResponse<DMProcess>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchStartedProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchStartedProcesses(InquiryProcessRequest request)
	{
		return processResultsResponse(ProcessStatusEnum.STARTED, ProcessItemStatusEnum.RUNNING);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessItemsBySchedule(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsBySchedule(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<ProcessItem>(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED,
				null, FOUR));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessItemsByProcessId(com.sensus.dm.common.process.model.
	 * request.
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByProcessId(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		InternalResultsResponse<ProcessItem> responseItem =
				new InternalResultsResponse<ProcessItem>(new ProcessItem(new Device(new Radio(new BigInteger(
						HAN_DEVICE_FLEXNET_ID)))));

		return responseItem;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessByRniEventId(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessByRniEventId(ProcessRequest processRequest)
	{
		return processResultsResponse(ProcessStatusEnum.COMPLETED, ProcessItemStatusEnum.COMPLETED);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessProperty(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Property> fetchProcessProperty(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<Property>();
	}

	/**
	 * Process results response.
	 * 
	 * @param processStatusEnum the process status enum
	 * @param processItemStatusEnum the process item status enum
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<DMProcess> processResultsResponse(ProcessStatusEnum processStatusEnum,
			ProcessItemStatusEnum processItemStatusEnum)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		DMProcess process = TestBaseUtil.createProcess(processStatusEnum,
				new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
		process.setProcessItems(TestBaseUtil.createProcessItem(processItemStatusEnum, null, FOUR));
		process.setTotalSmartpoints(1);

		response.getResultsList().add(process);

		process = TestBaseUtil.createProcess(processStatusEnum,
				new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
		process.setProcessItems(TestBaseUtil.createProcessItem(processItemStatusEnum, null, FOUR));
		process.setTotalSmartpoints(1);

		response.getResultsList().add(process);

		return response;
	}

	/**
	 * Creates the list action name.
	 * 
	 * @return the list action name.
	 */
	private List<Process> createListProcessAndActionName()
	{
		List<Process> processList = new ArrayList<Process>();

		processList.add(new Process(new ProcessType(CreateScheduleAction.ACTION)));
		processList.add(new Process(new ProcessType(AddGroupToScheduleAction.ACTION)));
		processList.add(new Process(new ProcessType(EditScheduleAction.ACTION)));
		processList.add(new Process(new ProcessType(DeleteDeviceFromScheduleAction.ACTION)));
		processList.add(new Process(new ProcessType(AddDeviceToScheduleAction.ACTION)));
		processList.add(new Process(new ProcessType(RemoveScheduleAction.ACTION)));
		processList.add(new Process(new ProcessType(EditGroupAction.ACTION)));
		processList.add(new Process(new ProcessType(AddDeviceToGroupAction.ACTION)));
		processList.add(new Process(new ProcessType(DeleteDeviceFromGroupAction.ACTION)));
		processList.add(new Process(new ProcessType(CreateTagAction.ACTION)));
		processList.add(new Process(new ProcessType(DeleteDeviceFromTagAction.ACTION)));
		processList.add(new Process(new ProcessType(RemoveTagAction.ACTION)));
		processList.add(new Process(new ProcessType(GenerateCSVFileAction.ACTION)));
		processList.add(new Process(new ProcessType(RemoveGroupAction.ACTION)));

		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.DISCONNECT_HAN_DEVICE))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.SEND_HAN_TEXT_MESSAGE))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.GET_HAN_CONNECTION_STATUS))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.CONNECT_HAN_DEVICE))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.GET_DEMAND_RESPONSE_EVENT_STATUS))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_SETUP))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.REMOTE_ARM_CONNECT))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.REMOTE_DISCONNECT))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.IMPORT_HAN_DEVICE))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.GET_HAN_CONNECTION_STATUS))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.REMOTE_CONNECT))));
		processList.add(new Process(new DMAction(new ActionType(ActionTypeEnum.DELETE_HAN_DEVICE))));

		return processList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessItemsProcessProperty(com.sensus.dm.common.process
	 * .model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsProcessProperty(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<ProcessItem>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchAllProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ProcessItem> fetchAllProcessItems(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<ProcessItem>(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.RUNNING,
				"", 2));
	}
}